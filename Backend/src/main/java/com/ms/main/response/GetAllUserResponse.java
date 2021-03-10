package com.ms.main.response;

import com.ms.main.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllUserResponse {

    private boolean isSuccess;

    private String message;

    private List<User> userList = new ArrayList<>();

    public GetAllUserResponse() {
    }

    public GetAllUserResponse(boolean isSuccess, String message, List<User> userList) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.userList = userList;
    }
}
