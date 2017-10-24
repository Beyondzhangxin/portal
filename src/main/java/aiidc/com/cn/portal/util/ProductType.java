package aiidc.com.cn.portal.util;

public enum ProductType {
	A_PTL("平台类") {},
	B_HYL("行业类") {},
	;
	private final String value;
	
	private ProductType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
