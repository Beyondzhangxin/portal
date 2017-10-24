package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;

/**
 * 查询Model
 */
public class AclResourceModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
	private String parentId;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
