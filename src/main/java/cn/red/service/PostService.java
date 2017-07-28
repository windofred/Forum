package cn.red.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.red.mapper.PostMapper;
import cn.red.model.Post;

@Service
public class PostService {
	
	@Autowired
	private PostMapper postMapper;
	
	// 获得帖子列表
	public List<Post> getPostList(int uid) {
		return postMapper.listPostByUid(uid);
	}
	
}
