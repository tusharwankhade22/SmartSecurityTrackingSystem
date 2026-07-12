package edu.tushar.securitytrackingsystem.service;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tushar.securitytrackingsystem.dto.request.StaffRequestDto;
import edu.tushar.securitytrackingsystem.dto.request.StaffStatusRequestDto;
import edu.tushar.securitytrackingsystem.dto.response.StaffResponseDto;
import edu.tushar.securitytrackingsystem.response.ResponseStructure;
import jakarta.validation.Valid;

@Service
public interface StaffService {

    ResponseEntity<ResponseStructure<StaffResponseDto>>
    addStaff(StaffRequestDto dto);

    ResponseEntity<ResponseStructure<List<StaffResponseDto>>>
    getAllStaff();

    ResponseEntity<ResponseStructure<StaffResponseDto>>
    getStaffById(Long id);

    ResponseEntity<ResponseStructure<StaffResponseDto>>
    updateStaff(Long id, StaffRequestDto dto);

    ResponseEntity<byte[]>
    getQrCode(Long id);

    ResponseEntity<ResponseStructure<StaffResponseDto>> updateActiveStatus(Long id, @Valid StaffStatusRequestDto dto);
}
