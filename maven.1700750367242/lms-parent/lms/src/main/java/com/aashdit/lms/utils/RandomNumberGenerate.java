package com.aashdit.lms.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import com.aashdit.lms.model.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomNumberGenerate {

	public static final String CHAR_LIST_TWO = "12345678910";
	 
	public static String otpGenertor()
	{
		SecureRandom random= new SecureRandom();
		int num=random.nextInt(100000);
		String formatted=String.format("%06d", num);
		return formatted; 
	} 
	
	public static String demographyGenertor()
	{
		SecureRandom random= new SecureRandom();
		int num=random.nextInt(100000);
		String formatted=String.format("%08d", num);
		return formatted; 
	} 
	
	public static String generateReportCode() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST_TWO.charAt(number);
			buffer.append(ch);
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String numbers[] = dtf.format(now).split("\\s+");
		String number1[] = numbers[0].split("/");
		String number2[] = numbers[1].split(":");
		String generateFileNoSuffix = number1[0] + number1[1] + number1[2] + number2[0] + number2[1] + number2[2];
		//return buffer + generateFileNoSuffix; //Random Number With Current Time
		return generateFileNoSuffix; //Only Current Time 
	}
	
	public static int getRandomNumber() {
		int randomInt = 0;
		Random randomNumber = new Random();
		randomInt = randomNumber.nextInt(CHAR_LIST_TWO.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public static String generateGatePassId(String buyType,String vehicleNo ) {
		
		SecureRandom random= new SecureRandom();
		String randomNumber = String.format("%04d", random.nextInt(10000)); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String numbers[] = dtf.format(now).split("\\s+");
		String number1[] = numbers[0].split("/");
		String generateFileNoSuffix = number1[0] + number1[1] + number1[2];
		String trainingCode = buyType + "/" + generateFileNoSuffix + "/" +vehicleNo.replaceAll("\\s+","")+"/" + randomNumber; 
		System.out.println(trainingCode);
		return trainingCode;
	}
	
	public static String getRandomUniqueCode(int length) {
	    SecureRandom random = new SecureRandom();
	    StringBuilder sb = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	        int asciiValue = random.nextInt(26) + 'A'; 
	        sb.append((char) asciiValue);
	    }
	    return sb.toString();
	}
	
	
	public static String getUniqueLibraryCardNo(String membercode) {
		String keys="";
		try {
			String memberfst3=membercode.substring(0,3);
			 Random rand = new Random();
		        int random6Digit = rand.nextInt(900000) + 100000; // Generate a random 6-digit number
		        keys ="LMS"+"-"+memberfst3+"-"+random6Digit;
		} catch (Exception e) {
			log.error("Exception occured in RandomNumberGenerate at getUniqueLibraryCardNo()==>"+e);
		}
		return keys;
	}

}
