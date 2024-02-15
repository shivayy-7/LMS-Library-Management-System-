package com.aashdit.lms.VO;

import org.springframework.web.multipart.MultipartFile;

import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.LibraryCard;

import lombok.Data;

@Data
public class BookIssueVO {

	private Long bookIssuedId;
	private Book book;
	private LibraryCard libraryCard;
	private String issuedDate;
	private String returnDate;
//	private Long bookIssuedId;
	private String status;
	private Boolean isActive;
	private String fineAmount;
	private String bookCondition;
	private String actualReturnDate;
	private String reIssuedate;
	private Boolean isBookReturnLate;
	private MultipartFile damageOrLostImg;
	private String damageOrLostFine;
}
