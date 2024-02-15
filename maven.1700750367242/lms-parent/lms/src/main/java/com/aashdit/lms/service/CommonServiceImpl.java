package com.aashdit.lms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.MasterApiDto;
import com.aashdit.lms.model.Author;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.model.BookCatalogImage;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.Gender;
import com.aashdit.lms.model.Language;
import com.aashdit.lms.model.Library;
import com.aashdit.lms.model.LibrarySection;
import com.aashdit.lms.model.Limit;
import com.aashdit.lms.model.LookupValue;
import com.aashdit.lms.model.Member;
import com.aashdit.lms.model.Publisher;
import com.aashdit.lms.model.Rack;
import com.aashdit.lms.model.Shelf;
import com.aashdit.lms.model.SubCategory;
import com.aashdit.lms.model.TypeMember;
import com.aashdit.lms.repository.AuthorRepository;
import com.aashdit.lms.repository.BookCatalogImageRepository;
import com.aashdit.lms.repository.BookCatalogRepository;
import com.aashdit.lms.repository.BookIssueRepository;
import com.aashdit.lms.repository.CategoryRepository;
import com.aashdit.lms.repository.GenderRepository;
import com.aashdit.lms.repository.LibraryRepository;
import com.aashdit.lms.repository.LibrarySectionRepository;
import com.aashdit.lms.repository.LookupValueRepository;
import com.aashdit.lms.repository.MemberRepository;
import com.aashdit.lms.repository.PublisherRepository;
import com.aashdit.lms.repository.RackRepository;
import com.aashdit.lms.repository.ShelfRepository;
import com.aashdit.lms.repository.SubCategoryRepository;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.umt.model.User;
import com.aashdit.umt.service.AccessService;
import com.aashdit.umt.util.SecurityHelper;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService,MessageSourceAware {
	
	@Autowired private GenderRepository genderRepository;
	
	@Autowired private LibraryRepository libRepository;
	
	@Autowired private LibrarySectionRepository libSectionRepository;
	
	@Autowired private CategoryRepository categoryRepository;
	
	@Autowired private SubCategoryRepository subCategoryRepository;
	
	@Autowired private RackRepository rackrepository;
	
	@Autowired private BookCatalogRepository bookCatagoryRepository;
	
	@Autowired private BookIssueRepository bookIssueRepository;
	
	@Autowired private BookCatalogImageRepository bookCatalogImageRepository;
	
	@Autowired private MemberRepository memberRepository;
	
	@Autowired
	private ShelfRepository shelfRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private LookupValueRepository lookupValueRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private AccessService accessService;
	
	private MessageSource messageSource;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public ServiceOutcome<List<?>> getAllAjaxCallDetails(String identity, String id) {
		ServiceOutcome<List<?>> soc = new ServiceOutcome<List<?>>();
		try {
		switch(identity) {
			 case "SHELFBYRACK":
				 List<Shelf> findAllByRackRackId = shelfRepository.findAllByRackRackId(Long.parseLong(id));
				 soc.setData(findAllByRackRackId);
				 break;
			    
			  default:
			    // code block
			}
			 soc.setMessage("success");
			  soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occurred in getAllAjaxCallDetails("+identity+") -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	
	@Override /* For Only State, Dist, Block/Municipality/Municipality Corporation, Gp, Village/Ward */
	public ServiceOutcome<List<?>> getAllData(String identity){
		ServiceOutcome<List<?>> soc = new ServiceOutcome<List<?>>();
		try {
			switch(identity) {
			case "STATE":
			
				break;
			case "GENDER":
				List<Gender> genderList = genderRepository.findAllByIsActiveTrue();
				soc.setData(genderList);
				break;
			case "LIBRARY":
				List<Library> findAllByIsActiveTrue = libRepository.findAllByIsActive(true);
				soc.setData(findAllByIsActiveTrue);
				break;
			case "LIBRARY-SECTION":
				List<LibrarySection> findAllByIsActive = libSectionRepository.findAllByIsActive(true);
				soc.setData(findAllByIsActive);
				break;
			case "CATEGORY":
			     List<Category> findAllCategoryByIsActive = categoryRepository.findAllByIsActiveTrueOrderByCategoryId() ;
			     soc.setData(findAllCategoryByIsActive);
			     break;
			case "SUBCATEGORY":
				List<SubCategory> findAllSubCategoryByIsActive = subCategoryRepository.findAllByIsActiveTrueOrderBySubCategoryId();
				soc.setData(findAllSubCategoryByIsActive);
				break;
			case "RACK_ALL":
				List<Rack> findAll = rackrepository.findAll();
				soc.setData(findAll);
				break;
			case "SHELF_ALL":
				List<Shelf> findAllShelf = shelfRepository.findAll();
				soc.setData(findAllShelf);
				break;
			case "PUBLISHER":
				List<Publisher> findAllByIsActive2 = publisherRepository.findAllByIsActive(true);
				soc.setData(findAllByIsActive2);
				break;
			case "AUTHOR":
				List<Author> findAllByIsActive3 = authorRepository.findAllByIsActive(true);
				soc.setData(findAllByIsActive3);
				break;
			case "RACK_TRUE":
				List<Rack> findAllByIsActive4 = rackrepository.findAllByIsActive(true);
				soc.setData(findAllByIsActive4);
				break;
			case "BOOK-CATAGORY":
				List<BookCatalog> findAllByIsActive5 = bookCatagoryRepository.findAllByIsActive(true);
				soc.setData(findAllByIsActive5);
				break;
			case "ISSUEDBOOKLIST":
				List<BookIssue> findAllByIsActive6 = bookIssueRepository.findAllByIsActive(true);
				soc.setData(findAllByIsActive6.stream().filter(e->e.getStatus().equals("APPROVED")).collect(Collectors.toList()));
				break;
			case "ISSUEDBOOKLIST-RESERVED":
				
				List<String> statuses = Arrays.asList(ApplicationConstants.STATUS_RESERVED, ApplicationConstants.STATUS_APPROVED);

				List<BookIssue> findAllByIsActive7 = bookIssueRepository.findByStatusInAndIsActive(statuses,true);
				soc.setData(findAllByIsActive7);
				break;	
			case "LANGUAGE":
				List<LookupValue> lstData = lookupValueRepository.findByCodeOrderByValueCodeDesc(Language.DCODE);
				List<Language> lstSc = lstData.stream().map(e -> {
					Language sc = new Language();
					BeanUtils.copyProperties(e, sc);
					return sc;
				}).collect(Collectors.toList());
				soc.setData(lstSc);
				break;
			case "MAX_RESERVE_DAY":
				List<LookupValue> collect = lookupValueRepository.findByCodeOrderByValueCodeDesc(Limit.DCODE).stream()
																.filter(e->e.getValueCode().equals(ApplicationConstants.STATUS_RESERVED_DAY))
																.collect(Collectors.toList());
					List<Limit> limitData = collect.stream().map(e -> {
					Limit sc = new Limit();
					BeanUtils.copyProperties(e, sc);
					return sc;
					}).collect(Collectors.toList());
				soc.setData(limitData);
				break;
			case ApplicationConstants.STATUS_ALLOCATION_LIMIT:
				List<LookupValue> collect1 = lookupValueRepository.findByCodeOrderByValueCodeDesc(Limit.DCODE).stream()
																.filter(e->e.getValueCode().equals(ApplicationConstants.STATUS_ALLOCATION_LIMIT))
																.collect(Collectors.toList());
					List<Limit> limitData1 = collect1.stream().map(e -> {
					Limit sc = new Limit();
					BeanUtils.copyProperties(e, sc);
					return sc;
					}).collect(Collectors.toList());
				soc.setData(limitData1);
				break;
			
			  default:
			    // code block
			}
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in getAllData("+identity+") -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}


	@Override
	public ServiceOutcome<List<TypeMember>> findByMeberCode(String dcode) {
		ServiceOutcome<List<TypeMember>> svcOutcome = new ServiceOutcome<>();
		try
		{
			List<LookupValue> lstData = lookupValueRepository.findByCodeOrderByValueCodeDesc(TypeMember.DCODE);
			List<TypeMember> lstSc = lstData.stream().map(e -> {
				TypeMember sc = new TypeMember();
				BeanUtils.copyProperties(e, sc);
				return sc;
			}).collect(Collectors.toList());
			svcOutcome.setData(lstSc);
		}
		catch(Exception ex)
		{
			
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		
		return svcOutcome;
	}

	@Override
	public List<SubCategory> findSubCategoryListByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceOutcome<MasterApiDto> getMasterList() {
		ServiceOutcome<MasterApiDto> srvc = new ServiceOutcome<>();
		try {
			MasterApiDto masterApiVo = new MasterApiDto();
			masterApiVo.setCategory(categoryRepository.findAllByIsActiveTrueOrderByCategoryName());
			masterApiVo.setPublisher(publisherRepository.findAllByIsActiveTrueOrderByPublisherName());
			masterApiVo.setAuthor(authorRepository.findAllByIsActiveTrueOrderByAuthorName());
			srvc.setData(masterApiVo);
			srvc.setOutcome(true);
			srvc.setMessage("The data fetched successfully");
		} catch (Exception e) {
			srvc.setOutcome(false);
			srvc.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
    srvc.setData(null);
    e.printStackTrace();
		}
		return srvc;
	}

	@Override
	public ServiceOutcome<String> deleteBookCatalogImageById(Long id) {
		ServiceOutcome<String> soc= new ServiceOutcome<String>();
		try {
			Optional<BookCatalogImage> findById = bookCatalogImageRepository.findById(id);
			if(findById.isPresent()) {
				bookCatalogImageRepository.delete(findById.get());
				soc.setData("SUCCESS");
			}
			soc.setData("Data not found in this id");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in deleteBookCatalogImageById() -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Boolean> getDataByISBN(String id) {
		ServiceOutcome<Boolean> soc= new ServiceOutcome<Boolean>();
		try {
			Optional<BookCatalog> findByIsbnNoAndIsActive = bookCatagoryRepository.findByIsbnNoAndIsActive(id, true);
			if(findByIsbnNoAndIsActive.isPresent()) {
				soc.setData(true);
			}else {
				soc.setData(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in getDataByISBN() -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Boolean> getBookCountByMemberId(String identity) {
		ServiceOutcome<Boolean> soc= new ServiceOutcome<Boolean>();
		try {
			
			switch (identity) {
		    case ApplicationConstants.STATUS_RESERVED_LIMIT:
		    	User user = SecurityHelper.getCurrentUser();
		    	ServiceOutcome<List<Member>> outcome= accessService.getByRoleLevel(user.getUserId(), user.getPrimaryRole().getRoleId(), "MEMBER", Member.class);
		    	Long memberId=null;
		    	if(outcome.getData().size()>0) {
		    		memberId=outcome.getData().get(0).getMemberId();
		    	}
		    	if(Optional.ofNullable(memberId).isPresent()) {
					long count = bookIssueRepository.findAllByLibraryCardMemberMemberIdAndStatus(memberId, ApplicationConstants.STATUS_RESERVED).stream().count();
					List<LookupValue> collect = lookupValueRepository.findByCodeOrderByValueCodeDesc(Limit.DCODE).stream()
																					.filter(e->e.getValueCode().equals(ApplicationConstants.STATUS_RESERVED_LIMIT))
																					.collect(Collectors.toList());
					List<Limit> limitData = collect.stream().map(e -> {
						Limit sc = new Limit();
						BeanUtils.copyProperties(e, sc);
						return sc;
					}).collect(Collectors.toList());
					
					if(count >= Long.parseLong(limitData.get(0).getValueEn())) {
						soc.setData(true);
					}else {
						soc.setData(false);
					}
				}
		        break;
		    case ApplicationConstants.STATUS_ALLOCATION_LIMIT:
		    	
		        break;
		    
		    default:
		        
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in getBookCountByMemberId("+identity+") -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Long> getBookAllocationCountByMemberId(String identity, Long bookIssuedId) {
		ServiceOutcome<Long> soc = new ServiceOutcome<Long>();
		try {
			Optional<BookIssue> findByBookIssuedId = bookIssueRepository.findByBookIssuedId(bookIssuedId);
	    	if(findByBookIssuedId.isPresent()) {
	    		long count = bookIssueRepository.findAllByLibraryCardMemberMemberIdAndStatus(findByBookIssuedId.get().getLibraryCard().getMember().getMemberId(), ApplicationConstants.STATUS_APPROVED).stream().count();
	    		soc.setData(count);
	    		soc.setMessage("success");
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in getBookAllocationCountByMemberId("+identity+") -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
		}
		return soc;
	}

	@Override
	public ServiceOutcome<Boolean> checkUniqueEmail(String email) {
		ServiceOutcome<Boolean> soc= new ServiceOutcome<Boolean>();
		try {
			Optional<Member> findByIsbnNoAndIsActive = memberRepository.findByEmailIdAndIsActive(email, true);
			if(findByIsbnNoAndIsActive.isPresent()) {
				soc.setData(true);
			}else {
				soc.setData(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in checkUniqueEmail() -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
		}
		return soc ;
	}	
	
	
	
}
