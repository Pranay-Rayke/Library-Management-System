package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.DTO.RequestDtos.IssueBookRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.IssueBookResponseDto;

public interface TransactionService {

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception;
}
