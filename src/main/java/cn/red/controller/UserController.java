package cn.red.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.red.model.Post;
import cn.red.model.User;
import cn.red.service.PostService;
import cn.red.service.QiniuService;
import cn.red.service.UserService;
import cn.red.util.MyConstant;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private QiniuService qiniuService;
	
	/**
	 * 查看我的个人主页
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/toMyProfile")
	public String toMyProfile(HttpSession session, Model model) {
		int sessionUid = (int) session.getAttribute("uid");
		User user = userService.getProfile(sessionUid, sessionUid);
		List<Post> postList = postService.getPostList(sessionUid);
		model.addAttribute("user", user);
		model.addAttribute("postList", postList);
		return "myProfile";
		
	}
	
	// 去编辑信息的页面
	@RequestMapping("/toEditProfile")
	public String toEditProfile(HttpSession session, Model model) {
		int uid = (int) session.getAttribute("uid");
		User user = userService.getEditInfo(uid);
		model.addAttribute("user", user);
		return "editProfile";
	}
	
	// 编辑信息
	@RequestMapping("/editProfile")
	public String editProfile(User user) {
		System.out.println(user);
		userService.updateUser(user);
		return "redirect:toMyProfile";
	}
	
	// 修改密码
	@RequestMapping("/updatePassword")
	public String updatePassword(String password, String newpassword, String repassword, HttpSession session, Model model) {
		int sessionUid = (int) session.getAttribute("uid");
		String status = userService.updatePassword(password, newpassword, repassword, sessionUid);
		if (status.equals("ok")) {
			return "redirect:logout";
		} else {
			model.addAttribute("passwordError", status);
			return "editProfile";
		}
	}
	
	@RequestMapping("/updateHeadUrl")
    public String updateHeadUrl(MultipartFile myFileName,Model model,HttpSession session) throws IOException {
        // 文件类型限制
        String[] allowedType = {"image/bmp", "image/gif", "image/jpeg", "image/png"};
        boolean allowed = Arrays.asList(allowedType).contains(myFileName.getContentType());
        if (!allowed) {
            model.addAttribute("error3","图片格式仅限bmp，jpg，png，gif~");
            return "editProfile";
        }
        // 图片大小限制
        if (myFileName.getSize() > 3 * 1024 * 1024) {
            model.addAttribute("error3","图片大小限制在3M以下哦~");
            return "editProfile";
        }
        // 包含原始文件名的字符串
        String fi = myFileName.getOriginalFilename();
        // 提取文件拓展名
        String fileNameExtension = fi.substring(fi.indexOf("."), fi.length());
        // 生成云端的真实文件名
        String remoteFileName = UUID.randomUUID().toString() + fileNameExtension;
        qiniuService.upload(myFileName.getBytes(), remoteFileName);

        // 更新数据库中头像URL
        int uid = (int) session.getAttribute("uid");
        userService.updateHeadUrl(uid,MyConstant.QINIU_IMAGE_URL + remoteFileName);

        return "redirect:toMyProfile";
    }
	
}
