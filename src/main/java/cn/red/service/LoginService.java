package cn.red.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cn.red.async.MailTask;
import cn.red.mapper.UserMapper;
import cn.red.model.User;
import cn.red.util.MyUtil;

@Service
public class LoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	// 登录操作
	public Map<String, Object> login(User user) {
		Map<String, Object> map = new HashMap<>();
		Integer uid = userMapper.selectUidByEmailAndPassword(user);
		if (uid == null) {
			map.put("status", "no");
			map.put("error", "用户名或密码错误");
			return map;
		}
		
		int checkActived = userMapper.selectActived(user);
		if (checkActived == 0) {
			map.put("status", "no");
			map.put("error", "您还没有激活账户额，请前往邮箱激活吧~");
			return map;
		}
		
		String headUrl = userMapper.selectHeadUrl(uid);
		
		map.put("status", "yes");
		map.put("uid", uid);
		map.put("headUrl", headUrl);
		
		return map;
	}
	
	// 注册操作
	public String register(User user, String repassword) {
		// 校验邮箱格式
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
		Matcher matcher = pattern.matcher(user.getEmail());
		if (!matcher.matches()) {
			return "邮箱格式有问题";
		}
		
		// 校验密码长度
		pattern = Pattern.compile("^\\w{6,20}$");
		matcher = pattern.matcher(user.getPassword());
		if (!matcher.matches()) {
			return "密码长度要在6到20之间";
		}
		
		// 检查密码
		if (!user.getPassword().equals(repassword)) {
			return "两次输入的密码不一致";
		}
		
		// 检查邮箱是否已经被注册
		int emailCount = userMapper.selectEmailCount(user.getEmail());
		if (emailCount > 0) {
			return "该邮箱已经被注册";
		}
		
		// 构造user,设置未激活
		user.setActived(0);
		String activateCode = MyUtil.createActivateCode();
		user.setActiveCode(activateCode);
		user.setJoinTime(MyUtil.formatDate(new Date()));
		
		// 发送邮件
		taskExecutor.execute(new MailTask(activateCode, user.getEmail(), javaMailSender, 1));
		
		// 向数据库插入记录
		userMapper.insertUser(user);
		
		return "ok";
	}
	
	// 激活
	public void activate(String activateCode) {
		userMapper.updateActived(activateCode);
	}
	
}
