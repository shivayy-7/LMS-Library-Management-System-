package com.aashdit.lms.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.service.CommonService;
import com.aashdit.lms.service.SmsService;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.google.gson.JsonObject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Controller
@RequestMapping("/core")
@Slf4j
public class CommonController {

	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SmsService smsService;
	
	@GetMapping("/getData")
	public @ResponseBody ServiceOutcome<List<?>> getAjaxDtlsByIdentity(String identity, String id){
		ServiceOutcome<List<?>> soc = new ServiceOutcome<List<?>>();
		try {
			soc = commonService.getAllAjaxCallDetails(identity, id);
		} catch (Exception e) {
			log.error("Exception occurred in getAjaxDtlsByIdentity("+identity+") -> CommonController" + e.getMessage());
		}
		return soc;
	}
	
	@GetMapping("/checksms")
	public @ResponseBody String checkSms(String identity, String id){
		try {
//			smsService.sendSMS(identity, id, null)
		} catch (Exception e) {
			log.error("Exception occurred in getAjaxDtlsByIdentity("+identity+") -> CommonController" + e.getMessage());
		}
		return null;
	}
	
	@GetMapping("/deleteBookCatalogImage")
	public @ResponseBody ServiceOutcome<String> deleteBookCatalogImage(Long id){
		ServiceOutcome<String> soc = new ServiceOutcome<>();
		try {
			soc = commonService.deleteBookCatalogImageById(id);
		} catch (Exception e) {
			log.error("Exception occurred in deleteBookCatalogImage() -> CommonController" + e.getMessage());
		}
		return soc;
	}
	
	@GetMapping("/getInformationByISBN")
	public @ResponseBody Boolean getInformationByISBN(String id){
		ServiceOutcome<Boolean> soc = new ServiceOutcome<>();
		try {
			soc = commonService.getDataByISBN(id);
		} catch (Exception e) {
			log.error("Exception occurred in getInformationByISBN() -> CommonController" + e.getMessage());
		}
		return soc.getData();
	}
	
	@GetMapping("/checkMembersBookCount")
	public @ResponseBody Boolean checkMembersBookCount(String identity){
		ServiceOutcome<Boolean> soc = new ServiceOutcome<>();
		try {
			soc = commonService.getBookCountByMemberId(identity);
		} catch (Exception e) {
			log.error("Exception occurred in checkMembersBookCount() -> CommonController" + e.getMessage());
		}
		return soc.getData();
	}
	
	@GetMapping("/membersBookAllocationCount")
	public @ResponseBody Long membersBookAllocationCount(String identity, Long bookIssuedId){
		ServiceOutcome<Long> soc = new ServiceOutcome<>();
		try {
			soc = commonService.getBookAllocationCountByMemberId(identity, bookIssuedId);
		} catch (Exception e) {
			log.error("Exception occurred in membersBookAllocationCount() -> CommonController" + e.getMessage());
		}
		return soc.getData();
	}
	
//	@GetMapping("/emailCheck")
////	@ResponseBody
//	public ResponseEntity<String> emailCheck(String email) {
//	    ServiceOutcome<Boolean> soc = new ServiceOutcome<>();
//	    JSONObject jObj = new JSONObject();
//	    try {
//	        soc = commonService.checkUniqueEmail(email);
//	        jObj.put("value", soc.getData());
//	    } catch (Exception e) {
//	        log.error("Exception occurred in emailCheck() -> CommonController" + e.getMessage());
//	        jObj.put("error", "An error occurred");
//	    }
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    return new ResponseEntity<>(jObj.toString(), headers, HttpStatus.OK);
//	}
	
	

}
