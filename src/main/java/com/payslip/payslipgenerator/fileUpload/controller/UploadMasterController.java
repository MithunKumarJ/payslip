package com.payslip.payslipgenerator.fileUpload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.payslip.payslipgenerator.common.dto.ResponseMessage;
import com.payslip.payslipgenerator.fileUpload.helper.ExcelHelper;
import com.payslip.payslipgenerator.fileUpload.service.FileUploadService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/payslip")
public class UploadMasterController {

	@Autowired
	FileUploadService fileuploadservice;
	@PostMapping("/upload")
	public ResponseEntity<?> uploadMasterToDB(@RequestParam("file") MultipartFile file){
		 String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
		      try {
		    	fileuploadservice.save(file);
		        message = "Uploaded the file successfully: " + file.getOriginalFilename();
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		      } catch (Exception e) {
		    	 log.error("Exception in upload master class:"+e.getLocalizedMessage());
		        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		      }
		    }
		message = "Please upload an excel file!";
	    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
	
	@GetMapping("/mastercount")
	public ResponseEntity<?> getMasterCount(){
		
		String count="";
		try {
			count = fileuploadservice.getMasterCount();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}
	@GetMapping("/getMasterResource")
	public ResponseEntity<?> getMasterResource(@RequestParam(name = "pageno", required = false, defaultValue = "0") int pageNo){
		
		String masterResource="";
		try {
			masterResource = fileuploadservice.getMasterResource(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(masterResource);
	}
	@GetMapping("/getValidateResource")
	public ResponseEntity<?> getValidateResource(@RequestParam(name = "pageno", required = false, defaultValue = "0") int pageNo){
		
		String validateResource="";
		try {
			validateResource = fileuploadservice.getValidateResource(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(validateResource);
	}

	
	
}
