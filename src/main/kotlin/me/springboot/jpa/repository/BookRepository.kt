package me.springboot.jpa.repository

import me.springboot.jpa.entity.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>