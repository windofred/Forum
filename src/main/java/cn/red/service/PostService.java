package cn.red.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import cn.red.async.MessageTask;
import cn.red.mapper.MessageMapper;
import cn.red.mapper.PostMapper;
import cn.red.mapper.ReplyMapper;
import cn.red.mapper.UserMapper;
import cn.red.model.PageBean;
import cn.red.model.Post;
import cn.red.util.MyConstant;
import cn.red.util.MyUtil;

@Service
public class PostService {
	
	@Resource
	private PostMapper postMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
    private MessageMapper messageMapper;
	
	@Resource
    private ReplyMapper replyMapper;
	
	@Resource
	private JedisPool jedisPool;
	
	@Resource
	private TaskExecutor taskExecutor;
	
	// 根据用户id获得帖子列表
	public List<Post> getPostList(int uid) {
		return postMapper.listPostByUid(uid);
	}

	/**
	 * 按时间列出帖子(分页功能的实现)
	 * @param curPage 当前页
	 * @return
	 */
	public PageBean<Post> listPostByTime(int curPage) {
		// 每页记录数，从哪开始
		int limit = 8;
		int offset = (curPage - 1) * limit;
		// 获得总记录数、总页数
		int allCount = postMapper.selectPostCount();
		int allPage = 0;
		if (allCount <= limit) {
			allPage = 1;// 只有1页
		} else if (allCount / limit == 0) {
			allPage = allCount / limit;
		} else {
			allPage = allCount / limit + 1;
		}
		// 分页得到数据列表
		List<Post> postList = postMapper.listPostByTime(offset, limit);
		// 从线程池获得一个jedis连接对象
		Jedis jedis = jedisPool.getResource();
		for (Post post : postList) {
			// 设置帖子的点赞数
			// 返回集合中元素的数量
			// 点赞数刷到redis数据库
			post.setLikeCount((int)(long)jedis.scard(post.getPid() + ":like"));
		}
		// 构造PageBean
		PageBean<Post> pageBean = new PageBean<Post>(allPage, curPage);
		pageBean.setList(postList);
		
		if (jedis != null) {
			// 释放连接对象
			jedisPool.returnResource(jedis);
		}
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

	public Post getPostByPid(int pid) {
		//更新浏览数
        postMapper.updateScanCount(pid);
        Post post =postMapper.getPostByPid(pid);
        //设置点赞数
        Jedis jedis = jedisPool.getResource();
        long likeCount = jedis.scard(pid+":like");
        post.setLikeCount((int)likeCount);

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return post;
	}
	
	//点赞
    public String clickLike(int pid, int sessionUid) {
        Jedis jedis = jedisPool.getResource();
        //pid(帖子id)被sessionUid点赞
        jedis.sadd(pid+":like", String.valueOf(sessionUid));
        //增加用户获赞数
        jedis.hincrBy("vote",sessionUid+"",1);

        System.out.println(jedis.ping());
        
        //插入一条点赞消息
        taskExecutor.execute(new MessageTask(messageMapper,userMapper,postMapper,replyMapper,pid,0,sessionUid, MyConstant.OPERATION_CLICK_LIKE));
        String result = String.valueOf(jedis.scard(pid+":like"));

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    //某用户是否赞过某帖子
    public boolean getLikeStatus(int pid, int sessionUid) {
        Jedis jedis = jedisPool.getResource();
        boolean result = jedis.sismember(pid+":like", String.valueOf(sessionUid));

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return result;
    }
	
}
