package entities;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CustomerLogIn {

    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
