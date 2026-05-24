package edu.tushar.securitytrackingsystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.tushar.securitytrackingsystem.entity.Attendance;
import edu.tushar.securitytrackingsystem.entity.AttendanceDTO;
import edu.tushar.securitytrackingsystem.entity.Staff;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	 Optional<Attendance> findByStaffAndDate(Staff staff, LocalDate date);

	 @Query("SELECT new edu.tushar.securitytrackingsystem.entity.AttendanceDTO(" +
		       "a.staff.id, a.staff.name, a.date, a.checkInTime, a.checkOutTime, a.workingMinutes, a.status)" +
		       " FROM Attendance a")
	List<AttendanceDTO> getAllAttendanceLogs();
}
