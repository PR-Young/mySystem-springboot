package com.example.mySystem.service;

import com.example.mySystem.model.Msg;
import com.example.mySystem.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

	PageInfo<User> findAllUser(int pageNum, int pageSize);

	Msg insert(User user);

	Msg update(User user);

	Msg delete(User user);

	Msg deleteById(int id);
}
