package com.intervalintl.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {

	public static void main(String[] args) throws IOException {
		System.out.println("Start...");
		RandomAccessFile aFile = new RandomAccessFile("D://nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		// create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf); // read into buffer.
		while (bytesRead != -1) {
			buf.flip(); // make buffer ready for read
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get()); // read 1 byte at a time
			}

			buf.clear(); // make buffer ready for writing

			bytesRead = inChannel.read(buf);

		}

		
		System.out.println("End...");

		inChannel.position(inChannel.size());      
		  
		inChannel.write(ByteBuffer.wrap("ddddddddddddddd...dd".getBytes()));  
	  
		inChannel.close();  
		
		
		aFile.close();

	}
	
	public static void method1() {
		RandomAccessFile aFile = null;
		try {
			aFile = new RandomAccessFile("src/nio.txt", "rw");
			FileChannel fileChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024);

			int bytesRead = fileChannel.read(buf);
			System.out.println(bytesRead);

			while (bytesRead != -1) {
				buf.flip();
				while (buf.hasRemaining()) {
					System.out.print((char) buf.get());
				}

				buf.compact();
				bytesRead = fileChannel.read(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (aFile != null) {
					aFile.close();
				}
			} catch (IOException se) {
				se.printStackTrace();
			}
		}}
}
