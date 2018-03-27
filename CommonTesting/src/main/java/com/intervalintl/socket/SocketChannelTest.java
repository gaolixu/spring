package com.intervalintl.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {
	public static void main(String[] args) throws Exception {  
        
        SocketChannel socketChannel = SocketChannel.open();  
        socketChannel.connect(new InetSocketAddress("localhost", 9999));  
          
        String newData = "New String to write to socket...." + System.currentTimeMillis();   
        ByteBuffer buf = ByteBuffer.allocate(48);  
        buf.clear();  
        buf.put(newData.getBytes());  
        buf.flip();  
            
        while(buf.hasRemaining()) {  
            socketChannel.write(buf);  
        }  
          
        socketChannel.close();  
    }  
}
