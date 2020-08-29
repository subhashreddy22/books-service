package com.books.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {

    private int errorCode;
    private String errorMessage;
}
