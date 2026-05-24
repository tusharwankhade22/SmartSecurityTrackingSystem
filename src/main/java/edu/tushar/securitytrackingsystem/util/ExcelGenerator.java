package edu.tushar.securitytrackingsystem.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.tushar.securitytrackingsystem.entity.AttendanceDTO;

public class ExcelGenerator {

	public static ByteArrayInputStream generateExcel(List<AttendanceDTO> data) throws IOException {
		String[] headers= {"Staff Name", "Date", "Check-In", "Check-Out", "Working Minutes", "Status"};
		try(Workbook workbook=new XSSFWorkbook(); ByteArrayOutputStream out=new ByteArrayOutputStream()){
			Sheet sheet=workbook.createSheet("Attendence");
			
			Row headerRow=sheet.createRow(0);
			for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
			int rowIdx = 1;
			for (AttendanceDTO att : data) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(att.getStaffName());
                row.createCell(1).setCellValue(att.getDate().toString());
                row.createCell(2).setCellValue(att.getCheckInTime() != null ? att.getCheckInTime().toString() : "N/A");
                row.createCell(3).setCellValue(att.getCheckOutTime() != null ? att.getCheckOutTime().toString() : "N/A");
                row.createCell(4).setCellValue(att.getWorkingMinutes());
                row.createCell(5).setCellValue(att.getStatus());
            }
			workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
			
		}
	}
}
