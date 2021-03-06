package com.ms.main.controller;

import com.ms.main.request.Credentials;
import com.ms.main.request.GoogleSignIn;
import com.ms.main.request.UserSignup;
import com.ms.main.response.GetAllUserResponse;
import com.ms.main.response.SignInResponse;
import com.ms.main.response.SignupResponse;
import com.ms.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<SignupResponse> userSignup(@Valid @RequestBody UserSignup userSignup){
       SignupResponse response = userService.addUser(userSignup);
       if(response.isSignedUp())
           return ResponseEntity.ok(response);
       return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> userSignIn(@Valid @RequestBody Credentials credentials){
        SignInResponse response = userService.userSignIn(credentials);
        if(response.isValid())
            return ResponseEntity.ok(response);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GetAllUserResponse> getAllUsers(){
        GetAllUserResponse response = userService.getUsers();
        if(response.isSuccess()){

            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/googleSignIn")
    public ResponseEntity<SignInResponse> googleSignIn(@Valid @RequestBody GoogleSignIn googleSignIn){
        SignInResponse response = userService.googleSignIn(googleSignIn);
        if(response.isValid())
            return ResponseEntity.ok(response);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
