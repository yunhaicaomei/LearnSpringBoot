package com.mutildatasource.dao.master;

import com.mutildatasource.domain.OracleConnUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDaoMaster {
    List<OracleConnUserEntity> getUserList();
}
