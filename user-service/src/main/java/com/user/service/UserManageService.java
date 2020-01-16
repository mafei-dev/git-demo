package com.user.service;

import com.user.controller.model.request.ReqModelCreateNewUser;
import com.user.controller.model.response.ResModelCreateNewUser;

public interface UserManageService {
    ReqModelCreateNewUser createNewUser(ReqModelCreateNewUser reqModelCreateNewUser);
}
