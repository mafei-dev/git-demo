package com.user.service;

import com.user.controller.model.request.ReqModelCreateNewUser;
import com.user.controller.model.request.ReqUserSinIn;
import com.user.controller.model.response.ResModelCreateNewUser;
import com.user.controller.model.response.ResUserSinIn;

public interface UserManageService {
    ReqModelCreateNewUser createNewUser(ReqModelCreateNewUser reqModelCreateNewUser);

}
