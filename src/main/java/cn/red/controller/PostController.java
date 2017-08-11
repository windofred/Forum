package cn.red.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.red.model.PageBean;
import cn.red.model.Post;
import cn.red.model.Topic;
import cn.red.model.User;
import cn.red.service.PostService;
import cn.red.service.TopicService;
import cn.red.service.UserService;

@Controller
public class PostController {

	@Autowired
	private TopicService topicService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;

	/**
	 * 去发帖子的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/toPublish")
	public String toPublish(Model model) {
		List<Topic> topicList = topicService.listTopic();
		model.addAttribute("topicList", topicList);
		return "publish";
	}
	
	/**
	 * 发帖
	 * @param post
	 * @return
	 */
	@RequestMapping("/publishPost")
	public String publishPost(Post post) {
		int pid = postService.publishPost(post);
		return "redirect:toPost?pid=" + pid;
	}
	
	/**
	 * 去帖子详情页面
	 * @return
	 */
	@RequestMapping("/toPost")
	public String toPost(int pid, Model model, HttpSession session) {
		
		
		return null;
	}

	/**
	 * 按照时间列出帖子
	 * @param curPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPostByTime")
	public String listPostByTime(int curPage, Model model) {
		// 按时间列出帖子
		PageBean<Post> pageBean = postService.listPostByTime(curPage);
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
