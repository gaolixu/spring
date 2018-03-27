package com.intervalintl.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class MyGCPhantom {

	public static void main(String[] args) throws InterruptedException {
		GCPhantomObject p = new GCPhantomObject();
		ReferenceQueue phantomQueue = new ReferenceQueue();
		PhantomReference pr = new PhantomReference(p, phantomQueue);
		new GCPhantomThread(pr, phantomQueue, "Phantom").start();
		//p = null;
		//pr = null;
		System.gc();
		System.out.println("main thread done ...");
		Thread.sleep(5000L);
		System.out.println("ddddddd:  "+p);
	}
}

class GCPhantomObject {
	@Override
	protected void finalize() {
		System.out.println("GCPhantom finalized at " + System.nanoTime());
	}
}

class GCPhantomThread extends Thread {
	private ReferenceQueue referenceQueue;
	private String name;
	private PhantomReference pr;

	GCPhantomThread(PhantomReference pr, ReferenceQueue referenceQueue,
			String name) {
		this.referenceQueue = referenceQueue;
		this.name = name;
		this.pr = pr;
	}

	@Override
	public void run() {
		try {
			while (referenceQueue.remove(5000) == null) {
				System.gc();
			}
			System.out.println(name + " found at " + System.nanoTime());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
