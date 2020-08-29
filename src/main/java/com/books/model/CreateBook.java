package com.books.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateBook {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "author is required")
    private String author;

    @DecimalMin(value = "0.0", message = "rating cannot be less than 0.0")
    @DecimalMax(value = "5.0", message = "rating cannot be more than 5.0")
    private Double rating;
}