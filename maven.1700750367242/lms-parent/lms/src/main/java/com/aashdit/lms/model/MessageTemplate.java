package com.aashdit.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name  =  "t_mst_message_template")
public class MessageTemplate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "template_id")
	private Long templateId;
	
	@Column(name = "linked_header")
	private String linkedHeader;

	@Column(name = "template_name")
	private String templateName;
	
	@Column(name = "template_regd_no")
	private String templateRegdNo;
	
	@Column(name = "sms_content")
	private String smsContent;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "department_id")
	private String departmentId;

}
