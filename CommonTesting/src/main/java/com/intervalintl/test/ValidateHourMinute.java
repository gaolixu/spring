package com.intervalintl.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHourMinute {

	public static void main(String[] args) {
		System.out.println(validate("12:00"));
		System.out.println(validate("23:59"));
		System.out.println(validate("00:00"));
		System.out.println(validate(" 12:00"));
		System.out.println(validate("25:00"));
		System.out.println(validate("23:60"));
		
	}
	
	public static boolean validate (String pInput) {
        if(pInput == null){
            return false;
        }
        Pattern p = Pattern.compile("(([0-1][0-9])|2[0-3]):[0-5][0-9]");
       return p.matcher(pInput).matches();
    }

}
