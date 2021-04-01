package com.payslip.payslipgenerator.fileUpload.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PayslipValidate {
	@Id
	private int eCode;
	private String plant;
	private String dateOfJoining;
	private String category;
	private String name;
	private String nameAtEpfRecord;
	private String sex;
	private String dateOfBirth;
	private String firstName;
	private String bank;
	private String bankAccountNumber;
	private String bankIfsc;
	private String aadhaar;
	private String phone;
	private String pan;
	private String esi;
	private String uan;
	private String status;
}
