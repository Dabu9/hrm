package com.damaha.controller;

import com.damaha.dto.ResponseDTO;
import com.damaha.entity.Account;
import com.damaha.service.ForgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgetController {
    @Autowired
    ForgetService forgetService;
    @PostMapping("/forget")
    public ResponseDTO addForgetAccount(@RequestBody Account account){
        return forgetService.addForgetAccount(account);
    }
}
