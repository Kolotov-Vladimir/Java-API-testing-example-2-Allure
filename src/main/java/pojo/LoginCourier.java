package pojo;

import lombok.Data;

@Data
public class LoginCourier {

    private String login;
    private String password;

    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }
}