package com.books.repository;

import com.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The JPA repository for Book entity
 *
 * @author Subhash Reddy
 * @version 1
 */
public interface BookRepository extends JpaRepository<Book, Long> {}
