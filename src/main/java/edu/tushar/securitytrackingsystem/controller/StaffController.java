package edu.tushar.securitytrackingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tushar.securitytrackingsystem.dto.request.StaffRequestDto;
import edu.tushar.securitytrackingsystem.dto.response.StaffResponseDto;
import edu.tushar.securitytrackingsystem.response.ResponseStructure;
import edu.tushar.securitytrackingsystem.service.StaffService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin(origins = "*")
public class StaffController {
	@Autowired
    private StaffService staffService;
	 
    @PostMapping
    public ResponseEntity<ResponseStructure<StaffResponseDto>> addStaff(@Valid @RequestBody StaffRequestDto staff) {
        return staffService.addStaff(staff); 
    }
  
    @GetMapping
    public ResponseEntity<ResponseStructure<List<StaffResponseDto>>> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<StaffResponseDto>> getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }
 
    @GetMapping("/{id}/qr")
    public ResponseEntity<byte[]> getQrCode(@PathVariable Long id) {
        return staffService.getQrCode(id);   
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<StaffResponseDto>> updateStaff(@PathVariable Long id, @Valid @RequestBody StaffRequestDto dto) {
    	return staffService.updateStaff(id, dto);
    }
    
    
}
