package com.example.LibraryManagementSystem.DTO.ResponseDtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAuthorResponseDto {

    private String name;

    private String email;
}
