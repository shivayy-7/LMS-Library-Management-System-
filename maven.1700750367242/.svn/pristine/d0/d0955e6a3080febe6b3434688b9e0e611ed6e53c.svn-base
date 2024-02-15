package com.aashdit.lms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="t_lms_mst_book_catalog")
public class BookCatalog extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4011475586078147769L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_catalog_id")
	private Long bookCatalogId;
	
	@Column(name="book_catalog_code")
	private String bookCatalogCode;
	
	@Column(name = "qr_path")
	private String qrPath;
	
	@Column(name="isbn")
	private String isbnNo;
	
	@Column(name="book_titles")
	private String bookTitles;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="author_id")
	private Author author;
	
	@Column(name="total_book")
	private Long totalBook;
	
	@Column(name="book_subject")
	private String bookSubject;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="sub_category_id")
	private SubCategory subCategory;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="language")
	private LookupValue language;
	
	@Column(name="no_of_page")
	private Long noOfPage;
	
	@Column(name="img_path")
	private String imgPath;
	
	@Column(name="book_price")
	private String bookPrice;
	
	@Column(name="purchase_date")
	private Date purchaseDate;
	
	@Column(name="publish_date")
	private Date publishDate;
	
//	@Column(name="publish_date")
//	private String publishDate;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="lib_id")
	private Library lib;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="publisher_id")
	private Publisher publisher;
	

	@Column(name="is_active")
	private Boolean isActive;
}
