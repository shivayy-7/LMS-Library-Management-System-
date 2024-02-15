package com.aashdit.lms.VO;

import java.util.Date;
import java.util.ResourceBundle;

import com.aashdit.lms.utils.ApplicationConstants;

import lombok.Data;

@Data
public class BookCatalogDTO {
	    private String bookType;
	    private Long bookCatalogId;
	    private String bookTitles;
	    private String bookSubject;
	    private String bookCatalogCode;
	    private Long subCategoryId;
	    private String description;
	    private String language;
	    private Long noOfPage;
	    private String imagePath;
	    private String bookPrice;
	    private String purchaseDate;
	    private String publishDate;
	    private Long libraryId;
	    private Long publisherId;
	    private String isbn;
	    private String publisherName;
	    private String authorName;

	   

	    public BookCatalogDTO(Object[] row) {
	    	this.bookType= String.valueOf(row[1]);
	        this.bookCatalogId = Long.parseLong(String.valueOf(row[2]));
	        this.bookTitles = String.valueOf(row[3]);
	        this.bookSubject = String.valueOf(row[10]);
	        this.bookCatalogCode = String.valueOf(row[11]);
	        this.subCategoryId = Long.parseLong(String.valueOf(row[13]));
	        this.description = String.valueOf(row[14]);
	        this.language = String.valueOf(row[15]);
	        this.noOfPage = Long.parseLong(String.valueOf(row[16]));
	        this.imagePath =String.valueOf(row[17]);//rb+"/"+module+"/"+
	        this.bookPrice = String.valueOf(row[18]);
	        this.purchaseDate = String.valueOf(row[19]);
	        this.publishDate = String.valueOf(row[20]);
	        this.libraryId = Long.parseLong(String.valueOf(row[21]));
	        this.publisherId = Long.parseLong(String.valueOf(row[22]));
	        this.isbn = String.valueOf(row[23]);
//	        this.publisherName = String.valueOf(row[24]);
//	        this.authorName = String.valueOf(row[25]);
	    }
}
