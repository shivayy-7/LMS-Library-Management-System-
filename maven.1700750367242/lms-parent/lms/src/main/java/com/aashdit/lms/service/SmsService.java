package com.aashdit.lms.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.aashdit.lms.model.MessageTemplate;
import com.aashdit.lms.model.Notification;

@Component
public class SmsService {
	private static final Logger log = LoggerFactory.getLogger(SmsService.class);
	public String sendSMS(String message , String mobileNumber, MessageTemplate messageTemplate){
		String responseString = "";
		SSLContext context=null;
		String responseMain="";
		try {
			log.debug("FUNCTION HIT FOR OTP SMS DETAILS BELOW =========!! ");
			//context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
			context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
			context.init(null, null, null);
			HttpClient client=new DefaultHttpClient();
			HttpPost httpPost=new HttpPost("https://govtsms.odisha.gov.in/api/api.php");
			
			List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("action", messageTemplate.getAction()));
			nameValuePairs.add(new BasicNameValuePair("source", messageTemplate.getLinkedHeader()));
			nameValuePairs.add(new BasicNameValuePair("department_id", messageTemplate.getDepartmentId()));
			nameValuePairs.add(new BasicNameValuePair("template_id", messageTemplate.getTemplateRegdNo()));
			nameValuePairs.add(new BasicNameValuePair("sms_content", message));
			nameValuePairs.add(new BasicNameValuePair("phonenumber", mobileNumber));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response=client.execute(httpPost);
			BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			responseMain=bf.readLine();
			
			log.debug("ACTION TAKEN ---"+mobileNumber);
			log.debug("DEPARTMENT ID -----"+messageTemplate.getDepartmentId());
			//log.debug("TEMPLATE ID------"+templateid);
			log.debug("MESSAGE -----"+message);
			log.debug("SINGLE MOBILE NO ---"+mobileNumber);
			log.debug("STEP 1-----"+nameValuePairs);
			log.debug("STEP 2-----"+httpPost.getEntity());
			log.debug("STEP 3-----"+response);
			
			if(responseMain != null && responseMain != "") {
				JSONObject json=new JSONObject(responseMain);
				log.debug("STEP 4-----"+responseMain);
			    responseString=String.valueOf(json.get("status"));
			}
			log.debug("SMS SEND TO "+mobileNumber+"___"+responseString);
		} catch (NoSuchAlgorithmException e) {
			log.error("Error While Send SMS NoSuchAlgorithmException=====>",e);
			e.printStackTrace();
		} catch (KeyManagementException e) {
			log.error("Error While Send SMS KeyManagementException=====>",e);
			e.printStackTrace();
		} 
		catch(Exception e) {
			log.error("Error While Send SMS Exception=====>",e);
		}
		return responseString;
		
	}
	
}
