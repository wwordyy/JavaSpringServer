package org.example.productservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ManufacturerDTO {

    @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!")
    @NotNull
    private String name;

    public @Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!") String getName() {
        return name;
    }

    public void setName(@Size(min = 5, max = 50, message = "Название должно быть от 5 до 50 символов!!!") String name) {
        this.name = name;
    }
}
