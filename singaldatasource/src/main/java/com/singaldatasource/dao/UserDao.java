package com.singaldatasource.dao;

import com.singaldatasource.domain.OracleConnUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<OracleConnUserEntity> getUserList();
}
