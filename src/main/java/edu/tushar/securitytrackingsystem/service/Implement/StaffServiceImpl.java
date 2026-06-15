package edu.tushar.securitytrackingsystem.service.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tushar.securitytrackingsystem.dto.request.StaffRequestDto;
import edu.tushar.securitytrackingsystem.dto.response.StaffResponseDto;
import edu.tushar.securitytrackingsystem.entity.Staff;
import edu.tushar.securitytrackingsystem.exception.StaffNotFoundException;
import edu.tushar.securitytrackingsystem.repository.StaffRepository;
import edu.tushar.securitytrackingsystem.response.ResponseStructure;
import edu.tushar.securitytrackingsystem.service.StaffService;
import edu.tushar.securitytrackingsystem.util.QRCodeGenerator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final QRCodeGenerator qrCodeGenerator;

    @Override
    public ResponseEntity<ResponseStructure<StaffResponseDto>> addStaff(
            StaffRequestDto dto) {

        Staff staff = mapToEntity(dto);

        Staff savedStaff = staffRepository.save(staff);

        String qrPath = qrCodeGenerator.generateQRCodeImage(
                savedStaff.getId().toString());

        savedStaff.setQrCodePath(qrPath);

        savedStaff = staffRepository.save(savedStaff);

        ResponseStructure<StaffResponseDto> response =
                new ResponseStructure<>();

        response.setStatus("SUCCESS");
        response.setMessage("Staff added successfully");
        response.setData(mapToDto(savedStaff));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public ResponseEntity<ResponseStructure<List<StaffResponseDto>>> getAllStaff() {

        List<StaffResponseDto> staffList = staffRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();

        ResponseStructure<List<StaffResponseDto>> response =
                new ResponseStructure<>();

        response.setStatus("SUCCESS");
        response.setMessage("Staff fetched successfully");
        response.setData(staffList);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ResponseStructure<StaffResponseDto>> getStaffById(Long id) {

        Optional<Staff> optionalStaff = staffRepository.findById(id);

        ResponseStructure<StaffResponseDto> response =
                new ResponseStructure<>();

        if (optionalStaff.isPresent()) {

            response.setStatus("SUCCESS");
            response.setMessage("Staff found successfully");
            response.setData(mapToDto(optionalStaff.get()));

            return ResponseEntity.ok(response);
        }

        response.setStatus("ERROR");
        response.setMessage("Staff not found with id : " + id);
        response.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @Override
    public void deleteStaff(Long id) {

        Optional<Staff> optionalStaff = staffRepository.findById(id);

        if (optionalStaff.isPresent()) {
            staffRepository.delete(optionalStaff.get());
        } else {
            throw new StaffNotFoundException("Staff with the given id is not found");
        }
    }

    // DTO -> Entity
    private Staff mapToEntity(StaffRequestDto dto) {

        Staff staff = new Staff();

        staff.setName(dto.getName());
        staff.setMobile(dto.getMobile());
        staff.setEmail(dto.getEmail());
        staff.setAge(dto.getAge());
        staff.setAddress(dto.getAddress());
        staff.setDesignation(dto.getDesignation());

        return staff;
    }

    // Entity -> DTO
    private StaffResponseDto mapToDto(Staff staff) {

        StaffResponseDto dto = new StaffResponseDto();

        dto.setId(staff.getId());
        dto.setName(staff.getName());
        dto.setMobile(staff.getMobile());
        dto.setEmail(staff.getEmail());
        dto.setAge(staff.getAge());
        dto.setAddress(staff.getAddress());
        dto.setDesignation(staff.getDesignation());
        dto.setQrCodePath(staff.getQrCodePath());

        return dto;
    }
}