package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class IndexController {
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/info")
    public ResponseEntity<?> index() {
        HashMap<Object, Object> serverData = new HashMap<>();
        serverData.put("RemoteUser", request.getRemoteUser());
        serverData.put("RemoteAddr", request.getRemoteAddr());
        serverData.put("RemoteHost", request.getRemoteHost());
        serverData.put("LocalAddr", request.getLocalAddr());
        serverData.put("LocalPort", request.getLocalPort());
        return new ResponseEntity<>(serverData, HttpStatus.OK);
    }

}
