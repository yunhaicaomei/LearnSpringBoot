package com.mutildatasource.service.slaver;


import com.mutildatasource.dao.slaver.UserDaoSlaver;
import com.mutildatasource.domain.OracleConnUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceSlaver {
    @Autowired
    private UserDaoSlaver userDaoSlaver;

    public List<OracleConnUserEntity> getUserList() {
        return userDaoSlaver.getUserList();
    }
}
