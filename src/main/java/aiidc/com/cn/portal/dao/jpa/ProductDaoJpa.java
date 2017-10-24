package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import aiidc.com.cn.portal.dao.ProductDao;
import aiidc.com.cn.portal.entity.Product;
import aiidc.com.cn.portal.parameter.ProductModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质Dao Jpa实现
 */
@Repository("ProductDao")
public class ProductDaoJpa extends AbstractDaoJpa<Product> implements ProductDao {

	public List<Product> listQuery(ProductModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}

	public Pagination pageQuery(ProductModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, ProductModel queryModel) {
		param.addOrder(Order.desc("createTime"));
		if (StringUtils.hasText(queryModel.getType())) {
			param.add(Restrictions.eq("type", queryModel.getType()));
		}
		if (queryModel.getSubType() !=null) {
			param.add(Restrictions.eq("subType", queryModel.getSubType()));
		}
		if (StringUtils.hasText(queryModel.getStatus())) {
			param.add(Restrictions.eq("status", queryModel.getStatus()));
		}
	}

}
