package by.itacademy.userservice.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String mail;
    private String telegram;
    private String password;
    private String position;
    @Column(name = "way_of_receive")
    private String wayOfReceive;
    @ManyToOne
    private Role role;
    private boolean isRead;
    @ManyToOne
    private UserStatus status;

    public User() {
    }

    public User(
            Long id,
            String fullName,
            String mail,
            String telegram,
            String password,
            String position,
            String wayOfReceive,
            Role role,
            boolean isRead,
            UserStatus status
    ) {
        this.id = id;
        this.fullName = fullName;
        this.mail = mail;
        this.telegram = telegram;
        this.password = password;
        this.position = position;
        this.wayOfReceive = wayOfReceive;
        this.role = role;
        this.isRead = isRead;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWayOfReceive() {
        return wayOfReceive;
    }

    public void setWayOfReceive(String wayOfReceive) {
        this.wayOfReceive = wayOfReceive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", mail='" + mail + '\'' +
                ", telegram='" + telegram + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", wayOfReceive='" + wayOfReceive + '\'' +
                ", role=" + role +
                ", isRead=" + isRead +
                ", status=" + status +
                '}';
    }
}