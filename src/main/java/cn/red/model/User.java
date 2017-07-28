package cn.red.model;

public class User {

	private Integer uid;// 用户id
	private String email;// 用户邮箱
	private String password;// 用户密码
	private Integer actived;// 激活
	private String activateCode;// 激活码
	private String joinTime;// 加入时间

	private String username;// 用户名
	private String description;// 描述
	private String headUrl;
	private String position;// 位置
	private String school;// 学校
	private String job;// 工作

	private Integer likeCount;
	private Integer postCount;
	private Integer scanCount;
	private Integer followCount;
	private Integer followerCount;

	public User() {
		super();
	}

	public User(Integer uid) {
		super();
		this.uid = uid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getActived() {
		return actived;
	}

	public void setActived(Integer actived) {
		this.actived = actived;
	}

	public String getActiveCode() {
		return activateCode;
	}

	public void setActiveCode(String activeCode) {
		this.activateCode = activeCode;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	public Integer getScanCount() {
		return scanCount;
	}

	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public Integer getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", email=" + email + ", password="
				+ password + ", actived=" + actived + ", activeCode="
				+ activateCode + ", joinTime=" + joinTime + ", username="
				+ username + ", description=" + description + ", headUrl="
				+ headUrl + ", position=" + position + ", school=" + school
				+ ", job=" + job + ", likeCount=" + likeCount + ", postCount="
				+ postCount + ", scanCount=" + scanCount + ", followCount="
				+ followCount + ", followerCount=" + followerCount + "]";
	}

}
