package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.Product;
import aiidc.com.cn.portal.parameter.ProductModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质Dao接口
 */
public interface ProductDao extends AbstractDao<Product> {
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<Product> listQuery(ProductModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(ProductModel queryModel, int pageNo, int pageSize);
}
