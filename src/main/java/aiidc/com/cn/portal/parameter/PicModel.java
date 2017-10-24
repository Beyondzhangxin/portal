package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;

/**
 * 图片查询Model
 */
public class PicModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
	private String type;

	private String itemId;

	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
