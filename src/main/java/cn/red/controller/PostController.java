package cn.red.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.red.model.Topic;
import cn.red.service.TopicService;

@Controller
public class PostController {
	
	@Autowired
	private TopicService topicService;
	
	// 去发帖子的页面
	@RequestMapping("/toPublish")
	public String toPublish(Model model) {
		List<Topic> topicList = topicService.listTopic();
		model.addAttribute("topicList", topicList);
		return "publish";
	}
	
}
