package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;

/**
 * 查询Model
 */
public class LeaderModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
	private String status;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
