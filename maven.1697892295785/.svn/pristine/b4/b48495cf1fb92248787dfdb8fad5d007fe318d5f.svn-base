package com.aashdit.lms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.BookDetailsApiDto;
import com.aashdit.lms.dto.TopRatedBookDto;
import com.aashdit.lms.service.MasterService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/api")
public class ListDetailsApiController {
	
	@Autowired
	private MasterService masterService;
	
	@GetMapping("/getTopRatedBook")
	public @ResponseBody ResponseEntity<?> bookListDetails() {
		    ServiceOutcome<BookDetailsApiDto> svOut=new ServiceOutcome<>();
		    BookDetailsApiDto data=new BookDetailsApiDto();
	        ServiceOutcome<List<TopRatedBookDto>> topRatedBookList = masterService.getTopRatedBookByDate();
	        data.setTopRatedBookDto(topRatedBookList.getData());
	        svOut.setData(data);
	        svOut.setOutcome(true);
	        return  ResponseEntity.ok(svOut);
	   
	}

}
