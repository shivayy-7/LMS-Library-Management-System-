package com.aashdit.lms.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDataDto {
	private String encData;
	private MultipartFile addAttachmentDocs;

}