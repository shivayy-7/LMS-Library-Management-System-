package com.aashdit.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.lms.dto.CatagoriesSubCatagoresReport;
import com.aashdit.lms.dto.CategoriesFillterDto;
import com.aashdit.lms.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	 @GetMapping("/categories-subcategories-report") 
		public String  getcategorieswisereport(Model model) {
			
			try {
				
				
			} catch (Exception e) {
				log.error("Exception occurred in getcatagorieswisereport of ", e);
			}
			return "site.report.categories";	
		}
	 
	 
//	 
//	 @GetMapping(value = "/filltercategoriesdata", name = "Report")
//		public String getgilltercategoriesdata(RedirectAttributes attr,CategoriesFillterDto categoriesFillterDto) {
//	   List<CatagoriesSubCatagoresReport> result = new ArrayList<>();
//	    try {
//	    	if(categoriesFillterDto.getEntitylevel()==null) {
//	    		categoriesFillterDto.setEntitylevel("CATEGORY");
//	    		categoriesFillterDto.setLevelid(0l);
//	    	}
//	    	
//		           	 result = reportService.categorieswiseData(categoriesFillterDto);
//		    
//	        attr.addFlashAttribute("catlist",result);
//	        attr.addFlashAttribute("categoriesFillterDto",categoriesFillterDto);
//	    } catch (Exception e) {
//	        log.error("Exception occurred in getgilltercategoriesdata in ReportController", e);
//	    }
//	    
//	    return "site.report.categories";
//	    
//	} 
//	 
	 
	 
	 @GetMapping(value = "/filltercategoriesdata", name = "Report")
	 public String getgilltercategoriesdata(Model model, CategoriesFillterDto categoriesFillterDto) {
	     List<CatagoriesSubCatagoresReport> result = new ArrayList<>();
	     try {
	         if(categoriesFillterDto.getEntitylevel() == null) {
	             categoriesFillterDto.setEntitylevel("CATEGORY");
	             categoriesFillterDto.setLevelid(0l);
	         }
	         
	         result = reportService.categorieswiseData(categoriesFillterDto);
	         
	         model.addAttribute("catlist", result);
	         model.addAttribute("categoriesFillterDto", categoriesFillterDto);
	     } catch (Exception e) {
	         log.error("Exception occurred in getgilltercategoriesdata in ReportController", e);
	     }
	     
	     return "site.report.categories";
	 }

}

