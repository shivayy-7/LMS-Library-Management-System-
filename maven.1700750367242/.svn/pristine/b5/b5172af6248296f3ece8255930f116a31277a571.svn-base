package com.aashdit.lms.model;

import java.io.Serializable;

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

import lombok.Data;

@Data
@Entity
@Table(name = "t_lms_mst_book")
public class Book extends Auditable<User> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4011475586078147769L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "book_uk_no")
	private String bookUkNo;
	
	@Column(name = "book_code")
	private String bookCode;
	
	@ManyToOne
	@JoinColumn(name = "book_catalog_id")
	private BookCatalog bookCatalog;

	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "shelf_id")
	private Shelf shelf;

	@ManyToOne
	@JoinColumn(name = "rack_id")
	private Rack rack;

	@Column(name = "is_active")
	private Boolean isActive;

}
