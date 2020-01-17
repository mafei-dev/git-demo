package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
@RefreshScope
@Controller
public class IndexController {
    @Autowired
    private HttpServletRequest request;

    @Value("${spring.cloud.config.test-value}")
    public String testMessage;

    @GetMapping("/info")
    public ResponseEntity<?> index() {
        HashMap<Object, Object> serverData = new HashMap<>();
        serverData.put("RemoteUser", request.getRemoteUser());
        serverData.put("RemoteAddr", request.getRemoteAddr());
        serverData.put("RemoteHost", request.getRemoteHost());
        serverData.put("LocalAddr", request.getLocalAddr());
        serverData.put("LocalPort", request.getLocalPort());
        serverData.put("spring.cloud.config.test-value", testMessage);
        return new ResponseEntity<>(serverData, HttpStatus.OK);
    }

}
