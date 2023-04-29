package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.DTO.RequestDtos.StudentRequestDto;
import com.example.LibraryManagementSystem.DTO.RequestDtos.UpdateMobileNoRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.StudentResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.UpdateMobileNoResponseDto;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto)
    {
        return studentService.addStudent(studentRequestDto);
    }

    @GetMapping("/get")
    public StudentResponseDto getStudentById(@RequestParam int studentId) throws Exception {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/update_mobile")
    public UpdateMobileNoResponseDto updateMobileNo(@RequestBody UpdateMobileNoRequestDto updateMobileNoRequestDto) throws StudentNotFoundException {
        return studentService.updateMobileNo(updateMobileNoRequestDto);
    }
}
