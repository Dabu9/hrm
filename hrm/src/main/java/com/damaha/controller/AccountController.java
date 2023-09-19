package com.damaha.controller;

import com.damaha.dto.ResponseDTO;
import com.damaha.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name) {
        return this.accountService.list(current, size, name);
    }
    @GetMapping ("/{id}")
    public ResponseDTO resetPwd(@PathVariable() Integer id){
        return this.accountService.resetPwd(id);
    }
    @GetMapping ("/cancel/{id}")
    public ResponseDTO cancelApp(@PathVariable() Integer id){
        return this.accountService.cancelApp(id);
    }
}
