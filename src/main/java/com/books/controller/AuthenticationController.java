package com.books.controller;

import com.books.config.JwtTokenProvider;
import com.books.model.AuthenticationToken;
import com.books.model.AuthenticationRequest;
import com.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/auth/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationToken> getToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws UsernameNotFoundException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationToken authenticationToken = jwtTokenProvider.createToken(
                authenticationRequest.getUsername(),
                userRepository.findByUsername(authenticationRequest.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("Requested user is not found")).getRoles());
        return ResponseEntity.ok(authenticationToken);
    }
}
