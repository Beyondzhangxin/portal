package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.CustomerMessage;
import aiidc.com.cn.portal.parameter.CustomerMessageModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 客户留言Dao接口
 */
public interface CustomerMessageDao extends AbstractDao<CustomerMessage> {
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<CustomerMessage> listQuery(CustomerMessageModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(CustomerMessageModel queryModel, int pageNo, int pageSize);
}
