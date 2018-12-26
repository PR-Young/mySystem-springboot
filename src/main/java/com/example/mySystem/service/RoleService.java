package com.example.mySystem.service;

import com.example.mySystem.model.Msg;
import com.example.mySystem.model.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

	PageInfo<Role> findAllRole(int pageNum, int pageSize);

	Msg insert(Role role);

	Msg update(Role role);

	Msg deleteById(int id);
}
