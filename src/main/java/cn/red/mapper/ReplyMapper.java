package cn.red.mapper;

import java.util.List;

import cn.red.model.Comment;
import cn.red.model.Reply;

public interface ReplyMapper {
	
	String getContentByRid(int rid);
	
	List<Reply> listReply(int pid);
	
	List<Comment> listComment(Integer rid);
	
	void insertReply(Reply reply);
	
	void insertComment(Comment comment);
	
}
