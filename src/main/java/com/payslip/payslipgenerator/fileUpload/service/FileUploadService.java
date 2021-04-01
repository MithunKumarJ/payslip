package com.payslip.payslipgenerator.fileUpload.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payslip.payslipgenerator.config.PaySlipConstant;
import com.payslip.payslipgenerator.fileUpload.entity.PayslipValidate;
import com.payslip.payslipgenerator.fileUpload.entity.Resource;
import com.payslip.payslipgenerator.fileUpload.fileUploadRepository.FileUploadRepository;
import com.payslip.payslipgenerator.fileUpload.fileUploadRepository.PayslipValidationRepository;
import com.payslip.payslipgenerator.fileUpload.helper.ExcelHelper;

@Service
public class FileUploadService {

	@Autowired
	FileUploadRepository fileUploadRepository;

	@Autowired
	PayslipValidationRepository payslipValidationRepository;

	public void save(MultipartFile file) {
		try {
			List<Resource> resources = ExcelHelper.excelToTutorials(file.getInputStream());
			validateMasterDb(resources);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}

	}

	public void validateMasterDb(List<Resource> resources) {

		PayslipValidate validate = new PayslipValidate();
		int flag = 0;
		boolean status = true;
		for (Resource resource : resources) {
			if (resource.getECode() != 0) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getAadhaar() != null) {
				if (resource.getAadhaar().length() == 12) {
					flag = 1;
				}
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getBank() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getBankAccountNumber() != null) {
				if (resource.getBankAccountNumber().length() <= 9) {
					flag = 1;
				}
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getBankIfsc() != null) {
				if (resource.getBankIfsc().length() == 11) {
					flag = 1;
				}
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getCategory() != "") {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getDateOfBirth() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getDateOfJoining() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getEsi() != null) {
				flag = 1;

			} else {
				flag = 0;
				status = false;
			}
			if (resource.getFirstName() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getName() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getNameAtEpfRecord() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getPan() != null) {
				if (resource.getPan().length() != 10) {

					flag = 1;
				}
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getPhone() != null) {
				if (resource.getPhone().length() == 10) {
					flag = 1;
				}
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getPlant() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getSex() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getStatus() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (resource.getUan() != null) {
				flag = 1;
			} else {
				flag = 0;
				status = false;
			}
			if (flag == 0 || status == false) {
				status = true;
				try {
					BeanUtils.copyProperties(validate, resource);
					payslipValidationRepository.save(validate);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} else if (flag == 1) {
				fileUploadRepository.save(resource);
			}
		}

	}

	public String getMasterCount() {
		int masterCount = fileUploadRepository.findAll().size();
		int validateCount = payslipValidationRepository.findAll().size();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("master", masterCount);
		jsonObject.put("validate", validateCount);
		return jsonObject.toString();
	}

	public String getMasterResource(int pageNo) {
		if (pageNo > 0)
			pageNo = pageNo - 1;
		Pageable pageable = PageRequest.of(pageNo, PaySlipConstant.itemPerPage);
		Page<Resource> MasterData = fileUploadRepository.findAll(pageable);
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String JSONObject = gson.toJson(MasterData);
		return JSONObject;
	}

	public String getValidateResource(int pageNo) {
		if (pageNo > 0)
			pageNo = pageNo - 1;
		Pageable pageable = PageRequest.of(pageNo, PaySlipConstant.itemPerPage);
		Page<PayslipValidate> validateData = payslipValidationRepository.findAll(pageable);
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String JSONObject = gson.toJson(validateData);
		return JSONObject;
	}

}
