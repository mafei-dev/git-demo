package com.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//@RestController
//@RequestMapping("/zuul")
public class ZuulController {
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/info")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(request.getLocalPort(), HttpStatus.OK);
    }
}