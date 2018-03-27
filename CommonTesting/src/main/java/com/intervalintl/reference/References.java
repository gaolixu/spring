package com.intervalintl.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class References {
	private static ReferenceQueue<Grocery> rq = new ReferenceQueue<Grocery>();

	public static void checkQueue() {
		Reference<? extends Grocery> inq = rq.poll(); // 从队列中取出一个引用
		if (inq != null)
			System.out.println("In queue: " + inq + " : " + inq.get());
	}

	public static void main(String[] args) {
		final int size = 10;
		// 创建10个Grocery对象以及10个软引用
		Set<SoftReference<Grocery>> sa = new HashSet<SoftReference<Grocery>>();
		for (int i = 0; i < size; i++) {
			SoftReference<Grocery> ref = new SoftReference<Grocery>(
					new Grocery("软引用 " + i), rq);
			System.out.println("刚刚 创建了: " + ref.get());
			sa.add(ref);
		}
		System.gc();
		checkQueue();
		// 创建10个Grocery对象以及10个弱引用
		Set<WeakReference<Grocery>> wa = new HashSet<WeakReference<Grocery>>();
		for (int i = 0; i < size; i++) {
			WeakReference<Grocery> ref = new WeakReference<Grocery>(
					new Grocery("弱引用 " + i), rq);
			System.out.println("刚刚 创建了: " + ref.get());
			wa.add(ref);
		}
		System.gc();
		checkQueue();
		// 创建10个Grocery对象以及10个虚引用
		Set<PhantomReference<Grocery>> pa = new HashSet<PhantomReference<Grocery>>();
		for (int i = 0; i < size; i++) {
			PhantomReference<Grocery> ref = new PhantomReference<Grocery>(
					new Grocery("phantom " + i), rq);
			System.out.println("刚刚 创建了: " + ref.get());
			pa.add(ref);
		}
		System.gc();
		checkQueue();
	}
}
