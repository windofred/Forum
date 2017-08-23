package cn.red.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.red.model.Info;
import cn.red.model.User;

public interface UserMapper {
	
	// 根据邮箱 和 密码 来查询用户ID
	Integer selectUidByEmailAndPassword(User user);
	
	int selectActived(User user);
	
	String selectHeadUrl(int uid);
	
	User selectUserByUid(int uid);
	
	int selectEmailCount(String email);
	
	void insertUser(User user);
	
	// 收到邮件后，“点击激活”后，就将验证码发送到服务器端，然后修改数据库中激活状态(0：未激活      1：激活)
	void updateActived(String activateCode);

	User selectEditInfo(int uid);

	void updateUser(User user);

	String selectPasswordByUid(int uid);

	void updatePassword(@Param("newPassword") String newPassword, @Param("uid") int uid);

	void updateHeadUrl(@Param("uid") int uid, @Param("headUrl") String headUrl);

	// 记录访问信息
	void insertInfo(Info info);

	// 列出用户
	List<User> listUserByTime();
	
	// 列出热门用户
	List<User> listUserByHot();

	// 更新用户的发帖数量
	void updatePostCount(Integer uid);
	
	// 根据uid查找用户名
	User selectUsernameByUid(int uid);
	
}
