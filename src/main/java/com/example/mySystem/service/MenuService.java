package com.example.mySystem.service;

import java.util.List;

import com.example.mySystem.model.Menu;
import com.example.mySystem.model.Msg;
import com.github.pagehelper.PageInfo;

public interface MenuService {

	PageInfo<Menu> findAllMenu(int pageNum, int pageSize);

	Msg insert(Menu menu);

	Msg update(Menu menu);

	Msg deleteById(int id);

	List<Menu> parentMenus();

}
