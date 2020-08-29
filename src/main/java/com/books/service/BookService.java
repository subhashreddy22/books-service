package com.books.service;

import com.books.entity.Book;
import com.books.model.CreateBook;
import com.books.model.UpdateBook;
import com.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    @Transactional
    public Book createBook(CreateBook createBook) {
        Book book = new Book();
        book.setName(createBook.getName());
        book.setAuthor(createBook.getAuthor());
        book.setRating(createBook.getRating());
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public boolean updateBook(Long id, UpdateBook newBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book oldBook = optionalBook.get();
            if (newBook.getName() != null) {
                oldBook.setName(newBook.getName());
            }
            if (newBook.getAuthor() != null) {
                oldBook.setAuthor(newBook.getAuthor());
            }
            if (newBook.getRating() >= 0.0) {
                oldBook.setRating(newBook.getRating());
            }
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteBook(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
