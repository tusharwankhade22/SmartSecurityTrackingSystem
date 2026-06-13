package edu.tushar.securitytrackingsystem.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class StaffServiceImpl implements StaffService{
	    @Autowired
	    private StaffRepository staffRepository;

	    @Autowired
	    private QRCodeGenerator qrCodeGenerator;

	    @Override
	    public ResponseEntity<ResponseStructure<StaffResponseDto>> addStaff(StaffRequestDto dto) {
	    	
	    	Staff staff = new Staff();
	    	staff.setName(dto.getName());
	        staff.setMobile(dto.getMobile());
	        staff.setEmail(dto.getEmail());
	        staff.setAge(dto.getAge());
	        staff.setAddress(dto.getAddress());
	        staff.setDesignation(dto.getDesignation());
	        
	        Staff saved = staffRepository.save(staff);
	        String qrPath = qrCodeGenerator.generateQRCodeImage(saved.getId().toString());
	        saved.setQrCodePath(qrPath);
	        saved =staffRepository.save(saved); 
	       
	        StaffResponseDto responseDto = new StaffResponseDto();
	        responseDto.setId(saved.getId());
	        responseDto.setName(saved.getName());
	        responseDto.setMobile(saved.getMobile());
	        responseDto.setEmail(saved.getEmail());
	        responseDto.setAge(saved.getAge());
	        responseDto.setAddress(saved.getAddress());
	        responseDto.setDesignation(saved.getDesignation());
	        responseDto.setQrCodePath(saved.getQrCodePath());
	        
	        ResponseStructure<StaffResponseDto> response = new ResponseStructure<>();
	        response.setStatus("Success");
	        response.setMessage("Staff added successfully");
	        response.setData(responseDto);
	        
	        return new ResponseEntity<>(
	                response,
	                HttpStatus.CREATED);
	    }
	    
	    @Override
	    public List<Staff> getAllStaff() {
	        return staffRepository.findAll();
	    }

	    @Override
	    public Staff getStaffById(Long id) {
	    	return staffRepository.findById(id)
	                .orElseThrow(() -> new StaffNotFoundException());
	    }

	    @Override
	    public void deleteStaff(Long id) {
	    	if (!staffRepository.existsById(id)) {
	            throw new StaffNotFoundException();
	        }
	        staffRepository.deleteById(id);
	    }
}
