package com.ssm.demo.test;

import com.alibaba.fastjson.JSON;
import com.ssm.demo.model.ssmdemo.SysUser;
import com.ssm.demo.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class TestMyBatis {
	
	private static Logger logger = Logger.getLogger(TestMyBatis.class);  
	private ApplicationContext ac = null;  
    @Resource  
    private UserService userService = null;

	@Before  
	public void before() {
		ac = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml");  
		userService = (UserService) ac.getBean("userService");
	}

    @Test
    public void test1() {
        SysUser user = userService.getUserById(1l);
        if(user!=null){
        	System.out.println(user.getUserName());
            logger.info("值："+user.getUserName());
            logger.info(JSON.toJSONString(user));
        }else{
        	System.out.println("没有查出结果,删除sql条件试试，或者重启eclipse试试");
        }
    }
    
}
