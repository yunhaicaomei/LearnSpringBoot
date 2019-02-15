package com.mutildatasource.service.master;


import com.mutildatasource.dao.master.UserDaoMaster;
import com.mutildatasource.domain.OracleConnUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMaster {
    @Autowired
    private UserDaoMaster userDaoMaster;

    public List<OracleConnUserEntity> getUserList() {
        return userDaoMaster.getUserList();
    }
}
