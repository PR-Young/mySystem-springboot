package com.example.mySystem.dao;

import com.example.mySystem.model.RoleResourcesKey;

public interface RoleResourcesMapper {
    int deleteByPrimaryKey(RoleResourcesKey key);

    int insert(RoleResourcesKey record);

    int insertSelective(RoleResourcesKey record);
}