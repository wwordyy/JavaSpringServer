package org.example.productservice.dto;

import jakarta.validation.constraints.Size;

public class TypeOfProductDTO {


    @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!")
    private String title;

    public @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!") String getTitle() {
        return title;
    }

    public void setTitle(@Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!") String title) {
        this.title = title;
    }
}
