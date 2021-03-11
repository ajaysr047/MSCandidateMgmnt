package com.ms.main.controller;

import com.ms.main.request.Credentials;
import com.ms.main.request.GoogleSignIn;
import com.ms.main.request.UserSignup;
import com.ms.main.response.GetAllUserResponse;
import com.ms.main.response.SignInResponse;
import com.ms.main.response.SignupResponse;
import com.ms.main.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    void userSignupTest() {
        SignupResponse response = new SignupResponse(1, true, "Test signup");
        UserSignup userSignup = new UserSignup();
        Mockito.when(userService.addUser(Mockito.any(UserSignup.class))).thenReturn(response);
        Assertions.assertTrue(Objects.requireNonNull(userController.userSignup(userSignup).getBody()).isSignedUp());

        response.setSignedUp(false);
        Assertions.assertFalse(Objects.requireNonNull(userController.userSignup(userSignup).getBody()).isSignedUp());
    }

    @Test
    void userSignInTest() {
        SignInResponse response = new SignInResponse();
        response.setValid(true);
        Credentials credentials = new Credentials();

        Mockito.when(userService.userSignIn(Mockito.any(Credentials.class))).thenReturn(response);
        Assertions.assertTrue(Objects.requireNonNull(userController.userSignIn(credentials).getBody()).isValid());

        response.setValid(false);
        Assertions.assertFalse(Objects.requireNonNull(userController.userSignIn(credentials).getBody()).isValid());
    }

    @Test
    void getAllUsersTest() {
        GetAllUserResponse response =  new GetAllUserResponse();
        response.setSuccess(true);

        Mockito.when(userService.getUsers()).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(userController.getAllUsers().getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(userController.getAllUsers().getBody()).isSuccess());
    }

    @Test
    void googleSignInTest() {
        SignInResponse response = new SignInResponse();
        response.setValid(true);
        GoogleSignIn signIn = new GoogleSignIn();

        Mockito.when(userService.googleSignIn(Mockito.any(GoogleSignIn.class))).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(userController.googleSignIn(signIn).getBody()).isValid());

        response.setValid(false);
        Assertions.assertFalse(Objects.requireNonNull(userController.googleSignIn(signIn).getBody()).isValid());
    }
}