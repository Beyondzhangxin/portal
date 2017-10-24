package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.Product;
import aiidc.com.cn.portal.parameter.ProductModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质业务接口
 */
public interface ProductManager {
	
	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(ProductModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Product> ListQuery(ProductModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Product entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Product entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Product findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Product> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public Product findByProperty(String Property , Object value);
}
