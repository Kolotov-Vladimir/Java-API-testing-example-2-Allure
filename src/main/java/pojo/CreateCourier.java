package pojo;

public class CreateCourier {
    private static final String COURIER = "steps";
    private String login;
    private String password;
    private String firstName;

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

//    public String getFirstName() {
//        return firstName;
//    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }


}