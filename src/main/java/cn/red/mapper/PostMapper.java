package cn.red.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.red.model.PageBean;
import cn.red.model.Post;

public interface PostMapper {
	
	List<Post> listPostByUid(int uid);

	// 获得总记录数
	int selectPostCount();

	// 按时间列出帖子
	List<Post> listPostByTime(@Param("offset") int offset, @Param("limit") int limit);

	// 插入一条帖子
	void insertPost(Post post);
	
}
