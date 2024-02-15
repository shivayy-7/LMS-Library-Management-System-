package com.aashdit.lms.service;

import java.util.List;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.VO.BookCatalogDTO;
import com.aashdit.lms.dto.ApproveDto;
import com.aashdit.lms.dto.LanguageDto;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.model.LibraryCard;
import com.aashdit.lms.model.Member;
import com.aashdit.lms.model.SubCategory;

public interface MemberService {

	ServiceOutcome<String> saveMember(Member mamber);

	ServiceOutcome<Member> getMemeberListByMemberCode(String mamberCode);

	ServiceOutcome<List<Member>> getMemberList(boolean b);

	ServiceOutcome<LibraryCard> getMemebeByMemberCodeFOrApproval(ApproveDto approveDto);

	List<Member> findByAadhrNo(String trim);

	ServiceOutcome<LibraryCard> getValidDateBymemberId(Long memberId);

	ServiceOutcome<LibraryCard> getMemebeByMemberCodeFOrReApproval(ApproveDto approveDto);

	List<BookCatalogDTO> getDashboardData(Long mamberId);

	ServiceOutcome<LibraryCard> getMemebeByMebmerCodeForView(ApproveDto approveDto);

	ServiceOutcome<List<LanguageDto>> getLanguageList(boolean b);

	ServiceOutcome<List<SubCategory>> getSubCatagoriesList(Long categoryId);

	ServiceOutcome<List<BookCatalog>> getBookListBySubCatagoriesList(Long subcategoryId);

	ServiceOutcome<List<BookCatalog>> getBookListByBookName(String books);

	ServiceOutcome<List<BookCatalog>> getBookListByLnaguageId(Long languageId);

	ServiceOutcome<Member> rejectMember(ApproveDto approveDto);

	ServiceOutcome<List<BookCatalog>> getBookListByCatName(String catName);

}
