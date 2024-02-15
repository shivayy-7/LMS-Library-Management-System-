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

@Data
@Entity
@Table(name="t_lms_mst_book_catalog_img_path")
public class BookCatalogImage extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4011475586078147769L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="catalog_img_path_id")
	private Long bookCatalogId;
	
	@Column(name="catalog_img_path")
	private String catalogImgPath;
	
	@ManyToOne
	@JoinColumn(name = "book_catalog_id")
	private BookCatalog bookCatalog;
	
	@Column(name="is_active")
	private Boolean isActive;

}
