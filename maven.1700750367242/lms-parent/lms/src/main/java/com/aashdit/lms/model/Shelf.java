package com.aashdit.lms.model;

import java.util.Optional;

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
@Table(name  =  "t_lms_mst_shelf")	
public class Shelf extends Auditable<User> {
	
	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "shelf_id")
	private Long shelfId;
	
	@Column(name = "shelf_name")
	private String shelfName;
	
	@Column(name = "shelf_code")
	private String shelfCode;
	
	@ManyToOne
	@JoinColumn(name = "rack_id")
	private Rack rack;
	
	@Column(name = "is_active")
	private Boolean isActive=true;


}
