package cn.red.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.red.model.User;
import cn.red.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	// 去注册和登陆的页面
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
	// 登陆操作
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, Model model, HttpSession session) {
		Map<String, Object> map = loginService.login(user);
		if (map.get("status").equals("yes")) {
			session.setAttribute("uid", map.get("uid"));
			session.setAttribute("headUrl", map.get("headUrl"));
			// 重定向到 个人主页
			return "redirect:toMyProfile";
		} else {
			model.addAttribute("email",user.getEmail());
            model.addAttribute("error",map.get("error"));
            return "login";
		}
	}
	
	// 注册
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(User user, String repassword, Model model) {
		String result = loginService.register(user,repassword);
        if(result.equals("ok")){
            model.addAttribute("info","系统已经向你的邮箱发送了一封邮件哦，验证后就可以登录啦~");
            return "prompt/promptInfo";
        }else {
            model.addAttribute("register","yes");
            model.addAttribute("email",user.getEmail());
            model.addAttribute("error",result);
            return "login";
        }
	}
	
	// 激活
	@RequestMapping("/activate")
	public String activate(String code, Model model) {
		loginService.activate(code);
		
		model.addAttribute("info", "您的账户已经激活成功啦，可以去登陆啦~");
		return "prompt/promptInfo";
	}
	
	// 注销
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("uid");
		return "redirect:toIndex";
	}
	
}
