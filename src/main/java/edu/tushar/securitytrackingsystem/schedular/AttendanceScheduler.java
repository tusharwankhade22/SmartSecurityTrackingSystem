package edu.tushar.securitytrackingsystem.schedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import edu.tushar.securitytrackingsystem.service.AttendanceService;

public class AttendanceScheduler {

	@Autowired
	private AttendanceService attendanceService;
	
	@Scheduled(cron = "0 59 23 * * ?")
	public void autoMarkAbsentees() {
	        attendanceService.markAbsentForToday();
	        System.out.println("✅ [Auto] Absent marking completed for today.");
	}
}
