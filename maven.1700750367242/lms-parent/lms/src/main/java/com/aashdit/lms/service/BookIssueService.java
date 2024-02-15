package com.aashdit.lms.service;

import java.util.List;

import org.json.JSONObject;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.CatalogDto;
import com.aashdit.lms.dto.IssuingDetails;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.LibraryCard;

public interface BookIssueService  {

	List<Object[]> findApprovalNo(String aaprovalNo);

	ServiceOutcome<LibraryCard> findMemberByApprovalNo(String sentapprovalNo);

	List<Object[]> findBookSByNamel(String books);

	ServiceOutcome<List<Book>> findByCode(String code);

	ServiceOutcome<String> saveBookISuue(IssuingDetails issuingDetails);

	ServiceOutcome<List<CatalogDto>> getAllBookCatalougeData();

	ServiceOutcome<JSONObject> createPayment(Long rentMonthId, String status);

	ServiceOutcome<JSONObject> saveSuccAndErrorRes(String signature, String order_id, String payment_id, String status,
			String reason, Long rentMonthId, String preference);



}
