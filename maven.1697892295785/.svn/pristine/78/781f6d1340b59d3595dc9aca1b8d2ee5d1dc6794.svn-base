package com.aashdit.lms.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.ApproveDto;
import com.aashdit.lms.dto.CommonDataDto;
import com.aashdit.lms.model.LibraryCard;
import com.aashdit.lms.model.Member;
import com.aashdit.lms.model.TypeMember;
import com.aashdit.lms.service.CommonService;
import com.aashdit.lms.service.MemberService;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
//@RequestMapping("/api")
public class MemberApiController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/add-member-data")
	public ResponseEntity<Map<String, Object>> addMemberApi() {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        ServiceOutcome<List<TypeMember>> typeList = commonService.findByMeberCode(TypeMember.DCODE);
	        response.put("typeListData", typeList.getData());
	        response.put("genderList", commonService.getAllData("GENDER").getData());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("Exception occurred in MemberController at addMember() => " + e);
	        response.put("error", "An error occurred.");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/public/save-member-data")
	public ResponseEntity<String> saveMember(CommonDataDto memberDataDto) {
	    try {
	        Gson gson = new Gson();
	        Member memberobj = gson.fromJson(memberDataDto.getEncData(), Member.class);
	        memberobj.setAddAttachment(memberDataDto.getAddAttachmentDocs());
	        ServiceOutcome<String> schemeData = memberService.saveMember(memberobj);

	        if (schemeData.getOutcome()) {
	            return new ResponseEntity<>("Success: " + schemeData.getMessage(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Error: " + schemeData.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred in MemberController at saveMember() => " + e);
	        return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/api/view-member")
	public ResponseEntity<Map<String, Object>> viewMember(String  memberCode) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	         User user = SecurityHelper.getCurrentUser();
	           ApproveDto approveDto = new ApproveDto();
	           approveDto.setMamberCode(memberCode);
	        ServiceOutcome<LibraryCard> libraryCardData = memberService.getMemebeByMebmerCodeForView(approveDto);
	        if (libraryCardData.getOutcome()) {
	            response.put("roleCode", user.getPrimaryRole().getRoleCode());
	            response.put("mambermodule", ApplicationConstants.DocMember);
	            response.put("pntData", libraryCardData.getData());
	            String upLoadImg = libraryCardData.getData().getBarcode();
	            try {
	                File img = new File(upLoadImg);
	                FileInputStream fileInputStream = new FileInputStream(img);
	                byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
	                fileInputStream.close();
	                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                response.put("base64Image", base64Image);
	            } catch (Exception e) {
	                log.error("exception===>" + e);
	                response.put("base64Image", "");
	            }
	        } else {
	            response.put("error_msg", libraryCardData.getMessage());
	        }
	        return new ResponseEntity<>(response, libraryCardData.getOutcome() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        log.error("Exception occurred in MemberController at viewmember()==>" + e);
	        response.put("error", "Internal Server Error");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



}
