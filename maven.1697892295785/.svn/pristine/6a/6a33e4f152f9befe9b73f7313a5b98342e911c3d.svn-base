package com.aashdit.lms.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.VO.BookCatalogDTO;
import com.aashdit.lms.dto.CatalogDto;
import com.aashdit.lms.dto.DashboardDto;
import com.aashdit.lms.repository.BookCatalogRepository;
import com.aashdit.lms.service.BookIssueService;
import com.aashdit.lms.service.DashBoardService;
import com.aashdit.lms.service.MemberService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/api/home")
public class DashboardApiController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookCatalogRepository bookCatalogRepository;
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@Autowired
	private BookIssueService bookIssueService;
	
	@GetMapping("/dashboardApi")
	public  ResponseEntity<Map<String, Object>> getDynamicWebpageParametersApi() {
		Map<String, Object> response = new HashMap<>();
		try {
			Long mamberId  = 1L;
	    	List<BookCatalogDTO> applicationContents = memberService.getDashboardData(mamberId);
	    	JSONArray jsonArray=new JSONArray();
	    	Map<String, List<BookCatalogDTO>> result = applicationContents.stream().collect(Collectors.groupingBy(abc ->abc.getBookType(),Collectors.toList()));
	    	for (Map.Entry<String, List<BookCatalogDTO>> entry : result.entrySet()) {
   		      JSONObject jsonObject=new JSONObject();
   		      jsonObject.put("title", entry.getKey());
   		      jsonObject.put("list", entry.getValue());
   		      jsonArray.put(jsonObject);
	    	}
	    	response.put("book_list", jsonArray.toList());
	    	ServiceOutcome<List<CatalogDto>> bookCatalogs = bookIssueService.getAllBookCatalougeData();
	    	JSONArray jsonArray1=new JSONArray();
	    	Map<String, List<CatalogDto>> bookCatalogsKey = bookCatalogs.getData().stream().collect(Collectors.groupingBy(abc ->abc.getSubCategoryName(),Collectors.toList()));
	    	for (Map.Entry<String, List<CatalogDto>> entry : bookCatalogsKey.entrySet()) {
	   		      JSONObject jsonObject=new JSONObject();
	   		      jsonObject.put("title", entry.getKey());
	   		      jsonObject.put("list", entry.getValue());
	   		   jsonArray1.put(jsonObject);
		    	}
		    response.put("bookCatalogs", jsonArray1.toList());
	    	
	    	return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception occurred in ListBookRestController at getBookListData(): " + e);
            response.put("error", "An error occurred.");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
