package cn.red.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.red.model.PageBean;
import cn.red.model.Post;
import cn.red.model.Reply;
import cn.red.model.Topic;
import cn.red.model.User;
import cn.red.service.PostService;
import cn.red.service.ReplyService;
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
	
	@Autowired
	private ReplyService replyService;

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
		// pid表示帖子的ID
		return "redirect:toPost?pid=" + pid;
	}
	
	/**
	 * 去帖子详情页面
	 * @return
	 */
	@RequestMapping("/toPost")
	public String toPost(int pid, Model model, HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("uid");
        //获取帖子信息
        Post post = postService.getPostByPid(pid);
        //获取评论信息
        List<Reply> replyList = replyService.listReply(pid);

        //判断用户是否已经点赞
        boolean liked = false;
        if(sessionUid!=null){
            liked = postService.getLikeStatus(pid,sessionUid);
        }
        //向模型中添加数据
        model.addAttribute("post",post);
        model.addAttribute("replyList",replyList);
        model.addAttribute("liked",liked);
        return "post";
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
	
	//异步点赞
    @RequestMapping(value = "/ajaxClickLike",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String ajaxClickLike(int pid, HttpSession session){
        int sessionUid = (int) session.getAttribute("uid");
        return postService.clickLike(pid,sessionUid);
    }

}
