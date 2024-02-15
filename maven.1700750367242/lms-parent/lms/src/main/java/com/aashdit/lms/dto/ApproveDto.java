package com.aashdit.lms.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ApproveDto {
	
	private String mamberCode;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date valiDate;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date revaliDate;
	
	private String remark;
	
}