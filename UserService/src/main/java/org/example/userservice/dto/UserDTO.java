package org.example.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @Size(min = 3, max = 50)
    @NotNull
    private String username;

    @Size(min = 3, max = 50)
    @NotNull
    private String password;



    public @Size(min = 3, max = 50) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 3, max = 50) String password) {
        this.password = password;
    }

    public @Size(min = 3, max = 50) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 50) String username) {
        this.username = username;
    }
}
