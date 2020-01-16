package com.user.controller.model.request;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReqModelCreateNewUser implements Serializable {

    private String userId;
    @NotNull(message = "username can't be empty")
    @NotEmpty(message = "username can't be empty")
    private String username;

    @NotNull(message = "first name can't be empty")
    @NotEmpty(message = "first name can't be empty")
    private String firstName;
    @NotNull(message = "last name can't be empty")
    @NotEmpty(message = "last name can't be empty")
    private String lastName;

    @NotNull(message = "password name can't be empty")
    @NotEmpty(message = "password name can't be empty")
    private String password;
}
