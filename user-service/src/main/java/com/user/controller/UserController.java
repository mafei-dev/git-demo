package com.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.controller.model.request.ReqModelCreateNewUser;
import com.user.controller.model.request.ReqUserSinIn;
import com.user.controller.model.response.ResModelCreateNewUser;
import com.user.controller.model.response.ResUserSinIn;
import com.user.service.UserManageService;
import com.user.service.impl.JWTUserDetailsService;
import com.user.util.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Log4j2
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/info")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(request.getLocalPort(), HttpStatus.OK);
    }


    @Autowired
    private UserManageService userManageService;


    @Autowired
    private JWTUserDetailsService userDetailsService;


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/sign-in")
    public ResponseEntity<ResUserSinIn> signIn(@RequestBody @Valid ReqUserSinIn reqUserSinIn) throws Exception {
        authenticate(reqUserSinIn.getUsername(), reqUserSinIn.getPassword());
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(reqUserSinIn.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(new ResUserSinIn(
                userDetails.getUsername(), token));
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    private void authenticate(String username, String password) throws Exception {
        log.debug("UserController.authenticate");
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("new ObjectMapper().writeValueAsString(authenticate) = " + new ObjectMapper().writeValueAsString(authenticate));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @PostMapping("/sign-up")
    public ResponseEntity<ResModelCreateNewUser> create(@RequestBody @Valid ReqModelCreateNewUser reqModelCreateNewUser) {
        ReqModelCreateNewUser newUser = userManageService.createNewUser(reqModelCreateNewUser);
        ModelMapper mapper = new ModelMapper();
        ResModelCreateNewUser map = mapper.map(newUser, ResModelCreateNewUser.class);
        return ResponseEntity.ok().body(map);
    }


}
