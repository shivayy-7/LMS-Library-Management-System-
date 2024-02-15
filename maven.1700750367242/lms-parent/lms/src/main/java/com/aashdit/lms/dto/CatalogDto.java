package com.aashdit.lms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CatalogDto {
	private Long bookCatalogId;
	private String bookCatalogCode;
	private String isbnNo;
	private String bookTitles;
	private String authorName;
	private Long totalBook;
	private String bookSubject;
	private String subCategoryName;
	private String description;
	private String language;
	private Long noOfPage;
	private String imgPath;
	private String bookPrice;
	private Date purchaseDate;
	private Date publishDate;
	private String libName;
	private String publisherName;
	private Boolean isActive;
}
