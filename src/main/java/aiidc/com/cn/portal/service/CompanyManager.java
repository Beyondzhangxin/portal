package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.Company;
import aiidc.com.cn.portal.parameter.CompanyModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 公司业务接口
 */
public interface CompanyManager {

	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(CompanyModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Company> ListQuery(CompanyModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Company entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Company entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Company findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Company> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public Company findByProperty(String Property , Object value);
}
