package com.ms.main.service.implementation;

import com.ms.main.entity.User;
import com.ms.main.repository.UserRepository;
import com.ms.main.request.Credentials;
import com.ms.main.request.GoogleSignIn;
import com.ms.main.request.UserSignup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;


    @Test
    void addUserTest() {
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());

        UserSignup userSignup = new UserSignup();
        userSignup.setName("Test name");
        userSignup.setRole("Test Role");
        userSignup.setPassword("Test Password");
        userSignup.setEmail("test@testmail.com");
        Assertions.assertTrue(userService.addUser(userSignup).isSignedUp());

        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        Assertions.assertFalse(userService.addUser(userSignup).isSignedUp());

    }

    @Test
    void userSignInTest() {
        Credentials credentials = new Credentials();
        credentials.setEmail("test@gmail.com");
        credentials.setPassword("123");

        User user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode("123"));

        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));
        Assertions.assertTrue(userService.userSignIn(credentials).isValid());

        user.setPassword("");
        Assertions.assertFalse(userService.userSignIn(credentials).isValid());
    }

    @Test
    void getUsersTest() {
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertFalse(userService.getUsers().isSuccess());

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Assertions.assertTrue(userService.getUsers().isSuccess());
    }

    @Test
    void googleSignInTest() {
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertFalse(userService.googleSignIn(new GoogleSignIn()).isValid());

        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        Assertions.assertTrue(userService.googleSignIn(new GoogleSignIn()).isValid());
    }
}