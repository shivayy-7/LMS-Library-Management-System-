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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;


@Entity
@Table(name="t_lms_mst_member")
@Data
public class Member  implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_code", unique = true, nullable = false)
    private String memberCode;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "email_id")
	private String emailId;  
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "img_doc_path")
	private String imagePath;
	
	@Column(name = "member_type_id")
	private Long memberTypeId;
	
	@ManyToOne
	@JoinColumn(name="gender_code", referencedColumnName = "gender_code")
	private Gender gender;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_of_birth")
	private Date dob;
	
	@Column(name = "aadhar_no")
    private String aadharNo;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	
	@Column(name = "last_updated_on")
	private Date lastupdatedOn;

	 @Column(name = "is_valid")
	  private Boolean isValid;
	 
	 @Column(name = "remark")
	  private String remark;
	    
		@Column(name = "rejected_aadhar_no")
	    private String rejectedAadhar;

	@Transient
	 private MultipartFile addAttachment;


}
