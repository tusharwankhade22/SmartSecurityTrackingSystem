package edu.tushar.securitytrackingsystem.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tushar.securitytrackingsystem.entity.Staff;
import edu.tushar.securitytrackingsystem.exception.StaffNotFoundException;
import edu.tushar.securitytrackingsystem.repository.StaffRepository;
import edu.tushar.securitytrackingsystem.service.StaffService;
import edu.tushar.securitytrackingsystem.util.QRCodeGenerator;
@Service
public class StaffServiceImpl implements StaffService{
	    @Autowired
	    private StaffRepository staffRepository;

	    @Autowired
	    private QRCodeGenerator qrCodeGenerator;

	    @Override
	    public Staff addStaff(Staff staff) {
	        Staff saved = staffRepository.save(staff);
	        String qrPath = qrCodeGenerator.generateQRCodeImage(saved.getId().toString());
	        saved.setQrCodePath(qrPath);
	        return staffRepository.save(saved); // update QR path
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
