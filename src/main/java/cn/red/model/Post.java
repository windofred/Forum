package cn.red.model;

public class Post {

	private Integer pid;// 帖子的id
	private String title;// 帖子的标题
	private String content;// 帖子的内容
	private String publishTime;// 发布时间
	private String replyTime;// 回帖时间
	private Integer replyCount; // 回帖的数量
	private Integer likeCount;// 获赞
	private Integer scanCount;// 被浏览

	private User user;// 用户
	private Topic topic;// 主题

	public Post() {
		super();
	}

	public Post(Integer pid) {
		super();
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getScanCount() {
		return scanCount;
	}

	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Post [pid=" + pid + ", title=" + title + ", content=" + content
				+ ", publishTime=" + publishTime + ", replyTime=" + replyTime
				+ ", replyCount=" + replyCount + ", likeCount=" + likeCount
				+ ", scanCount=" + scanCount + ", user=" + user + ", topic="
				+ topic + "]";
	}

}
