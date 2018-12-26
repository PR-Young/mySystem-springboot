package com.example.mySystem.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mySystem.dao.UserMapper;
import com.example.mySystem.model.Msg;
import com.example.mySystem.model.User;
import com.example.mySystem.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/*
	 * 这个方法中用到了我们开头配置依赖的分页插件pagehelper 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
	 * pageNum 开始页数 pageSize 每页显示的数据条数
	 */
	@Override
	public PageInfo<User> findAllUser(int pageNum, int pageSize) {
		// 将参数传给这个方法就可以实现物理分页了，非常简单。
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userMapper.selectAllUser();
		PageInfo<User> result = new PageInfo<User>(list);
		return result;
	}

	@Override
	public Msg insert(User user) {
		Msg msg = new Msg();
		int ret = userMapper.insert(user);
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
	public Msg update(User user) {
		Msg msg = new Msg();
		int ret = userMapper.updateByPrimaryKey(user);
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
	public Msg delete(User user) {
		Msg msg = new Msg();
		return msg;
	}

	@Override
	public Msg deleteById(int id) {
		Msg msg = new Msg();
		int ret = userMapper.deleteByPrimaryKey(id);
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
