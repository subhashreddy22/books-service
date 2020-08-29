package com.books.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationToken {

    private String token;

    private int expiresIn;

}