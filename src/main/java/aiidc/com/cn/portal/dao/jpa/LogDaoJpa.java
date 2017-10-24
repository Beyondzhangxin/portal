package aiidc.com.cn.portal.dao.jpa;

import aiidc.com.cn.portal.dao.LogDao;
import aiidc.com.cn.portal.entity.Log;
import aiidc.com.cn.portal.parameter.LogModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司新闻Dao Jpa实现
 */
@Repository("LogDao")
public class LogDaoJpa extends AbstractDaoJpa<Log> implements LogDao
{

	public List<Log> listQuery(LogModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}

	public Pagination pageQuery(LogModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		param.addOrder(Order.desc("isTop")).addOrder(Order.desc("lastModifiedTime"));
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, LogModel queryModel) {
//		if (StringUtils.hasText(queryModel.getLoginName())) {
//			param.add(Restrictions.eq("loginName", queryModel.getLoginName()));
//		}
		
	}

}
