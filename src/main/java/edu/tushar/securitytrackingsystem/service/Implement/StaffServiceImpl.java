package edu.tushar.securitytrackingsystem.service.Implement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.tushar.securitytrackingsystem.dto.request.StaffRequestDto;
import edu.tushar.securitytrackingsystem.dto.response.StaffResponseDto;
import edu.tushar.securitytrackingsystem.entity.Staff;
import edu.tushar.securitytrackingsystem.entity.StaffSequence;
import edu.tushar.securitytrackingsystem.exception.EmailExistsException;
import edu.tushar.securitytrackingsystem.exception.PhoneExistsException;
import edu.tushar.securitytrackingsystem.exception.StaffNotFoundException;
import edu.tushar.securitytrackingsystem.repository.StaffRepository;
import edu.tushar.securitytrackingsystem.repository.StaffSequenceRepository;
import edu.tushar.securitytrackingsystem.response.ResponseStructure;
import edu.tushar.securitytrackingsystem.service.StaffService;
import edu.tushar.securitytrackingsystem.util.QRCodeGenerator;
import edu.tushar.securitytrackingsystem.util.StaffCodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffSequenceRepository sequenceRepository;
    private final StaffCodeGenerator staffCodeGenerator;
    private final QRCodeGenerator qrCodeGenerator;

    @Override
    @Transactional
    public ResponseEntity<ResponseStructure<StaffResponseDto>> addStaff(
            StaffRequestDto dto) {
    	
    	if(staffRepository.existsByEmail(dto.getEmail())) {
    		throw new EmailExistsException("Email already exists");
    	}
    	
    	if(staffRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
    		throw new PhoneExistsException("Phone number already exists");
    	}
    	
    	String staffCode = generateStaffCode();
    	
        Staff staff = mapToEntity(dto);
        
        staff.setStaffCode(staffCode);
        staff.setQrCodeData("STAFF:"+staffCode);
        staff.setActive(true);
        
        Staff savedStaff = staffRepository.save(staff);

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

        return ResponseEntity.status(HttpStatus.OK).body(response);
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

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        throw new StaffNotFoundException("Staff with the given id is not found");
    }

    @Override
    public ResponseEntity<byte[]> getQrCode(Long id) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() ->
                        new StaffNotFoundException("Staff not found with id : " + id));

        byte[] qrImage = qrCodeGenerator.generateQRCodeImage(
                staff.getQrCodeData());

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.IMAGE_PNG)
                .body(qrImage);
    }
    
    @Override
	public ResponseEntity<ResponseStructure<StaffResponseDto>> updateStaff(Long id, StaffRequestDto dto) {
    	
    	Optional<Staff> optionalStaff = staffRepository.findById(id);
    	
    	if(optionalStaff.isEmpty()) {
    		throw new StaffNotFoundException("Staff is not found with given id!");
    	}
    	
    	Staff staff = optionalStaff.get();
    	
    	Optional<Staff> existingEmail = staffRepository.findByEmail(dto.getEmail());
    	
    	if(existingEmail.isPresent() && !existingEmail.get().getId().equals(id)) {
    		throw new EmailExistsException("Email is already exists");
    	}
    	
    	Optional<Staff> existingPhoneNumber = staffRepository.findByPhoneNumber(dto.getPhoneNumber());
    	
    	if(existingPhoneNumber.isPresent() && !existingPhoneNumber.get().getId().equals(id)) {
    		throw new PhoneExistsException("Phone number is already exists");
    	}
    	 
    	updateStaffEntity(staff, dto);
    	
    	Staff updatedStaff = staffRepository.save(staff);
    	
    	ResponseStructure<StaffResponseDto> response = new ResponseStructure<>();
    	response.setStatus("Success");
    	response.setMessage("Staff Updated Successfully!!");
    	response.setData(mapToDto(updatedStaff));
    	
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    //To update staff
    private void updateStaffEntity(Staff staff, StaffRequestDto dto) {

        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setEmail(dto.getEmail());
        staff.setPhoneNumber(dto.getPhoneNumber());
        staff.setDesignation(dto.getDesignation());
        staff.setGender(dto.getGender());
        staff.setAddress(dto.getAddress());
        staff.setDateOfBirth(dto.getDateOfBirth());
        staff.setEmergencyContact(dto.getEmergencyContact());
        staff.setJoiningDate(dto.getJoiningDate());

    }

    // DTO -> Entity
    private Staff mapToEntity(StaffRequestDto dto) {

        Staff staff = new Staff();

        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setPhoneNumber(dto.getPhoneNumber());
        staff.setEmail(dto.getEmail());
        staff.setDateOfBirth(dto.getDateOfBirth());
        staff.setAddress(dto.getAddress());
        staff.setDesignation(dto.getDesignation());
        staff.setAddress(dto.getAddress());
        staff.setDateOfBirth(dto.getDateOfBirth());
        staff.setGender(dto.getGender());
        staff.setEmergencyContact(dto.getEmergencyContact());
        staff.setJoiningDate(dto.getJoiningDate());
        return staff;
    }

    // Entity -> DTO
    private StaffResponseDto mapToDto(Staff staff) {

        StaffResponseDto dto = new StaffResponseDto();

        dto.setId(staff.getId());
        dto.setStaffCode(staff.getStaffCode());
        dto.setFirstName(staff.getFirstName());
        dto.setLastName(staff.getLastName());
        dto.setEmail(staff.getEmail());
        dto.setPhoneNumber(staff.getPhoneNumber());
        dto.setDesignation(staff.getDesignation());
        dto.setGender(staff.getGender());
        dto.setJoiningDate(staff.getJoiningDate());
        dto.setActive(staff.getActive());

        return dto;
    }
    
    private String generateStaffCode() {
    	LocalDate today = LocalDate.now();
    	int year = today.getYear() % 100;
    	int month = today.getMonthValue();
    	
    	StaffSequence sequence = sequenceRepository.findByYearAndMonth(year, month)
    			                .orElseGet(() -> {
    			                	StaffSequence newSeqence = new StaffSequence();
    			                	newSeqence.setYear(year);
    			                	newSeqence.setMonth(month);
    			                	newSeqence.setLastSequence(0);
    			                	
    			                	return sequenceRepository.save(newSeqence);
    			                });
    	
    	sequence.setLastSequence(sequence.getLastSequence() + 1);
    	sequenceRepository.save(sequence);
    	
    	return staffCodeGenerator.generateStaffCode(year, month, sequence.getLastSequence());
    }

	
	@Override
	public ResponseEntity<ResponseStructure<String>> activateStaff(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deactivateStaff(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}