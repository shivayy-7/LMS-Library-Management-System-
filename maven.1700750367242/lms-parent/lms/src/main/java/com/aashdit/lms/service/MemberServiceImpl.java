package com.aashdit.lms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.VO.BookCatalogDTO;
import com.aashdit.lms.dto.ApproveDto;
import com.aashdit.lms.dto.LanguageDto;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.LibraryCard;
import com.aashdit.lms.model.LibraryCardHistory;
import com.aashdit.lms.model.Member;
import com.aashdit.lms.model.SubCategory;
import com.aashdit.lms.repository.BookCatalogRepository;
import com.aashdit.lms.repository.GenderRepository;
import com.aashdit.lms.repository.LibraryCardHistoryRepository;
import com.aashdit.lms.repository.LibraryCardRepository;
import com.aashdit.lms.repository.MemberRepository;
import com.aashdit.lms.repository.SubCategoryRepository;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.lms.utils.CommonUploadFile;
import com.aashdit.lms.utils.QrCodeGenrator;
import com.aashdit.lms.utils.RandomNumberGenerate;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.RoleRightLevelMaster;
import com.aashdit.umt.model.User;
import com.aashdit.umt.model.UserRoleMap;
import com.aashdit.umt.repository.RoleRepository;
import com.aashdit.umt.repository.RoleRightLevelMasterRepository;
import com.aashdit.umt.repository.UserRepository;
import com.aashdit.umt.repository.UserRoleMapRepository;
import com.aashdit.umt.service.AccessService;
import com.aashdit.umt.util.SecurityHelper;
import com.ibm.icu.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Autowired
	private GenderRepository genderRepository;
	@Autowired
	private LibraryCardRepository libraryCardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleMapRepository userRoleMapRepository;
	
	@Autowired
	private RoleRightLevelMasterRepository roleRightLevelMasterRepository;
	
	@Autowired
	private AccessService accessService;
	
	@Autowired
	private LibraryCardHistoryRepository libraryCardHistoryRepository;
	
	@Autowired
	private BookCatalogRepository bookCatalogRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private MailService mailService;
	
	ResourceBundle rb = ResourceBundle.getBundle("application");

	@Override
	public ServiceOutcome<String> saveMember(Member mamber) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		try {
			Member maberdata = new Member();
			if(mamber.getMemberId()!=null) {
				maberdata=memberRepository.findByMemberId(mamber.getMemberId());
				maberdata.setLastupdatedOn(new Date());
				maberdata.setLastUpdatedBy("user_la");
				message = "Member Updated Successfully";
			}else {		
				String membercode= RandomNumberGenerate.getRandomUniqueCode(8);				
				maberdata.setMemberCode(membercode);
				maberdata.setCreatedOn(new Date());
				maberdata.setCreatedBy("user_MEMBER");
				maberdata.setLastupdatedOn(new Date());
				maberdata.setLastUpdatedBy("user_MEMBER");
					//maberdata.setQrPath(qrPath);
				message = "Member Saved Successfully";
			}
			maberdata.setMemberName(mamber.getMemberName());
			maberdata.setAddress(mamber.getAddress());
			maberdata.setEmailId(mamber.getEmailId());
			maberdata.setPhoneNo(mamber.getPhoneNo());
			maberdata.setMemberTypeId(mamber.getMemberTypeId());	
			maberdata.setDob(mamber.getDob());
			maberdata.setStatus("PENDING");
			maberdata.setAadharNo(mamber.getAadharNo());
			maberdata.setGender(Optional.ofNullable(mamber.getGender().getGenderId()).isPresent() ? genderRepository.findByGenderId(mamber.getGender().getGenderId()) : null);
			if(!mamber.getAddAttachment().isEmpty()) {
				String path=CommonUploadFile.upload(mamber.getAddAttachment(), rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.DocMember, "DOCS"+mamber.getMemberName());
				maberdata.setImagePath(path);
			}
			
			maberdata.setIsActive(true);
			maberdata=memberRepository.save(maberdata);
			
			srvc.setMessage(message);
		    srvc.setOutcome(true);
		
			    
		    
		} catch (Exception e) {
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
			log.error("Exception occured in saveMember in MemberServiceImpl-->", e);
		}
		return srvc;
	}

	@Override
	public ServiceOutcome<Member> getMemeberListByMemberCode(String mamberCode) {
		ServiceOutcome<Member> srvc = new ServiceOutcome<>();
		String message="";
		Boolean flag=true;
		Member mamber = new Member();
		try {		
			if(Optional.ofNullable(mamberCode).isPresent()) {
				mamber = memberRepository.findByMemberCode(mamberCode);
				}else {
					message="Mamber Not Found";
					flag=false;
				}	
		} catch (Exception e) {
			message="Something went wrong please try again";
			flag=false;
			log.error("Exception occured in getMemeberListByMemberCode method in MemberServiceImpl-->", e);
		}
		srvc.setData(mamber);
		srvc.setMessage(message);
		srvc.setOutcome(flag);
		return srvc;
	}

	@Override
	public ServiceOutcome<List<Member>> getMemberList(boolean b) {
		ServiceOutcome<List<Member>> outcome = new ServiceOutcome<>();
		List<Member> memberList = new ArrayList<>();
		try {
//			if(b) {
				memberList = memberRepository.findAllByIsActiveTrueOrderByMemberId();
				for (Member member : memberList) {
					ServiceOutcome<LibraryCard> memaber2= this.getValidDateBymemberId(member.getMemberId());
					if(memaber2.getOutcome()){
			    		Date todayDate = new Date();
			    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
			    		String formattedDate = sdf.format(todayDate);

			    		Date date = sdf.parse(formattedDate);
			    		Date validDate = memaber2.getData().getValidDate();
			    		if (validDate != null) {
			    		    SimpleDateFormat sdfValidDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
			    		    String formattedValidDate = sdfValidDate.format(validDate);
			    		    Date formattedDateValid = sdfValidDate.parse(formattedValidDate);
			    		    
			    		    if (formattedDateValid.before(date)) {
			    		    	  member.setIsValid(true);
			    		    } else {
			    		        // Do something else if the dates are not equal
			    		    }
			    		} else {
			    		    // Handle the case where validDate is null
			    		}

			    	}
				    memberRepository.save(member);
				}
//			}else {
//				memberList = memberRepository.findAll();
//				
//			}
			outcome.setData(memberList);
		} catch (Exception e) {
			outcome.setOutcome(false);
			log.error("Exception occured in getting getMemberList in MemberServiceImpl-->", e);
		}
		return outcome;
	}

	@Override
	public ServiceOutcome<LibraryCard> getMemebeByMemberCodeFOrApproval(ApproveDto approveDto) {
		 ServiceOutcome<LibraryCard> srvc = new ServiceOutcome<>();
		    String message = "";
		    Boolean flag = true;
		    LibraryCard cardData = new LibraryCard();

		    try {
		        User user = SecurityHelper.getCurrentUser();

		        // Check if mamberCode is present
		        if (Optional.ofNullable(approveDto.getMamberCode()).isPresent()) {
		            Member mamber = memberRepository.findByMemberCode(approveDto.getMamberCode());
		            mamber.setStatus("APPROVED");
		            memberRepository.save(mamber);
		            // Set cardData attributes
		            cardData.setMember(memberRepository.findByMemberId(mamber.getMemberId()));
		            cardData.setCreatedBy(user);
		            cardData.setCreatedOn(new Date());
		            String qrPath = QrCodeGenrator.generateQRCode( mamber.getMemberCode(),  rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.BOOKCATQR,"QR" + mamber.getMemberCode());
		            cardData.setBarcode(qrPath);
		            cardData.setIsActive(true);
		            cardData.setStatus("APPROVE");
		            cardData.setIssueDate(new Date());
		            cardData.setValidDate(approveDto.getValiDate());
		            String approvNo= RandomNumberGenerate.getUniqueLibraryCardNo(mamber.getMemberCode());	
		            cardData.setApproveNo(approvNo);
		            libraryCardRepository.save(cardData);

		            // Check if existUser is null
		            User existUser = userRepository.findByUserName("ME_" + mamber.getMemberId());
		            if (existUser == null) {
		                createUser(user, mamber);
		            }

		            createLibraryCardHistory(user, mamber, cardData);
		        } else {
		            message = "Member Not Found";
		            flag = false;
		        }
		    } catch (Exception e) {
		        message = "Something went wrong, please try again";
		        flag = false;
		        log.error("Exception occurred in getMemeberListByMemberCode method in MemberServiceImpl-->", e);
		    }

		    srvc.setData(cardData);
		    srvc.setMessage(message);
		    srvc.setOutcome(flag);

		    return srvc;
	}
	
	// Create user and associated roles
		private void createUser(User currentUser, Member mamber) {
		    User userDtls = new User();
		    String password = "123456";
		    userDtls.setFirstName(mamber.getMemberName());
		    userDtls.setLastName("NA");
		    userDtls.setUserName("ME_" + mamber.getMemberId());
		    userDtls.setDateOfBirth(null);
		    userDtls.setMobile(mamber.getPhoneNo().toString());
		    userDtls.setEmail(mamber.getEmailId());
		    userDtls.setPassword(bCryptPasswordEncoder.encode(password));
		    userDtls.setDesignation("ROLE_MEMBER");
		    userDtls.setIsActive(true);
		    userDtls.setIsEnabled(true);
		    userDtls.setIsLocked(false);
		    userDtls.setIsLoggedIn(false);
		    userDtls.setWrongLoginCount(0);
		    userDtls.setAllowMultipleSession(false);
		    userDtls.setMobile(String.valueOf(mamber.getPhoneNo()));
		    userDtls.setEmail(mamber.getEmailId());
		    userDtls.setCreatedOn(new Date());
		    userDtls.setCreatedBy(currentUser);
		    userDtls.setLastUpdatedOn(new Date());
		    userDtls.setLastUpdatedBy(currentUser);
		    userDtls.setPrimaryRole(roleRepository.findByRoleCode("ROLE_MEMBER"));
		    userDtls = userRepository.save(userDtls);

		    Role theRole = roleRepository.findByRoleCode("ROLE_MEMBER");
		    if (theRole != null) {
		        UserRoleMap userRoleMap = new UserRoleMap();
		        userRoleMap.setUserId(userDtls.getUserId());
		        userRoleMap.setRoleId(theRole.getRoleId());
		        userRoleMap.setIsActive(true);
		        userRoleMapRepository.save(userRoleMap);
		    }

		    RoleRightLevelMaster rrlm = roleRightLevelMasterRepository.findByLevelCode("MEMBER");
		    ServiceOutcome<String> svcAccess = accessService.saveConfig(userDtls.getUserId(),userDtls.getPrimaryRole().getRoleId(),rrlm.getRoleRightLevelId(),mamber.getMemberId());

		    if (svcAccess.getOutcome()) {
		         mailService.mailLoginCredentials(password, "LOGIN CREDENTIALS", userDtls);
		    }
		}

		// Create LibraryCardHistory
		private void createLibraryCardHistory(User user, Member mamber, LibraryCard cardData) {
		    LibraryCardHistory libhis = new LibraryCardHistory();
		    libhis.setBarcode(cardData.getBarcode());  
		    libhis.setIssueDate(cardData.getIssueDate());
		    libhis.setValidDate(cardData.getValidDate());
		    libhis.setLibraryCard(libraryCardRepository.findByLib_cardId(cardData.getLib_cardId()));
		    libhis.setMember(memberRepository.findByMemberId(cardData.getMember().getMemberId()));
		    libhis.setIsActive(cardData.getIsActive());
		    libhis.setStatus(cardData.getStatus());
		    libhis.setCreatedBy(user);
		    libhis.setCreatedOn(cardData.getCreatedOn());
		    libhis.setLastUpdatedOn(cardData.getLastUpdatedOn());
		    libhis.setLastUpdatedBy(user);	
		    libraryCardHistoryRepository.save(libhis);
		}

		@Override
		public List<Member> findByAadhrNo(String aadharNo) {
			List<Member> memberList = new ArrayList<Member>();
			try {
				
				memberList = memberRepository.findByAadharNO(aadharNo);
			}
			catch (Exception e) {
				log.error("Exception occured in findByAadhrNo in MemberServiceImpl-->", e);
			}
			return memberList;
		}

		@Override
		public ServiceOutcome<LibraryCard> getValidDateBymemberId(Long memberId) {
			ServiceOutcome<LibraryCard> srvc = new ServiceOutcome<>();
			String message="";
			Boolean flag=true;
			LibraryCard cardData = new LibraryCard();
			try {		
				if(Optional.ofNullable(memberId).isPresent()) {
					cardData = libraryCardRepository.findByMemberAndmemberId(memberId);
					if(cardData !=null) {
						
					}else {
						message="data Not Found";
						flag=false;
					}
					}else {
						message="data Not Found";
						flag=false;
					}	
			} catch (Exception e) {
				message="Something went wrong please try again";
				flag=false;
				log.error("Exception occured in getValidDateBymemberId method in MemberServiceImpl-->", e);
			}
			srvc.setData(cardData);
			srvc.setMessage(message);
			srvc.setOutcome(flag);
			return srvc;
		}

		@Override
		@Transactional
		public ServiceOutcome<LibraryCard> getMemebeByMemberCodeFOrReApproval(ApproveDto approveDto) {
			ServiceOutcome<LibraryCard> srvc = new ServiceOutcome<>();
		    String message = "";
		    Boolean flag = true;
		    LibraryCard cardData =null;
		    try {
		        User user = SecurityHelper.getCurrentUser();

		        // Check if mamberCode is present
		        if (Optional.ofNullable(approveDto.getMamberCode()).isPresent()) {
		            Member mamber = memberRepository.findByMemberCode(approveDto.getMamberCode());
		            // Set cardData attributes
		            mamber.setIsValid(false);
		             cardData = libraryCardRepository.findByMemberAndmemberId(mamber.getMemberId());
		            cardData.setValidDate(approveDto.getRevaliDate());
		            libraryCardRepository.save(cardData);
		            memberRepository.save(mamber);
		            message = "Member Card Re-Issue";
		            createLibraryCardHistory(user, mamber, cardData);
		        } else {
		            message = "Member Not Found";
		            flag = false;
		        }
		    } catch (Exception e) {
		        message = "Something went wrong, please try again";
		        flag = false;
		        log.error("Exception occurred in getMemeberListByMemberCode method in MemberServiceImpl-->", e);
		        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		    }

		    srvc.setData(cardData);
		    srvc.setMessage(message);
		    srvc.setOutcome(flag);

		    return srvc;		}

		@Override
		public List<BookCatalogDTO> getDashboardData(Long mamberId) {
			List<BookCatalogDTO> bookCatalogDTOs=new ArrayList<>();
			try {
				StringBuilder query = new StringBuilder();
				query.append("select * from \n");
				query.append("(select a.book_catalog_id as book_catalog_id1 ,a.book_type from \n");
				query.append("(select tlmb2.* ,'MOST_BUYING_BOOK' as book_type  from t_lms_mst_book tlmb2\n");
				query.append("where tlmb2.book_id in (\n");
				query.append("select tlmb.book_id from public.t_lms_book_issued bi\n");
				query.append("join t_lms_mst_book tlmb on tlmb.book_id = bi.book_id \n");
				query.append("WHERE bi.created_on  BETWEEN (CURRENT_DATE - INTERVAL '30 days') AND CURRENT_DATE\n");
				query.append("group by tlmb.book_id \n");
				query.append("order by count(*) asc limit 5)\n");
				query.append("union all \n");
				query.append("select tlmb2.* ,'BUYING_BOOK_BY_USER' as book_type from t_lms_mst_book tlmb2\n");
				query.append("where tlmb2.book_id in (\n");
				query.append("select tlmb.book_id from public.t_lms_book_issued bi\n");
				query.append("join t_lms_mst_book tlmb on tlmb.book_id = bi.book_id \n");
				query.append("WHERE bi.library_card_id = :libCardId and bi.status ='ALLOCATED'\n");
				query.append("group by tlmb.book_id order by tlmb.book_id asc)\n");
				query.append("union all \n");
				query.append("select tlmb2.* ,'MOST_POPOLER_BOOK' as book_type from t_lms_mst_book tlmb2\n");
				query.append("where tlmb2.book_id in (\n");
				query.append("select tlmb.book_id from public.t_lms_book_issued bi\n");
				query.append("join t_lms_mst_book tlmb on tlmb.book_id = bi.book_id \n");
				query.append("WHERE bi.created_on  BETWEEN (CURRENT_DATE - INTERVAL '30 days') AND CURRENT_DATE\n");
				query.append("group by tlmb.book_id \n");
				query.append("order by count(*) asc limit 5)\n");
				query.append("union all\n");
				query.append("select tlmb2.* ,'NEW_BOOK' as book_type from t_lms_mst_book tlmb2\n");
				query.append("where tlmb2.is_active = true) as a\n");
				query.append("group by a.book_catalog_id,a.book_type) as b\n");
				query.append("join t_lms_mst_book_catalog tlmbc on tlmbc.book_catalog_id =b.book_catalog_id1;\n");
				Query query1 = entityManager.createNativeQuery(query.toString());
               
				
				if(mamberId!=null) {
					 query1.setParameter("libCardId",mamberId);
				}
				List<Object[]> res = query1.getResultList();
				for (Object[] row : res) {
//					BookCatalogDTO fstInstlDataDto = new BookCatalogDTO(row,rb.getString("UPLOAD.FILE.PATH"),ApplicationConstants.BOOKIMAGE);
					BookCatalogDTO fstInstlDataDto = new BookCatalogDTO(row);

					bookCatalogDTOs.add(fstInstlDataDto);
				}

			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return bookCatalogDTOs;
		}

		@Override
		public ServiceOutcome<LibraryCard> getMemebeByMebmerCodeForView(ApproveDto approveDto) {
			ServiceOutcome<LibraryCard> srvc = new ServiceOutcome<>();
		    String message = "";
		    Boolean flag = true;
		    LibraryCard cardData = new LibraryCard();
		    try {
		        User user = SecurityHelper.getCurrentUser();
		        if(user.getPrimaryRole().getRoleCode().equals("ROLE_MEMBER")) {
					ServiceOutcome<List<Member>> memberList = accessService.getByRoleLevel(user.getUserId(), user.getPrimaryRole().getRoleId(), "MEMBER", Member.class);
					if(memberList.getData().size()>0) {
						//Long memberId=memberList.getData().get(0).getMemberId();
						String MemberCode = memberList.getData().get(0).getMemberCode();
						approveDto.setMamberCode(MemberCode);					}
					}
		        // Check if mamberCode is present
		        if (Optional.ofNullable(approveDto.getMamberCode()).isPresent()) {
		            Member mamber = memberRepository.findByMemberCode(approveDto.getMamberCode());
		            cardData = libraryCardRepository.findByMemberAndmemberId(mamber.getMemberId());
		        } else {
		            message = "Member Not Found";
		            flag = false;
		        }
		    } catch (Exception e) {
		        message = "Something went wrong, please try again";
		        flag = false;
		        log.error("Exception occurred in getMemebeByMebmerCodeForView method in MemberServiceImpl-->", e);
		    }

		    srvc.setData(cardData);
		    srvc.setMessage(message);
		    srvc.setOutcome(flag);

		    return srvc;
		}

		@Override
		public ServiceOutcome<List<LanguageDto>> getLanguageList(boolean b) {
			ServiceOutcome<List<LanguageDto>> outcome = new ServiceOutcome<>();
			 List<Object[]> objectList =null;
			try {
				if(b) {
					 List<LanguageDto> lanaguagleList = new ArrayList<>();
					objectList = bookCatalogRepository.findAllLanguageList();
					  for (Object obj : objectList) {
				            Object[] data = (Object[]) obj;
				            LanguageDto prodintDto = new LanguageDto();
				            prodintDto.setLanguageName((String) data[0]);
				            lanaguagleList.add(prodintDto);
				        }
					  outcome.setData(lanaguagleList);
				}
				
			} catch (Exception e) {
				outcome.setOutcome(false);
				log.error("Exception occured in getLanguageList method in MasterServiceImpl-->", e);
			}
			return outcome;
		}

		@Override
		public ServiceOutcome<List<SubCategory>> getSubCatagoriesList(Long categoryId) {
			ServiceOutcome<List<SubCategory>> outcome = new ServiceOutcome<>();
			try {
					 List<SubCategory> subcatagiesList = subCategoryRepository.findByCatagoiesId(categoryId);
				
					 outcome.setData(subcatagiesList);
			} catch (Exception e) {
				outcome.setOutcome(false);
				log.error("Exception occured in getSubCatagoriesList method in MasterServiceImpl-->", e);
			}
			return outcome;
		}

		@Override
		public ServiceOutcome<List<BookCatalog>> getBookListBySubCatagoriesList(Long subcategoryId) {
			ServiceOutcome<List<BookCatalog>> outcome = new ServiceOutcome<>();
			try {
					 List<BookCatalog> bookcatalogList = bookCatalogRepository.findBysubCatgoriesId(subcategoryId);
				
					 outcome.setData(bookcatalogList);
			} catch (Exception e) {
				outcome.setOutcome(false);
				log.error("Exception occured in getBookListBySubCatagoriesList method in MasterServiceImpl-->", e);
			}
			return outcome;
		}

		@Override
		public ServiceOutcome<List<BookCatalog>> getBookListByBookName(String booksDel) {
			ServiceOutcome<List<BookCatalog>> outcome = new ServiceOutcome<>();
			try {
					 List<BookCatalog> bookcatalogList = bookCatalogRepository.findByBooksName(booksDel);
				
					 outcome.setData(bookcatalogList);
			} catch (Exception e) {
				outcome.setOutcome(false);
				log.error("Exception occured in getSubCatagoriesList method in MasterServiceImpl-->", e);
			}
			return outcome;
		}

		@Override
		public ServiceOutcome<List<BookCatalog>> getBookListByLnaguageId(Long languageId) {
			ServiceOutcome<List<BookCatalog>> outcome = new ServiceOutcome<>();
			try {
					 List<BookCatalog> bookcatalogList = bookCatalogRepository.findByBookLanguageId(languageId);
				
					 outcome.setData(bookcatalogList);
			} catch (Exception e) {
				outcome.setOutcome(false);
				log.error("Exception occured in getSubCatagoriesList method in MasterServiceImpl-->", e);
			}
			return outcome;
		}

		@Override
		public ServiceOutcome<Member> rejectMember(ApproveDto approveDto) {
			 ServiceOutcome<Member> srvc = new ServiceOutcome<>();
			    String message = "";
			    Boolean flag = true;
			    Member mamber = null;

			    try {
			        User user = SecurityHelper.getCurrentUser();
			        // Check if mamberCode is present
			        if (Optional.ofNullable(approveDto.getMamberCode()).isPresent()) {
			             mamber = memberRepository.findByMemberCode(approveDto.getMamberCode());
			            mamber.setStatus("REJECTED");
			            mamber.setRemark(approveDto.getRemark());
			            mamber.setRejectedAadhar(mamber.getAadharNo());
			            mamber.setAadharNo(null);
			            memberRepository.save(mamber);
			            message = "Member Rejected";
			        } else {
			            message = "Member Not Found";
			            flag = false;
			        }
			    } catch (Exception e) {
			        message = "Something went wrong, please try again";
			        flag = false;
			        log.error("Exception occurred in rejectMember method in MemberServiceImpl-->", e);
			    }

			    srvc.setData(mamber);
			    srvc.setMessage(message);
			    srvc.setOutcome(flag);

			    return srvc;
		}

		@Override
		public ServiceOutcome<List<BookCatalog>> getBookListByCatName(String catName) {
			ServiceOutcome<List<BookCatalog>> outcome = new ServiceOutcome<>();
			try {
					 List<BookCatalog> bookcatalogList = bookCatalogRepository.findByBooKCatName(catName);
				
					 outcome.setData(bookcatalogList);
			} catch (Exception e) {
				outcome.setOutcome(false);
				log.error("Exception occured in getSubCatagoriesList method in MasterServiceImpl-->", e);
			}
			return outcome;
		}

}
