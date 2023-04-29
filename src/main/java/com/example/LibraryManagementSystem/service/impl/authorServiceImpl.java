package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.DTO.ResponseDtos.GetAuthorResponseDto;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class authorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Override
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author added successfully";
    }

    @Override
    public GetAuthorResponseDto getAuthorByEmailId(String emailId) {

        Author author = authorRepository.findByEmail(emailId);

        GetAuthorResponseDto getAuthorResponseDto = new GetAuthorResponseDto();

        getAuthorResponseDto.setEmail(author.getEmail());

        getAuthorResponseDto.setName(author.getName());

        return getAuthorResponseDto;
    }
}
