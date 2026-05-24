package edu.tushar.securitytrackingsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tushar.securitytrackingsystem.entity.AttendanceDTO;
import edu.tushar.securitytrackingsystem.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
	@Autowired
	AttendanceService attendanceService;
	
	@PostMapping("/{staffId}/mark")
	public String markAttendance(@PathVariable Long staffId) {
        return attendanceService.markAttendance(staffId);
    }
	
	@GetMapping("/admin/logs")
	public List<AttendanceDTO> getLogs() {
	    return attendanceService.getAllAttendanceLogs();
	}
	
	@PostMapping("/admin/mark-absent")
	public String markAbsentees() {
	    attendanceService.markAbsentForToday();
	    return "Absent records marked for today.";
	}
	@GetMapping("/admin/logs/export/excel")
	public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
		return attendanceService.exportAttendanceToExcel();
	}
}
