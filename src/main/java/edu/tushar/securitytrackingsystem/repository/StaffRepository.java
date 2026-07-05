package edu.tushar.securitytrackingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tushar.securitytrackingsystem.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{
	
	Optional<Staff> findByEmail(String email);
	
	Optional<Staff> findByPhoneNumber(String phoneNumber);
	
	Optional<Staff> findByStaffCode(String staffCode);
	
	Optional<Staff> findByQrCodeData(String qrCodeData);
	
	boolean existsByEmail(String email);
	
	boolean existsByPhoneNumber(String phoneNumber);
}
