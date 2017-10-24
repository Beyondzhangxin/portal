package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.Culture;
import aiidc.com.cn.portal.parameter.CultureModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质业务接口
 */
public interface CultureManager {
	
	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(CultureModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Culture> ListQuery(CultureModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Culture entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Culture entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Culture findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Culture> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public Culture findByProperty(String Property , Object value);
}
