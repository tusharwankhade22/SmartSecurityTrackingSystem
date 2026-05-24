package edu.tushar.securitytrackingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.tushar.securitytrackingsystem.entity.Staff;

@Service
public interface StaffService {
	Staff addStaff(Staff staff);
    List<Staff> getAllStaff();
    Staff getStaffById(Long id);
    void deleteStaff(Long id);
}
