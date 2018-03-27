package com.intervalintl.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatRoomServer {
	ServerSocket ss;
	ArrayList<Socket> outSockets;

	public void go() {
		outSockets = new ArrayList<Socket>();
		try {
			ss = new ServerSocket(5000);
			System.out.println("没跳过线程继续执行");
			while (true) {
				Socket socket = ss.accept();
				outSockets.add(socket);
				System.out.println("Accept Client : "+socket.toString());
				Thread clientThread = new Thread(new AccpetMessage(socket));
				clientThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 内部类-多线程处理接收客户端发送请求的操作实现
	 * 
	 * @author Administrator
	 * 
	 */
	public class AccpetMessage implements Runnable {

		Socket sClient;
		BufferedReader br;
		PrintWriter pw;
		String message = "";

		public AccpetMessage(Socket sClient) {
			this.sClient = sClient;
			try {
				br = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//@Override
		public void run() {
			while (true) {
				try {
					message = Thread.currentThread().getName() + "说：" + br.readLine();
					System.out.println(message);
					for (Socket sc : outSockets) {
						pw = new PrintWriter(sc.getOutputStream());
						pw.println(message);
						pw.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		new ChatRoomServer().go();
	}

}