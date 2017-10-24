package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;

/**
 * 人才招聘查询Model
 */
public class RecruitmentModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
	/**
	 * 审核状态：未审核，通过，不通过 0,1,2
	 */
	private String status;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
