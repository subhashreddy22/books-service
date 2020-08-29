package com.books.util;

import com.books.entity.Book;
import com.books.entity.User;
import com.books.repository.BookRepository;
import com.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;

@Component
public class DataLoader {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeData() {
        Book book1 = new Book("Deep Dive into Spring Security", "Joshua", 4.5);
        Book book2 = new Book("Reactive Spring Boot", "Ivan", 4.4);
        Book book3 = new Book("Kubernetes Made Easy", "Fajardo", 4.7);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEnabled("Y");
        user.setRoles(Collections.singletonList("ROLE_USER"));
        userRepository.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEnabled("Y");
        admin.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        userRepository.save(admin);
    }
}
