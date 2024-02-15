package com.aashdit.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Data
@Entity
@Table(name  =  "t_lms_mst_category")	
public class Category extends Auditable<User> implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "category_code")
	private String categoryCode;

	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "category_description")
	private String categoryDescription;
	
	@Column(name = "category_image_path")
	private String categoryImagePath;
	
	@Column(name = "is_active")
	private Boolean isActive=true;
	
	@Transient
	private MultipartFile categoryImage;

}
