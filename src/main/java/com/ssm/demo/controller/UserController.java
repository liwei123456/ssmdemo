package com.ssm.demo.controller;

import com.ssm.demo.model.ssmdemo.SysUser;
import com.ssm.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller  
@RequestMapping("/user")
public class UserController {
	@Resource
    private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
    public Map<String,Object> login(HttpServletRequest request,HttpServletResponse response,SysUser user){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<SysUser> user1 = userService.findByUserName(user.getUserName());
		String message = "";
		if(user1.size()>0){
			List<SysUser> user2 = userService.checkPassword(user.getUserName(), user.getPassword());
			if(user2.size()>0){
				jsonMap.put("user", user2.get(0));
			}else{
				message = "密码错误！";
			}
		}else{
			message = "用户名不存在！";
		}
		jsonMap.put("message", message);
		return jsonMap;
		//旧的写法
		/*String jsonResult = JSON.toJSONString(jsonMap);
		renderData(response, jsonResult);*/
    }

	/**
	 * 通过PrintWriter将响应数据写入response，ajax可以接受到这个数据
	 * 
	 * @param response
	 * @param data
	 */
	private void renderData(HttpServletResponse response, String data) {
		//设置页面不缓存
        response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(data);
		} catch (IOException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (null != printWriter) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}
	
    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request,Model model){
        Long userId = Long.parseLong(request.getParameter("id"));
        SysUser user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/showUser";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,Model model){
        return "../login";
    }
    
}
