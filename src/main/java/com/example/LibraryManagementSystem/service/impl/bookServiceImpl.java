package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String addBook(Book book) throws Exception {

        Author author;

        try
        {
            author = authorRepository.findById(book.getAuthor().getId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Author Not Found");
        }

        author.getBook().add(book);
        book.setAuthor(author);

        authorRepository.save(author);

        return "Book Added Successfully";
    }
}
