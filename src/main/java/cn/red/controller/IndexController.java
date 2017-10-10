package cn.red.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.red.model.PageBean;
import cn.red.model.Post;
import cn.red.model.User;
import cn.red.service.PostService;
import cn.red.service.UserService;

@Controller
public class IndexController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private PostService postService;
	
	/**
	 * 去首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String index(Model model, HttpServletRequest request) {
		// 获取客户端的访问地址
		System.out.println(request.getRemoteAddr());
		// 记录访问信息
		userService.record(request.getRequestURL(), request.getContextPath(), request.getRemoteAddr());
		// 按时间列出帖子
		PageBean<Post> pageBean = postService.listPostByTime(1);
		// 按时间列出用户
		List<User> userList = userService.listUserByTime();
		// 活跃用户
		List<User> hotUserList = userService.listUserByHot();
		
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("userList", userList);
		model.addAttribute("hotUserList", hotUserList);
		return "index";
	}
	
}
