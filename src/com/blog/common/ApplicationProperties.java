package com.blog.common;

import java.util.Properties;

/**
 * 通过此类读取application.properties的属性
 * @author liugang
 *
 */
public class ApplicationProperties {
	private static Properties prop;
	
	public Properties getProp() {
		return prop;
	}

	public static void setProp(Properties props) {
	    prop = props;
	}
	
	public static String getProproperty(String key){
		return prop.getProperty(key);
	}
}
