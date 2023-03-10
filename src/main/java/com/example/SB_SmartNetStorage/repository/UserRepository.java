package com.example.SB_SmartNetStorage.repository;

import com.example.SB_SmartNetStorage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserMail(String userMail);
    List<User> findByUserLogInNickname(String userLogInNickname);

}
