package com.damaha.controller;

import com.damaha.entity.Staff;
import com.damaha.dto.ResponseDTO;
import com.damaha.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dabu9
 * @Date 2023/9/18 15:35
 * 处理登陆信息的控制层方法
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody Staff staff) {
        return this.loginService.login(staff);
    }
}
