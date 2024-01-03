package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.DTO.RequestDtos.StudentRequestDto;
import com.example.LibraryManagementSystem.DTO.RequestDtos.UpdateMobileNoRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.StudentResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.UpdateMobileNoResponseDto;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;

public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public StudentResponseDto getStudentById(int studentId) throws Exception;

    public UpdateMobileNoResponseDto updateMobileNo(UpdateMobileNoRequestDto updateMobileNoRequestDto) throws StudentNotFoundException;
}
