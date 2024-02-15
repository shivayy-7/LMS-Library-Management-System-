package com.aashdit.lms.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.IssuingDetails;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.LibraryCard;
import com.aashdit.lms.model.Member;
import com.aashdit.lms.service.BookIssueService;
import com.aashdit.lms.service.CommonService;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
public class BookIssueController {

	@Autowired
	private BookIssueService bookIssueService;
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping("/book-issue")
	public String bookIssue(Model model) {
		try {
			
			model.addAttribute("authorList", commonService.getAllData("AUTHOR").getData());
			model.addAttribute("subCatList", commonService.getAllData("SUBCATEGORY").getData());
			model.addAttribute("libraryList", commonService.getAllData("LIBRARY").getData());
			model.addAttribute("publisherList", commonService.getAllData("PUBLISHER").getData());
			model.addAttribute("rackList", commonService.getAllData("RACK_TRUE").getData());
			model.addAttribute("maxReserveDay", commonService.getAllData("MAX_RESERVE_DAY").getData().get(0));
		}
		catch (Exception e) {
			log.error("Exception occured in BookIssueController at bookIssue() method==>"+e);
		}
		return "site.book.issue";
	}
	
	  @GetMapping("/findbyapprovalno.htm")
	    public ResponseEntity<String> findByApprovalNo(@RequestParam String aaprovalNo) {
	        JSONArray arrs = new JSONArray();
	        List<Object[]> objectslst = bookIssueService.findApprovalNo(aaprovalNo);
	        JSONObject jsonObject = null;
	        for (Object[] result : objectslst) {
	            jsonObject = new JSONObject();
	            jsonObject.put("aaprovalNo", result[0]);
	            arrs.put(jsonObject);
	        }
	        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(arrs.toString());
	    }
	  
		@GetMapping("/member-deatils")
		public String memberDetails(RedirectAttributes attr,String sentapprovalNo) {
			try {
		    	ServiceOutcome<LibraryCard> memberData= bookIssueService.findMemberByApprovalNo(sentapprovalNo);
		    	if(memberData.getOutcome()) {
					attr.addFlashAttribute("manamnetData",memberData.getData());
				}else {
					attr.addFlashAttribute("error_msg",memberData.getMessage());
				}
		    	}catch (Exception e) {
					log.error("Exception occured in BookIssueController at memberDetails()==>"+e);
				}
		    	return "redirect:/book-issue";
		}
	
		
		@GetMapping("/findbybookname.htm")
		 public ResponseEntity<String> findByBookName(@RequestParam String books) {
	        JSONArray arrs = new JSONArray();

	        List<Object[]> objectslst = bookIssueService.findBookSByNamel(books);
	        JSONObject jsonObject;

	        for (Object[] result : objectslst) {
	            jsonObject = new JSONObject();

	            String imagePath = (String) result[0];
	            if (imagePath != null) {
	                File img = new File(imagePath);
	                if (img.exists()) {
	                    try {
	                        FileInputStream fileInputStream = new FileInputStream(img);
	                        byte[] imageBytes = IOUtils.toByteArray(fileInputStream);
	                        fileInputStream.close();
	                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                        jsonObject.put("image", base64Image);
	                    } catch (IOException e) {
	                        e.printStackTrace(); // Handle the exception appropriately
	                    }
	                }
	            }

	            jsonObject.put("books", result[1]);
	            jsonObject.put("bookcode", result[2]);
	            arrs.put(jsonObject);
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(arrs.toString());
	    }
	
		
		
		@GetMapping("/member-deatils-data")
		public ResponseEntity<String> memberDetailsData(RedirectAttributes attr, @RequestParam String approvalNo) {
		    try {
		        ServiceOutcome<LibraryCard> memberData = bookIssueService.findMemberByApprovalNo(approvalNo);

		        JSONObject jsonObject = new JSONObject();
		        jsonObject.put("approvalNo", memberData.getData().getApproveNo()); 
		        jsonObject.put("meberName", memberData.getData().getMember().getMemberName()); 
		        jsonObject.put("imagePath", memberData.getData().getMember().getImagePath()); 
		        jsonObject.put("address", memberData.getData().getMember().getAddress()); 
		        jsonObject.put("validDate", memberData.getData().getValidDate()); 
		        jsonObject.put("issueDate", memberData.getData().getIssueDate()); 
		        jsonObject.put("email", memberData.getData().getMember().getEmailId()); 
		        jsonObject.put("phoneno", memberData.getData().getMember().getPhoneNo()); 

		        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonObject.toString());
		    } catch (Exception e) {
		        log.error("Exception occurred in BookIssueController at memberDetailsData()==>" + e);
		        // Handle the exception appropriately. You might want to return an error response.
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		    }
		}

		
		
		@GetMapping("/book-deatils")
		public ResponseEntity<String>bookDetaiolsDat(RedirectAttributes attr, @RequestParam String code) {
		     JSONArray arrs = new JSONArray();
		     try {
		    	 ServiceOutcome<List<Book>> memberData = bookIssueService.findByCode(code);
			        JSONObject jsonObject = null;
			        for (Book result : memberData.getData()) {
			            jsonObject = new JSONObject();
			            jsonObject.put("bookcode", result.getBookUkNo());
			            jsonObject.put("slfId", result.getShelf().getShelfId());
			            jsonObject.put("rakId", result.getRack().getRackId());
			            jsonObject.put("rackName", result.getRack().getRackName());
			            jsonObject.put("slfName",result.getShelf().getShelfName());
			            arrs.put(jsonObject);
			        } 
		     }
		     catch (Exception e) {
		    	  log.error("Exception occurred in BookIssueController at bookDetaiolsDat()==>" + e);
		    	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
			}
		       
		        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(arrs.toString());
		}
		
		
		@PostMapping("/save-issuedetails")
		@ResponseBody
		public ResponseEntity<ServiceOutcome<String>> saveIssuingDetails(@RequestBody IssuingDetails issuingDetails, RedirectAttributes attr) {
		    try {
		        ServiceOutcome<String> issueData = bookIssueService.saveBookISuue(issuingDetails);
		        return ResponseEntity.ok(issueData);
		    } catch (Exception e) {
		        ServiceOutcome<String> errorOutcome = new ServiceOutcome<>();
		        errorOutcome.setOutcome(false);
		        errorOutcome.setMessage("Error: " + e.getMessage());
		        return ResponseEntity.ok(errorOutcome);
		    }
		    
		    
		}

		@GetMapping("/createPayment")
		public @ResponseBody String  createPayment(Long rentMonthId,String amount) {
			
		 ServiceOutcome<JSONObject> serviceOut = bookIssueService.createPayment(rentMonthId,amount);
				
			
			return serviceOut.getData().toString();
			
		}
		@GetMapping("/afterThePayment")
		public @ResponseBody String paymentPay(String signature ,String order_id ,String payment_id,String status,
				String reason,Long rentMonthId,String preference) {
			   JSONObject jsonObject=new JSONObject();
			try {
				ServiceOutcome<JSONObject> serviceOutcome = bookIssueService.saveSuccAndErrorRes(signature,order_id,payment_id,status,reason,rentMonthId,preference);
				if(serviceOutcome.getOutcome()) {
					return serviceOutcome.getData().toString();
				}else {
					return serviceOutcome.getData().toString();
				}
			} catch (Exception e) {
				jsonObject.put("transactionId", "");
				log.error(e.getMessage());
				return jsonObject.toString();
			}
		}
}
