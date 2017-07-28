package cn.red.mapper;

import java.util.List;

import cn.red.model.Post;

public interface PostMapper {
	
	List<Post> listPostByUid(int uid);
	
}
