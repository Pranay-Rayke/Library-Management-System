package com.example.LibraryManagementSystem.DTO.RequestDtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateMobileNoRequestDto {

    private int studentId;

    private String mobileNo;
}
