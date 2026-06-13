package edu.tushar.securitytrackingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tushar.securitytrackingsystem.dto.request.StaffRequestDto;
import edu.tushar.securitytrackingsystem.dto.response.StaffResponseDto;
import edu.tushar.securitytrackingsystem.entity.Staff;
import edu.tushar.securitytrackingsystem.response.ResponseStructure;
import edu.tushar.securitytrackingsystem.service.StaffService;


@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {
	@Autowired
    private StaffService staffService;

    @PostMapping
    public ResponseEntity<ResponseStructure<StaffResponseDto>> addStaff(@RequestBody StaffRequestDto staff) {
        return staffService.addStaff(staff); 
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }
}
