package com.books.controller;

import com.books.entity.Book;
import com.books.exception.BookNotFoundException;
import com.books.model.CreateBook;
import com.books.model.UpdateBook;
import com.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller class
 *
 * @author Subhash Reddy
 * @version 1
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) throws BookNotFoundException {
        Book book = bookService.getBook(id);
        if (book == null) {
            throw new BookNotFoundException("Requested book is not found");
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBook createBook,
                                           HttpServletRequest request) {
        Book book = bookService.createBook(createBook);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/books/{id}")
                        .buildAndExpand(book.getId())
                        .toUri())
                        .build();
    }

    @PutMapping(value = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id, @RequestBody UpdateBook updateBook) throws BookNotFoundException {
        boolean updated = bookService.updateBook(id, updateBook);
        if (!updated) {
            throw new BookNotFoundException("Book was not found to update");
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/books/{id}")
    public void deleteBook(@PathVariable("id") Long id) throws BookNotFoundException {
        boolean isDeleted = bookService.deleteBook(id);
        if (!isDeleted) {
            throw new BookNotFoundException("Book not found to delete");
        }
    }

}
