package cn.red.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.red.model.Message;
import cn.red.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	// 去消息页面
	@RequestMapping("/toMessage")
	public String toMessage(Model model, HttpSession session) {
		Integer sessionUid = (Integer) session.getAttribute("uid");
		Map<String,List<Message>> map = messageService.listMessageByUid(sessionUid);
        model.addAttribute("map",map);
        System.out.println(map);
        return "message";
	}
}
