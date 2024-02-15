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
@Table(name="t_lms_mst_lib_section")
public class LibrarySection extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 466450320282370594L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sec_id")
	private Long secId;
	
	@Column(name="sec_code")
	private String secCode;
	
	@Column(name="sec_name")
	private String secName;
	
	@Column(name="sec_desc")
	private String secDesc;
	
	@Column(name="capacity")
	private Long capacity;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="lib_id")
	private Library library;
	
	@Column(name="is_active")
	private Boolean isActive;

}
