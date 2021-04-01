package com.payslip.payslipgenerator.fileUpload.fileUploadRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payslip.payslipgenerator.fileUpload.entity.Resource;

@Repository
public interface FileUploadRepository extends JpaRepository<Resource, Integer> {
	
	Page<Resource> findAll(Pageable pageable);

}
