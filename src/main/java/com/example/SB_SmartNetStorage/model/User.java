package com.example.SB_SmartNetStorage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Entity
@Component
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    private Long userID;
    //jobtitle
    private String userProfession;
    private String userName;
    private String userSurname;
    private String userLogInNickname;
    private String userMail;
    private String userPassword;

    public User() {

    }
}
