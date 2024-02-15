package com.aashdit.lms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.util.RandomString;
import com.aashdit.lms.VO.BookCatalogImageVO;
import com.aashdit.lms.VO.BookCatalogVO;
import com.aashdit.lms.VO.BookDtls;
import com.aashdit.lms.VO.BookIssueVO;
import com.aashdit.lms.VO.BookVO;
import com.aashdit.lms.VO.LibraryDtls;
import com.aashdit.lms.VO.LibrarySectionVO;
import com.aashdit.lms.VO.LibraryVO;
import com.aashdit.lms.VO.PublisherDtls;
import com.aashdit.lms.VO.PublisherVO;
import com.aashdit.lms.VO.RackDtls;
import com.aashdit.lms.VO.ShelfDtls;
import com.aashdit.lms.VO.ShelfVO;
import com.aashdit.lms.dto.TopRatedBookDto;
import com.aashdit.lms.model.Author;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.model.BookCatalogImage;
import com.aashdit.lms.model.BookFine;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.model.BookIssueHistory;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.Gender;
import com.aashdit.lms.model.Librarian;
import com.aashdit.lms.model.Library;
import com.aashdit.lms.model.LibraryCard;
import com.aashdit.lms.model.LibrarySection;
import com.aashdit.lms.model.Member;
import com.aashdit.lms.model.MemberTypeFine;
import com.aashdit.lms.model.Publisher;
import com.aashdit.lms.model.Rack;
import com.aashdit.lms.model.Shelf;
import com.aashdit.lms.model.SubCategory;
import com.aashdit.lms.repository.AuthorRepository;
import com.aashdit.lms.repository.BookCatalogImageRepository;
import com.aashdit.lms.repository.BookCatalogRepository;
import com.aashdit.lms.repository.BookFineRepository;
import com.aashdit.lms.repository.BookIssueHistoryRepository;
import com.aashdit.lms.repository.BookIssueRepository;
import com.aashdit.lms.repository.BookRepository;
import com.aashdit.lms.repository.CategoryRepository;
import com.aashdit.lms.repository.GenderRepository;
import com.aashdit.lms.repository.LibrarianRepository;
import com.aashdit.lms.repository.LibraryCardHistoryRepository;
import com.aashdit.lms.repository.LibraryCardRepository;
import com.aashdit.lms.repository.LibraryRepository;
import com.aashdit.lms.repository.LibrarySectionRepository;
import com.aashdit.lms.repository.LookupValueRepository;
import com.aashdit.lms.repository.MemberRepository;
import com.aashdit.lms.repository.MemberTypeFineRepository;
import com.aashdit.lms.repository.PublisherRepository;
import com.aashdit.lms.repository.RackRepository;
import com.aashdit.lms.repository.ShelfRepository;
import com.aashdit.lms.repository.SubCategoryRepository;
import com.aashdit.lms.utils.AES;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.lms.utils.CommonUploadFile;
import com.aashdit.lms.utils.QrCodeGenrator;
import com.aashdit.lms.utils.RandomNumberGenerate;
import com.aashdit.umt.model.User;
import com.aashdit.umt.repository.RoleRepository;
import com.aashdit.umt.repository.RoleRightLevelMasterRepository;
import com.aashdit.umt.repository.UserRepository;
import com.aashdit.umt.repository.UserRoleMapRepository;
import com.aashdit.umt.service.AccessService;
import com.aashdit.umt.util.SecurityHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MasterServiceImplementation implements MasterService {
	
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private LibraryRepository libRepository;
	
	@Autowired
	private GenderRepository genderRepository;
	
	@Autowired
	private LibrarySectionRepository libSectionRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private ShelfRepository shelfRepository;
	
	@Autowired
	private RackRepository rackRepository;
	
	@Autowired
	private LibraryCardRepository libraryCardRepository;
	
	@Autowired
	private LibraryCardHistoryRepository libraryCardHistoryRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private UserRoleMapRepository userRoleMapRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRightLevelMasterRepository roleRightLevelMasterRepository;
	
	@Autowired
	private AccessService accessService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookCatalogRepository bookCatalogRepository;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private LibrarianRepository librarianRepo;
	
	@Autowired
	private BookIssueRepository bookIssueRepository;
	
	@Autowired
	private MemberTypeFineRepository memberTypeFineRepository;
	
	@Autowired
	private LookupValueRepository lookupValueRepository;
	
	@Autowired
	private BookCatalogImageRepository bookCatalogImageRepository;
	
	@Autowired
	private BookFineRepository bookFineRepository;
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BookIssueHistoryRepository bookIssueHistoryRepository;
	
	@Value("${UPLOAD.FILE.PATH}")
    private String filePath;
	
	ResourceBundle rb = ResourceBundle.getBundle("application");

	
	@Override
	public ServiceOutcome<LibraryDtls> manageLib(LibraryDtls libraryDtls) {
		ServiceOutcome<LibraryDtls> soc = new ServiceOutcome<LibraryDtls>();
		User user = SecurityHelper.getCurrentUser();
		Random rand = new Random();
		try {
			Library lib = Optional.ofNullable(libraryDtls.getLibraryVO().getLibId())
			        .flatMap(libRepository::findById)
			        .map(existingLib->{
			        	BeanUtils.copyProperties(libraryDtls.getLibraryVO(), existingLib);
			        	existingLib.setLastUpdatedBy(user);
			        	existingLib.setLastUpdatedOn(new Date());
			        	existingLib.setIsActive(true);
			        	return existingLib;
			        }).orElseGet(()->{
			        	Library newLibrary = new Library();
			        	BeanUtils.copyProperties(libraryDtls.getLibraryVO(), newLibrary);
			        	String libCode = "LIB_" + rand.ints(48, 100)
  				        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
  				        .limit(6)
  				        .mapToObj(c -> (char) c)
  				        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
  				        .toString();
			        	newLibrary.setLibCode(libCode);
			        	newLibrary.setLastUpdatedBy(user);
			        	newLibrary.setLastUpdatedOn(new Date());
			        	return newLibrary;
			        });
			lib.setIsActive(true);
			libRepository.save(lib);
			
			soc.setMessage(Optional.ofNullable(libraryDtls.getLibraryVO().getLibId()).isPresent() ? "Library Updated Successfully" : "Library Added Successfully");
			soc.setOutcome(true);
			
		} catch (Exception e) {
			log.error("Exception occured in getting manageLib in MasterServiceImpl-->", e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<LibraryDtls> getLibByCode(String libCode) {
	    ServiceOutcome<LibraryDtls> soc = new ServiceOutcome<>();
	    LibraryDtls libraryDtls = new LibraryDtls();
	    LibraryVO libraryVO = new LibraryVO();
	    try {
	        if (libCode != null) {
	            Optional<Library> libraryOptional = libRepository.findByLibCode(libCode);
	            if (libraryOptional.isPresent()) {
	                Library library = libraryOptional.get();
	                BeanUtils.copyProperties(library, libraryVO);
	                libraryDtls.setLibraryVO(libraryVO);
	                
	                soc.setOutcome(true);
	                soc.setMessage("Library found successfully");
	                soc.setData(libraryDtls);
	            } else {
	                soc.setOutcome(false);
	                soc.setMessage("Library not found for libCode: " + libCode);
	            }
	        } else {
	            soc.setOutcome(false);
	            soc.setMessage("libCode is null");
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred in getLibByCode in MasterServiceImpl-->", e);
	        soc.setOutcome(false);
	        soc.setMessage("Unable to process");
	    }
	    return soc;
	}

	@Override
	public ServiceOutcome<LibraryDtls> manageLibSection(LibraryDtls libraryDtls) {
		ServiceOutcome<LibraryDtls> soc = new ServiceOutcome<>();
	    User user = SecurityHelper.getCurrentUser();
	    Random rand = new Random();
		try {
			
			if(libraryDtls.getLibId().size()>0) {
				
				libraryDtls.getLibId().forEach(libId->{
					List<Long> libSectionIds = libSectionRepository.findAllByLibraryLibIdAndIsActive(libId, true).stream()
					                                                                  .map(LibrarySection::getSecId)
					                                                                  .collect(Collectors.toList());
					List<Long> existinglibSectionIds = new ArrayList<>();
					libraryDtls.getLibrarySectionVOList().forEach(section->{
						if (section.getSecName() == null) {
							return;
						}
						if(libSectionIds.contains(section.getSecId())) {
							Optional<LibrarySection> findBySecId = libSectionRepository.findBySecId(section.getSecId());
							
							if(findBySecId.isPresent()) {
								BeanUtils.copyProperties(section, findBySecId.get());
								findBySecId.get().setLibrary(libRepository.findById(libId).get());
								findBySecId.get().setIsActive(true);
								findBySecId.get().setLastUpdatedBy(user);
								findBySecId.get().setLastUpdatedOn(new Date());
								libSectionRepository.save(findBySecId.get());
								existinglibSectionIds.add(section.getSecId());
							}else {
								LibrarySection newlibSection = new LibrarySection();
								BeanUtils.copyProperties(section, newlibSection);
								String secCode = "SEC_" + rand.ints(48, 100)
							        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
							        .limit(6)
							        .mapToObj(c -> (char) c)
							        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
							        .toString();
								newlibSection.setSecCode(secCode);
								newlibSection.setLibrary(libRepository.findById(libId).get());
								newlibSection.setIsActive(true);
								newlibSection.setCreatedBy(user);
								newlibSection.setCreatedOn(new Date());
								libSectionRepository.save(newlibSection);
							}
							
						}else {
							LibrarySection newlibSection = new LibrarySection();
							BeanUtils.copyProperties(section, newlibSection);
							String secCode = "SEC_" + rand.ints(48, 100)
						        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
						        .limit(6)
						        .mapToObj(c -> (char) c)
						        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
						        .toString();
							newlibSection.setSecCode(secCode);
							newlibSection.setLibrary(libRepository.findById(libId).get());
							newlibSection.setIsActive(true);
							newlibSection.setCreatedBy(user);
							newlibSection.setCreatedOn(new Date());
							libSectionRepository.save(newlibSection);
						}
					});
					
					libSectionIds.removeAll(existinglibSectionIds);
					
					libSectionIds.forEach(ixistingSection->{
						Optional<LibrarySection> findById = libSectionRepository.findById(ixistingSection);
						findById.get().setIsActive(false);
						libSectionRepository.save(findById.get());
					});
				});
			}
			
			/*
			 * libraryDtls.getLibId().forEach(lib->{
			 * libraryDtls.getLibrarySectionVOList().forEach(libSection->{
			 * 
			 * }); });
			 */
			
			soc.setMessage(null);
			
		} catch (Exception e) {
			 log.error("Exception occurred in manageLibSection in MasterServiceImpl-->", e);
		     soc.setOutcome(false);
		     soc.setMessage("Unable to process");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<LibraryDtls> getLibSectionbylibId(Long libId) {
		ServiceOutcome<LibraryDtls> soc = new ServiceOutcome<>();
		LibraryDtls libraryDtls = new LibraryDtls();
		List<LibrarySectionVO> librarySectionVOList = new ArrayList<>();
		try {
			
			if (libId != null) {
	            List<LibrarySection> findByLibId = libSectionRepository.findAllByLibraryLibIdAndIsActive(libId, true);
	            if (findByLibId.size() > 0) {
	            	
	            	findByLibId.forEach(libSection->{
	            		LibrarySectionVO librarySecVO = new LibrarySectionVO();
	            		BeanUtils.copyProperties(libSection, librarySecVO);
	            		librarySectionVOList.add(librarySecVO);
	            	});
	            	
	            	libraryDtls.setLibrarySectionVOList(librarySectionVOList);
	                soc.setOutcome(true);
	                soc.setMessage("LibrarySection  found successfully");
	                soc.setData(libraryDtls);
	            } else {
	                soc.setOutcome(false);
	                soc.setMessage("LibrarySection not found for SectionCode: " + libId);
	            }
	        } else {
	            soc.setOutcome(false);
	            soc.setMessage("SectionCode is null");
	        }
			
		} catch (Exception e) {
			log.error("Exception occurred in getLibSectionbySectionCode in MasterServiceImpl-->", e);
		     soc.setOutcome(false);
		     soc.setMessage("Unable to process");
		}
		return soc;
	}
	

	@Override
	public ServiceOutcome<String> saveCategoryData(Category category) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		try {
			Category categoryData=null;
			if(category.getCategoryId() != null) {
				categoryData= categoryRepository.findById(category.getCategoryId()).get();
				message = "Category Updated Successfully"; 
			}else {
					RandomString rs = new RandomString(8);
					categoryData = new Category();
					categoryData.setCategoryCode(rs.nextString());
					message = "Category Saved Successfully";
				}
			categoryData.setCategoryName(category.getCategoryName());
			categoryData.setCategoryDescription(category.getCategoryDescription());
			if(!category.getCategoryImage().isEmpty()) {
				String imageName = FilenameUtils.getBaseName(category.getCategoryImage().getOriginalFilename());
				categoryData.setCategoryImagePath(CommonUploadFile.upload(category.getCategoryImage(), rb.getString("UPLOAD.FILE.PATH"), "CATEGORY", imageName));
			}
			categoryRepository.save(categoryData);
			srvc.setMessage(message);
		    srvc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in saveCategoryData method in MasterServiceImpl-->", e);
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
		}
		
		return srvc;
	}
	
	@Override
	public ServiceOutcome<String> activeInactiveCategory(Long categoryId, Boolean status) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		boolean flag=true;
		try {
			if(Optional.ofNullable(categoryId).isPresent()) {
				Category category = categoryRepository.findByCategoryId(categoryId);
				if(Optional.ofNullable(category).isPresent()) {
					category.setIsActive(status);
					categoryRepository.save(category);
				}
				if(status == true) {
					message="Category Activated Successfully";
				}else {
					flag=false;
					message="Category De-Activated Successfully";
				}
			}
		} catch (Exception e) {
			log.error("Exception occured in activeInactiveCategory method in MasterServiceImpl-->", e);
			message="Something went wrong please try again";
			flag=false;
		}
		srvc.setOutcome(flag);
		srvc.setMessage(message);
		return srvc;
	}
	
	@Override
	public ServiceOutcome<Category> findCategoryDataByCategoryId(Long categoryId) {
		ServiceOutcome<Category> srvc = new ServiceOutcome<>();
		String message="";
		Boolean flag=true;
		Category category = new Category();
		try {
			if(Optional.ofNullable(categoryId).isPresent()) {
				category = categoryRepository.findByCategoryId(categoryId);
			}else {
				message="Category Not Found";
				flag=false;
			}
			srvc.setData(category);
		} catch (Exception e) {
			log.error("Exception occured in findCategoryDataByCategoryId method in MasterServiceImpl-->", e);
			message="Something went wrong please try again";
			flag=false;	
		}
		srvc.setOutcome(flag);
		srvc.setMessage(message);
		return srvc;
	}

	@Override
	public ServiceOutcome<List<Category>> getCategoryList(boolean b) {
		ServiceOutcome<List<Category>> outcome = new ServiceOutcome<>();
		List<Category> categoryList = new ArrayList<>();
		try {
			if(b) {
				categoryList = categoryRepository.findAllByIsActiveTrueOrderByCategoryId();
			}else {
				categoryList = categoryRepository.findAll();
			}
			outcome.setData(categoryList);
		} catch (Exception e) {
			outcome.setOutcome(false);
			log.error("Exception occured in getCategoryList method in MasterServiceImpl-->", e);
		}
		return outcome;
	}
	

	@Override
	public ServiceOutcome<String> saveSubCategoryData(SubCategory subCategory, Long categoryId, String keywords) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		try {
			SubCategory subCategoryData=null;
			if(subCategory.getSubCategoryId() != null) {
				subCategoryData= subCategoryRepository.findById(subCategory.getSubCategoryId()).get();
				message = "Sub-Category Updated Successfully"; 
			}else {
					RandomString rs = new RandomString(8);
					subCategoryData = new SubCategory();
					subCategoryData.setSubCategoryCode(rs.nextString());
					message = "Sub-Category Saved Successfully";
				}
			subCategoryData.setSubCategoryName(subCategory.getSubCategoryName());
			subCategoryData.setCategory(categoryRepository.findByCategoryId(categoryId));
			subCategoryData.setSubCategoryDescription(subCategory.getSubCategoryDescription());
			subCategoryData.setKeywords(keywords);
			if(!subCategory.getSubCategoryImage().isEmpty()) {
				String imageName = FilenameUtils.getBaseName(subCategory.getSubCategoryImage().getOriginalFilename());
				subCategoryData.setSubCategoryImagePath(CommonUploadFile.upload(subCategory.getSubCategoryImage(), rb.getString("UPLOAD.FILE.PATH"), "SUBCATEGORY", imageName));
			}
			subCategoryData.setKeywords(subCategory.getKeywords());
			subCategoryRepository.save(subCategoryData);
			srvc.setMessage(message);
		    srvc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in saveSubCategoryData method in MasterServiceImpl-->", e);
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
		}
		
		return srvc;
	}
	
	@Override
	public ServiceOutcome<SubCategory> findSubCategoryDataBySubCategoryId(Long subCategoryId) {
		ServiceOutcome<SubCategory> srvc = new ServiceOutcome<>();
		String message="";
		Boolean flag=true;
		SubCategory subCategory = new SubCategory();
		try {
			if(Optional.ofNullable(subCategoryId).isPresent()) {
				subCategory = subCategoryRepository.findBySubCategoryId(subCategoryId);
			}else {
				message="Sub-Category Not Found";
				flag=false;
			}
			srvc.setData(subCategory);
		} catch (Exception e) {
			log.error("Exception occured in findSubCategoryDataBySubCategoryId method in MasterServiceImpl-->", e);
			message="Something went wrong please try again";
			flag=false;	
		}
		srvc.setOutcome(flag);
		srvc.setMessage(message);
		return srvc;
	}
	
	@Override
	public ServiceOutcome<String> activeInactiveSubCategory(Long subCategoryId, Boolean status) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		boolean flag=true;
		try {
			if(Optional.ofNullable(subCategoryId).isPresent()) {
				SubCategory subCategory = subCategoryRepository.findBySubCategoryId(subCategoryId);
				if(Optional.ofNullable(subCategory).isPresent()) {
					subCategory.setIsActive(status);
					subCategoryRepository.save(subCategory);
				}
				if(status == true) {
					message="Sub-Category Activated Successfully";
				}else {
					flag=false;
					message="Sub-Category De-Activated Successfully";
				}
			}
		} catch (Exception e) {
			log.error("Exception occured in activeInactiveSubCategory method in MasterServiceImpl-->", e);
			message="Something went wrong please try again";
			flag=false;
		}
		srvc.setOutcome(flag);
		srvc.setMessage(message);
		return srvc;
	}

	@Override
	public ServiceOutcome<List<SubCategory>> getSubCategoryList(boolean b) {
		ServiceOutcome<List<SubCategory>> outcome = new ServiceOutcome<>();
		List<SubCategory> subCategoryList = new ArrayList<>();
		try {
			if(b) {
				subCategoryList = subCategoryRepository.findAllByIsActiveTrueOrderBySubCategoryId();
			}else {
				subCategoryList = subCategoryRepository.findAll();
			}
			outcome.setData(subCategoryList);
		} catch (Exception e) {
			outcome.setOutcome(false);
			log.error("Exception occured in getSubCategoryList method in MasterServiceImpl-->", e);
		}
		return outcome;
	}

	@Override
	public ServiceOutcome<RackDtls> addAndUpdateRack(RackDtls rackDtls) {
		ServiceOutcome<RackDtls> soc = new ServiceOutcome<RackDtls>();
		Random rand = new Random();
		User user = SecurityHelper.getCurrentUser();
		try {
			
			rackDtls.getRackVOList().forEach(rack->{
				if (rack.getRackName() == null || rack.getRackName() == "") {
					return;
				}
				if(Optional.ofNullable(rack.getRackId()).isPresent()) {
					Optional<Rack> findById = rackRepository.findById(rack.getRackId());
					if(findById.isPresent()) {
						BeanUtils.copyProperties(rack, findById.get(), "isActive");
						findById.get().setLastUpdatedBy(user);
						findById.get().setLastUpdatedOn(new Date());
						rackRepository.save(findById.get());
					}
					soc.setMessage("Rack Updated");
				}else {
					Rack newRack = new Rack();
					BeanUtils.copyProperties(rack, newRack);
					String rackCode = "RACK_" + rand.ints(48, 100)
			        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
			        .limit(6)
			        .mapToObj(c -> (char) c)
			        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
			        .toString();
					newRack.setRackCode(rackCode);
					newRack.setIsActive(true);
					newRack.setCreatedBy(user);
					newRack.setCreatedOn(new Date());
					rackRepository.save(newRack);
					soc.setMessage("Rack Added");
				}
				
				
			});
			
			soc.setOutcome(true);
			
		} catch (Exception e) {
			log.error("Exception occured in addAndUpdateRack method in MasterServiceImpl-->", e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<RackDtls> activeInactiveRack(RackDtls rackDtls) {
		ServiceOutcome<RackDtls> soc = new ServiceOutcome<RackDtls>();
		User user = SecurityHelper.getCurrentUser();
        try {
        	Optional<Rack> findById = rackRepository.findByRackCode(rackDtls.getRackVO().getRackCode());
        	if(findById.isPresent()) {
        		findById.get().setIsActive(rackDtls.getRackVO().getIsActive() ? false : true);
        		findById.get().setLastUpdatedBy(user);
        		findById.get().setLastUpdatedOn(new Date());
        		rackRepository.save(findById.get());
        	}
        	soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in activeInactiveRack method in MasterServiceImpl-->", e);
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}
	
	
	@Override
	public ServiceOutcome<String> saveShelfData(ShelfDtls shelf) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		try {
			Optional<Rack> findById = rackRepository.findById(shelf.getRackId());
			shelf.getShelfVOList().forEach(shelfId ->{
				if (shelfId.getShelfName() == null) {
					return;
				}
				if(shelfId.getShelfId()!= null) {
					Optional<Shelf> findById2 = shelfRepository.findById(shelfId.getShelfId());
					if(findById2.isPresent()) {
						BeanUtils.copyProperties(shelfId, findById2.get());
						findById2.get().setIsActive(true);
						findById2.get().setRack(findById.get());
						shelfRepository.save(findById2.get());
					}
					srvc.setMessage("Shelf Updated Successfully.");
				}
				else {
					Shelf  newShelf = new Shelf();
					BeanUtils.copyProperties(shelfId, newShelf);
					newShelf.setRack(findById.get());
					RandomString rs = new RandomString(8);
					newShelf.setShelfCode(rs.nextString());
					newShelf.setIsActive(true);
					shelfRepository.save(newShelf);
					srvc.setMessage("Shelf Saved Successfully.");
				}
				
			});
		} catch (Exception e) {
			log.error("Exception occured in saveShelfData method in MasterServiceImpl-->", e);
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
		}
		
		return srvc;
	}

	
	@Override
	public ServiceOutcome<ShelfDtls> getAllShelfByRackId(Long rackId) {
		ServiceOutcome<ShelfDtls> srvc = new ServiceOutcome<>();
		ShelfDtls shelfDtls = new ShelfDtls();
		List<ShelfVO> shelfVoList = new ArrayList<>();
		try {
			List<Shelf> findAllRackRackId = shelfRepository.findAllByRackRackId(rackId);
			if(findAllRackRackId.size()>0) {
				findAllRackRackId.forEach(shelf->{
					ShelfVO shelfVO = new ShelfVO();
					BeanUtils.copyProperties(shelf, shelfVO);
					shelfVoList.add(shelfVO);
				});
			}
			shelfDtls.setShelfVOList(shelfVoList);
			srvc.setData(shelfDtls);
		} catch (Exception e) {
			log.error("Exception occured in getAllShelfByRackId method in MasterServiceImpl-->", e);
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
		}
		return srvc;
	}
	
	@Override
	public ServiceOutcome<String> activeInactiveShelf(Long shelfId, Boolean status) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		boolean flag=true;
		try {
			if(Optional.ofNullable(shelfId).isPresent()) {
				Shelf shelf = shelfRepository.findByShelfId(shelfId);
				if(Optional.ofNullable(shelf).isPresent()) {
					shelf.setIsActive(status);
					shelfRepository.save(shelf);
				}
				if(status == true) {
					message="Shelf Activated Successfully";
				}else {
					flag=false;
					message="Shelf De-Activated Successfully";
				}
			}
		} catch (Exception e) {
			log.error("Exception occured in activeInactiveShelf method in MasterServiceImpl-->", e);
			message="Something went wrong please try again";
			flag=false;
		}
		srvc.setOutcome(flag);
		srvc.setMessage(message);
		return srvc;
	}


	@Override
	public ServiceOutcome<PublisherDtls> addAndUpdatePublisher(PublisherDtls publisherDtls) {
		ServiceOutcome<PublisherDtls> soc = new ServiceOutcome<PublisherDtls>();
		PublisherDtls publisherDtl = new PublisherDtls();
		User user = SecurityHelper.getCurrentUser();
		Random rand = new Random();
		try {
//			publisherRepository
			Publisher publisherData = Optional.ofNullable(publisherDtls.getPublisherVO().getPublisherId())
			        .flatMap(publisherRepository::findById)
			        .map(existingPublisher->{
			        	BeanUtils.copyProperties(publisherDtls.getPublisherVO(), existingPublisher);
			        	existingPublisher.setLastUpdatedBy(user);
			        	existingPublisher.setLastUpdatedOn(new Date());
			        	return existingPublisher;
			        }).orElseGet(()->{
			        	Publisher newPublisher = new Publisher();
			        	BeanUtils.copyProperties(publisherDtls.getPublisherVO(), newPublisher);
			        	String pubCode = "PUB_" + rand.ints(48, 100)
  				        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
  				        .limit(6)
  				        .mapToObj(c -> (char) c)
  				        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
  				        .toString();
			        	newPublisher.setPublisherCode(pubCode);
			        	newPublisher.setCreatedBy(user);
			        	newPublisher.setCreatedOn(new Date());
			        	return newPublisher;
			        });
			
			publisherData.setIsActive(true);
			publisherRepository.save(publisherData);
			
			soc.setMessage(Optional.ofNullable(publisherDtls.getPublisherVO().getPublisherId()).isPresent() ? "Publisher Updated Successfully." : "Publisher Added Successfully.");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in addAndUpdatePublisher method in MasterServiceImpl-->", e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<PublisherDtls> getPublisherByPublisherCode(String publisherCode) {
		ServiceOutcome<PublisherDtls> soc = new ServiceOutcome<PublisherDtls>();
		PublisherDtls publisherDtl = new PublisherDtls();
		PublisherVO publisherVO = new PublisherVO();
		try {
			Optional<Publisher> findByPublisherCode = publisherRepository.findByPublisherCode(publisherCode);
			
			if(findByPublisherCode.isPresent()) {
				BeanUtils.copyProperties(findByPublisherCode.get(), publisherVO);
				publisherDtl.setPublisherVO(publisherVO);
			}
			
			soc.setData(publisherDtl);
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in getPublisherByPublisherCode method in MasterServiceImpl-->", e);
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	@Transactional
	public ServiceOutcome<BookDtls> addAndUpdateBook(BookDtls bookDtls) {
		ServiceOutcome<BookDtls> soc = new ServiceOutcome<BookDtls>();
		PublisherDtls publisherDtl = new PublisherDtls();
		User user = SecurityHelper.getCurrentUser();
		Random rand = new Random();
		SimpleDateFormat adjustedFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
 
			BookCatalog bookCatData = Optional.ofNullable(bookDtls.getBookCatalogVO().getBookCatalogId())
			        .flatMap(bookCatalogRepository::findById)
			        .map(existingBookCatalog->{
			        	BeanUtils.copyProperties(bookDtls.getBookCatalogVO(), existingBookCatalog);
			        	bookDtls.setUniqueCode(existingBookCatalog.getBookCatalogCode());
			        	existingBookCatalog.setBookTitles(bookDtls.getBookCatalogVO().getBookTitles().toUpperCase());
			        	existingBookCatalog.setLastUpdatedBy(user);
			        	existingBookCatalog.setLastUpdatedOn(new Date());
			        	return existingBookCatalog;
			        }).orElseGet(()->{
			        	BookCatalog bookcatalog = new BookCatalog();
			        	BeanUtils.copyProperties(bookDtls.getBookCatalogVO(), bookcatalog);
			        	String bookCatagoryCode = "BOOKCAT_" + rand.ints(48, 100)
				        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
				        .limit(6)
				        .mapToObj(c -> (char) c)
				        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				        .toString();
			        	bookcatalog.setBookCatalogCode(bookCatagoryCode);
			        	bookDtls.setUniqueCode(bookCatagoryCode);
			        	bookcatalog.setBookTitles(bookDtls.getBookCatalogVO().getBookTitles().toUpperCase());
			            String qrPath = QrCodeGenrator.generateQRCodeForBookCat(bookCatagoryCode,  rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.BOOKCATQR,"QR" + bookCatagoryCode);
			            bookcatalog.setQrPath(qrPath);
			        	bookcatalog.setCreatedBy(user);
			        	bookcatalog.setCreatedOn(new Date());
			        	return bookcatalog;
			        });
//			RestTemplate restTemplate = new RestTemplate();
//			String imageUrl = "https://" + bookDtls.getBookCatalogVO().getImgPath();
//
//			try {
//			    byte[] imageBytes = restTemplate.getForObject(imageUrl, byte[].class);
//
//			    if (imageBytes != null) {
//			        String[] urlParts = imageUrl.split("/");
//			        String fileName = urlParts[urlParts.length - 1];
//
//			        LocalDate dt = LocalDate.now();
//			        LocalDateTime tm = LocalDateTime.now();
//			        Calendar cal = Calendar.getInstance();
//			        String meridiem = cal.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault());
//			        String currdt = "_" + dt.getMonth() + "_" + tm.getNano() + 1 + "_" + meridiem;
//
//			        String modifiedFileName = currdt + fileName;
//			        Path imagePath = Paths.get(filePath, modifiedFileName);
//			        Path write = Files.write(imagePath, imageBytes);
//
//			        bookCatData.setImgPath(imagePath.toString());
//			    }
//			} catch (IOException e) {
//			    log.error("Exception occurred during image download and storage: " + e.getMessage());
//			}


			bookCatData.setPublishDate(adjustedFormat.parse(bookDtls.getBookCatalogVO().getPublishDate().trim()));
			bookCatData.setPurchaseDate(adjustedFormat.parse(bookDtls.getBookCatalogVO().getPurchaseDate().trim()));
			bookCatData.setAuthor(Optional.ofNullable(bookDtls.getBookCatalogVO().getAuthor().getAuthorId()).isPresent() ? authorRepo.findById(bookDtls.getBookCatalogVO().getAuthor().getAuthorId()).get() : null);
			bookCatData.setSubCategory(Optional.ofNullable(bookDtls.getBookCatalogVO().getSubCategory().getSubCategoryId()).isPresent() ? subCategoryRepository.findById(bookDtls.getBookCatalogVO().getSubCategory().getSubCategoryId()).get() : null);
			bookCatData.setLib(Optional.ofNullable(bookDtls.getBookCatalogVO().getLib().getLibId()).isPresent() ? libRepository.findById(bookDtls.getBookCatalogVO().getLib().getLibId()).get() : null);
			bookCatData.setPublisher(Optional.ofNullable(bookDtls.getBookCatalogVO().getPublisher().getPublisherId()).isPresent() ? publisherRepository.findById(bookDtls.getBookCatalogVO().getPublisher().getPublisherId()).get() : null);
			bookCatData.setLanguage(Optional.ofNullable(bookDtls.getBookCatalogVO().getLanguage().getValueId()).isPresent() ? lookupValueRepository.findById(bookDtls.getBookCatalogVO().getLanguage().getValueId()).get() : null);
			bookCatData.setImgPath(bookDtls.getBookCatalogVO().getBookCatogoryImages().get(0).isEmpty() ? null : CommonUploadFile.upload(bookDtls.getBookCatalogVO().getBookCatogoryImages().get(0), rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.BOOKIMAGE, "DOCS"+bookDtls.getUniqueCode()));
			bookCatData.setIsActive(true);
			BookCatalog saveBookCatalog = bookCatalogRepository.save(bookCatData);
			
			
			List<Long> bookCatalogIds = bookCatalogImageRepository.findByBookCatalogBookCatalogId(saveBookCatalog.getBookCatalogId()).stream().map(BookCatalogImage::getBookCatalogId).collect(Collectors.toList());
			List<Long> existingBookCatalogIds = new ArrayList<>();
			bookDtls.getBookCatalogVO().getBookCatogoryImages().forEach(image->{
				if(!(image.getSize() > 0)) {
					return ;
				}
				try {
					//String upload = CommonUploadFile.upload(image, filePath, "BOOK_CATALOG_IMAGES", "");
					String upload = CommonUploadFile.upload(image, rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.BOOK_CATALOG_IMAGES, "DOCS"+saveBookCatalog.getBookCatalogCode());
					BookCatalogImage bookCatalogImage = new BookCatalogImage();
					bookCatalogImage.setCatalogImgPath(upload);
					bookCatalogImage.setBookCatalog(saveBookCatalog);
					bookCatalogImage.setIsActive(true);
					bookCatalogImage.setCreatedBy(user);
					bookCatalogImage.setCreatedOn(new Date());
					bookCatalogImageRepository.save(bookCatalogImage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			List<Long> bookIds = bookRepository.findByBookCatalogBookCatalogIdAndIsActive(saveBookCatalog.getBookCatalogId(), true).stream().map(Book::getBookId).collect(Collectors.toList());
			List<Long> existingBookIds = new ArrayList<>();
			bookDtls.getBookVOList().forEach(book->{
				if(!Optional.ofNullable(book.getBookUkNo()).isPresent()) {
					return ;
				}
				
				if(Optional.ofNullable(book.getBookId()).isPresent() && bookIds.contains(book.getBookId())) {
					Optional<Book> findById = bookRepository.findById(book.getBookId());
					if(findById.isPresent()) {
						BeanUtils.copyProperties(book, findById.get());
						findById.get().setRack(Optional.ofNullable(book.getRack().getRackId()).isPresent() ? rackRepository.findById(book.getRack().getRackId()).get() : null);
						findById.get().setShelf(Optional.ofNullable(book.getShelf().getShelfId()).isPresent() ? shelfRepository.findById(book.getShelf().getShelfId()).get() : null);
						findById.get().setBookCatalog(saveBookCatalog);
						findById.get().setLastUpdatedBy(user);
						findById.get().setLastUpdatedOn(new Date());
						findById.get().setIsActive(true);
						bookRepository.save(findById.get());
						existingBookIds.add(book.getBookId());
					}
				}else {
					Book newBook = new Book();
					BeanUtils.copyProperties(book, newBook);
					String bookCode = "BOOK_" + rand.ints(48, 100)
			        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
			        .limit(6)
			        .mapToObj(c -> (char) c)
			        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
			        .toString();
					newBook.setBookCode(bookCode);
					newBook.setRack(Optional.ofNullable(book.getRack().getRackId()).isPresent() ? rackRepository.findById(book.getRack().getRackId()).get() : null);
					newBook.setShelf(Optional.ofNullable(book.getShelf().getShelfId()).isPresent() ? shelfRepository.findById(book.getShelf().getShelfId()).get() : null);
					newBook.setBookCatalog(saveBookCatalog);
					newBook.setCreatedBy(user);
					newBook.setCreatedOn(new Date());
					newBook.setIsActive(true);
					bookRepository.save(newBook);
				}
				
			});
			
			bookIds.removeAll(existingBookIds);
			if(existingBookIds.size() > 0) {
				bookIds.forEach(id->{
					Optional<Book> findById = bookRepository.findById(id);
					if(findById.isPresent()) {
						findById.get().setIsActive(false);
						bookRepository.save(findById.get());
					}
				});
			}
			
			soc.setMessage(Optional.ofNullable(bookDtls.getBookCatalogVO().getBookCatalogId()).isPresent() ? "Book Updated Successfully" : "Book Added Successfully");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in addAndUpdateBook method in MasterServiceImpl-->", e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return soc;
	}
	
	@Override
	public ServiceOutcome<List<Gender>> getGenderList() {
		ServiceOutcome<List<Gender>> soc= new ServiceOutcome<>();
		List<Gender> gndr= new ArrayList<>();
		try {
			gndr = genderRepository.findAll();
			soc.setData(gndr);			
		} catch (Exception e) {
			e.printStackTrace();
			soc.setData(null);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Author> saveAuthorDetails(Author author) {
		ServiceOutcome<Author> soc= new ServiceOutcome<>();
		User user= SecurityHelper.getCurrentUser();
		try {
			Author authr= null;
			if(author.getAuthorId()!=null) {
				authr= authorRepo.findById(author.getAuthorId()).get();
				soc.setMessage("Author Details Updated successfully.");
				authr.setLastUpdatedBy(user);
				authr.setLastUpdatedOn(new Date());
			}
			else {
				authr = new Author(); 
				soc.setMessage("Author Details saved successfully.");					
				user.setCreatedBy(user);
				user.setCreatedOn(new Date());
			}
			
			authr.setAuthorName(author.getAuthorName());
			authr.setGender(
				    author.getGenderId() != null ? genderRepository.findByGenderId(author.getGenderId()) :
				        null
				       
				);
			authr.setAddress(author.getAddress());
			authr.setMail(author.getMail());
			authr.setMobileNumber(author.getMobileNumber());
			String random= RandomNumberGenerate.getRandomUniqueCode(8);
			authr.setAuthorCode(random);
			authr.setIsActive(true);
			authorRepo.save(authr);
			soc.setData(authr);
			soc.setOutcome(true);
		} catch (Exception e) {
			e.printStackTrace();
			soc.setData(null);
			soc.setOutcome(false);
			soc.setMessage("could not save data");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Author> editAuthorData(Long authorId) {
		ServiceOutcome<Author> soc= new ServiceOutcome<>();
		try {
			if(authorId!=null) {
			Author author= authorRepo.findById(authorId).get();
			soc.setData(author);
			soc.setOutcome(true);
			soc.setMessage("Success");
		}
		}catch (Exception e) {
			log.error("Exception occured in masterServiceImpl -> in editAuthorData()"+e);
			soc.setData(null);
			soc.setMessage("Error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<List<Author>> getAuthorList() {
		ServiceOutcome<List<Author>> srvc = new ServiceOutcome<>();
		try {
			List<Author> author = new ArrayList<>();
			author = authorRepo.findAll();
			srvc.setData(author);
			srvc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in getAuthorList()  Method in MasterServiceImpl-->", e);				
			srvc.setOutcome(false);
		}
		return srvc;
	}
	
	
	@Override
	public ServiceOutcome<Author> checkActiveInactiveAuthor(Long authorId) {
		ServiceOutcome<Author> soc= new ServiceOutcome<>();	
		Author author=null;
		try {
			author=authorRepo.findById(authorId).get();
			author.setIsActive(author.getIsActive()? false:true);
			authorRepo.save(author);
			soc.setData(author);			
			soc.setOutcome(true);
			soc.setMessage(author.getIsActive() ? "Activated" : "Deactivated");
		} catch (Exception e) {
			log.error("Exception occured in masterServiceImpl-> in activeInactiveBanks()",e);
			soc.setData(null);
			soc.setOutcome(false);
			soc.setMessage("Error");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Librarian> saveLibrarianDetails(Librarian librarian) {
		ServiceOutcome<Librarian> soc= new ServiceOutcome<>();
		User user= SecurityHelper.getCurrentUser();
		try {
			Librarian libran= null;
			if(librarian.getLibrarianId() !=null) {
				libran= librarianRepo.findById(librarian.getLibrarianId()).get();
				soc.setMessage("Librarian Details Updated successfully.");
				libran.setLastUpdatedBy(user);
				libran.setLastUpdatedOn(new Date());
			}
			else {
				libran = new Librarian(); 
				soc.setMessage("Librarian Details saved successfully.");					
				user.setCreatedBy(user);
				user.setCreatedOn(new Date());
			}
			
			libran.setLibrarianName(librarian.getLibrarianName()); 			
			libran.setGender(librarian.getGenderId() != null ? genderRepository.findByGenderId(librarian.getGenderId()) : null);				       				
			libran.setLibrary(librarian.getLibId() != null ? libRepository.findById(librarian.getLibId()).get() :null);
			libran.setLibrarianAddress(librarian.getLibrarianAddress());
			libran.setEmail(librarian.getEmail());
			libran.setMobile(librarian.getMobile());
			String random= RandomNumberGenerate.getRandomUniqueCode(8);
			libran.setLibrarianCode(random);
			libran.setIsActive(true);
			librarianRepo.save(libran);
			soc.setData(libran);
			soc.setOutcome(true);
		} catch (Exception e) {
			e.printStackTrace();
			soc.setData(null);
			soc.setOutcome(false);
			soc.setMessage("could not save data");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<List<Library>> getLibraryList() {
		ServiceOutcome<List<Library>> soc= new ServiceOutcome<>();
		try {
			List<Library> library = new ArrayList<>();			
			library = libRepository.findAll();
			soc.setData(library);
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in getLibraryList()  Method in MasterServiceImpl-->", e);				
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<List<Librarian>> getLibrarianDetailsList() {
		ServiceOutcome<List<Librarian>> srvc = new ServiceOutcome<>();
		try {
			List<Librarian> librn = new ArrayList<>();
			librn = librarianRepo.findAll();
			srvc.setData(librn);
			srvc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in getLibrarianDetailsList()  Method in MasterServiceImpl-->", e);				
			srvc.setOutcome(false);
		}
		return srvc;
	}

	@Override
	public ServiceOutcome<Librarian> editLibrarianData(Long librarianId) {
		ServiceOutcome<Librarian> soc= new ServiceOutcome<>();
		try {
			if(librarianId!=null) {
			Librarian librian= librarianRepo.findById(librarianId).get();
			soc.setData(librian);
			soc.setOutcome(true);
			soc.setMessage("Success");
		} 
		}catch (Exception e) {
			log.error("Exception occured in masterServiceImpl -> in editLibrarianData()"+e);
			soc.setData(null);
			soc.setMessage("Error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Librarian> checkActiveInactiveLibrarian(Long librarianId) {
		ServiceOutcome<Librarian> soc= new ServiceOutcome<>();	
		Librarian librarian=null;
		try {
			librarian=librarianRepo.findById(librarianId).get();
			librarian.setIsActive(librarian.getIsActive()? false:true);
			librarianRepo.save(librarian);
			soc.setData(librarian);			
			soc.setOutcome(true);
			soc.setMessage(librarian.getIsActive() ? "Activated" : "Deactivated");
		} catch (Exception e) {
			log.error("Exception occured in masterServiceImpl-> in activeInactiveBanks()",e);
			soc.setData(null);
			soc.setOutcome(false);
			soc.setMessage("Error");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<BookDtls> getBookCatalogByCatalogCode(String catalogCode) {
		ServiceOutcome<BookDtls> soc = new ServiceOutcome<BookDtls>();
		BookDtls bookDtls = new BookDtls();
		BookCatalogVO bookCatalogVO = new BookCatalogVO();
		List<BookCatalogImageVO> bookCatalogImageVOList = new ArrayList<>();
		List<BookVO> bookVOList = new ArrayList<>();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			Optional<BookCatalog> findByBookCatalogCode = bookCatalogRepository.findByBookCatalogCode(catalogCode);
			
			if(findByBookCatalogCode.isPresent()) {
				BeanUtils.copyProperties(findByBookCatalogCode.get(), bookCatalogVO);
				Date publishDate = dateTimeFormat.parse(findByBookCatalogCode.get().getPublishDate().toString());
				Date purchaseDate = dateTimeFormat.parse(findByBookCatalogCode.get().getPurchaseDate().toString());
				bookCatalogVO.setPublishDate(dateFormat.format(publishDate));
				bookCatalogVO.setPurchaseDate(dateFormat.format(purchaseDate));
				bookDtls.setBookCatalogVO(bookCatalogVO);
				List<Book> findByBookCatalogId = bookRepository.findByBookCatalogBookCatalogIdAndIsActive(findByBookCatalogCode.get().getBookCatalogId(), true);

				if(findByBookCatalogId.size() > 0) {
					findByBookCatalogId.forEach(existingBook->{
						BookVO existBookVO = new BookVO();
						BeanUtils.copyProperties(existingBook, existBookVO);
						bookVOList.add(existBookVO);
					});
				}
			}
			List<BookCatalogImage> findByBookCatalogBookCatalogId = bookCatalogImageRepository.findByBookCatalogBookCatalogId(findByBookCatalogCode.get().getBookCatalogId());
			if(findByBookCatalogBookCatalogId.size() > 0) {
				findByBookCatalogBookCatalogId.forEach(viewImage->{
					BookCatalogImageVO bookCatalogImageVO = new BookCatalogImageVO();
					BeanUtils.copyProperties(viewImage, bookCatalogImageVO);
					String imagePath = viewImage.getCatalogImgPath();
					if(Optional.ofNullable(imagePath).isPresent()){
						String path = rb.getString("UPLOAD.FILE.PATH")+File.separator+ApplicationConstants.BOOK_CATALOG_IMAGES+File.separator+imagePath;
					if(!path.isEmpty()) {
						File file = new File(path);
						byte[] bytes;
						try {
							bytes = Files.readAllBytes(file.toPath());
							String encoded = Base64.getEncoder().encodeToString(bytes);
							bookCatalogImageVO.setEncodeImagePath(encoded);
						} catch (IOException e) {
							//e.printStackTrace();
						}
					}
				}
					bookCatalogImageVOList.add(bookCatalogImageVO);
				});
				
			}
			bookDtls.setBookCatalogVO(bookCatalogVO);
			bookDtls.setBookCatalogImageVO(bookCatalogImageVOList);
			bookDtls.setBookVOList(bookVOList);
			
			soc.setData(bookDtls);
			soc.setMessage("Book Details fetch Succesfully");
			soc.setOutcome(true);
			
		} catch (Exception e) {
			log.error("Exception occured in getBookCatalogByCatalogCode method in MasterServiceImpl-->", e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<String> imgPathByCode(String catalogCode, String identity) {
		ServiceOutcome<String> soc = new ServiceOutcome();
		try {
			
			switch(identity) {
			 case "BOOKCATALOG":
				 Optional<BookCatalog> findById = bookCatalogRepository.findByBookCatalogCode(catalogCode);
					if(findById.isPresent()) {
						soc.setData(findById.get().getImgPath());
					}
				 break;
			 case "BOOKCATALOGIMAGE":
				 Optional<BookCatalogImage> findById2 = bookCatalogImageRepository.findById(Long.parseLong(catalogCode));
				 if(findById2.isPresent())
				 break;
			    
			  default:
			    // code block
			}
			
			
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in imgPathByCode method in MasterServiceImpl-->", e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<BookDtls> getBookIssuedDtlsByIssuedId(Long bookIssuedId) {
		ServiceOutcome<BookDtls> soc = new ServiceOutcome<BookDtls>();
		BookDtls bookDtls = new BookDtls();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			Optional<BookIssue> findById = bookIssueRepository.findById(bookIssuedId);
			if(findById.isPresent()) {
				BookIssueVO bookIssueVO = new BookIssueVO();
				BeanUtils.copyProperties(findById.get(), bookIssueVO);
				if(Optional.ofNullable(findById.get().getIssuedDate()).isPresent()) {
					Date issuedDate = dateTimeFormat.parse(findById.get().getIssuedDate().toString());
					bookIssueVO.setIssuedDate(dateFormat.format(issuedDate));
				}
				if(Optional.ofNullable(findById.get().getReturnDate()).isPresent()) {
					Date returnDate = dateTimeFormat.parse(findById.get().getReturnDate().toString());
					bookIssueVO.setReturnDate(dateFormat.format(returnDate));
				}
				
				bookDtls.setBookIssueVO(bookIssueVO);
				MemberTypeFine findByMemberTypeId = memberTypeFineRepository.findByMemberTypeId(findById.get().getLibraryCard().getMember().getMemberTypeId());
				bookDtls.setFineAmount(findByMemberTypeId.getFineAmount());
			}
			soc.setData(bookDtls);
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in getBookIssuedDtlsByIssuedId method in MasterServiceImpl-->", e);
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	@Transactional
	public ServiceOutcome<BookDtls> returnBook(BookDtls bookdtls) {
		ServiceOutcome<BookDtls> soc = new ServiceOutcome<BookDtls>();
		try {
			Optional<BookIssue> findById = bookIssueRepository.findById(bookdtls.getBookIssueVO().getBookIssuedId());
			if(findById.isPresent()) {
				findById.get().setActualReturnDate(new Date());
				findById.get().setBookCondition(bookdtls.getBookIssueVO().getBookCondition());
				
				if(!bookdtls.getBookIssueVO().getBookCondition().equalsIgnoreCase("GOOD")) {
					if(bookdtls.getBookIssueVO().getDamageOrLostImg() != null) {
						String upload = CommonUploadFile.upload(bookdtls.getBookIssueVO().getDamageOrLostImg(), rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.BOOK_ISSUE_IMAGES, "DOCS"+findById.get().getBookIssuedId());
						findById.get().setDamageOrLostImg(upload);
					}
					findById.get().setDamageOrLostFine(bookdtls.getBookIssueVO().getDamageOrLostFine());
				}else {
					findById.get().setDamageOrLostImg(null);
					findById.get().setDamageOrLostFine(null);
				}
				
				if(bookdtls.getBookIssueVO().getIsBookReturnLate()) {
					findById.get().setFineAmount(bookdtls.getBookIssueVO().getFineAmount());
				}else {
					findById.get().setFineAmount(null);
				}
				findById.get().setStatus(ApplicationConstants.STATUS_RETURN);
				findById.get().setIsActive(false);
				BookIssue saveBookIssue = bookIssueRepository.save(findById.get());
				
				if(bookdtls.getBookIssueVO().getIsBookReturnLate()) {
					BookFine bookFine = new BookFine();
					bookFine.setBookIssuedId(saveBookIssue);
					bookFine.setFineAmount(saveBookIssue.getFineAmount());
					bookFine.setIsActive(true);
//					bookFine.setStatus();
					bookFineRepository.save(bookFine);
				}
				
				// After Book return status null.
				Book findByBookId = bookRepository.findByBookId(findById.get().getBook().getBookId());
				findByBookId.setStatus(null);
				bookRepository.save(findByBookId);
				
			}
			soc.setMessage("success");
			soc.setOutcome(true);
			
		} catch (Exception e) {
			log.error("Exception occured in returnBook method in MasterServiceImpl-->", e);
			soc.setMessage("Unable to Process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	@Transactional
	public ServiceOutcome<BookDtls> reIssueBook(BookDtls bookdtls) {
		
		ServiceOutcome<BookDtls> soc = new ServiceOutcome<BookDtls>();
		SimpleDateFormat adjustedFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Optional<BookIssue> findById = bookIssueRepository.findById(bookdtls.getBookIssueVO().getBookIssuedId());
			if(findById.isPresent()) {
				
				findById.get().setStatus("RE-ISSUE");
				findById.get().setIsActive(false);
				BookIssue saveBookIssue = bookIssueRepository.save(findById.get());
				
				BookIssue bookIssue = new BookIssue();
				BeanUtils.copyProperties(saveBookIssue, bookIssue);
				bookIssue.setBookIssuedId(null);
				bookIssue.setReturnDate(adjustedFormat.parse(bookdtls.getBookIssueVO().getReIssuedate().trim()));
				bookIssue.setStatus("APPROVED");
				bookIssue.setParntBookIssue(saveBookIssue);
				bookIssue.setIsActive(true);
				bookIssueRepository.save(bookIssue);
				
				
				Book findByBookId = bookRepository.findByBookId(findById.get().getBook().getBookId());
				findByBookId.setStatus("APPROVED");
				bookRepository.save(findByBookId);
				
			}
			soc.setMessage("success");
			soc.setOutcome(true);
			
		} catch (Exception e) {
			log.error("Exception occured in returnBook method in MasterServiceImpl-->", e);
			soc.setMessage("Unable to Process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<String> savereservedBookData(BookDtls bookDtls) {
		User user= SecurityHelper.getCurrentUser();
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		ServiceOutcome<List<Member>> outcome= accessService.getByRoleLevel(user.getUserId(), user.getPrimaryRole().getRoleId(), "MEMBER", Member.class);
	
		Long memberId=null;
		if(outcome.getData().size()>0) {
			memberId=outcome.getData().get(0).getMemberId();
		}
		LibraryCard lib=libraryCardRepository.findByMemberMemberId(memberId);
		String message="";   
		try {
			if(lib.getValidDate().after(new Date())) {
			Book bookByBookCatalogId = bookRepository.getBookByBookCatalogId(bookDtls.getBookCatalogVO().getBookCatalogId());
			bookByBookCatalogId.setStatus("RESERVED");
			BookIssue bookIssueData=new BookIssue();
			bookIssueData.setIsActive(true);
			bookIssueData.setStatus("RESERVED");
			bookIssueData.setLibraryCard(libraryCardRepository.findByMemberMemberId(memberId)); 
			bookIssueData.setBook(bookByBookCatalogId);
			bookIssueData.setCreatedOn(new Date());
			bookIssueData.setCreatedBy(user);
			
			
			String qrPath = QrCodeGenrator.generateQRCode(lib.getMember().getMemberCode(),  rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.QRFORRESVERING,"QR" +lib.getMember().getMemberCode());
			bookIssueData.setBarchodePath(qrPath);
			message="Book Reserved Successfully";
			bookIssueRepository.save(bookIssueData);
			bookRepository.save(bookByBookCatalogId);
			
			 BookIssueHistory bookhis= new BookIssueHistory();
				BeanUtils.copyProperties(bookIssueData, bookhis);
				bookhis.setBookIssue(bookIssueData);
				bookhis.setLastUpdatedBy(user);
				bookhis.setCreatedOn(new Date());
				bookhis.setLastUpdatedBy(user);
				bookhis.setCreatedBy(user);
				bookIssueHistoryRepository.save(bookhis);
				
			srvc.setMessage(message);
		    srvc.setOutcome(true);
			} else {
				message="Your Library Card has been Expired.";	
				srvc.setMessage(message);
			    srvc.setOutcome(false);
			}
			
		} catch (Exception e) {
			log.error("Exception occured in savereservedBookData method in MasterServiceImpl-->", e);
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
		}
		
		return srvc;
	}

	@Override
	public List<BookIssue> getAllReservedBook() {
		List<BookIssue> bookList=bookIssueRepository.findAllByStatus("RESERVED");
		return bookList;
	}
	
	public List<BookIssue> getMemberBookList() {
//		List<BookIssue> bookList=bookIssueRepository.getAllByMemberStatus();
		List<String> statuses = Arrays.asList("REJECTED", "APPROVED");

		List<BookIssue> bookIssues = bookIssueRepository.findByStatusInAndIsActive(statuses, true);

		return bookIssues;
	}

	@Override
	public ServiceOutcome<String> savereservedBookStatusData(Long bookIssueId, String status, String returnDate, String issueDate) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		User user= SecurityHelper.getCurrentUser();
		String message="";
		SimpleDateFormat adjustedFormat = new SimpleDateFormat("dd/MM/yyyy");
		try { 
			
			BookIssue bookIssue = bookIssueRepository.findByBookIssuedId(bookIssueId).get();
			if(status.equals("APPROVED")) {
			bookIssue.setReturnDate(adjustedFormat.parse(returnDate.trim()));
			bookIssue.setIssuedDate(adjustedFormat.parse(issueDate.trim()));
			}
			bookIssue.setStatus(status);
			bookIssue.setIssuedDate(status.equals("APPROVED") ? new Date() : null);
			String memberCode = bookIssue.getLibraryCard().getMember().getMemberCode();
//			String qrPath = QrCodeGenrator.generateQRCodeGenerateForBooking( memberCode,  rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.QRBOOKING,"QR" + memberCode,bookIssue.getBook().getBookUkNo());
//			bookIssue.setBarchodePath(qrPath);
			message="Book "+status+" Successfully";
			bookIssueRepository.save(bookIssue);
			
			Book bookByBookCatalogId = bookRepository.findByBookId(bookIssue.getBook().getBookId());
			bookByBookCatalogId.setStatus("APPROVED");
			bookRepository.save(bookByBookCatalogId);
			 BookIssueHistory bookhis= new BookIssueHistory();
				BeanUtils.copyProperties(bookIssue, bookhis);
				bookhis.setBookIssue(bookIssue);
				bookhis.setLastUpdatedBy(user);
				bookhis.setCreatedOn(new Date());
				bookhis.setLastUpdatedBy(user);
				bookhis.setCreatedBy(user);
				bookIssueHistoryRepository.save(bookhis);
			if(status.equals("APPROVED")) {
			srvc.setMessage(message);
		    srvc.setOutcome(true);
			}
			if(status.equals("REJECTED")){
				srvc.setMessage(message);
			    srvc.setOutcome(true);	
			}
		} catch (Exception e) {
			log.error("Exception occured in savereservedBookData method in MasterServiceImpl-->", e);
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
		}
		return srvc;
	}

	
	@Override
	public ServiceOutcome<List<TopRatedBookDto>> getTopRatedBookByDate() {
		ServiceOutcome<List<TopRatedBookDto>> serviceOutcome=new ServiceOutcome<>();
		try {
			StringBuilder query = new StringBuilder();
			query.append("select\n");
			query.append("tlmb.book_id as bookId,\n");
			query.append("tlmb.book_uk_no as bookUkNo,\n");
			query.append("tlmbc.description as description,\n");
			query.append("tlma.author_id as authorId,\n");
			query.append("tlma.author_name as authorName ,\n");
			query.append("tlbi.status as status,\n");
			query.append("tlbi.book_issued_id as bookIssuedId,\n");
			query.append("tlbi.issued_date as issuedDate,\n");
			query.append("tlmbc.img_path as imgPath\n");
			query.append("from\n");
			query.append("lms.public.t_lms_book_issued tlbi\n");
			query.append("join lms.public.t_lms_mst_book tlmb on\n");
			query.append("tlmb.book_id = tlbi.book_id\n");
			query.append("join lms.public.t_lms_mst_book_catalog tlmbc on\n");
			query.append("tlmbc.book_catalog_id = tlmb.book_catalog_id\n");
			query.append("join lms.public.t_lms_mst_author tlma on\n");
			query.append("tlma.author_id = tlmbc.author_id\n");
			query.append("where\n");
			query.append("tlbi.status = 'APPROVE' or tlbi.status='APPROVED'\n");
			query.append("and tlbi.issued_date >= CURRENT_DATE - interval '1 month';\n");

			List<TopRatedBookDto> resultList = jdbcTemplate.query(query.toString(),
					new BeanPropertyRowMapper<>(TopRatedBookDto.class));
			
			if(resultList.size()>0) {
				resultList=resultList.stream().filter(Objects::nonNull).map(data -> {
                if (data.getImgPath() != null) {
                    File file = new File(data.getImgPath());

                    // Check if the file path exists
                    if (file.exists()) {
                        // Check if the file itself exists
                        if (file.isFile()) {
                            try {
                             File img = new File(file.getPath());
             FileInputStream fileInputStream = new FileInputStream(img);
             byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
                                data.setImage(imageBytes);
                            } catch (IOException e) {
                                // Handle the exception if conversion fails
                            }
                        }
                    }
                }
                return data;
				}).collect(Collectors.toList());
			}
			
			
				serviceOutcome.setData(resultList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(this.messageSource.getMessage("msg.error", null,LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<BookDtls> getBookListByBookCatagoryCode(String catalogCode) {
		ServiceOutcome<BookDtls> soc = new ServiceOutcome<BookDtls>();
		BookDtls bookDtls = new BookDtls();
		List<BookVO> bookVOList = new ArrayList<>();
		try {
			Long bookCatalogId = bookCatalogRepository.findByBookCatalogCode(catalogCode).get().getBookCatalogId();
			List<Book> findAllByBookCatalogBookCatalogIdAndIsActive = bookRepository.findAllByBookCatalogBookCatalogIdAndIsActive(bookCatalogId, true);
			findAllByBookCatalogBookCatalogIdAndIsActive.forEach(book->{
				BookVO bookVO = new BookVO();
				BeanUtils.copyProperties(book, bookVO);
				bookVOList.add(bookVO);
			});
			bookDtls.setBookVOList(bookVOList);
			
			soc.setData(bookDtls);
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occured in getBookListByBookCatagoryCode method in MasterServiceImpl-->", e);
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public Boolean compareTwoList(List<BookVO> bookVOList, List<?> data) {
//		ServiceOutcome<T> soc = new ServiceOutcome<>();
		boolean allBooksAvailable = false ;
		try {
			List<BookIssue> bookIssueList = new ArrayList<>();
			for (Object item : data) {
			    if (item instanceof BookIssue) {
			        bookIssueList.add((BookIssue) item);
			    }
			}
			List<Long> bookIssueIDList = bookIssueList.stream()
			        .filter(bookIssue -> bookIssue.getBook() != null) // Filter out null values
			        .map(bookIssue -> bookIssue.getBook().getBookId())
			        .collect(Collectors.toList());

			List<Long> bookIdList =
					bookVOList.stream().map(BookVO::getBookId).collect(Collectors.toList());
			
			allBooksAvailable = bookIssueIDList.containsAll(bookIdList);
			/*
			 * System.out.println("BOOKISSUE"+bookIssueIDList);
			 * 
			 * System.out.println("BOOKID"+bookIdList);
			 * 
			 * notMatchIds = new ArrayList<>(bookIssueIDList); // Find elements that are in
			 * both lists and remove them from notMatchIds
			 * notMatchIds.retainAll(bookIdList); // Remove the elements that match from
			 * bookIssueIDList bookIssueIDList.removeAll(notMatchIds); // Remove the
			 * elements that match from bookIdList bookIdList.removeAll(notMatchIds);
			 * System.out.println("FINAL"+notMatchIds);
			 */
			
		} catch (Exception e) {
			log.error("Exception occured in getBookListByBookCatagoryCode method in MasterServiceImpl-->", e);
//			soc.setMessage("error");
//			soc.setOutcome(false);
		}
		return allBooksAvailable;
	}

	@Override
	public List<Book> getBooksByCatalohId(Long bookCatalogId) {
		List<Book> books = null;
		try {
//			bookCatalogRepository.findById(bookCatalogId);
			books = bookRepository.getAllBookByBookCatalogId(bookCatalogId);
			
		} catch (Exception e) {
			log.error("Exception occured in getBooksByCatalohId method in MasterServiceImpl-->", e);
		}
		
		return books;
	}

	@Override
	public List<BookIssue> getallBookIssueList() {
		User user= SecurityHelper.getCurrentUser();
		ServiceOutcome<List<Member>> outcome= accessService.getByRoleLevel(user.getUserId(), user.getPrimaryRole().getRoleId(), "MEMBER", Member.class);
		Long memberId=null;
		if(outcome.getData().size()>0) {
			memberId=outcome.getData().get(0).getMemberId();
		}
		LibraryCard lib=libraryCardRepository.findByMemberMemberId(memberId);
		List<BookIssue> bookIssueList=bookIssueRepository.getAllByLibraryCard(lib.getLib_cardId());
		return bookIssueList;
	}

	@Override
	public ServiceOutcome<PublisherDtls> saveEncData(String encData) {
		
		try {
			
//			String decryptedData = AES.decrypt(encData, ApplicationConstants.AESKEY);

	        // Convert the decrypted data to a DTO
	        Class<?> convert = convert(decryptedData);
	        ObjectMapper objectMapper = new ObjectMapper();
	        Publisher readValue = objectMapper.readValue(decryptedData, Publisher.class);
	        publisherRepository.save(readValue);
			
		} catch (Exception e) {
			log.error("Exception occured in saveEncData method in MasterServiceImpl-->", e);
		}
		
		return null;
	}
	
	 private String decryptData(String encryptedData, String key) {
	        CryptoJS.AES.decrypt(encryptedData, key);
	        return decrypted.toString(CryptoJS.enc.Utf8);
	    }

	public static Class<?> convert(String decryptedData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Class<?>> typeReference = new TypeReference<Class<?>>() {};

        return objectMapper.readValue(decryptedData, typeReference);
    }
	
  	
}
