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
@Table(name="t_lms_mst_library")
public class Library extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 466450320282370594L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lib_id")
	private Long libId;
	
	@Column(name="lib_code")
	private String libCode;
	
	@Column(name="lib_name")
	private String libName;
	
	@Column(name="lib_address")
	private String libAddress;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="opening_hours")
	private String openingHours;
	
	@Column(name="is_active")
	private Boolean isActive;
	
}
