package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.CustomerMessage;
import aiidc.com.cn.portal.parameter.CustomerMessageModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 客户留言业务接口
 */
public interface CustomerMessageManager {

	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(CustomerMessageModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<CustomerMessage> ListQuery(CustomerMessageModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(CustomerMessage entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(CustomerMessage entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public CustomerMessage findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<CustomerMessage> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public CustomerMessage findByProperty(String Property , Object value);
}
