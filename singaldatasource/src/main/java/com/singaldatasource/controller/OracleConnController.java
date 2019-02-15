package com.singaldatasource.controller;


import com.singaldatasource.domain.OracleConnUserEntity;
import com.singaldatasource.service.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@EnableAutoConfiguration
public class OracleConnController {

    @Resource
    private UserService userService;

    @RequestMapping("/ceshi")
    public String ceshi(){
        return "ceshii";
    }

    @RequestMapping("/users")
    List<OracleConnUserEntity> getUserList(HttpServletRequest request) {
        return userService.getUserList();
    }

}
