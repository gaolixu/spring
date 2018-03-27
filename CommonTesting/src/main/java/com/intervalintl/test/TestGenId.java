package com.intervalintl.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;

public class TestGenId {
	public static void main(String[] args) throws UnknownHostException {

		String ip = InetAddress.getLocalHost().getHostAddress();
		String[] ipArray = ip.split("\\.");

		final String lastTwoPhaseIp = StringUtils.rightPad(ipArray[2], 3, '0')
				+ StringUtils.leftPad(ipArray[3], 3, '0');

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				public void run() {
					// TSS commons工具类
					String tss = TSS.getTimeStampSequence();
					String id = tss + lastTwoPhaseIp + "000" + "01" + "08";
					String id2 = tss;
					//System.out.println(id);
					System.out.println(id2);
				}
			}).start();
		}
	}
}

class TSS {  
    // 默认1个大小  
    private static ConcurrentHashMap<String, AtomicInteger> cache = new ConcurrentHashMap<String, AtomicInteger>(1);  
          
    public static String getTimeStampSequence() {  
          
        String timestamp = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());  
          
        String inc = null;  
          
        AtomicInteger value = cache.get(timestamp);  
          
        if(value == null) {  
            cache.clear();  
            int defaultStartValue = 0;  
            cache.put(timestamp, new AtomicInteger(defaultStartValue));  
            inc = String.valueOf(defaultStartValue);  
        } else {  
            inc = String.valueOf(value.addAndGet(1));  
        }  
          
        return timestamp + StringUtils.leftPad(inc, 3, '0');  
    }  
  
}  
