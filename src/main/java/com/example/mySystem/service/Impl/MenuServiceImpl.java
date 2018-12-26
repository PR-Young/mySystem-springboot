package com.example.mySystem.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mySystem.dao.MenuMapper;
import com.example.mySystem.model.Menu;
import com.example.mySystem.model.Msg;
import com.example.mySystem.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public PageInfo<Menu> findAllMenu(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Menu> list = menuMapper.selectAllMenu();
		PageInfo<Menu> result = new PageInfo<Menu>(list);
		return result;
	}

	@Override
	public Msg insert(Menu menu) {
		Msg msg = new Msg();
		menu.setMenuId(UUID.randomUUID().toString());
		menu.setCreateTime(new Date());
		menu.setModifyTime(new Date());
		int ret = menuMapper.insert(menu);
		if (ret != 0) {
			msg.setStatus("success");
			msg.setMessage("成功！");
		} else {
			msg.setStatus("error");
			msg.setMessage("失败！");
		}
		return msg;
	}

	@Override
	public Msg update(Menu menu) {
		Msg msg = new Msg();
		menu.setModifyTime(new Date());
		int ret = menuMapper.updateByPrimaryKey(menu);
		if (ret != 0) {
			msg.setStatus("success");
			msg.setMessage("成功！");
		} else {
			msg.setStatus("error");
			msg.setMessage("失败！");
		}
		return msg;
	}

	@Override
	public Msg deleteById(int id) {
		Msg msg = new Msg();
		int ret = menuMapper.deleteByPrimaryKey(id);
		if (ret != 0) {
			msg.setStatus("success");
			msg.setMessage("成功！");
		} else {
			msg.setStatus("error");
			msg.setMessage("失败！");
		}
		return msg;
	}

	@Override
	public List<Menu> parentMenus() {
		return menuMapper.parentMenus();
	}

}
