package com.aashdit.lms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.ApproveDto;
import com.aashdit.lms.dto.FillterDto;
import com.aashdit.lms.dto.LanguageDto;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.model.Category;
import com.aashdit.lms.model.LibraryCard;
import com.aashdit.lms.model.Member;
import com.aashdit.lms.model.SubCategory;
import com.aashdit.lms.model.TypeMember;
import com.aashdit.lms.service.CommonService;
import com.aashdit.lms.service.MasterService;
import com.aashdit.lms.service.MemberService;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.ibm.icu.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mst")
public class MemberController {
	
	
	ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MemberService memberService ;
	
	
	@Autowired
	private MasterService masterService;
	
	@GetMapping("/add-member")
	public String addMember(Model model) {
		try {
			
			ServiceOutcome<List<TypeMember>> typeList=commonService.findByMeberCode(TypeMember.DCODE);
			model.addAttribute("typeListData", typeList.getData());
			
			model.addAttribute("genderList", commonService.getAllData("GENDER").getData());
			model.addAttribute("user", SecurityHelper.getCurrentUser());
		}
		catch (Exception e) {
			log.error("Exception occured in MemberController at addMember()==>"+e);
		}
		return "site.master.addmember";
	}
	
	
	@PostMapping("/save-member")
	public String saveMember(RedirectAttributes attr, Member mamber) {
		User user = SecurityHelper.getCurrentUser();
		try {
			
			ServiceOutcome<String> schemeData= memberService.saveMember(mamber);
			attr.addFlashAttribute(schemeData.getOutcome() ? "success_msg" : "error_msg",schemeData.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in MemberController at saveMember()==>"+e);
		}
		if(user !=null) {
			if(user.getRoles().get(0).getRoleCode().equals("ROLE_LA")) {
				return "redirect:/mst/list-member";
			}
			else {
				return "redirect:/mst/add-member";
			}
		}else {
			return "redirect:/mst/add-member";
		}
		
	
	}
	
	
    @PostMapping("/edit-member")
    public String editMemaberData(RedirectAttributes attr,String mamberCode) {
    	try {
    		User user = SecurityHelper.getCurrentUser();
    	ServiceOutcome<Member> memaber= memberService.getMemeberListByMemberCode(mamberCode);
    	ServiceOutcome<LibraryCard> memaber2= memberService.getValidDateBymemberId(memaber.getData().getMemberId());
    	attr.addFlashAttribute("roleCode",user.getPrimaryRole().getRoleCode());
    	attr.addFlashAttribute("mambermodule",ApplicationConstants.DocMember);
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
    		        attr.addFlashAttribute("cardData", validDate);
    		    } else {
    		        // Do something else if the dates are not equal
    		    }
    		} else {
    		    // Handle the case where validDate is null
    		}

    	}
    	if(memaber.getOutcome()) {
			attr.addFlashAttribute("mamberData",memaber.getData());
		}else {
			attr.addFlashAttribute("error_msg",memaber.getMessage());
		}
    	}catch (Exception e) {
			log.error("Exception occured in MemberController at editMemaberData()==>"+e);
		}
    	return "redirect:/mst/add-member";
    }
	
	@GetMapping("/list-member")
	public String listMember(Model model) {
		try {
			ServiceOutcome<List<Member>> memberList =memberService.getMemberList(false);
			model.addAttribute("memberList", memberList.getData());
		} catch (Exception e) {
			log.error("Exception occured in MemberController at listMember()==>"+e);
		}
		return "site.master.listmember";
	}
	
	
	@GetMapping("/view-member")
	public String ViewMember(Model model,ApproveDto approveDto) {
    	try {
    	
    		User user = SecurityHelper.getCurrentUser();
	    	ServiceOutcome<LibraryCard> libryCradData= memberService.getMemebeByMebmerCodeForView(approveDto);
	    	
	    	model.addAttribute("roleCode",user.getPrimaryRole().getRoleCode());
	    	model.addAttribute("mambermodule",ApplicationConstants.DocMember);
	    	if(libryCradData.getOutcome()) {
	    		model.addAttribute("pntData",libryCradData.getData());
	    		 String upLoadImg =	libryCradData.getData().getBarcode();
	    		 try {
	    			 File img = new File(upLoadImg);
				        FileInputStream fileInputStream = new FileInputStream(img);
				        byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
				        fileInputStream.close(); 
				        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				    	model.addAttribute("base64Image",base64Image);
	    		 }
			        catch(Exception e){
			        	log.error("exception===>"+e);
			        }
				
			}else {
				model.addAttribute("error_msg",libryCradData.getMessage());
			}
	    	}catch (Exception e) {
				log.error("Exception occured in MemberController at viewmember()==>"+e);
			}
	    	return "site.library.cardgen";
    }
//	public String ViewMember(Model model) {
//		try {
//			
//		}
//		catch (Exception e) {
//			log.error("Exception occured in MemberController at addMember()==>"+e);
//		}
//		return "site.master.viewmember";
//	}
//	
	
	 @PostMapping("/send-approve-member")
	    public String approveMemeber(Model model,ApproveDto approveDto) {
	    	try {
	    		User user = SecurityHelper.getCurrentUser();
	    	ServiceOutcome<LibraryCard> libryCradData= memberService.getMemebeByMemberCodeFOrApproval(approveDto);
	    	
	    	model.addAttribute("roleCode",user.getPrimaryRole().getRoleCode());
	    	model.addAttribute("mambermodule",ApplicationConstants.DocMember);
	    	if(libryCradData.getOutcome()) {
	    		model.addAttribute("pntData",libryCradData.getData());
	    		 String upLoadImg =	libryCradData.getData().getBarcode();
	    		 try {
	    			 File img = new File(upLoadImg);
				        FileInputStream fileInputStream = new FileInputStream(img);
				        byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
				        fileInputStream.close(); 
				        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				    	model.addAttribute("base64Image",base64Image);
	    		 }
			        catch(Exception e){
			        	log.error("exception===>"+e);
			        }
				System.out.println("mamberCardData: " + libryCradData.getData());
			}else {
				model.addAttribute("error_msg",libryCradData.getMessage());
			}
	    	}catch (Exception e) {
				log.error("Exception occured in MemberController at approveMemeber()==>"+e);
			}
	    	return "site.library.cardgen";
	    }
		
	 
	 
	 @ResponseBody
		@GetMapping(path="/duplicate_aadhar_check",name="Check duplicate code")
		public String validateAadharNo(String aadharNO) throws Exception {
			JSONObject jsonObject=new JSONObject();
			List<Member> addharkOutcome=memberService.findByAadhrNo(aadharNO.trim());
			if(addharkOutcome.size() >0) {
				jsonObject.put("isDuplicate", true);
				jsonObject.put("aadharno", addharkOutcome.get(0).getAadharNo());
			}else {
				jsonObject.put("isDuplicate", false);
			}
			return jsonObject.toString();
			
		}
	 
	 
	 @PostMapping("/send-reapprove-member")
	    public String reApproveMemeber(Model model,ApproveDto approveDto) {
	    	try {
	    		User user = SecurityHelper.getCurrentUser();
	    	ServiceOutcome<LibraryCard> libryCradData= memberService.getMemebeByMemberCodeFOrReApproval(approveDto);
	    	if(libryCradData.getOutcome()) {
	    		model.addAttribute("success_msg",libryCradData.getMessage());
	    	}
	    	else {
	    		model.addAttribute("error_msg",libryCradData.getMessage());
	    	}
	    	
//	    	model.addAttribute("roleCode",user.getPrimaryRole().getRoleCode());
//	    	model.addAttribute("mambermodule",ApplicationConstants.DocMember);
//	    	if(libryCradData.getOutcome()) {
//	    		model.addAttribute("pntData",libryCradData.getData());
//	    		 String upLoadImg =	libryCradData.getData().getBarcode();
//	    		 try {
//	    			 File img = new File(upLoadImg);
//				        FileInputStream fileInputStream = new FileInputStream(img);
//				        byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
//				        fileInputStream.close(); 
//				        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//				    	model.addAttribute("base64Image",base64Image);
//	    		 }
//			        catch(Exception e){
//			        	log.error("exception===>"+e);
//			        }
//		
//			}else {
//				model.addAttribute("error_msg",libryCradData.getMessage());
//			}
	    	}catch (Exception e) {
				log.error("Exception occured in MemberController at approveMemeber()==>"+e);
			}
	    	return "redirect:/mst/list-member";
	    }
		
	 
	 @PostMapping("/view-member")
	    public String viewmember(Model model,ApproveDto approveDto) {
	    	try {
	    	
	    		User user = SecurityHelper.getCurrentUser();
		    	ServiceOutcome<LibraryCard> libryCradData= memberService.getMemebeByMebmerCodeForView(approveDto);
		    	
		    	model.addAttribute("roleCode",user.getPrimaryRole().getRoleCode());
		    	model.addAttribute("mambermodule",ApplicationConstants.DocMember);
		    	if(libryCradData.getOutcome()) {
		    		model.addAttribute("pntData",libryCradData.getData());
		    		 String upLoadImg =	libryCradData.getData().getBarcode();
		    		 try {
		    			 File img = new File(upLoadImg);
					        FileInputStream fileInputStream = new FileInputStream(img);
					        byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
					        fileInputStream.close(); 
					        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					    	model.addAttribute("base64Image",base64Image);
		    		 }
				        catch(Exception e){
				        	log.error("exception===>"+e);
				        }
					
				}else {
					model.addAttribute("error_msg",libryCradData.getMessage());
				}
		    	}catch (Exception e) {
					log.error("Exception occured in MemberController at viewmember()==>"+e);
				}
		    	return "site.library.cardgen";
	    }
		
	 
	 
	 
	 
	 @GetMapping("/view-allbooks")
		public String viewAllbookS(Model model) {
			try {
				ServiceOutcome<List<Category>> categoryList = masterService.getCategoryList(true);
				model.addAttribute("categoryList", categoryList.getData());

				model.addAttribute("languageList", commonService.getAllData("LANGUAGE").getData());
					
			}
			catch (Exception e) {
				log.error("Exception occured in MemberController at viewAllbookS()==>"+e);
			}
			return "site.view.allbooks";
		}
	 
	 
	 
		@PostMapping("/fillterbook")
		public String viewAllbokSFillter(Model model, FillterDto fillterDto) {
			try {
				ServiceOutcome<List<Category>> categoryList = masterService.getCategoryList(true);
				model.addAttribute("categoryList", categoryList.getData());
				model.addAttribute("languageList", commonService.getAllData("LANGUAGE").getData());
				if(fillterDto.getCategoryId() !=null) {
					ServiceOutcome<List<SubCategory>> subcatogoriesList = memberService.getSubCatagoriesList(fillterDto.getCategoryId());
					model.addAttribute("subcatogoriesList", subcatogoriesList.getData());
				}
				
				if(fillterDto.getSubcategoryId()!=null) {
					ServiceOutcome<List<BookCatalog>> bookcatalogList = memberService.getBookListBySubCatagoriesList(fillterDto.getSubcategoryId());
					model.addAttribute("bookcatalogList", bookcatalogList.getData());
				}
				if(fillterDto.getBooks()!=null) {
					ServiceOutcome<List<BookCatalog>> bookcatalogList = memberService.getBookListByBookName(fillterDto.getBooks());
					model.addAttribute("bookcatalogList", bookcatalogList.getData());
				}
				
				if(fillterDto.getLanguageId()!=null) {
					ServiceOutcome<List<BookCatalog>> bookcatalogList = memberService.getBookListByLnaguageId(fillterDto.getLanguageId());
					model.addAttribute("bookcatalogList", bookcatalogList.getData());
				}
				
				if(fillterDto.getCatName()!=null) {
					ServiceOutcome<List<BookCatalog>> bookcatalogList = memberService.getBookListByCatName(fillterDto.getCatName());
					model.addAttribute("bookcatalogList", bookcatalogList.getData());
				}
				model.addAttribute("fillterDto", fillterDto);

			} catch (Exception e) {
				log.error("Exception occured in MemberController at viewAllbokSFillter()==>" + e);
			}
			return "site.view.allbooks";
		}
	 
	 
	 
		 @PostMapping("/reject-member")
		    public String rejectMemebr(Model model,ApproveDto approveDto) {
		    	try {
		    	User user = SecurityHelper.getCurrentUser();
		    	ServiceOutcome<List<TypeMember>> typeList=commonService.findByMeberCode(TypeMember.DCODE);
				model.addAttribute("typeListData", typeList.getData());
				model.addAttribute("genderList", commonService.getAllData("GENDER").getData());
		    	ServiceOutcome<Member> memeberData= memberService.rejectMember(approveDto);
		    	model.addAttribute("roleCode",user.getPrimaryRole().getRoleCode());
		    	model.addAttribute("mambermodule",ApplicationConstants.DocMember);
		    	if(memeberData.getOutcome()) {
		    		model.addAttribute("mamberData",memeberData.getData());
		    		model.addAttribute("success_msg",memeberData.getMessage());
				}else {
					model.addAttribute("error_msg",memeberData.getMessage());
				}
		    	}catch (Exception e) {
					log.error("Exception occured in MemberController at approveMemeber()==>"+e);
				}
		    	return "site.master.addmember";
		    }
		

}