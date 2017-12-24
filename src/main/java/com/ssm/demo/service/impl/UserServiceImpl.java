package com.ssm.demo.service.impl;

import com.ssm.demo.dao.SysUserMapper;
import com.ssm.demo.model.ssmdemo.SysUser;
import com.ssm.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource  
    private SysUserMapper userDao;
	
	@Override
	public SysUser getUserById(Long userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public List<SysUser> findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public List<SysUser> checkPassword(String userName, String password) {
		// TODO Auto-generated method stub
		return userDao.findByUserNameAndPassword(userName, password);
	}

}
