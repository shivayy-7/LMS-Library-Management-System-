package com.aashdit.lms.controller;


import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.util.ViewDocuments;
import com.aashdit.lms.VO.BookDtls;
import com.aashdit.lms.VO.LibraryDtls;
import com.aashdit.lms.VO.PublisherDtls;
import com.aashdit.lms.VO.PublisherVO;
import com.aashdit.lms.VO.RackDtls;

import com.aashdit.lms.VO.ShelfDtls;
import com.aashdit.lms.model.Author;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.Librarian;


import com.aashdit.lms.model.SubCategory;

import com.aashdit.lms.service.CommonService;
import com.aashdit.lms.service.MasterService;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mst")
public class MasterController {
	
	@Autowired
	private MasterService masterService;
	
	ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@Autowired
	private CommonService commonService;
	
		
	/** @Author= @SHIVAYY **/
	@GetMapping("/add-library")
	public String addLibrary(Model model, @RequestParam(name="libCode", required = false) String libCode) {
		
		try {
			model.addAttribute("genderList", commonService.getAllData("GENDER").getData());
			if(Optional.ofNullable(libCode).isPresent()) {
				ServiceOutcome<LibraryDtls> libById = masterService.getLibByCode(libCode);
				model.addAttribute("libDtls", libById.getData());
				model.addAttribute(libById.getOutcome()?"success_msg":"error_msg", libById.getOutcome() ? libById.getMessage():libById.getMessage());
			}
		} catch (Exception e) {
			log.error("Exception occured in MasterController at addLibrary()==>"+ e);
		}
		return "site.master.addLibrary";
	}

	/** @Author= @SHIVAYY **/
	@PostMapping("/manage-lib")
	public String manageLibrary(LibraryDtls libraryDtls, RedirectAttributes attr) {
		
		try {
			ServiceOutcome<LibraryDtls> manageLib = masterService.manageLib(libraryDtls);
			attr.addFlashAttribute(manageLib.getOutcome()?"success_msg":"error_msg", manageLib.getOutcome() ? manageLib.getMessage():manageLib.getMessage());
			
		} catch (Exception e) {
			log.error("Exception occured in MasterController at manageLibrary()==>"+ e);
		}
		return "redirect:/mst/lib-list";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/lib-list")
	public String LibraryList(Model model) {
		
		try {
			model.addAttribute("libList", commonService.getAllData("LIBRARY").getData());
			
		} catch (Exception e) {
			log.error("Exception occured in MasterController at LibraryList()==>"+ e);
		}
		return "site.master.libList";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/add-lib-section")
	public String librarySection(Model model, @RequestParam(name="libId", required = false) Long libId) {
		
		try {
			model.addAttribute("libList", commonService.getAllData("LIBRARY").getData());
			if(Optional.ofNullable(libId).isPresent()) {
				ServiceOutcome<LibraryDtls> libSectionbySectionCode = masterService.getLibSectionbylibId(libId);
				model.addAttribute("libSectionDtls", libSectionbySectionCode.getData());
				model.addAttribute(libSectionbySectionCode.getOutcome()?"success_msg":"error_msg", libSectionbySectionCode.getOutcome() ? libSectionbySectionCode.getMessage():libSectionbySectionCode.getMessage());
			}
		} catch (Exception e) {
			log.error("Exception occured in MasterController at librarySection()==>"+ e);
		}
		return "site.master.addSection";
	}
	
	/** @Author= @SHIVAYY **/
	@PostMapping("/manage-lib-section")
	public String manageLibSection(LibraryDtls libraryDtls, RedirectAttributes attr) {
		
		try {
			ServiceOutcome<LibraryDtls> manageLibSection = masterService.manageLibSection(libraryDtls);
			attr.addFlashAttribute(manageLibSection.getOutcome()?"success_msg":"error_msg", manageLibSection.getOutcome() ? manageLibSection.getMessage():manageLibSection.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at manageLibSection()==>"+ e);
		}
		return "redirect:/mst/add-lib-section";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/lib-section-list")
	public String librarySecList(Model model) {
		try {
			model.addAttribute("libSectionList", commonService.getAllData("LIBRARY-SECTION").getData());
			System.out.println(commonService.getAllData("LIBRARY-SECTION").getData());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at librarySecList()==>"+ e);
		}
		return "site.master.libSectionList";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/add-rack")
	public String addRack(Model model) {
		try {
			model.addAttribute("rackList", commonService.getAllData("RACK_ALL").getData());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at addRack()==>"+ e);
		}
		return "site.master.addRack";
	}
	
	/** @Author= @SHIVAYY **/
	@PostMapping("/manage-rack")
	public String manageRack(RackDtls rackDtls, RedirectAttributes attr) {
		
		try {
			ServiceOutcome<RackDtls> addAndUpdateRack = masterService.addAndUpdateRack(rackDtls);
			attr.addFlashAttribute(addAndUpdateRack.getOutcome()?"success_msg":"error_msg", addAndUpdateRack.getOutcome() ? addAndUpdateRack.getMessage():addAndUpdateRack.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at manageRack()==>"+ e);
		}
		return "redirect:/mst/add-rack";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/rackManager")
	public String rackManager(RackDtls rackDtls, RedirectAttributes attr) {
		try {
			masterService.activeInactiveRack(rackDtls);
		} catch (Exception e) {
			log.error("Exception occured in MasterController at rackManager()==>"+ e);
		}
		return "redirect:/mst/add-rack";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/add-publisher")
	public String addPublisher(PublisherVO publisherVO, @RequestParam(name="publisherCode", required = false) String publisherCode , Model model) {
		try {
			if(Optional.ofNullable(publisherCode).isPresent()) {
				ServiceOutcome<PublisherDtls> publisherByPublisherCode = masterService.getPublisherByPublisherCode(publisherCode);
				model.addAttribute("publisherData", publisherByPublisherCode.getData());
			}
		} catch (Exception e) {
			log.error("Exception occured in MasterController at addPublisher()==>"+ e);
		}
		return "site.master.addPublisher";
	}
	
	/** @Author= @SHIVAYY **/
	@PostMapping("/manage-publisher-00000000000000")
	public String managePublisher(PublisherDtls publisherDtls, RedirectAttributes attr) {
		try {
			ServiceOutcome<PublisherDtls> addAndUpdatePublisher = masterService.addAndUpdatePublisher(publisherDtls);
			attr.addFlashAttribute(addAndUpdatePublisher.getOutcome()?"success_msg":"error_msg", addAndUpdatePublisher.getOutcome() ? addAndUpdatePublisher.getMessage():addAndUpdatePublisher.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at managePublisher()==>"+ e);
		}
		return "redirect:/mst/add-publisher";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/manage-publisher")
	public String managePublisher(@RequestParam String encData, RedirectAttributes attr) {//PublisherDtls publisherDtls
		try {
			System.out.println(encData);
			masterService.saveEncData(encData);
		} catch (Exception e) {
			log.error("Exception occured in MasterController at managePublisher()==>"+ e);
		}
		return "redirect:/mst/add-publisher";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/list-publisher")
	public String listPublisher(Model model) {
		try {
			model.addAttribute("publisherList", commonService.getAllData("PUBLISHER").getData());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at listPublisher()==>"+ e);
		}
		return "site.master.listPublisher";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/add-book")
	public String addBook(Model model, @RequestParam(name="catalogCode", required = false) String catalogCode, @RequestParam(name="status", required = false) String status) {
		try {
			model.addAttribute("authorList", commonService.getAllData("AUTHOR").getData());
			model.addAttribute("subCatList", commonService.getAllData("SUBCATEGORY").getData());
			model.addAttribute("libraryList", commonService.getAllData("LIBRARY").getData());
			model.addAttribute("publisherList", commonService.getAllData("PUBLISHER").getData());
			model.addAttribute("rackList", commonService.getAllData("RACK_TRUE").getData());
			model.addAttribute("languageList", commonService.getAllData("LANGUAGE").getData());
			if(Optional.ofNullable(catalogCode).isPresent()) {
				ServiceOutcome<BookDtls> bookCatalogByCatalogCode = masterService.getBookCatalogByCatalogCode(catalogCode);
				model.addAttribute("bookDtls", bookCatalogByCatalogCode.getData());
				model.addAttribute("status", status);
			}
		} catch (Exception e) {
			log.error("Exception occured in MasterController at addBook()==>"+ e);
		}
		return "site.master.addBook";
	}
	
	/** @Author= @SHIVAYY **/
	@PostMapping("/manage-book")
	public String manageBook(BookDtls bookDtls, RedirectAttributes attr) {
		try {
			ServiceOutcome<BookDtls> addAndUpdateBook = masterService.addAndUpdateBook(bookDtls);
			attr.addFlashAttribute(addAndUpdateBook.getOutcome()?"success_msg":"error_msg", addAndUpdateBook.getOutcome() ? addAndUpdateBook.getMessage():addAndUpdateBook.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at manageBook()==>"+ e);
		}
		return "redirect:/mst/add-book";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/viewImage")
	public void viewImage(@RequestParam String catalogCode,@RequestParam String identity, HttpServletResponse response, HttpServletRequest request) {
		try {
			ServiceOutcome<String> bookCatalogByCatalogId = masterService.imgPathByCode(catalogCode, identity);
			if(Optional.ofNullable(bookCatalogByCatalogId.getData()).isPresent()) {
//				ViewDocuments.viewUploadedDocument(bookCatalogByCatalogId.getData().getImgPath(), request, response);
			}
		} catch (Exception e) {
			log.error("Exception occured in MasterController at viewImage()==>"+ e);
		}
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/issued-bookList")
	public String issuedBookList(Model model) {
		try {
			model.addAttribute("issuedBookList", commonService.getAllData("ISSUEDBOOKLIST").getData());
		} catch (Exception e) {
			log.error("Exception occured in MasterController at issuedBookList()==>"+ e);
		}
		return "site.master.issuedBookList";
	}
	
	/** @Author= @SHIVAYY **/
	@GetMapping("/return-book")
	public String returnBook(@RequestParam(name="bookIssuedId") Long bookIssuedId,@RequestParam(name="type") String type, Model model) {
		String page = null;
		try {
			if(type.equals("RETURNBOOK")) {
				
				ServiceOutcome<BookDtls> bookIssuedDtlsByIssuedId = masterService.getBookIssuedDtlsByIssuedId(bookIssuedId);
				model.addAttribute("bookissueDtls", bookIssuedDtlsByIssuedId.getData());
				page = "site.master.bookReturn";
			}else {
				ServiceOutcome<BookDtls> bookIssuedDtlsByIssuedId = masterService.getBookIssuedDtlsByIssuedId(bookIssuedId);
				model.addAttribute("bookissueDtls", bookIssuedDtlsByIssuedId.getData());
				page = "site.master.bookReIssue";
			}
			
		} catch (Exception e) {
			log.error("Exception occured in MasterController at issuedBookList()==>"+ e);
		}
		return page;
	}
	
	/** @Author= @SHIVAYY **/
	@PostMapping("/manage-returnBook")
	public String manageReturnBook(BookDtls bookdtls, RedirectAttributes attr) {
		try {
			
			ServiceOutcome<BookDtls> returnBook = masterService.returnBook(bookdtls);
			
		} catch (Exception e) {
			log.error("Exception occured in MasterController at manageReturnBook()==>"+ e);
		}
		return "redirect:/mst/issued-bookList";
	}
	
	/** @Author= @SHIVAYY **/
	@PostMapping("/manage-reIssueBook")
	public String manageReIssueBook(BookDtls bookdtls, RedirectAttributes attr) {
		try {
			
			ServiceOutcome<BookDtls> returnBook = masterService.reIssueBook(bookdtls);
			
		} catch (Exception e) {
			log.error("Exception occured in MasterController at manageReIssueBook()==>"+ e);
		}
		return "redirect:/mst/issued-bookList";
	}
	
	
	//======================================== Category ========================================	
	
	/** @Author= @SURJYAMANGARAJ **/	
		@GetMapping("/add-Category")
		public String addCategory(Model model) {
		   try {
			    ServiceOutcome<List<Category>> categoryList =masterService.getCategoryList(false);
				model.addAttribute("categoryList", categoryList.getData());
			} catch (Exception e) {
				log.error("Exception occured in addCategory method in MasterController",e);
			}	
		   return "site.master.addcategory";

	   }
		
	/** @Author= @SURJYAMANGARAJ **/	
		@PostMapping("/save-Category")
		public String saveCategory(RedirectAttributes attr, Category category) {
			  try {
				  ServiceOutcome<String>  listcategory = masterService.saveCategoryData(category);
					attr.addFlashAttribute(listcategory.getOutcome().equals(true) ? "success_msg" : "error_msg",listcategory.getMessage());
				} catch (Exception e) {
					log.error("Exception occured in addCategory method in MasterController",e);
				}	
			   return "redirect:/mst/add-Category";
		}
	
	/** @Author= @SURJYAMANGARAJ **/	
		@GetMapping("/edit-Category")
		public String editCategoryDetails(RedirectAttributes attr,Long categoryId) {
			try {
			ServiceOutcome<Category> category=masterService.findCategoryDataByCategoryId(categoryId);
			if(category.getOutcome()) {
				attr.addFlashAttribute("categoryData",category.getData());
			}else {
				attr.addFlashAttribute("error_msg",category.getMessage());
			}
			}catch (Exception e) {
				log.error("Exception occured in MasterController at editCategoryDetails()==>"+e);
			}
		return	"redirect:/mst/add-Category";	
		}
		
	/** @Author= @SURJYAMANGARAJ **/		
		@PostMapping("/activeInactiveCategory")
		public String activeInactiveCategory(RedirectAttributes attr,Long categoryId,Boolean status) {
			try {
				ServiceOutcome<String>  outcome = masterService.activeInactiveCategory(categoryId,status);
				attr.addFlashAttribute(outcome.getOutcome() ? "success_msg" : "error_msg",outcome.getMessage());
			} catch (Exception e) {
				log.error("Exception occured in MasterController at activeInactiveCategory()==>"+e);
			}
			return "redirect:/mst/add-Category";
		}
		
		//======================================== Sub Category ========================================	
		
	/** @Author= @SURJYAMANGARAJ **/			
		@GetMapping("/add-SubCategory")
		public String addSubCategory(Model model) {
		   try {
			   ServiceOutcome<List<Category>> categoryList =masterService.getCategoryList(true);
				model.addAttribute("categoryList", categoryList.getData());
				ServiceOutcome<List<SubCategory>> subCategoryList =masterService.getSubCategoryList(false);
				model.addAttribute("subCategoryList", subCategoryList.getData());
			} catch (Exception e) {
				log.error("Exception occured in addSubCategory method in MasterController",e);
			}	
		   return "site.master.addsubcategory";
	   }
	
	/** @Author= @SURJYAMANGARAJ **/	
		@PostMapping("/save-SubCategory")
		public String saveSubCategory(RedirectAttributes attr, SubCategory subCategory ,Long categoryId, @RequestParam("keywords") String keywords) {
			  try {
				  ServiceOutcome<String>  listsubcategory = masterService.saveSubCategoryData(subCategory,categoryId, keywords);
					attr.addFlashAttribute(listsubcategory.getOutcome().equals(true) ? "success_msg" : "error_msg",listsubcategory.getMessage());
				} catch (Exception e) {
					log.error("Exception occured in saveSubCategory method in MasterController",e);
				}	
			   return "redirect:/mst/add-SubCategory";
		}
		
	/** @Author= @SURJYAMANGARAJ **/		
		@GetMapping("/edit-SubCategory")
		public String editSubCategoryDetails(RedirectAttributes attr,Long subCategoryId) {
			try {
			ServiceOutcome<SubCategory> subCategory=masterService.findSubCategoryDataBySubCategoryId(subCategoryId);
			if(subCategory.getOutcome()) {
				attr.addFlashAttribute("subCategoryData",subCategory.getData());
			}else {
				attr.addFlashAttribute("error_msg",subCategory.getMessage());
			}
			}catch (Exception e) {
				log.error("Exception occured in MasterController at editSubCategoryDetails()==>"+e);
			}
		return	"redirect:/mst/add-SubCategory";	
		}
		
	/** @Author= @SURJYAMANGARAJ **/		
		@PostMapping("/activeInactiveSubCategory")
		public String activeInactiveSubCategory(RedirectAttributes attr,Long subCategoryId,Boolean status) {
			try {
				ServiceOutcome<String>  outcome = masterService.activeInactiveSubCategory(subCategoryId,status);
				attr.addFlashAttribute(outcome.getOutcome() ? "success_msg" : "error_msg",outcome.getMessage());
			} catch (Exception e) {
				log.error("Exception occured in MasterController at activeInactiveSubCategory()==>"+e);
			}
			return "redirect:/mst/add-SubCategory";
		}
		
	//======================================== SHELF ========================================
		
		/** @Author= @SURJYAMANGARAJ **/			
		@GetMapping("/add-Shelf")
		public String addShelf(Model model, RedirectAttributes redirectAttributes) {
		   try {
				model.addAttribute("rackList", commonService.getAllData("RACK_ALL").getData());
				if (redirectAttributes.getFlashAttributes().containsKey("shelfDtls")) {
		            ShelfDtls shelfDtls = (ShelfDtls) redirectAttributes.getFlashAttributes().get("shelfDtls");
		            model.addAttribute("shelfDtls", shelfDtls);
		            Long rackId= (Long)redirectAttributes.getFlashAttributes().get("rackId");
		            model.addAttribute("rackId", rackId);
		        }
			} catch (Exception e) {
				log.error("Exception occured in addShelf method in MasterController",e);
			}	
		   return "site.master.addshelf";
	   }
		
		/** @Author= @SURJYAMANGARAJ **/	
		@PostMapping("/save-Shelf")
		public String saveShelf(ShelfDtls shelfDtls, RedirectAttributes attr) {
			  try {
				  ServiceOutcome<String>  listshelf = masterService.saveShelfData(shelfDtls);
					attr.addFlashAttribute(listshelf.getOutcome().equals(true) ? "success_msg" : "error_msg",listshelf.getMessage());
				} catch (Exception e) {
					log.error("Exception occured in saveSubCategory method in MasterController",e);
				}	
			   return "redirect:/mst/add-Shelf";
		}
		
		/** @Author= @SURJYAMANGARAJ **/			
		@GetMapping("/getShelfByRackId")
		public String getShelfByRackId(@RequestParam Long rackId, RedirectAttributes attr) {
		   try {
				ServiceOutcome<ShelfDtls> allShelfByRackId = masterService.getAllShelfByRackId(rackId);
				attr.addFlashAttribute("shelfDtls", allShelfByRackId.getData());
				attr.addFlashAttribute("rackId", rackId );
			} catch (Exception e) {
				log.error("Exception occured in addShelf method in MasterController",e);
			}	
		   return "redirect:/mst/add-Shelf";
	   }
		
		/** @Author= @SURJYAMANGARAJ **/		
		@PostMapping("/activeInactiveShelf")
		public String activeInactiveShelf(RedirectAttributes attr,Long shelfId,Boolean status) {
			try {
				ServiceOutcome<String>  outcome = masterService.activeInactiveShelf(shelfId,status);
				attr.addFlashAttribute(outcome.getOutcome() ? "success_msg" : "error_msg",outcome.getMessage());
			} catch (Exception e) {
				log.error("Exception occured in MasterController at activeInactiveShelf()==>"+e);
			}
			return "redirect:/mst/add-Shelf";
		}
		
		
		//======================================== Author ========================================	
		
				@GetMapping("/addAuthorDetails")
				public String addAuthorDetails(Model model ,@RequestParam(value= "status", required = false ) String status) {
					try {
						
						model.addAttribute("genderList", masterService.getGenderList().getData());
						
					} catch (Exception e) {
						log.error("Exception occured in masterController->in addAuthorDetails()"+e);
					}
					
					return "site.addAuthorDetails";			
				}
				
				
				@PostMapping("/saveAuthorDetails")
				public String saveAuthorDetails(Author author, RedirectAttributes attributes) {
					try {
						ServiceOutcome<Author> authorData = masterService.saveAuthorDetails(author);
						attributes.addFlashAttribute(authorData.getOutcome() ? "success_msg" : "error_msg", authorData.getMessage());
					} catch (Exception e) {
						log.error("exception occured in master controller saveAuthorDetails() ->"+e);
					}
					
					return "redirect:/mst/addAuthorDetails";		
				}
				
				@GetMapping("/editAuthorDetails")
				public String editAuthorData(@RequestParam("authorId") Long authorId,RedirectAttributes attr, @RequestParam String status) {
					try {
						ServiceOutcome<Author>author  = masterService.editAuthorData(authorId);
						attr.addFlashAttribute("editAuthor", author.getData());
						attr.addFlashAttribute("status", status);
					}catch (Exception e) {
						log.error("Exception occured in masterController->in editAuthorDetails()"+e);
						
					}
					return "redirect:/mst/addAuthorDetails";
				}
				
				@GetMapping("/getAuthorDetailsList")
				public String getBankBranchDetailsList(Model model) {
					try {
						ServiceOutcome<List<Author>> author =masterService.getAuthorList();
						model.addAttribute("authorDetailsList", author.getData());		
					} catch (Exception e) {
						log.error("Exception occured in masterController->in getAuthorDetailsList()"+e);
					}		
					return "site.viewAuthorDetailsList";		
				}

				@PostMapping("/checkActivInactiveAuthorData")
				public String checkActiveInactiveAuthorData(@RequestParam ("authorId")Long authorId, RedirectAttributes attribute) {
					try {
							ServiceOutcome<Author> author=  masterService.checkActiveInactiveAuthor(authorId);
						     attribute.addFlashAttribute(author.getData().getIsActive() ? "success_msg" : "error_msg", author.getMessage());
					}catch (Exception e) {
						log.error("error occured in masterController->in checkActivInactiveAuthorData()"+e);
					}

					return "redirect:/mst/getAuthorDetailsList";
				}
				//======================================== Librarian ========================================	
				@GetMapping("/addLibrarianDetails")
				public String addLibrarianDetails(Model model ,@RequestParam(value= "status", required = false ) String status) {
					try {
						model.addAttribute("genderList", masterService.getGenderList().getData());			
						model.addAttribute("libraryList", masterService.getLibraryList().getData());
					} catch (Exception e) {
						log.error("Exception occured in masterController->in addLibrarianDetails()"+e);
					}
					
					return "site.addLibrarianDetails";			
				}
				
				@PostMapping("/saveLibrarianDetails")
				public String saveLibrarianDetails(Librarian librarian, RedirectAttributes attributes) {
					try {
						ServiceOutcome<Librarian> librarianData = masterService.saveLibrarianDetails(librarian);
						attributes.addFlashAttribute(librarianData.getOutcome() ? "success_msg" : "error_msg", librarianData.getMessage());
					} catch (Exception e) {
						log.error("exception occured in master controller in saveLibrarianDetails() ->"+e);
					}
					
					return "redirect:/mst/getLibrarianDetailsList";		
				}
				
				@GetMapping("/getLibrarianDetailsList")
				public String getLibrarianDetailsList(Model model) {
					try {
						ServiceOutcome<List<Librarian>> library =masterService.getLibrarianDetailsList();
						model.addAttribute("librarianDetailsList", library.getData());		
					} catch (Exception e) {
						log.error("Exception occured in masterController->in getLibrarianDetailsList()"+e);
					}		
					return "site.viewLibrarianDetailsList";		
				}
				
				@GetMapping("/editLibrarianDetails")
				public String editLibrarianDetails(@RequestParam("librarianId") Long librarianId,RedirectAttributes attr, @RequestParam String status) {
					try {
						ServiceOutcome<Librarian>librarian  = masterService.editLibrarianData(librarianId);
						attr.addFlashAttribute("editLibrarian", librarian.getData());
						attr.addFlashAttribute("status", status);
					}catch (Exception e) {
						log.error("Exception occured in masterController->in editLibrarianDetails()"+e);
						
					}
					return "redirect:/mst/addLibrarianDetails";
				}
				
				
				@PostMapping("/checkActivInactiveData")
				public String checkActiveInactiveData(@RequestParam ("librarianId")Long librarianId, RedirectAttributes attribute) {
					try {
							ServiceOutcome<Librarian> librarian=  masterService.checkActiveInactiveLibrarian(librarianId);
						     attribute.addFlashAttribute(librarian.getData().getIsActive() ? "success_msg" : "error_msg", librarian.getMessage());
					}catch (Exception e) {
						log.error("error occured in masterController->in checkActivInactiveData()"+e);
					}

					return "redirect:/mst/getLibrarianDetailsList";
				}
				
				
				//======================================== Reserved Book Lists ========================================
				
				/** @Author= @SURJYAMANGARAJ **/
				@GetMapping("/list-book-catalog")
				public String listbook(Model model) {
					User user=SecurityHelper.getCurrentUser();
					try {
						model.addAttribute("bookCatagoryList", commonService.getAllData("BOOK-CATAGORY").getData());
						model.addAttribute("Role", user.getPrimaryRole().getRoleCode());
						
						
//						ServiceOutcome<BookDtls> listOfBook=masterService.getBookListByBookCatagoryCode(catalogCode);
//						model.addAttribute("bookList", listOfBook.getData());
//						List<Long> compareTwoList = masterService.compareTwoList(listOfBook.getData().getBookVOList(), commonService.getAllData("ISSUEDBOOKLIST-RESERVED").getData());
//						model.addAttribute("compareTwoList", compareTwoList);
						
					} catch (Exception e) {
						log.error("Exception occured in MasterController at listbook()==>"+ e);
					}
					return "site.master.listBookCatalog";
				}
				
				/** @Author= @SURJYAMANGARAJ **/
				@GetMapping("/reservedBook")
				public String reservedBook(Model model, @RequestParam(name="catalogCode", required = false) String catalogCode) {
					User user=SecurityHelper.getCurrentUser();
					  try {
						  if(Optional.ofNullable(catalogCode).isPresent()) {
								ServiceOutcome<BookDtls> bookCatalogByCatalogCode = masterService.getBookCatalogByCatalogCode(catalogCode);
								List<Book> booksByCatalohId = masterService.getBooksByCatalohId(bookCatalogByCatalogCode.getData().getBookCatalogVO().getBookCatalogId());
								bookCatalogByCatalogCode.getData().getBookCatalogVO().setAvailableBook(
								        booksByCatalohId != null ? booksByCatalohId.stream().count() : 0
								);
								model.addAttribute("bookDtls", bookCatalogByCatalogCode.getData());
								
								ServiceOutcome<BookDtls> listOfBook=masterService.getBookListByBookCatagoryCode(catalogCode);
								model.addAttribute("bookList", listOfBook.getData());
								Boolean allBooksAvailable = masterService.compareTwoList(listOfBook.getData().getBookVOList(), commonService.getAllData("ISSUEDBOOKLIST-RESERVED").getData());
								model.addAttribute("allBooksAvailable", allBooksAvailable);
								model.addAttribute("Role", user.getPrimaryRole().getRoleCode());
							}
						} catch (Exception e) {
							log.error("Exception occured in reservedBook method in MasterController",e);
						}	
					   return "site.master.reservedBook";
				}	
				
				/** @Author= @SURJYAMANGARAJ **/
				@PostMapping("/save-reservedBook")
				public String savereservedBook(BookDtls bookDtls,RedirectAttributes attr) {
					try {
						 ServiceOutcome<String>  listbook = masterService.savereservedBookData(bookDtls);
							attr.addFlashAttribute(listbook.getOutcome().equals(true) ? "success_msg" : "error_msg",listbook.getMessage());
					} catch (Exception e) {
						log.error("Exception occured in reservedBook method in MasterController",e);
					}
					return "redirect:/mst/reservedBookList";
				}
				
				/** @Author= @SURJYAMANGARAJ **/
				@GetMapping("/reservedBookList")
				public String reservedBookList(Model model) {
					User user=SecurityHelper.getCurrentUser();
					String role=user.getPrimaryRole().getRoleCode();
					  try {
						  if(role.equals("ROLE_LA")) {
						  List<BookIssue> booklist=masterService.getAllReservedBook();
						  model.addAttribute("bookList", booklist);
						  model.addAttribute("Role", role);
						  model.addAttribute("maxAllocationLimit", commonService.getAllData(ApplicationConstants.STATUS_ALLOCATION_LIMIT).getData().get(0));
						  model.addAttribute("maxReserveDay", commonService.getAllData("MAX_RESERVE_DAY").getData().get(0));
						  }
						  if(role.equals("ROLE_MEMBER")) {
							List<BookIssue> bookList=masterService.getallBookIssueList();
							
							model.addAttribute("bookList", bookList);
							 model.addAttribute("Role", role);
						  }
						} catch (Exception e) {
							log.error("Exception occured in reservedBookList method in MasterController",e);
						}	
         					   return "site.master.reservedBookList";
				}
				
				/** @Author= @SURJYAMANGARAJ **/
				@PostMapping("/save-ReservedBookStatus")
				public String saveReservedBookStatus(@RequestParam(name="status", required = false) String status,
							                        @RequestParam(name="bookIssueId", required = false) Long bookIssueId,
							                        @RequestParam(name="returnDate", required = false) String returnDate,
							                        @RequestParam(name="issueDate", required = false) String issueDate, 
							                        RedirectAttributes attr) {
					try {
						ServiceOutcome<String>  bookList = masterService.savereservedBookStatusData(bookIssueId,status, returnDate, issueDate);
						attr.addFlashAttribute(bookList.getOutcome().equals(true) ? "success_msg" : "error_msg",bookList.getMessage());
					} catch (Exception e) {
						log.error("Exception occured in saveReservedBookStatus method in MasterController",e);
					}
					return "redirect:/mst/reservedBookList";
				}
				
				@GetMapping("/bookIssueList")
				public String getBookIssueList(Model model) {
					try {
                  List<BookIssue> issueList=masterService.getallBookIssueList();
                  model.addAttribute("bookIssueList", issueList);
					} catch (Exception e) {
						log.error("Exception occured in getBookIssueList method in MasterController",e);
					}
					return "site.master.bookIssueList";
				}

				@GetMapping("/emailCheck")
				public @ResponseBody Boolean emailCheck(String email) {
				    ServiceOutcome<Boolean> soc = new ServiceOutcome<>();
				    try {
				        soc = commonService.checkUniqueEmail(email);
				    } catch (Exception e) {
				        log.error("Exception occurred in emailCheck() -> CommonController" + e.getMessage());
				    }
				    return soc.getData();
				}
		
		
		
}
