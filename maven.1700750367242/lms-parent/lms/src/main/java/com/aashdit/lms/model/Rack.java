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
@Table(name="t_lms_mst_rack")
public class Rack extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368018496607473362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rack_id")
	private Long rackId;
	
	@Column(name="rack_code")
	private String rackCode;
	
	@Column(name="rack_name")
	private String rackName;
	
	@Column(name="is_active")
	private Boolean isActive;
	
}
