package com.thoughtfocus.leave.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Check {
	
	public static void main(String[] args){

		String temp = "2015-4-1";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(temp);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(date.getDate());

}
}
