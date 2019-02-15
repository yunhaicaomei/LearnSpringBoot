package com.mutildatasource.dao.slaver;

import com.mutildatasource.domain.OracleConnUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDaoSlaver {
    List<OracleConnUserEntity> getUserList();
}
