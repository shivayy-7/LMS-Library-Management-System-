package com.aashdit.lms.VO;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.aashdit.lms.model.Author;
import com.aashdit.lms.model.Library;
import com.aashdit.lms.model.LookupValue;
import com.aashdit.lms.model.Publisher;
import com.aashdit.lms.model.SubCategory;
import com.aashdit.lms.repository.LookupValueRepository;

import lombok.Data;
@Data
public class BookCatalogVO {

	private Long bookCatalogId;
	private String bookCatalogCode;
	private String isbnNo;
	private String bookTitles;
	private Author author;
//	private String authorId;
	private String qrPath;
	private Long totalBook;
	private String bookSubject;
	private SubCategory subCategory;
	private String description;
	private LookupValue language;
	private Long noOfPage;
	private String imgPath;
	private String bookPrice;
	private String purchaseDate;
	private String publishDate;
	private Library lib;
	private Publisher publisher;
	private Boolean isActive;
	private List<MultipartFile> bookCatogoryImages;
	private List<String> bookCatogoryImagesShow;
	private Long availableBook;
	
	
	
}
