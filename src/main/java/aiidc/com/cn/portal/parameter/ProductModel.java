package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;
import aiidc.com.cn.portal.util.ProductSubType;

/**
 * 产品查询Model
 */
public class ProductModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
	private String status ;
	private String type;
	private String subTypeStr;
	private ProductSubType subType;

	public ProductSubType getSubType()
	{
		return subType;
	}

	public void setSubType(ProductSubType subType)
	{
		this.subType = subType;
	}

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

	public String getSubTypeStr()
	{
		return subTypeStr;
	}

	public void setSubTypeStr(String subTypeStr)
	{
		this.subTypeStr = subTypeStr;
	}
}
