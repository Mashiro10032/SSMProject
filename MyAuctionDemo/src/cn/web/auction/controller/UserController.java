package cn.web.auction.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.web.auction.pojo.User;
import cn.web.auction.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/doLogin")
	public String doLogin(String username,
			String userpassword,
			HttpSession session,
			String inputCode,Model model) {
		
		if(!inputCode.equals(session.getAttribute("numrand"))) {
			model.addAttribute("errorMsg","验证码错误");
			return "login";
		}
		
		User user = userService.login(username, userpassword);
		
		if(user!=null) {   //登录
			//键值对
			//利用session作用域帮我们暂时保存用户信息,随着浏览器关闭而清除!
			session.setAttribute("user",user);
			return "redirect:/auction/queryAuctions";
		}else {  //登录失败
			model.addAttribute("errormsg","用户名或者密码错误");
			//失败则是重新返回login.jsp页面
			return "login";
		}
	}
}
