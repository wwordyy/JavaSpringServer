package org.example.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RoleDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
