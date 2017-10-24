package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import aiidc.com.cn.portal.dao.CustomerMessageDao;
import aiidc.com.cn.portal.entity.CustomerMessage;
import aiidc.com.cn.portal.parameter.CustomerMessageModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 客户留言Dao Jpa实现
 */
@Repository("customerMessageDao")
public class CustomerMessageDaoJpa extends AbstractDaoJpa<CustomerMessage> implements CustomerMessageDao {

	public List<CustomerMessage> listQuery(CustomerMessageModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}

	public Pagination pageQuery(CustomerMessageModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, CustomerMessageModel queryModel) {
		param.addOrder(Order.desc("createTime"));
		if (StringUtils.hasText(queryModel.getStatus())) {
			param.add(Restrictions.eq("status", queryModel.getStatus()));
		}
		if (StringUtils.hasText(queryModel.getIsVisible())) {
			param.add(Restrictions.eq("isVisible", queryModel.getIsVisible()));
		}
	}

}
