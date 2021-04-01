package com.payslip.payslipgenerator.fileUpload.fileUploadRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payslip.payslipgenerator.fileUpload.entity.PayslipValidate;

public interface PayslipValidationRepository extends JpaRepository<PayslipValidate, Integer>{

}
