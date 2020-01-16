package com.user.controller;

import com.user.controller.model.request.ReqModelCreateNewUser;
import com.user.controller.model.response.ResModelCreateNewUser;
import com.user.service.UserManageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/info")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(request.getLocalPort(), HttpStatus.OK);
    }

    @Autowired
    private UserManageService userManageService;

    @PostMapping("/create")
    public ResponseEntity<ResModelCreateNewUser> create(@RequestBody @Valid ReqModelCreateNewUser reqModelCreateNewUser) {
        ReqModelCreateNewUser newUser = userManageService.createNewUser(reqModelCreateNewUser);
        ModelMapper mapper = new ModelMapper();
        ResModelCreateNewUser map = mapper.map(newUser, ResModelCreateNewUser.class);
        return ResponseEntity.ok().body(map);
    }


}
