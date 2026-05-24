package edu.tushar.securitytrackingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tushar.securitytrackingsystem.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{
	
}
