package cn.red.model;

public class Topic {

	private Integer tid;// 话题id
	private String name;// 名字
	private String content;// 内容
	private String image;// 图片

	public Topic() {
		super();
	}

	public Topic(Integer tid) {
		super();
		this.tid = tid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Topic [tid=" + tid + ", name=" + name + ", content=" + content
				+ ", image=" + image + "]";
	}

}
