package cn.red.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.red.model.Topic;
import cn.red.service.TopicService;

@Controller
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/listTopic")
	public String listTopic(Model model) {
		List<Topic> topicList = topicService.listTopic();
		model.addAttribute("topicList", topicList);
		return "topic";
	}
	
	@RequestMapping("/listImage")
	public String listImage(Model model) {
		List<String> imageList = topicService.listImage();
		model.addAttribute("imageList", imageList);
		return "image";
	}
	
}
