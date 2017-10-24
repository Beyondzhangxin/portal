package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import aiidc.com.cn.portal.dao.CultureDao;
import aiidc.com.cn.portal.entity.Culture;
import aiidc.com.cn.portal.parameter.CultureModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质Dao Jpa实现
 */
@Repository("cultureDao")
public class CultureDaoJpa extends AbstractDaoJpa<Culture> implements CultureDao {

	public List<Culture> listQuery(CultureModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}

	public Pagination pageQuery(CultureModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, CultureModel queryModel) {
		param.addOrder(Order.desc("createTime"));
		if (StringUtils.hasText(queryModel.getType())) {
			param.add(Restrictions.eq("type", queryModel.getType()));
		}
		if (StringUtils.hasText(queryModel.getStatus())) {
			param.add(Restrictions.eq("status", queryModel.getStatus()));
		}
	}

}
