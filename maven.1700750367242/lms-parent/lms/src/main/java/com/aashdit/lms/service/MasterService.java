package com.aashdit.lms.service;

import java.util.List;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.VO.BookDtls;
import com.aashdit.lms.VO.BookVO;
import com.aashdit.lms.VO.LibraryDtls;
import com.aashdit.lms.VO.PublisherDtls;
import com.aashdit.lms.VO.RackDtls;
import com.aashdit.lms.VO.ShelfDtls;
import com.aashdit.lms.dto.TopRatedBookDto;
import com.aashdit.lms.model.Author;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.Gender;
import com.aashdit.lms.model.Librarian;
import com.aashdit.lms.model.Library;
import com.aashdit.lms.model.SubCategory;

public interface MasterService {

	ServiceOutcome<LibraryDtls> manageLib(LibraryDtls libraryDtls);

	ServiceOutcome<LibraryDtls> getLibByCode(String libCode);

	ServiceOutcome<LibraryDtls> manageLibSection(LibraryDtls libraryDtls);

	ServiceOutcome<List<Category>> getCategoryList(boolean b);

	ServiceOutcome<String> saveCategoryData(Category category);

	ServiceOutcome<Category> findCategoryDataByCategoryId(Long categoryId);

	ServiceOutcome<String> activeInactiveCategory(Long categoryId, Boolean status);

	ServiceOutcome<String> saveSubCategoryData(SubCategory subCategory, Long categoryId, String keywords);

	ServiceOutcome<LibraryDtls> getLibSectionbylibId(Long libId);

	ServiceOutcome<SubCategory> findSubCategoryDataBySubCategoryId(Long subCategoryId);

	ServiceOutcome<String> activeInactiveSubCategory(Long subCategoryId, Boolean status);

	ServiceOutcome<List<SubCategory>> getSubCategoryList(boolean b);

	ServiceOutcome<RackDtls> addAndUpdateRack(RackDtls rackDtls);

	ServiceOutcome<RackDtls> activeInactiveRack(RackDtls rackDtls);

	ServiceOutcome<String> saveShelfData(ShelfDtls shelf);

	ServiceOutcome<ShelfDtls> getAllShelfByRackId(Long rackId);

	ServiceOutcome<String> activeInactiveShelf(Long shelfId, Boolean status);

	ServiceOutcome<PublisherDtls> getPublisherByPublisherCode(String publisherCode);

	ServiceOutcome<PublisherDtls> addAndUpdatePublisher(PublisherDtls publisherDtls);

	ServiceOutcome<BookDtls> addAndUpdateBook(BookDtls bookDtls);
	
	
	
	ServiceOutcome<List<Gender>> getGenderList();

	ServiceOutcome<Author> saveAuthorDetails(Author author);

	ServiceOutcome<Author> editAuthorData(Long authorId);

	ServiceOutcome<List<Author>> getAuthorList();

	ServiceOutcome<Librarian> saveLibrarianDetails(Librarian librarian);

	ServiceOutcome<List<Library>> getLibraryList();

	ServiceOutcome<List<Librarian>> getLibrarianDetailsList();

	ServiceOutcome<Librarian> editLibrarianData(Long librarianId);

	ServiceOutcome<Librarian> checkActiveInactiveLibrarian(Long librarianId);

	ServiceOutcome<Author> checkActiveInactiveAuthor(Long authorId);

	ServiceOutcome<BookDtls> getBookCatalogByCatalogCode(String catalogCode);

	ServiceOutcome<String> imgPathByCode(String catalogCode, String identity);

	ServiceOutcome<BookDtls> getBookIssuedDtlsByIssuedId(Long bookIssuedId);

	ServiceOutcome<BookDtls> returnBook(BookDtls bookdtls);

	ServiceOutcome<BookDtls> reIssueBook(BookDtls bookdtls);

	ServiceOutcome<String> savereservedBookData(BookDtls bookDtls);

	List<BookIssue> getAllReservedBook();

	ServiceOutcome<String> savereservedBookStatusData(Long bookIssueId, String status, String returnDate, String issueDate);

	List<BookIssue> getMemberBookList();

	ServiceOutcome<List<TopRatedBookDto>> getTopRatedBookByDate();

	ServiceOutcome<BookDtls> getBookListByBookCatagoryCode(String catalogCode);

	Boolean compareTwoList(List<BookVO> bookVOList, List<?> data);

	List<Book> getBooksByCatalohId(Long bookCatalogId);

	List<BookIssue> getallBookIssueList();

	ServiceOutcome<PublisherDtls> saveEncData(String encData);


}