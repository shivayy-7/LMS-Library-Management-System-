package com.aashdit.lms.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ImageViewController {

	ResourceBundle rb = ResourceBundle.getBundle("application"); 
	
	@GetMapping(value="/image/viewDocuments", name="Download Document")
	public void downloadDocuments(Model model, String moduleName,String filePath,HttpServletResponse response,HttpServletRequest request) {
		try{
			if(Optional.ofNullable(filePath).isPresent()) {
				String path = rb.getString("UPLOAD.FILE.PATH")+File.separator+moduleName+File.separator+filePath;
				Path file = Paths.get(path);
				if (Files.exists(file)) {
					DownloadFile.viewUploadedDocument(file, request, response);
				}
			}
		 }catch(Exception e){
			 log.error("Exception occured in viewDocuments method in ImageViewController where moduleName is ->"+moduleName+" and filePath is->"+filePath+" and exception message is -->"+e.getMessage());
		 }
	}
	
	@ResponseBody
	@GetMapping(value="/api/allowAll/image/viewDocuments", name="Download Document")
	public void apiDownloadDocuments(Model model, String moduleName,String filePath,HttpServletResponse response,HttpServletRequest request) {
		try{
			if(Optional.ofNullable(filePath).isPresent()) {
				String path = rb.getString("UPLOAD.FILE.PATH")+File.separator+moduleName+File.separator+filePath;
				Path file = Paths.get(path);
				if (Files.exists(file)) {
					DownloadFile.viewUploadedDocument(file, request, response);
				}
			}
		 }catch(Exception e){
			 log.error("Exception occured in apiDownloadDocuments method in ImageViewController where moduleName is ->"+moduleName+" and filePath is->"+filePath+" and exception message is -->"+e.getMessage());
		 }
	}
	
	@GetMapping(value="/image/viewimage", name="Download Document")
	public void downloadDocumentsView(Model model, @RequestParam("filePath")String filePath,HttpServletResponse response,HttpServletRequest request) {
		String moduleName = ApplicationConstants.DocMember;
		try{
			if(Optional.ofNullable(filePath).isPresent()) {
				String path = rb.getString("UPLOAD.FILE.PATH")+File.separator+moduleName+File.separator+filePath;
				Path file = Paths.get(path);
				if (Files.exists(file)) {
					DownloadFile.viewUploadedDocument(file, request, response);
				}
			}
		 }catch(Exception e){
			 log.error("Exception occured in downloadDocumentsView method in ImageViewController where moduleName is ->"+moduleName+" and filePath is->"+filePath+" and exception message is -->"+e.getMessage());
		 }
	}
	
	@GetMapping(value="/image/catagories", name="Download Document")
	public void viewOimageList(Model model, @RequestParam("filePath")String filePath,HttpServletResponse response,HttpServletRequest request) {
		String moduleName = ApplicationConstants.SUBCATEGORY;
		try{
			if(Optional.ofNullable(filePath).isPresent()) {
				String path = rb.getString("UPLOAD.FILE.PATH")+File.separator+moduleName+File.separator+filePath;
				
				Path file = Paths.get(path);
				if (Files.exists(file)) {
					DownloadFile.viewUploadedDocument(file, request, response);
				}
			}
		 }catch(Exception e){
			 log.error("Exception occured in downloadDocumentsView method in ImageViewController where moduleName is ->"+moduleName+" and filePath is->"+filePath+" and exception message is -->"+e.getMessage());
		 }
	}
	
	@GetMapping(value="/image/bookcat", name="Download Document")
	public void viewBookimageList(Model model, @RequestParam("bookfilePath")String filePath,HttpServletResponse response,HttpServletRequest request) {
		String moduleName = ApplicationConstants.BOOKIMAGE;
		try{
			if(Optional.ofNullable(filePath).isPresent()) {
				String path = rb.getString("UPLOAD.FILE.PATH")+File.separator+moduleName+File.separator+filePath;
				Path file = Paths.get(path);
				if (Files.exists(file)) {
					DownloadFile.viewUploadedDocument(file, request, response);
				}
			}
		 }catch(Exception e){
			 log.error("Exception occured in viewBookimageList method in ImageViewController where moduleName is ->"+moduleName+" and filePath is->"+filePath+" and exception message is -->"+e.getMessage());
		 }
	}
	
	
}
