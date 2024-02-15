package com.aashdit.lms.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.VO.BookDtls;
import com.aashdit.lms.dto.CommonDataDto;
import com.aashdit.lms.dto.MasterApiDto;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.SubCategory;
import com.aashdit.lms.repository.CategoryRepository;
import com.aashdit.lms.repository.SubCategoryRepository;
import com.aashdit.lms.service.CommonService;
import com.aashdit.lms.service.MasterService;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/api")
public class MasterApiController {
	
	@Autowired
	private CategoryRepository  categoryRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MasterService masterService;
	
	
	
	@GetMapping(path="/getCategoryList")
	public @ResponseBody List<Category> getCategoryList(){
		return categoryRepository.findAllByIsActiveTrueOrderByCategoryId();
	}
	
	
	@GetMapping(path="/getSubCategoryListByCategory")
	public @ResponseBody ResponseEntity<List<SubCategory>> getSubCategoryListByCategry(Long categoryId){
		List<SubCategory>  subCategoryList =null;
		try {
			if(categoryId!=null) {
				subCategoryList = commonService.findSubCategoryListByCategory(categoryId);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<SubCategory>>(subCategoryList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<SubCategory>>(subCategoryList, HttpStatus.OK);
	}
	@GetMapping(value = "/getMasterData")
	public ResponseEntity<?> getMasterData() throws Exception { 
		ServiceOutcome<MasterApiDto> srvc = new ServiceOutcome<>();
		try {
			srvc = commonService.getMasterList();
			return ResponseEntity.ok(srvc); 
		}catch (Exception e) {
			log.error("Exception occured in getMasterData of MasterApiController => "+e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	@GetMapping("/reservedBook")
	public ResponseEntity<Map<String, Object>> reservedBook(@RequestParam(name = "catalogCode", required = false) String catalogCode) {
		Map<String, Object> responseData = new HashMap<>();
		try {
	        if (catalogCode != null) {
	            User user = SecurityHelper.getCurrentUser();
	            ServiceOutcome<BookDtls> bookCatalogByCatalogCode = masterService.getBookCatalogByCatalogCode(catalogCode);
	            List<Book> booksByCatalogId = masterService.getBooksByCatalohId(bookCatalogByCatalogCode.getData().getBookCatalogVO().getBookCatalogId());
	            bookCatalogByCatalogCode.getData().getBookCatalogVO().setAvailableBook(
	                    booksByCatalogId != null ? booksByCatalogId.stream().count() : 0
	            );
	            ServiceOutcome<BookDtls> listOfBook = masterService.getBookListByBookCatagoryCode(catalogCode);
	            Boolean allBooksAvailable = masterService.compareTwoList(listOfBook.getData().getBookVOList(), commonService.getAllData("ISSUEDBOOKLIST-RESERVED").getData());
	            responseData.put("bookDtls", bookCatalogByCatalogCode.getData().getBookCatalogVO());
	            responseData.put("allBooksAvailable", allBooksAvailable);
//	            responseData.put("availableBooks", availableBooks);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred in reservedBook method in MasterController", e);
	    }
	    return ResponseEntity.ok(responseData);
	}
	
//	@GetMapping("/reservedBook")
//	public ResponseEntity<Map<String, Object>> reservedBook(@RequestParam(name = "catalogCode", required = false) String catalogCode) {
//	    Map<String, Object> response = new HashMap<>();
//	    try {
//	        if (Optional.ofNullable(catalogCode).isPresent()) {
//	            ServiceOutcome<BookDtls> bookCatalogByCatalogCode = masterService.getBookCatalogByCatalogCode(catalogCode);
//	            List<Book> booksByCatalogId = masterService.getBooksByCatalohId(bookCatalogByCatalogCode.getData().getBookCatalogVO().getBookCatalogId());
//	            bookCatalogByCatalogCode.getData().getBookCatalogVO().setAvailableBook(
//	                    booksByCatalogId != null ? booksByCatalogId.stream().count() : 0
//	            );
//	            ServiceOutcome<BookDtls> listOfBook = masterService.getBookListByBookCatagoryCode(catalogCode);
//	            Boolean allBooksAvailable = masterService.compareTwoList(listOfBook.getData().getBookVOList(), commonService.getAllData("ISSUEDBOOKLIST-RESERVED").getData());
//	            response.put("bookDtls", bookCatalogByCatalogCode.getData());
//	            response.put("bookList", listOfBook.getData());
//	            response.put("allBooksAvailable", allBooksAvailable);
//	            response.put("Role", SecurityHelper.getCurrentUser().getPrimaryRole().getRoleCode());
//	            return new ResponseEntity<>(response, HttpStatus.OK);
//	        }
//	    } catch (Exception e) {
//	        log.error("Exception occurred in reservedBook method in MasterController", e);
//	        response.put("error", "Internal Server Error");
//	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//
//	    response.put("error", "Bad Request");
//	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}


	@PostMapping("/save-reservedBook")
	public ResponseEntity<?> savereservedBook(CommonDataDto commonDataDto) {
		ServiceOutcome<String>  listbook =new ServiceOutcome<String>();
		try {
			     Gson gson = new Gson();
			     BookDtls bookDtls = gson.fromJson(commonDataDto.getEncData(), BookDtls.class);
			     listbook = masterService.savereservedBookData(bookDtls);
			     listbook.setMessage(listbook.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in reservedBook method in MasterController",e);
		}
		return new ResponseEntity<ServiceOutcome<String>>(listbook, HttpStatus.OK);
	}

	
	@GetMapping("/bookIssueList")
	public ResponseEntity<?> getBookIssueList() {
		Map<String, Object> responseData = new HashMap<>();
		try {
           List<BookIssue> issueList=masterService.getallBookIssueList();
            responseData.put("bookIssueList", issueList);
		} catch (Exception e) {
			log.error("Exception occured in getBookIssueList method in MasterController",e);
		}
		return new ResponseEntity<Map<String, Object>>(responseData, HttpStatus.OK);
	}
	
	@GetMapping("/reservedBookList")
	public ResponseEntity<Map<String, Object>> reservedBookList(String memberCode) {
	    Map<String, Object> response = new HashMap<>();
	    User user = SecurityHelper.getCurrentUser();
	    try {
	        String role = user.getPrimaryRole().getRoleCode();
	        if (role.equals("ROLE_LA")) {
	            List<BookIssue> bookList = masterService.getAllReservedBook();
	            if(!memberCode.isEmpty()) {
	            	 response.put("bookList", bookList.stream().filter(a->a.getLibraryCard().getMember().getMemberCode().equals(memberCode)).collect(Collectors.toList()));
	            }else {
	            	response.put("bookList", bookList);
	            }
	            response.put("Role", role);
	            response.put("maxAllocationLimit", commonService.getAllData(ApplicationConstants.STATUS_ALLOCATION_LIMIT).getData().get(0));
	            response.put("maxReserveDay", commonService.getAllData("MAX_RESERVE_DAY").getData().get(0));
	        } 
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("Exception occurred in reservedBookList method in MasterController", e);
	        response.put("error", "An error occurred.");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@PostMapping("/save-ReservedBookStatus")
	public ResponseEntity<ServiceOutcome<String>> saveReservedBookStatus(
	        @RequestParam(name = "status", required = false) String status,
	        @RequestParam(name = "bookIssueId", required = false) Long bookIssueId,
	        @RequestParam(name = "returnDate", required = false) String returnDate,
	        @RequestParam(name = "issueDate", required = false) String issueDate) {
	    try {
	        ServiceOutcome<String> bookList = masterService.savereservedBookStatusData(bookIssueId, status, returnDate, issueDate);
	        return new ResponseEntity<>(bookList, HttpStatus.ACCEPTED.OK);
	    } catch (Exception e) {
	        log.error("Exception occurred in saveReservedBookStatus method in MasterController", e);
	        ServiceOutcome<String> errorOutcome = new ServiceOutcome<>();
            errorOutcome.setMessage("An error occurred.");
            errorOutcome.setOutcome(false);
            return new ResponseEntity<>(errorOutcome, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


}
