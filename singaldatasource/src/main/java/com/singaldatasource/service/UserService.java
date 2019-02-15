package com.singaldatasource.service;


import com.singaldatasource.dao.UserDao;
import com.singaldatasource.domain.OracleConnUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<OracleConnUserEntity> getUserList() {
        return userDao.getUserList();
    }
}
