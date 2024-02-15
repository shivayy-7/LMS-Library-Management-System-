package com.aashdit.lms.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashdit.lms.VO.BookCatalogDTO;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.repository.BookCatalogRepository;
import com.aashdit.lms.service.MemberService;


@Service
public class ApplicationContentUtils1 {
	
	final static Logger logger = Logger.getLogger(ApplicationContentUtils1.class);
	
//	@Autowired
//	private ApplicationContentRepository applicationContentRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookCatalogRepository bookCatalogRepository;
	
	
	  
		public void getDynamicWebpageParameters(HttpSessionEvent event) {
			try {
				HttpSession session = event.getSession(); 
				Long mamberId  = 1L;
		    	List<BookCatalogDTO> applicationContents = memberService.getDashboardData(mamberId);
		    	Map<String, List<BookCatalogDTO>> result = applicationContents.stream().collect(Collectors.groupingBy(abc ->abc.getBookType(),Collectors.toList()));
		    	for (Map.Entry<String, List<BookCatalogDTO>> entry : result.entrySet()) {
		    	    session.setAttribute(entry.getKey(), entry.getValue());
		    	}
		    	List<BookCatalog> bookCatalogs = bookCatalogRepository.findAllByIsActiveLimtTwinty(true);
//		    	Map<String, List<BookCatalog>> bookCatalogsKey = bookCatalogs.stream().collect(Collectors.groupingBy(abc ->abc.getSubCategory().getCategory().getCategoryName(),Collectors.toList()));
		    	
		    	Map<String, List<BookCatalog>> bookCatalogsKey = bookCatalogs.stream()
		    	        .collect(Collectors.groupingBy(
		    	                abc -> abc.getSubCategory().getCategory().getCategoryName(),
		    	                Collectors.collectingAndThen(
		    	                        Collectors.toList(),
		    	                        list -> list.stream().limit(12).collect(Collectors.toList())
		    	                )
		    	        ));
		    	session.setAttribute("bookCatalogsKey", bookCatalogsKey);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}

	

}
