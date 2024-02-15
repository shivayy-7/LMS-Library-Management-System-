package com.aashdit.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Data
@Entity
@Table(name="t_lms_mst_publisher")
public class Publisher extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368018496607473362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="publisher_id")
	private Long publisherId;
	
	@Column(name="publisher_code")
	private String publisherCode;
	
	@Column(name="publisher_name")
	private String publisherName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="website")
	private String website;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_active")
	private Boolean isActive;

}
