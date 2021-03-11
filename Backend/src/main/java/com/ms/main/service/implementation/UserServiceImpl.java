package com.ms.main.service.implementation;

import com.ms.main.constants.Constants;
import com.ms.main.entity.User;
import com.ms.main.repository.UserRepository;
import com.ms.main.request.Credentials;
import com.ms.main.request.GoogleSignIn;
import com.ms.main.request.UserSignup;
import com.ms.main.response.GetAllUserResponse;
import com.ms.main.response.SignInResponse;
import com.ms.main.response.SignupResponse;
import com.ms.main.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;


    @Override
    public SignupResponse addUser(UserSignup user) {
        SignupResponse signupResponse = new SignupResponse();

        User persistentUser = new User(user.getName(), user.getEmail(), user.getPassword(), user.getRole());


        if(userRepository.findByEmail(persistentUser.getEmail()).isEmpty()){
            String plainPassword = persistentUser.getPassword();
            persistentUser.setPassword(encryptPassword(plainPassword));
            persistentUser.setRole(persistentUser.getRole().toUpperCase());

            User savedUser = userRepository.save(persistentUser);

            signupResponse.setUserId(savedUser.getUserId());
            signupResponse.setSignedUp(true);
            signupResponse.setMessage(Constants.SIGNUP_SUCCESS_MESSAGE);
            logger.info("Add User success!");
            return signupResponse;
        }
        logger.warn("Add user failed!, Email is duplicate!");
        return new SignupResponse(Constants.FAILURE_USER_ID, false, Constants.SIGNUP_EMAIL_DUPLICATE_MESSAGE);
    }

    @Override
    public SignInResponse userSignIn(Credentials credentials) {
        Optional<User> user = userRepository.findByEmail(credentials.getEmail());
        if(user.isPresent() && isPasswordValid(credentials.getPassword(), user.get().getPassword())){
            logger.info("User sign in success!");
            return new SignInResponse(true, user.get().getName(), user.get().getEmail(), user.get().getUserId(), user.get().getRole(), Constants.SIGN_IN_SUCCESS_MESSAGE);
        }
        logger.warn("User sign in failed!, Invalid credentials!");
        return new SignInResponse(false, Constants.EMPTY_RESPONSE_STRING, Constants.EMPTY_RESPONSE_STRING, Constants.FAILURE_USER_ID, Constants.EMPTY_RESPONSE_STRING, Constants.SIGN_IN_FAILED_MESSAGE);
    }

    @Override
    public GetAllUserResponse getUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            logger.warn("Get all users failed!, No users found!");
            return new GetAllUserResponse(false, Constants.GET_USERS_FAILURE_MESSAGE, userList);
        }
        logger.info("Get all users success!");
        return new GetAllUserResponse(true, Constants.GET_USERS_SUCCESS_MESSAGE, userList);
    }

    @Override
    public SignInResponse googleSignIn(GoogleSignIn signIn) {

        Optional<User> user = userRepository.findByEmail(signIn.getEmail());
        logger.info("Google sign in Initiated!");
        return user.map(value -> new SignInResponse(true, value.getName(), value.getEmail(), value.getUserId(), value.getRole(), Constants.SIGN_IN_SUCCESS_MESSAGE)).orElseGet(() -> new SignInResponse(false, Constants.EMPTY_RESPONSE_STRING, Constants.EMPTY_RESPONSE_STRING, Constants.FAILURE_USER_ID, Constants.EMPTY_RESPONSE_STRING, Constants.SIGN_IN_FAILED_MESSAGE));
    }

    private String encryptPassword(String plainPassword) {
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    private boolean isPasswordValid(String inputPassword, String storedPassword){
        return new BCryptPasswordEncoder().matches(inputPassword, storedPassword);
    }
}
