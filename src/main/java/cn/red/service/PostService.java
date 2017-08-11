package cn.red.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.red.mapper.PostMapper;
import cn.red.mapper.UserMapper;
import cn.red.model.PageBean;
import cn.red.model.Post;
import cn.red.util.MyUtil;

@Service
public class PostService {
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	// 获得帖子列表
	public List<Post> getPostList(int uid) {
		return postMapper.listPostByUid(uid);
	}

	public PageBean<Post> listPostByTime(int curPage) {
		// 每页记录数，从哪开始
		int limit = 8;
		int offset = (curPage - 1) * limit;
		// 获得总记录数、总页数
		int allCount = postMapper.selectPostCount();
		int allPage = 0;
		if (allCount <= limit) {
			allPage = 1;
		} else if (allCount / limit == 0) {
			allPage = allCount / limit;
		} else {
			allPage = allCount / limit + 1;
		}
		// 分页得到数据列表
		List<Post> postList = postMapper.listPostByTime(offset, limit);
		// 构造PageBean
		PageBean<Post> pageBean = new PageBean<Post>(allPage, curPage);
		pageBean.setList(postList);
		
		return pageBean;
	}

	/**
	 * 发帖
	 * @param post
	 * @return
	 */
	public int publishPost(Post post) {
		post.setPublishTime(MyUtil.formatDate(new Date()));
		post.setReplyTime(MyUtil.formatDate(new Date()));
		post.setReplyCount(0);
		post.setLikeCount(0);
		post.setScanCount(0);
		// 插入一条帖子记录
		postMapper.insertPost(post);
		System.out.println(post.getPid());
		// 更新用户发帖量
		userMapper.updatePostCount(post.getUser().getUid());
		
		return post.getPid();
	}
	
	
	
}
