package aiidc.com.cn.portal.util;

public enum ResourceType {
	A_GSGKGL("公司概况管理", "icon-1.png"){},
	B_GSXWGL("公司新闻管理", "icon-5.png"){},
	C_JJFAGL("解决方案管理", "icon-2.png"){},
	D_RCZPGL("人才招聘管理", "icon-4.png"){},
	E_KHLYGL("客户留言管理", "icon-3.png"){},
	;
	
	private final String value;
	private final String logoPic;
	
	private ResourceType(String value, String logoPic) {
		this.value = value;
		this.logoPic = logoPic;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getLogoPic() {
		return logoPic;
	}
	
}
