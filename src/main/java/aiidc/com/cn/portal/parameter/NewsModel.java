package aiidc.com.cn.portal.parameter;

import aiidc.com.cn.portal.util.OrderPaginModel;

/**
 * 公司新闻查询Model
 */
public class NewsModel extends OrderPaginModel {
	// 可根据实际查询需要增加相应的字段
    private String type;
    private String status;
    private String iscover;

    public String getIscover()
    {
        return iscover;
    }

    public void setIscover(String iscover)
    {
        this.iscover = iscover;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
