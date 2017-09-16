package cn.red.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.red.mapper.UserMapper;
import cn.red.model.Info;
import cn.red.model.User;

@Service
public class UserService {
	
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 
	 * @param sessionUid 会话id
	 * @param uid 用户id
	 * @return 用户
	 */
	public User getProfile(int sessionUid, int uid) {
		// 通过uid查询用户
		User user = userMapper.selectUserByUid(uid);
		return user;
	}
	
	/**
	 * 获得用户的编辑信息
	 * @param uid
	 * @return
	 */
	public User getEditInfo(int uid) {
		return userMapper.selectEditInfo(uid);
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
	
	/**
	 * 更新密码
	 * @param password
	 * @param newpassword
	 * @param repassword
	 * @param sessionUid
	 * @return
	 */
	public String updatePassword(String password, String newpassword, String repassword, int sessionUid) {
		String oldPassword = userMapper.selectPasswordByUid(sessionUid);
        if(!oldPassword.equals(password)){
            return "原密码输入错误~";
        }

        if(newpassword.length() < 6 || newpassword.length() > 20){
            return "新密码长度要在6~20之间~";
        }

        if(!newpassword.equals(repassword)){
            return "新密码两次输入不一致~";
        }

        userMapper.updatePassword(newpassword,sessionUid);
        return "ok";
	}
	
	public void updateHeadUrl(int uid, String headUrl) {
        userMapper.updateHeadUrl(uid,headUrl);
    }

	/**
	 * 记录访问信息
	 * @param requestURL 请求的URL
	 * @param contextPath 上下文路径
	 * @param remoteAddr 客户端IP地址
	 */
	public void record(StringBuffer requestURL, String contextPath, String remoteAddr) {
		Info info = new Info();
		info.setRequestUrl(requestURL.toString());
		info.setContextPath(contextPath);
		info.setRemoteAddr(remoteAddr);
		userMapper.insertInfo(info);
	}

	/**
	 * 列出用户
	 * @return
	 */
	public List<User> listUserByTime() {
		return userMapper.listUserByTime();
	}

	/**
	 * 列出热门用户
	 * @return
	 */
	public List<User> listUserByHot() {
		return userMapper.listUserByHot();
	}
	
}
