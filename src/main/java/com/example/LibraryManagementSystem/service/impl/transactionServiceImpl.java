package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.DTO.RequestDtos.IssueBookRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDtos.IssueBookResponseDto;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.entity.Card;
import com.example.LibraryManagementSystem.entity.Transaction;
import com.example.LibraryManagementSystem.enums.CardStatus;
import com.example.LibraryManagementSystem.enums.TransactionStatus;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.CardRepositroy;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import com.example.LibraryManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class transactionServiceImpl implements TransactionService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepositroy cardRepositroy;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionId(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        Card card;

        try
        {
            card = cardRepositroy.findById(issueBookRequestDto.getCardId()).get();
        }
        catch (Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card Id Not Valid !!");
        }

        transaction.setCard(card);

        Book book;

        try
        {
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch (Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book Id Not Valid !!");
        }
        transaction.setBook(book);

        if(card.getCardStatus() != CardStatus.ACTIVATED)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card not activated");
        }

        if(book.isIssued() == true)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book already issued !!");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);

        card.getBookList().add(book);
        card.getTransactionList().add(transaction);
        cardRepositroy.save(card); //save card, book and transaction

        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTittle());
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionId());
        issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());

        String text = "Congratulations !! " + card.getStudent().getName() + " you have been issued "+book.getTittle()+" book";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pranay8308@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book");
        message.setText(text);
        javaMailSender.send(message);

        return issueBookResponseDto;
    }
//
}
