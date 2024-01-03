package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.DTO.RequestDtos.StudentRequestDto;
import com.example.LibraryManagementSystem.DTO.RequestDtos.UpdateMobileNoRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.CardResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.StudentResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.UpdateMobileNoResponseDto;
import com.example.LibraryManagementSystem.entity.Card;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.enums.CardStatus;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class studentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        Student student = new Student();

        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setName(studentRequestDto.getName());
        student.setMobNo(studentRequestDto.getMobNo());
        student.setEmail(studentRequestDto.getEmail());

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setValidTill("2023-12-31");
        card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);

        return "Student Added Succefully";
    }

    @Override
    public StudentResponseDto getStudentById(int studentId) throws Exception {

        try{
           Student student = studentRepository.findById(studentId).get();
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto.setId(student.getId());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setName(student.getName());
            studentResponseDto.setMobNo(student.getMobNo());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setEmail(student.getEmail());

            CardResponseDto cardResponseDto = new CardResponseDto();

            cardResponseDto.setCardStatus(student.getCard().getCardStatus());
            cardResponseDto.setId(student.getCard().getId());
            cardResponseDto.setIssueDate(student.getCard().getIssueDate());
            cardResponseDto.setUpdatedOn(student.getCard().getUpdatedOn());
            cardResponseDto.setValidTill(student.getCard().getValidTill());

            studentResponseDto.setCardResponseDto(cardResponseDto);

            return studentResponseDto;
        }
        catch (Exception e)
        {
            throw new Exception("Student not found!!");
        }
    }

    @Override
    public UpdateMobileNoResponseDto updateMobileNo(UpdateMobileNoRequestDto updateMobileNoRequestDto) throws StudentNotFoundException {

        try {
            Student student = studentRepository.findById(updateMobileNoRequestDto.getStudentId()).get();

            student.setMobNo(updateMobileNoRequestDto.getMobileNo());

            studentRepository.save(student);

            UpdateMobileNoResponseDto updateMobileNoResponseDto = new UpdateMobileNoResponseDto();

            updateMobileNoResponseDto.setName(student.getName());

            updateMobileNoResponseDto.setMobileNo(student.getMobNo());

            return updateMobileNoResponseDto;
        }
        catch (Exception e)
        {
            throw new StudentNotFoundException("Invali StudentId");
        }
    }
}
