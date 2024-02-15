package com.aashdit.lms.service;

import java.util.List;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.MasterApiDto;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.SubCategory;
import com.aashdit.lms.model.TypeMember;
import com.aashdit.umt.model.User;
import com.google.gson.JsonObject;

public interface CommonService {

	ServiceOutcome<List<?>> getAllAjaxCallDetails(String identity, String id);

	ServiceOutcome<List<?>> getAllData(String identity);

	ServiceOutcome<List<TypeMember>> findByMeberCode(String dcode);

	List<SubCategory> findSubCategoryListByCategory(Long categoryId);

	ServiceOutcome<MasterApiDto> getMasterList();

	ServiceOutcome<String> deleteBookCatalogImageById(Long id);

	ServiceOutcome<Boolean> getDataByISBN(String id);

	ServiceOutcome<Boolean> getBookCountByMemberId(String identity);

	ServiceOutcome<Long> getBookAllocationCountByMemberId(String identity, Long bookIssuedId);

	ServiceOutcome<Boolean> checkUniqueEmail(String email);


}
