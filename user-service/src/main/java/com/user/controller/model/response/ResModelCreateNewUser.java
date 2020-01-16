package com.user.controller.model.response;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ResModelCreateNewUser implements Serializable {
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
