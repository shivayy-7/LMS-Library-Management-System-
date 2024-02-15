package com.aashdit.lms.VO;

import java.util.List;

import com.aashdit.lms.model.BookCatalogImage;

import lombok.Data;

@Data
public class BookDtls {

	private BookCatalogVO bookCatalogVO;
	
	private List<BookVO> bookVOList;
	
	private BookIssueVO bookIssueVO;
	
	private String fineAmount;
	
	private List<BookCatalogImageVO> bookCatalogImageVO;
	
	private String uniqueCode;
	
}
