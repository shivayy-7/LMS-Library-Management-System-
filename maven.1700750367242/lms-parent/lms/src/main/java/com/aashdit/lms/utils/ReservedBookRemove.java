package com.aashdit.lms.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.util.MailServiceUtil;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.model.Limit;
import com.aashdit.lms.model.LookupValue;
import com.aashdit.lms.model.MailQueue;
import com.aashdit.lms.repository.BookIssueRepository;
import com.aashdit.lms.repository.LookupValueRepository;
import com.aashdit.umt.model.User;
import com.aashdit.umt.repository.UserRepository;
import com.aashdit.umt.util.SecurityHelper;

@Component
public class ReservedBookRemove {

	private static final Logger logger = Logger.getLogger(EmailSendTask.class);
	
	@Autowired
	private BookIssueRepository bookIssueRepository;
	
	@Autowired
	private LookupValueRepository lookupValueRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	 @Scheduled(cron = "0 0 0 * * ?") // Run every night at 12 AM
//	@Scheduled(fixedRate = 120000)
	    private void markForProcessing() {
	        deleteReserveData();
	}
 
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void deleteReserveData() {
		List<BookIssue> bookIssueList = new ArrayList<>();
		try {
			List<BookIssue> findAllByStatus = bookIssueRepository.findAllByStatus(ApplicationConstants.STATUS_RESERVED);
		    
			List<LookupValue> collect1 = lookupValueRepository.findByCodeOrderByValueCodeDesc(Limit.DCODE).stream()
																.filter(e->e.getValueCode().equals(ApplicationConstants.STATUS_RESERVED_DAY))
																.collect(Collectors.toList());
			List<Limit> limitData1 = collect1.stream().map(e -> {
			Limit sc = new Limit();
			BeanUtils.copyProperties(e, sc);
			return sc;
			}).collect(Collectors.toList());
			
			 LookupValue lookupValue = collect1.get(0);
			 
			 findAllByStatus.forEach(e->{
				 Date createdOn = e.getCreatedOn();
				 Calendar calendar = Calendar.getInstance();
				 calendar.setTime(createdOn);
				 calendar.add(Calendar.DATE,  Integer.parseInt(lookupValue.getValueEn()));
				 
				 LocalDate calendarDate = LocalDate.of(
						    calendar.get(Calendar.YEAR),
						    calendar.get(Calendar.MONTH) + 1, // Calendar months are 0-based, so add 1
						    calendar.get(Calendar.DAY_OF_MONTH)
						);

						LocalDate currentDate = LocalDate.now();

						if (currentDate.isEqual(calendarDate)) {
							User user = userRepository.findByUserName("system");
						    try {
						        e.setStatus(ApplicationConstants.STATUS_REJECTED);
						        e.setLastUpdatedBy(user);
						        e.setLastUpdatedOn(new Date());
						        bookIssueRepository.saveAndFlush(e);
//						        bookIssueList.add(e);
						    } catch (Exception saveException) {
						        saveException.printStackTrace();
						        logger.error(saveException.getMessage());
						    }
						}

			 });
			 
//			 bookIssueRepository.saveAll(bookIssueList);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}

	}
	
}
