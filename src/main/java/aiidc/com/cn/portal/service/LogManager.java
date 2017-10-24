package aiidc.com.cn.portal.service;

import aiidc.com.cn.portal.entity.Log;
import aiidc.com.cn.portal.parameter.LogModel;
import aiidc.com.cn.portal.util.Pagination;

import java.util.List;

/**
 * 公司新闻业务接口
 */
public interface LogManager
{

	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(LogModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Log> ListQuery(LogModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Log entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Log entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Log findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Log> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public Log findByProperty(String Property, Object value);
}
