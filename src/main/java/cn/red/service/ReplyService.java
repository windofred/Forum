package cn.red.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.red.mapper.ReplyMapper;
import cn.red.model.Reply;

@Service
public class ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	/**
	 * 根据pid列出回复
	 * @param pid
	 * @return
	 */
	public Reply listReply(int pid){
		// 列出回复
		//List<Reply> replyList = replyMapper.listReply(pid);
		
		return null;
	}
	
	
	
}
