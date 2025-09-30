package org.example.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewDTO {

    @NotNull
    @Size(min = 3)
    private String text;

    @NotNull
    private Double rating;


    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
