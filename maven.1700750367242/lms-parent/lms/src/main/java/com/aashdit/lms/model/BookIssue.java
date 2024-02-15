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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Entity
@Table(name="t_lms_book_issued")
@Data
public class BookIssue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4786765719689552814L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_issued_id")
	private Long bookIssuedId;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="library_card_id")
	private LibraryCard libraryCard;
	
	@Column(name="issued_date")
	private Date issuedDate;
	
	@Column(name="return_date")
	private Date returnDate;
	
//	@Column(name="issue_type_id")
//	private Long bookIssuedId;
	
	@Column(name="status")
	private String status;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="fine_amount")
	private String fineAmount;
	
	@Column(name="book_condition")
	private String bookCondition;
	
	@Column(name="actual_return_date")
	private Date actualReturnDate;
	
	@ManyToOne
	@JoinColumn(name="prnt_book_issue_id")
	private BookIssue parntBookIssue;
	
	@Column(name="barcode_res_path")
	private String barchodePath;
	
	@Column(name="damage_or_lost_img")
	private String damageOrLostImg;
	
	@Column(name="damage_or_lost_fine")
	private String damageOrLostFine;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@ManyToOne
	@JoinColumn(name="last_updated_by")
	private User lastUpdatedBy;
	
	@Column(name="last_updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn;
	
}