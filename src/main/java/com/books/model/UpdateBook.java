package com.books.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBook {

    private String name;

    private String author;

    @DecimalMin(value = "0.0", message = "rating cannot be less than 0.0")
    @DecimalMax(value = "5.0", message = "rating cannot be more than 5.0")
    private Double rating;
}
