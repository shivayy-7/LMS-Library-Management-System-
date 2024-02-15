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
import javax.persistence.Transient;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Data
@Entity
@Table(name="t_lms_mst_author")

public class Author extends Auditable<User> implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1099195760467515186L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private Long authorId;
	
	@Column(name = "author_name")
	private String authorName;
	
	@Column(name="author_code")
	private String authorCode;
	
	@Column(name="author_name_hi")
	private String autherNameHi;
	
	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender gender;
	
	@Transient
	private Long genderId;
	
	@Column(name="author_address")
	private String address;
	
	@Column(name = "author_mail")
	private String mail;
	
	@Column(name = "author_mobile_number")
	private String mobileNumber;
	
	@Column(name="is_active")
	private Boolean isActive;
	
}
