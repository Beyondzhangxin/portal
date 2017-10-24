package aiidc.com.cn.portal.util;

public enum ProductSubType {
	A_DSG("大数据", "dsj.png"){},
	B_ZHAJ("智慧安检", "zhaj.png"){},
	C_ZHHB("智慧环保", "zhhb.png"){},
	D_ZHJT("智慧交通", "zhjt.png"){},
	E_ZHJW("智慧检务", "zhjw.png"){},
	;
	
	private final String value;
	private final String logoPic;
	
	private ProductSubType(String value, String logoPic) {
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
