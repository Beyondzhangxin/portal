package aiidc.com.cn.portal.util;

/**
 * Created by Zhangx on 2017/9/8 at 15:20.
 */
public enum NewsType {
	公司("公司新闻", "1") {},
	媒体("媒体聚焦", "2") {},
	行业("行业新闻", "3") {},
	;
	private final String name;
	private final String value;
	
	private NewsType(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}


}
