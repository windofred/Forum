package cn.red.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MyUtil {
	
	// 日期的格式转换
	public static String formatDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:MM:ss");
		return simpleDateFormat.format(date);
	}
	
	// 创建激活码
	public static String createActivateCode() {
		return new Date().getTime() + UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(createActivateCode());
	}
	
}
