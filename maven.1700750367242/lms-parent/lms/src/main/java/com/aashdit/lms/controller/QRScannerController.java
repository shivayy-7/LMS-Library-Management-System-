package com.aashdit.lms.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.lms.model.Member;
import com.aashdit.lms.repository.MemberRepository;
import com.aashdit.lms.utils.ApplicationConstants;



@Controller
public class QRScannerController {
	
	
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/scanQR/{memberCode}")
	public String scanQRCode(@PathVariable("memberCode") String memberCode, Model model, RedirectAttributes attr) {
	    try {
	       // Add null check for file
	    	//     BufferedImage readerImage = ImageIO.read(new FileInputStream("C:\\Tools\\QRCodeGenerated.jpg"));
//	    			BinaryBitmap binaryBitmap = new BinaryBitmap (new HybridBinarizer (new BufferedImageLuminanceSource(readerImage)));
//	    			Result resultObj = new MultiFormatReader().decode(binaryBitmap);
//	    	
	    	
	    	
	            Member member = memberRepository.findByMemberCode(memberCode);
	        	model.addAttribute("mambermodule", ApplicationConstants.DocMember);  // Corrected spelling
	        	model.addAttribute("mamberData", member);  // Corrected spelling
	         
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "errorPage"; 
	    }
	    
	    return "site.master.addmember";
	}



}