package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.DTO.ResponseDtos.GetAuthorResponseDto;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author)
    {
        return authorService.addAuthor(author);
    }

    @GetMapping("/get_author_by_email")
    public GetAuthorResponseDto getAuthorByEmailId(@RequestParam String emailId)
    {
       return authorService.getAuthorByEmailId(emailId);
    }
}
