package com.example.mySystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mySystem.model.Menu;
import com.example.mySystem.model.Msg;
import com.example.mySystem.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/parentmenus")
	public List<Menu> parentMenus() {
		return menuService.parentMenus();
	}

	@RequestMapping(value = "/all")
	public List<Menu> findAllMenu(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
		List<Menu> list = menuService.findAllMenu(pageNum, pageSize).getList();
		return list;
	}

	@RequestMapping("add")
	public Msg addMenu(Menu menu) {
		Msg msg = menuService.insert(menu);
		return msg;
	}

	@RequestMapping("update")
	public Msg updateMenu(Menu menu) {
		Msg msg = menuService.update(menu);
		return msg;
	}

	@RequestMapping("delete")
	public Msg deleteMenu(int id) {
		Msg msg = menuService.deleteById(id);
		return msg;
	}

}
