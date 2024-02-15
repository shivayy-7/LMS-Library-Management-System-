package com.aashdit.lms.utils;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.jboss.logging.Logger;

public class ApplicationStringUtils {
	
	final static Logger logger = Logger.getLogger(ApplicationStringUtils.class);

	static SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
	static int finYrStartDay = 1;
	static int finYrStartMon = 4;

	/**
	 * @return Value after "E" is removed
	 * @description Removes the notation "E" from any value
	 */
	public static boolean foundScientificNotation(String numberString) {
		try {
			new BigDecimal(numberString);
		} catch (NumberFormatException e) {
			return false;
		}
		return numberString.toUpperCase().contains("E");
	}

	/**
	 * 
	 * @return Value (String) in #,##,##,### sdFormat without any decimal value
	 * @description Convert value to INR Format i.e. #,##,##,### without any decimal
	 *              value
	 */
	public static String convertValueToINRFormatWithoutDecimal(String strInputValue) {
		String finalFormattedAmount = convertAmountToINRFormat(strInputValue);
		if (finalFormattedAmount.indexOf(".") != -1) {
			finalFormattedAmount = finalFormattedAmount.substring(0, finalFormattedAmount.indexOf("."));
		}

		return finalFormattedAmount;
	}

	/**
	 * @return Amount (String) in #,##,##,###.## sdFormat
	 * @description Convert amount to INR Format i.e. #,##,##,###.##
	 */
	public static String convertAmountToINRFormat(String strInputAmount) {
		
		if(strInputAmount.equals("null")) {
			strInputAmount = "0.0";
			return strInputAmount;
		}else {
		if (strInputAmount.equals(""))
			strInputAmount = "0.00";
		String amountToBeConverted = "";
		String finalFormattedAmount = "";
		String decimalValue = "";

		DecimalFormat df1 = new DecimalFormat("#");
		df1.setMaximumFractionDigits(2);
		strInputAmount = df1.format(Double.parseDouble(strInputAmount));

		if (strInputAmount.indexOf(".") != -1) {
			amountToBeConverted = strInputAmount.substring(0, strInputAmount.indexOf("."));
			decimalValue = strInputAmount.substring(strInputAmount.indexOf(".") + 1);
			if (decimalValue.length() == 0)
				decimalValue = decimalValue + "00";
			if (decimalValue.length() == 1)
				decimalValue = decimalValue + "0";
		} else {
			amountToBeConverted = strInputAmount;
			decimalValue = "00";
		}

		StringBuilder stringBuilder = new StringBuilder();
		char amountArray[] = amountToBeConverted.toCharArray();
		int length1 = 0, length2 = 0;
		for (int i = amountArray.length - 1; i >= 0; i--) {
			if (length1 < 3) {
				stringBuilder.append(amountArray[i]);
				length1++;
			} else if (length2 < 2) {
				if (length2 == 0) {
					stringBuilder.append(",");
					stringBuilder.append(amountArray[i]);
					length2++;
				} else {
					stringBuilder.append(amountArray[i]);
					length2 = 0;
				}
			}
		}

		finalFormattedAmount = stringBuilder.reverse().append(".").append(decimalValue).toString();

		if (foundScientificNotation(finalFormattedAmount)) {
			finalFormattedAmount = new BigDecimal(finalFormattedAmount).toPlainString();
			Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
			finalFormattedAmount = format.format(new BigDecimal(finalFormattedAmount));
		}
		if (finalFormattedAmount.contains("Rs."))
			finalFormattedAmount = finalFormattedAmount.replaceAll("Rs.", "");
		return finalFormattedAmount.trim();
	}
	
	}
	/**
	 * @return Encodes a value using Base64 Encryption logic
	 * @throws UnsupportedEncodingException
	 * @description
	 */
	public static String encodeString(String strStringToEncode) throws UnsupportedEncodingException {
		String encodedString = "";
		byte[] byteStringToEncode = strStringToEncode.getBytes("UTF-8");
		encodedString = DatatypeConverter.printBase64Binary(byteStringToEncode);
		return encodedString;
	}

	/**
	 * @return Decodes a value using Base64 Decryption logic
	 * @throws UnsupportedEncodingException
	 * @description
	 */
	public static String decodeString(String strEncodedString) throws UnsupportedEncodingException {
		String decodedString = "";
		byte[] decodedByteArr = DatatypeConverter.parseBase64Binary(strEncodedString);
		decodedString = new String(decodedByteArr, "UTF-8");
		// decodedString = decodedString.substring(0, decodedString.indexOf("^"));
		return decodedString;
	}

	public static String generateRandomValue() {
		String string = RandomStringUtils.random(16, 0, 16, true, true, "ABCDXYZPTQRS2012347498IKHJL".toCharArray());
		string = RandomStringUtils.randomAlphanumeric(16);
		return string;
	}

	public static String generatedRandomValue() {
		String string = RandomStringUtils.random(16, 0, 16, true, true, "ABCDXYZPTQRS2012347498IKHJL".toCharArray());
		String str = string.substring(10);
		return str;
	}

	public static String generateDateYear0000Acknowledgement(String rowMax) {
		String row = " ";
		if (rowMax.length() == 1) {
			row = "000" + rowMax;
		} else if (rowMax.length() == 2) {
			row = "00" + rowMax;
		} else if (rowMax.length() == 3) {
			row = "0" + rowMax;
		} else if (rowMax.length() == 4) {
			row = rowMax;
		} else {
			row = " ";
		}
		return row;
	}
	
	
	/**
	 * get Current year as yyyyy fromat
	 * @return
	 */
	public static String getStringTodayYearAsYYYY()
	{
		Calendar calendarObj = Calendar.getInstance();
		String strTodayYearAsYYYY = "";
		strTodayYearAsYYYY = String.valueOf(calendarObj.get(Calendar.YEAR));
		return strTodayYearAsYYYY;
	}
	
	/**
	 *  get current month as MM
	 * @return
	 */
	public static String getStringTodayMonthAsMM()
	{
		Calendar calendarObj = Calendar.getInstance();
		String strTodayMonthAsMM = "";
		int todayMonth = calendarObj.get(Calendar.MONTH)+1;
		if(todayMonth < 10)
			strTodayMonthAsMM = "0"+todayMonth;
		else
			strTodayMonthAsMM = ""+todayMonth;

		return strTodayMonthAsMM;
	}
	
	/**
	 * Get current date as DD
	 * @return
	 */
	public static String getStringTodayDateAsDD()
	{
		Calendar calendarObj = Calendar.getInstance();
		String strTodayDateAsDD = "";
		int todayDate = calendarObj.get(Calendar.DATE);
		if(todayDate < 10)
			strTodayDateAsDD = "0"+todayDate;
		else
			strTodayDateAsDD = ""+todayDate;

		return strTodayDateAsDD;
	}
	
	/**
	 * get current finyear as YYYY-YYYY fromat
	 * @return
	 */
	public static String getCurrFinYearAsYYYYtoYYYY()
	{
		Calendar calendarObj = Calendar.getInstance();
		String currFinYearAsYYYYtoYYYY = "";
		int currYear = Integer.parseInt(getStringTodayYearAsYYYY());
		int currMonth = Integer.parseInt(getStringTodayMonthAsMM());
		int currDay = 0;

		if(getStringTodayDateAsDD().equals(""))
			currDay = calendarObj.get(Calendar.DATE);
		else
			currDay = Integer.parseInt(getStringTodayDateAsDD());

		int finYrStartYear = currYear;

		// Get the year of Fin Year end day -------- 
		String currDate = finYrStartYear +"-"+ finYrStartMon +"-"+ finYrStartDay;
		DateFormat sdfStrToDate = new SimpleDateFormat("yyyy-MM-dd"); 
		Date currDateObj = new Date();
		try
		{
			currDateObj = (Date)sdfStrToDate.parse(currDate);
			calendarObj.setTime(currDateObj);

			calendarObj.add(Calendar.YEAR, 1);
			calendarObj.add(Calendar.DAY_OF_MONTH, -1);

			String finYrEndDate = "";
			finYrEndDate = sdfDDMMYYYY.format(calendarObj.getTime());
			int finYrEndYear = Integer.parseInt(finYrEndDate.substring(finYrEndDate.length()-4));
			// Till this : Get the year of previous day of FinYear start day -------- 

			if(finYrStartYear != finYrEndYear)
			{
				if((currMonth < finYrStartMon) || (currMonth == finYrStartMon && currDay < finYrStartDay))
					currFinYearAsYYYYtoYYYY = (finYrStartYear-1) +"-"+ finYrStartYear;
				else if((currMonth == finYrStartMon && currDay >= finYrStartDay) || currMonth > finYrStartMon)
					currFinYearAsYYYYtoYYYY = finYrStartYear +"-"+ (finYrStartYear+1);
			}
			else
				currFinYearAsYYYYtoYYYY = finYrStartYear +"-"+ finYrEndYear;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return currFinYearAsYYYYtoYYYY;
	}

	/*
	 * public static Date getNISTInternetTimeServers() { Date time=null; try {
	 * String TIME_SERVER = "time-a.nist.gov"; NTPUDPClient timeClient = new
	 * NTPUDPClient(); InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
	 * TimeInfo timeInfo = timeClient.getTime(inetAddress); long returnTime =
	 * timeInfo.getReturnTime(); Date currentDate = new Date (returnTime);
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	 * String date = dateFormat.format(currentDate); logger.info("Time from " + time
	 * + ": " + date); } catch (Exception e) { logger.error("error in " +e); }
	 * return time; }
	 */

	
}
