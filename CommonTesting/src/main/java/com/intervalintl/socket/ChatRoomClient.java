package com.intervalintl.socket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatRoomClient {
	public JTextField mywords;
	public JTextArea sharRoom;
	public PrintWriter pw;
	public BufferedReader br;
	Socket socket;

	public void go() {
		JFrame myFrame = new JFrame("My Chat Room");
		JPanel myPanel = new JPanel();
		mywords = new JTextField(20);
		sharRoom = new JTextArea(8, 25);
		JScrollPane scroller = new JScrollPane(sharRoom);
		JButton sendB = new JButton("发送");
		sendB.addActionListener(new SendButtonListener());
		myPanel.add(scroller);
		myPanel.add(mywords);
		myPanel.add(sendB);
		myFrame.getContentPane().add(BorderLayout.CENTER, myPanel);
		setConnection();
		Thread readerThread = new Thread(new AcceptServerMessage());
		readerThread.start();
		myFrame.setSize(400, 500);
		myFrame.setVisible(true);
	}

	public void setConnection() {
		try {
			socket = new Socket("127.0.0.1", 5000);
			pw = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pw.println(mywords.getText());
			pw.flush();
			mywords.setText("");
			mywords.requestFocus();
		}

	}

	public class AcceptServerMessage implements Runnable {

		public void run() {
			while (true) {
				String message = "";
				try {
					if ((message = br.readLine()) != null) {
						sharRoom.append(message + "\r\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		 ChatRoomClient c1 = new ChatRoomClient();  
	        c1.go();  
	        ChatRoomClient c2 = new ChatRoomClient();  
	        c2.go(); 
	}

}
