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
@Table(name="t_lms_mst_librarianrarian")

public class Librarian extends Auditable<User> implements Serializable {

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 663916742425879571L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "librarian_id")
	    private Long librarianId;

	    @Column(name = "librarian_code")
	    private String librarianCode;

	    @Column(name = "librarian_name", nullable = false)
	    private String librarianName;

	    @Column(name = "librarian_address", nullable = false)
	    private String librarianAddress;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "mobile")
	    private String mobile;

	    @Column(name = "is_active", nullable = false)
	    private Boolean isActive;
	    
	    @ManyToOne
		@JoinColumn(name = "gender_id")
		private Gender gender;
		
		@Transient
		private Long genderId;

	    @ManyToOne
	    @JoinColumn(name = "lib_id")
	    private Library library;
	    
	    @Transient
	    private Long libId;

	
}
