package by.itacademy.userservice.core.dto;

public class UserLoginDTO {
    private String mail;
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(
            String mail,
            String password
    ) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
