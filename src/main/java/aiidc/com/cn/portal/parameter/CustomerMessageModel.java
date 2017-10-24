package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;

/**
 * 客户留言查询Model
 */
public class CustomerMessageModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
	private String status;
	private String isVisible;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
	
}
