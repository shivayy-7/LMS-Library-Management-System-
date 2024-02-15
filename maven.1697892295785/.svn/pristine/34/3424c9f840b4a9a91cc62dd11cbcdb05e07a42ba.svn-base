package com.aashdit.lms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.VO.BookDtls;
import com.aashdit.lms.VO.BookVO;
import com.aashdit.lms.dto.CatagorieswiseIssue;
import com.aashdit.lms.dto.DashboardContDto;
import com.aashdit.lms.dto.DashboardDto;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.repository.BookIssueRepository;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.ibm.icu.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DashBoardServiceImpl implements DashBoardService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BookIssueRepository bookIssueRepository;
	@Override
	public DashboardDto getDashboardCardData() {
		 DashboardDto dashboradDto = new DashboardDto();
		 User user = SecurityHelper.getCurrentUser();
		 try {
			
			 String query = "SELECT * from public.get_dashboard_card_data(?)";
			    Object[] params = {user.getPrimaryRole().getRoleCode()};
			    
			    List<DashboardDto> resultList = jdbcTemplate.query(query,params,
		                new BeanPropertyRowMapper<>(DashboardDto.class));
			    if(resultList.size() > 0) {
					dashboradDto = resultList.get(0);
				}
			    
			    
												
		 } catch (Exception e) {
			log.error("Exception Occured in DashBoardServiceImpl at getDashboardCardData() ==>",e);
		 }
		return dashboradDto;
	}
	@Override
	public DashboardContDto getDashboardConData() {
		
		DashboardContDto dashboradDto = new DashboardContDto();
		try {
			 User user = SecurityHelper.getCurrentUser();
			 String query = "SELECT * from public.get_dashboard_con_data(?)";
			    Object[] params = {user.getPrimaryRole().getRoleCode()};
			    
			    List<DashboardContDto> resultList = jdbcTemplate.query(query,params,
		                new BeanPropertyRowMapper<>(DashboardContDto.class));
			    if(resultList.size() > 0) {
					dashboradDto = resultList.get(0);
				}  
		
		}catch (Exception e) {
		e.printStackTrace();
		}

			return dashboradDto;
	}
	@Override
	public ServiceOutcome<String> getCtagorieswiseData() {

		ServiceOutcome<String> svcOutcome = new ServiceOutcome<String>();
		JSONArray jsonArray = new JSONArray();
		
		try {
			 User user = SecurityHelper.getCurrentUser();
			 String query = "SELECT * from public.get_dashboard_catagorieswise_data()";
			   // Object[] params = {user.getPrimaryRole().getRoleCode()};
			    
			    List<CatagorieswiseIssue> resultList = jdbcTemplate.query(query,
		                new BeanPropertyRowMapper<>(CatagorieswiseIssue.class));
		
			for (CatagorieswiseIssue row : resultList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("totalbook", row.getTotalbook());
				jsonObj.put("issuebook", row.getTotalissuebook());
				jsonObj.put("catagories", row.getCategory_name());
				jsonArray.put(jsonObj);
			}
			svcOutcome.setData(jsonArray.toString());
			svcOutcome.setOutcome(true);
		    } catch (Exception ex) {
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			
		}
		return svcOutcome;
	}
	@Override
	public ServiceOutcome<List<BookIssue>> getretunList() {
		ServiceOutcome<List<BookIssue>> soc = new ServiceOutcome<>();
	    try {
	    	Date todayDate = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
    		String formattedDate = sdf.format(todayDate);

    		Date date = sdf.parse(formattedDate);
    		
	        List<BookIssue> bookList = bookIssueRepository.findAllTODAYreturnbook(date);
	        
	        List<BookIssue> filteredList = new ArrayList<>();
	        for (BookIssue bookIssue : bookList) {
	        
	    		Date validDate = bookIssue.getReturnDate();
	    		if (validDate != null) {
	    		    SimpleDateFormat sdfValidDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
	    		    String formattedValidDate = sdfValidDate.format(validDate);
	    		    Date formattedDateValid = sdfValidDate.parse(formattedValidDate);
	    		    if (formattedDateValid.equals(date)) {
	    		    	 filteredList.add(bookIssue);
	    		    	
	    		    }
	        }
	        }
	        soc.setData(filteredList);
	        soc.setMessage("success");
	        soc.setOutcome(true);
	    } catch (Exception e) {
	        log.error("Exception occurred in getReturnList method in MasterServiceImpl -->", e);
	        soc.setMessage("error");
	        soc.setOutcome(false);
	    }
	    return soc;
	}

}
