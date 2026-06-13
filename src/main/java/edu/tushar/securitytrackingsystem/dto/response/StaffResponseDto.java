package edu.tushar.securitytrackingsystem.dto.response;

import lombok.Data;

@Data
public class StaffResponseDto {

    private Long id;
    private String name;
    private String mobile;
    private String email;
    private int age;
    private String address;
    private String designation;
    private String qrCodePath;
}