package cn.red.mapper;

import java.util.List;

import cn.red.model.Message;

public interface MessageMapper {
	
	List<Message> listMessageByUid(Integer uid);
	
	void insertMessage(Message message);
}
