package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.Solution;
import aiidc.com.cn.portal.parameter.SolutionModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 解决方案业务接口
 */
public interface SolutionManager {

	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(SolutionModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Solution> ListQuery(SolutionModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Solution entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Solution entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Solution findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	/**
	 * 查询所有
	 * @return
	 */
	public List<Solution> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public Solution findByProperty(String Property , Object value);
}
