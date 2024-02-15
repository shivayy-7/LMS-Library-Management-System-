package com.aashdit.lms.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.VO.BookCatalogVO;
import com.aashdit.lms.VO.BookDtls;
import com.aashdit.lms.service.BookIssueService;
import com.aashdit.lms.service.CommonService;
import com.aashdit.lms.service.MasterService;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/api/book")
public class BookIssueApiController {
	
	@Autowired
	private BookIssueService bookIssueService;
	
	@Autowired
	private MasterService masterService;
	

	    @Autowired
	    private CommonService commonService;

	    @GetMapping("/list-book-catalog")
	    public ResponseEntity<Map<String, Object>> getBookListData() {
	        User user = SecurityHelper.getCurrentUser();
	        Map<String, Object> response = new HashMap<>();

	        try {
	            response.put("bookCatagoryList", commonService.getAllData("BOOK-CATAGORY").getData());
	            response.put("Role", user.getPrimaryRole().getRoleCode());
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (Exception e) {
	            log.error("Exception occurred in ListBookRestController at getBookListData(): " + e);
	            response.put("error", "An error occurred.");
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @PostMapping("/save-reservedBook")
	    public ResponseEntity<ServiceOutcome<String>> savereservedBookApi(Long bookCatalogId) {
	        try {
	        	 BookCatalogVO booCatalogVO =  new BookCatalogVO();
	        	 BookDtls bookDtls = new BookDtls();
	        	 booCatalogVO.setBookCatalogId(bookCatalogId);
	        	 bookDtls.setBookCatalogVO(booCatalogVO);
	            ServiceOutcome<String> listbook = masterService.savereservedBookData(bookDtls);
	            String message = listbook.getMessage();
	            return new ResponseEntity<>(listbook, HttpStatus.ACCEPTED.OK);
	        } catch (Exception e) {
	            log.error("Exception occurred in reservedBook method in MasterController", e);
	            ServiceOutcome<String> errorOutcome = new ServiceOutcome<>();
	            errorOutcome.setMessage("An error occurred.");
	            errorOutcome.setOutcome(false);
	            return new ResponseEntity<>(errorOutcome, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
