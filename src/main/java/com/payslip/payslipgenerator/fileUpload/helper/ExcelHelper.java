package com.payslip.payslipgenerator.fileUpload.helper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.payslip.payslipgenerator.fileUpload.entity.Resource;
import com.payslip.payslipgenerator.fileUpload.fileUploadRepository.PayslipValidationRepository;

@Service
public class ExcelHelper {
	
	

	@Autowired
	PayslipValidationRepository payslipValidationRepository;
	
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "ECODE", "PLANT", "DOJ", "CAT","NAME","NAME AT EPF RECORD","SEX","DOB","FNAME","BANK","BANKAC","BANKIFSC","AADHAAR","PHONE","PAN","ESI","UAN","STATUS" };
	  static String SHEET = "Resource";

	  public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	 public  static List<Resource> excelToTutorials(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheetAt(0);
	      Iterator<Row> rows = sheet.iterator();

	      List<Resource> resources = new ArrayList<Resource>();
	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();
	        
	        Resource resource = new Resource();
	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();
	          currentCell.setCellType(CellType.STRING);
	          switch (cellIdx) {
	          case 0:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setECode(Integer.parseInt(currentCell.getStringCellValue()));
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setECode((int)currentCell.getNumericCellValue());
	            break;
	          case 1:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setPlant(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setPlant(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 2:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setDateOfJoining(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setDateOfJoining(String.valueOf(currentCell.getNumericCellValue()));  
	            break;
	          case 3:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setCategory(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setCategory(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 4:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setName(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setName(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 5:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setNameAtEpfRecord(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setNameAtEpfRecord(String.valueOf(currentCell.getNumericCellValue()));	  
	            break;
	          case 6:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setSex(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setSex(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 7:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setDateOfBirth(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	   resource.setDateOfBirth(String.valueOf(currentCell.getNumericCellValue())); 	  
	            break;
	          case 8:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setFirstName(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setFirstName(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 9:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setBank(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setBank(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 10:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setBankAccountNumber(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setBankAccountNumber(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 11:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setBankIfsc(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setBankIfsc(String.valueOf(currentCell.getNumericCellValue()));	  
	            break;
	          case 12:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setAadhaar(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setAadhaar(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 13:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setPhone(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        	  resource.setPhone(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 14:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setPan(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC)
	        		  resource.setPan(String.valueOf(currentCell.getNumericCellValue()));  
	            break;
	          case 15:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setEsi(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC) 
	        	  resource.setEsi(String.valueOf(currentCell.getNumericCellValue()));  
	            break;
	          case 16:
	        	  if(currentCell.getCellType()==CellType.STRING) 
	        	  resource.setUan(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC) 
	        	  resource.setUan(String.valueOf(currentCell.getNumericCellValue()));
	            break;
	          case 17:
	        	  if(currentCell.getCellType()==CellType.STRING)
	        	  resource.setStatus(currentCell.getStringCellValue());
	        	  else if(currentCell.getCellType()==CellType.NUMERIC) 
	        	  resource.setStatus(String.valueOf(currentCell.getNumericCellValue()));
	            break;  
	          
	          default:
	            break;
	          }

	          cellIdx++;
	        }
	        	resources.add(resource);	
	      }
	      workbook.close();
	      return resources;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }


}
