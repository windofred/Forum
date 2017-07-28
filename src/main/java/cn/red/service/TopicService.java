package cn.red.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.red.mapper.TopicMapper;
import cn.red.model.Topic;

@Service
public class TopicService {

	@Autowired
	private TopicMapper topicMapper;
	
	public List<Topic> listTopic() {
		return topicMapper.listTopic();
	}

	public List<String> listImage() {
		// TODO Auto-generated method stub
		return topicMapper.listImage();
	}
	
}
