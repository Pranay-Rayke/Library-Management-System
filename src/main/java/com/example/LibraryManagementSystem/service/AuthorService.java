package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.DTO.ResponseDtos.GetAuthorResponseDto;
import com.example.LibraryManagementSystem.entity.Author;

public interface AuthorService {

    public String addAuthor(Author author);

    public GetAuthorResponseDto getAuthorByEmailId(String emailId);
}
