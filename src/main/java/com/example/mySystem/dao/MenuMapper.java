package com.example.mySystem.dao;

import java.util.List;

import com.example.mySystem.model.Menu;

public interface MenuMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Menu record);

	int insertSelective(Menu record);

	Menu selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Menu record);

	int updateByPrimaryKey(Menu record);

	List<Menu> selectAllMenu();

	List<Menu> parentMenus();
}