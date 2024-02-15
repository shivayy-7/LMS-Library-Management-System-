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

import lombok.Data;

@Entity
@Table(name="t_lms_book_fine")
@Data
public class BookFine extends Auditable<User> implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8130188609344719310L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_fine_id")
	private Long bookFineId;
	
	@ManyToOne
	@JoinColumn(name="book_issued_id")
	private BookIssue bookIssuedId;
	
	@Column(name="fine_amount")
	private String fineAmount;
	
	@Column(name="status")
	private String status;
	
	@Column(name="is_active")
	private Boolean isActive;
	
}
