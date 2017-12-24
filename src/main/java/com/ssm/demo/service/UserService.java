package com.ssm.demo.service;

import com.ssm.demo.model.ssmdemo.SysUser;

import java.util.List;

public interface UserService {

	public SysUser getUserById(Long userId);
	
	public List<SysUser> findByUserName(String userName);
	
	public List<SysUser> checkPassword(String userName,String password);
	
}
