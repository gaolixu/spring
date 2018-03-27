package com.intervalintl.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelTest {
	 public static void main(String[] args) throws Exception {  
         
	        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();    
	        serverSocketChannel.socket().bind(new InetSocketAddress(9999));    
	        while(true){    
	            SocketChannel socketChannel = serverSocketChannel.accept();    
	            ByteBuffer buf = ByteBuffer.allocate(48);    
	            int bytesRead = socketChannel.read(buf);    
	             
	            while (bytesRead != -1) {    
	                    
	                System.out.println("Read " + bytesRead);    
	                buf.flip();    
	                    
	                while(buf.hasRemaining()){    
	                    System.out.print((char) buf.get());    
	                }    
	                    
	                buf.clear();    
	                bytesRead = socketChannel.read(buf);    
	            }    
	        }    
	    }  
	 
	 /*ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();    
     serverSocketChannel.socket().bind(new InetSocketAddress(9999));    
     serverSocketChannel.configureBlocking(false);  //设置为非阻塞式  
     while(true){    
         SocketChannel socketChannel = serverSocketChannel.accept();    
         if(socketChannel != null){  //为非阻塞式时，要进行非空判断  
             //do something with socketChannel...    
         }    
     }  */  
}
