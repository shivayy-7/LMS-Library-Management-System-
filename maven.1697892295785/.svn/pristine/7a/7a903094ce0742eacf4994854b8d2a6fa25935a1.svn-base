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

import org.springframework.web.multipart.MultipartFile;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Entity
@Table(name  =  "t_lms_mst_sub_category")
@Data
public class SubCategory extends Auditable<User> implements Serializable {
	
	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column(name = "sub_category_code")
	private String subCategoryCode;

	@Column(name = "sub_category_name")
	private String subCategoryName;
	
	@Column(name = "sub_category_description")
	private String subCategoryDescription;
	
	@Column(name = "sub_category_image_path")
	private String subCategoryImagePath;
	
	@Column(name = "sub_keywords")
	private String keywords;
	
	@Column(name = "is_active")
	private Boolean isActive = true;
	
	@Transient
	private MultipartFile subCategoryImage;

}
