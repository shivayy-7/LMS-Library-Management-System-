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

import org.springframework.format.annotation.DateTimeFormat;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="t_lms_library_card_history")
@Getter
@Setter
public class LibraryCardHistory  extends  Auditable<User> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lib_card_his_id")
    private Long lib_his_cardId;

	@ManyToOne
	@JoinColumn(name="library_card_id")
	private LibraryCard libraryCard; 
	
    @Column(name = "barcode")
    private String barcode;
    
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "issued_date")
	private Date issueDate;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "valid_till_date")
	private Date validDate;

	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Boolean isActive;
    
	

}
