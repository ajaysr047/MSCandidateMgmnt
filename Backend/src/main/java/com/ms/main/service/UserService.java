package com.ms.main.service;

import com.ms.main.request.Credentials;
import com.ms.main.request.UserSignup;
import com.ms.main.response.SignInResponse;
import com.ms.main.response.SignupResponse;

public interface UserService {
    SignupResponse addUser(UserSignup user);
    SignInResponse userSignIn(Credentials credentials);
}
