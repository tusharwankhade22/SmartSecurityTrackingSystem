package edu.tushar.securitytrackingsystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "staff_code", nullable = false, unique = true, length = 20)
	private String staffCode;
	
	 @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
	
	@Column(name = "last_name", length = 50)
	private String lastName;
	
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    private LocalDate dateOfBirth;
    
    @Column(name = "emergency_contact", length = 10)
    private String emergencyContact;
    
    private LocalDate joiningDate;
    
    @Column(length = 10)
    private String gender;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    @Column(nullable = false, length = 50)
    private String designation;
    
    @Column(name = "qr_code_data", nullable = false, unique = true)
    private String qrCodeData;  
    
    @Column(nullable = false)
    private Boolean active = true; 
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
}
