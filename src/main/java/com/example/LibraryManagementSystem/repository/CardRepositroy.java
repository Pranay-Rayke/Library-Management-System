package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepositroy extends JpaRepository<Card, Integer> {
}
