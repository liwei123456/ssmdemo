package com.ssm.demo.dao;

import com.ssm.demo.model.ssmdemo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
	
	SysUser selectByPrimaryKey(Long id);
    
	List<SysUser> findByUserName(String userName);

	List<SysUser> findByUserNameAndPassword(@Param("userName")String userName, @Param("password")String password);
	
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

}