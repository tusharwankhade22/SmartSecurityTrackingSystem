package edu.tushar.securitytrackingsystem.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class AttendanceDTO {
	private long staffId;
	private String staffName;
	private LocalDate date;
	private LocalTime checkInTime;
	private LocalTime checkOutTime;
	private Long workingMinutes;
	private String status;
	
	public AttendanceDTO(Long staffId, String staffName, LocalDate date, LocalTime checkInTime,
            LocalTime checkOutTime, Long workingMinutes, String status) {
       this.staffId = staffId;
       this.staffName = staffName;
       this.date = date;
       this.checkInTime = checkInTime;
       this.checkOutTime = checkOutTime;
       this.workingMinutes = workingMinutes;
       this.status = status;
}

	public AttendanceDTO() {
	}
}
