package com.mutildatasource.controller;

import com.mutildatasource.domain.OracleConnUserEntity;
import com.mutildatasource.service.master.UserServiceMaster;
import com.mutildatasource.service.slaver.UserServiceSlaver;
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
    private UserServiceMaster userServiceMaster;

    @Resource
    private UserServiceSlaver userServiceSlaver;

    @RequestMapping("/ceshi")
    public String ceshi(){
        return "ceshii";
    }

    @RequestMapping("/usersMaster")
    List<OracleConnUserEntity> getUserListMaster(HttpServletRequest request) {
        return userServiceMaster.getUserList();
    }

    @RequestMapping("/usersSlaver")
    List<OracleConnUserEntity> getUserListSlaver(HttpServletRequest request) {
        return userServiceSlaver.getUserList();
    }
}
