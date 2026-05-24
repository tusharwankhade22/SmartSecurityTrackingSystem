package edu.tushar.securitytrackingsystem.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tushar.securitytrackingsystem.entity.AttendanceDTO;



@Service
public interface AttendanceService {

	String markAttendance(Long staffId);

	List<AttendanceDTO> getAllAttendanceLogs();
	
	void markAbsentForToday();

	ResponseEntity<InputStreamResource> exportAttendanceToExcel();

	
}
