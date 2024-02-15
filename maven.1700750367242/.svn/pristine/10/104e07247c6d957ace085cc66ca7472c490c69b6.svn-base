package com.aashdit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.aashdit.lms.dto.CatagoriesSubCatagoresReport;
import com.aashdit.lms.dto.CatagorieswiseIssue;
import com.aashdit.lms.dto.CategoriesFillterDto;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImpl  implements ReportService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CatagoriesSubCatagoresReport> categorieswiseData(CategoriesFillterDto categoriesFillterDto) {
		List<CatagoriesSubCatagoresReport> dtoList = new ArrayList<CatagoriesSubCatagoresReport>();
		
		try {

			 User user = SecurityHelper.getCurrentUser();
			 String query = "SELECT * from public.get_report_catsubcatwise_data(?,?)";
			   Object[] params = {categoriesFillterDto.getLevelid(),categoriesFillterDto.getEntitylevel()};
			    
			    List<CatagoriesSubCatagoresReport> resultList = jdbcTemplate.query(query,params,
		                new BeanPropertyRowMapper<>(CatagoriesSubCatagoresReport.class));
		
			    dtoList.addAll(resultList);
	
		}catch (Exception e) {
   log.error("Exception occurred in categorieswiseData in ReportServiceImpl", e);
		}
		

			return dtoList;
	}	
	}
	


