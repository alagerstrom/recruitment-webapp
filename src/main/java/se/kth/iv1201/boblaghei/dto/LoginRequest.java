package se.kth.iv1201.boblaghei.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A dto class that represents a request to log in.
 */
public class LoginRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
