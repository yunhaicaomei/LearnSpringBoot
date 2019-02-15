package com.mutildatasource;

import com.mutildatasource.service.master.UserServiceMaster;
import com.mutildatasource.service.slaver.UserServiceSlaver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutildatasourceApplicationTests {

    @Resource
    private UserServiceMaster userServiceMaster;
    @Resource
    private UserServiceSlaver userServiceSlaver;


    @Test
    public void contextLoads() {
        userServiceMaster.getUserList();
        userServiceSlaver.getUserList();
    }

}

