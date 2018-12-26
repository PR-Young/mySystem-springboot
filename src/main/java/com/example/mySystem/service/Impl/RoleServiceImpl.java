package com.example.mySystem.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mySystem.dao.RoleMapper;
import com.example.mySystem.model.Msg;
import com.example.mySystem.model.Role;
import com.example.mySystem.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageInfo<Role> findAllRole(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Role> list = roleMapper.selectAllRole();
		PageInfo<Role> result = new PageInfo<Role>(list);
		return result;
	}

	@Override
	public Msg insert(Role role) {
		Msg msg = new Msg();
		int ret = roleMapper.insert(role);
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
	public Msg update(Role role) {
		Msg msg = new Msg();
		int ret = roleMapper.updateByPrimaryKey(role);
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
		int ret = roleMapper.deleteByPrimaryKey(id);
		if (ret != 0) {
			msg.setStatus("success");
			msg.setMessage("成功！");
		} else {
			msg.setStatus("error");
			msg.setMessage("失败！");
		}
		return msg;
	}

}
