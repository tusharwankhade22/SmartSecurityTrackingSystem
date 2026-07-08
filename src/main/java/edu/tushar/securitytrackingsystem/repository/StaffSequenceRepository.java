package edu.tushar.securitytrackingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import edu.tushar.securitytrackingsystem.entity.StaffSequence;
import jakarta.persistence.LockModeType;

@Repository
public interface StaffSequenceRepository extends JpaRepository<StaffSequence, Long> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<StaffSequence> findByYearAndMonth(int year, int month);
}
