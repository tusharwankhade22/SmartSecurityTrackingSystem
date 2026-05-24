package edu.tushar.securitytrackingsystem.service.Implement;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


import edu.tushar.securitytrackingsystem.entity.Attendance;
import edu.tushar.securitytrackingsystem.entity.AttendanceDTO;
import edu.tushar.securitytrackingsystem.entity.Staff;
import edu.tushar.securitytrackingsystem.exception.StaffNotFoundException;
import edu.tushar.securitytrackingsystem.repository.AttendanceRepository;
import edu.tushar.securitytrackingsystem.repository.StaffRepository;
import edu.tushar.securitytrackingsystem.service.AttendanceService;
import edu.tushar.securitytrackingsystem.util.ExcelGenerator;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	StaffRepository staffRepository;

	@Override
	public String markAttendance(Long staffId) {
		 Staff staff = staffRepository.findById(staffId)
	                .orElseThrow(() -> new StaffNotFoundException());
		 
	     LocalDate today = LocalDate.now();
	     LocalTime now = LocalTime.now();
	     
	     return attendanceRepository.findByStaffAndDate(staff, today).map(attendance -> {
	    	 if (attendance.getCheckOutTime() == null) {
	    		    attendance.setCheckOutTime(now);
	    		    calculateWorkingMinutes(attendance);
	    		    if (attendance.getWorkingMinutes() >= 300) {
	    		        attendance.setStatus("PRESENT");
	    		    } else {
	    		        attendance.setStatus("HALFDAY");
	    		    }

	    		    attendanceRepository.save(attendance);
	    		    return "Check-out marked successfully at " + now;
	    		}
                else {
	                return "Already checked in and checked out today.";
	            }
	        }).orElseGet(() -> {
	            Attendance newAttendance = new Attendance();
	            newAttendance.setStaff(staff);
	            newAttendance.setDate(today);
	            newAttendance.setCheckInTime(now);
	            attendanceRepository.save(newAttendance);
	            return "Check-in marked successfully at " + now;
	        });
	    }
	
	 public void calculateWorkingMinutes(Attendance attendance) {
	    if (attendance.getCheckInTime() != null && attendance.getCheckOutTime() != null) {
	        long minutes = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime()).toMinutes();
	        attendance.setWorkingMinutes(minutes);
	    } else {
	        attendance.setWorkingMinutes(0L);
	    }
	 }
	 @Override
	 public List<AttendanceDTO> getAllAttendanceLogs() {
		    List<Attendance> attendances = attendanceRepository.findAll();

		    return attendances.stream().map(att -> {
		        AttendanceDTO dto = new AttendanceDTO();
		        dto.setStaffName(att.getStaff().getName());
		        dto.setDate(att.getDate());
		        dto.setCheckInTime(att.getCheckInTime());
		        dto.setCheckOutTime(att.getCheckOutTime());
		        dto.setWorkingMinutes(att.getWorkingMinutes());
		        dto.setStatus(att.getStatus());
		        return dto;
		    }).collect(Collectors.toList());
		}
	 @Override
	 public void markAbsentForToday() {
	     LocalDate today = LocalDate.now();
	     List<Staff> allStaff = staffRepository.findAll();

	     for (Staff staff : allStaff) {
	         boolean hasAttendance = attendanceRepository.findByStaffAndDate(staff, today).isPresent();

	         if (!hasAttendance) {
	             Attendance absentRecord = new Attendance();
	             absentRecord.setStaff(staff);
	             absentRecord.setDate(today);
	             absentRecord.setStatus("ABSENT");
	             absentRecord.setWorkingMinutes(0L);
	             attendanceRepository.save(absentRecord);
	         }
	     }
	 }

	@Override
	public ResponseEntity<InputStreamResource> exportAttendanceToExcel() {
		try {
            List<AttendanceDTO> logs = attendanceRepository.getAllAttendanceLogs(); // Your logic here
            ByteArrayInputStream in = ExcelGenerator.generateExcel(logs);
            InputStreamResource file = new InputStreamResource(in);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attendance_logs.xlsx")
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(file);

        } catch (Exception e) {
            throw new RuntimeException("Failed to export Excel: " + e.getMessage());
        }

	}
 
	 
}
