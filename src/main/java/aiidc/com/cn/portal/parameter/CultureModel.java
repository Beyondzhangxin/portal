package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;

/**
 * 企业文化与资质查询Model
 */
public class CultureModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
	private String status ;
	/**
	 * 类型(1，企业文化  2，荣誉与资质)
	 */
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
