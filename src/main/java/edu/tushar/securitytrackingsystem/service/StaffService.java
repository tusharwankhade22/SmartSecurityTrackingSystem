package edu.tushar.securitytrackingsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tushar.securitytrackingsystem.dto.request.StaffRequestDto;
import edu.tushar.securitytrackingsystem.dto.response.StaffResponseDto;
import edu.tushar.securitytrackingsystem.entity.Staff;
import edu.tushar.securitytrackingsystem.response.ResponseStructure;

@Service
public interface StaffService {
	ResponseEntity<ResponseStructure<StaffResponseDto>>
	addStaff(StaffRequestDto dto);
	ResponseEntity<ResponseStructure<List<StaffResponseDto>>> getAllStaff();
	ResponseEntity<ResponseStructure<StaffResponseDto>> getStaffById(Long id);
    void deleteStaff(Long id);
    ResponseEntity<byte[]> getQrCode(Long id);
}
