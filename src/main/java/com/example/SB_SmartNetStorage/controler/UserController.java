package com.example.SB_SmartNetStorage.controler;

import com.example.SB_SmartNetStorage.algorithm.RandomString;
import com.example.SB_SmartNetStorage.exception.NotFoundException;
import com.example.SB_SmartNetStorage.model.User;
import com.example.SB_SmartNetStorage.repository.UserRepository;
import com.example.SB_SmartNetStorage.service.GMailer;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/addUser")
    User newUser(@RequestBody User newUSer) {
        return userRepository.save(newUSer);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id).map(
                user -> {
                    user.setUserProfession(newUser.getUserProfession());
                    user.setUserName(newUser.getUserName());
                    user.setUserSurname(newUser.getUserSurname());
                    user.setUserLogInNickname(newUser.getUserLogInNickname());
                    user.setUserMail(newUser.getUserMail());
                    user.setUserPassword(newUser.getUserPassword());
                    return userRepository.save(user);

                }
        ).orElseThrow(() -> new NotFoundException(id));

    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User With Id " + id + " Has Been Deleted Success.";
    }

    @SneakyThrows
    @GetMapping("/getUserByMail/{mail}")
    String getUserByMail(@PathVariable String mail) {
        GMailer gMailer = new GMailer();
        List<User> user = userRepository.findByUserMail(mail);
        if (user.isEmpty()) {
            return "Mail Is Not In Database";
        }


        boolean result = gMailer.sendMail("Test Mail" ,"Verification Code : "+ RandomString.getAlphaNumericString(10), "qiriagivi@gmail.com");

        if (!result) {
            return "The message could not be sent";
        }


        return "MailSent";
    }


    @PostMapping("/logIn/{nik}/{passwd}")
    User getUserByMail(@PathVariable String nik,@PathVariable String passwd){

        List<User> user = userRepository.findByUserLogInNickname(nik);

        if (user.isEmpty()){
            return null;
        }
        if (user.get(0).getUserPassword().equals(passwd)){
           return user.get(0);
        }
        return null;
    }



}
