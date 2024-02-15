package com.aashdit.lms.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.DashboardContDto;
import com.aashdit.lms.dto.DashboardDto;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.service.DashBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private DashBoardService dashBoardService;
	
	
	@GetMapping("/getDashboardCardData")
	public ResponseEntity<String> getDashboardCardData() {
		
		JSONObject jsonObject=new JSONObject();
		try {
			DashboardDto dashboardDto= dashBoardService.getDashboardCardData();
			
			jsonObject.put("totalvistors", dashboardDto.getTotalvistors());
			jsonObject.put("totalbookissued", dashboardDto.getTotal_bookissued());
			jsonObject.put("totalavliavble", dashboardDto.getTotalbook_avliable());
			jsonObject.put("totalbbok", dashboardDto.getTotalbbok());
			jsonObject.put("totalicardgen", dashboardDto.getTotalicardgen());
			jsonObject.put("totalcardrej", dashboardDto.getTotalcardrej());
		} catch (Exception e) {
			log.error("Exception occured in DashboardController at getDashboardCardData() ===>", e);
		}
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK); 
	}
	
	
      @GetMapping("/getDashboardCondtionData")
	public ResponseEntity<String> getDashBoardCondtionData() {
		
		JSONObject jsonObject=new JSONObject();
		try {
			
		DashboardContDto dashboardDto= dashBoardService.getDashboardConData();
			jsonObject.put("totalgood", dashboardDto.getTotal_good_book());
			jsonObject.put("totallost", dashboardDto.getTotal_lost_book());
			jsonObject.put("totaldamaged", dashboardDto.getTotal_damged_book());

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK); 
	}
      
      
  	@GetMapping("/getcatagorieswiase")
  	public ResponseEntity<Object> getCtagorieswisedata() {
  		String responseData = null;
  		try {
  			ServiceOutcome<String> serviceOutcome = dashBoardService.getCtagorieswiseData();
  			if (serviceOutcome.getOutcome()) {
  				responseData = serviceOutcome.getData();
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return new ResponseEntity<Object>(responseData, HttpStatus.OK);
  	}

  	
	
	@GetMapping("/getreturnlist")
	public ResponseEntity<Object> getRetuenList(Long districtId){
		  JSONArray jsonArray = new JSONArray();
	        try {
	        	ServiceOutcome<List<BookIssue>> outcome = dashBoardService.getretunList();
	       	 for (BookIssue dashBoardDto : outcome.getData()) {
	    		 JSONObject jsonObj = new JSONObject();
	    		 jsonObj.put("name",dashBoardDto.getLibraryCard().getMember().getMemberName());
	    		 jsonObj.put("phoneno",dashBoardDto.getLibraryCard().getMember().getPhoneNo());
	    		 jsonObj.put("address",dashBoardDto.getLibraryCard().getMember().getAddress());
	    		 jsonObj.put("catgoriesname",dashBoardDto.getBook().getBookCatalog().getSubCategory().getCategory().getCategoryName());
	    		 jsonObj.put("subcatname",dashBoardDto.getBook().getBookCatalog().getSubCategory().getSubCategoryName());
	    		 jsonObj.put("booktitel",dashBoardDto.getBook().getBookCatalog().getBookTitles());
	    		 jsonArray.put(jsonObj);
	    	 }
	        }catch (Exception e){
	        	log.error("Exception occured in DashboardController at getRetuenList() ===>", e);
	        }
	        return new ResponseEntity<Object>(jsonArray.toString(), HttpStatus.OK);
	    }
}
