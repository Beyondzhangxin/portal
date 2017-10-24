package aiidc.com.cn.portal.dao.jpa;

import aiidc.com.cn.portal.dao.SolutionDao;
import aiidc.com.cn.portal.entity.Solution;
import aiidc.com.cn.portal.parameter.SolutionModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 解决方案Dao Jpa实现
 */
@Repository("solutionDao")
public class SolutionDaoJpa extends AbstractDaoJpa<Solution> implements SolutionDao {

	public List<Solution> listQuery(SolutionModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}

	public Pagination pageQuery(SolutionModel queryModel, int pageNo, int pageSize) {
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
	private void setQueryParameters(DetachedCriteria param, SolutionModel queryModel) {
//		if (StringUtils.hasText(queryModel.getLoginName())) {
//			param.add(Restrictions.eq("loginName", queryModel.getLoginName()));
//		}
		if (StringUtils.hasText(queryModel.getType())){
			param.add(Restrictions.eq("type", queryModel.getType()));
		}
		if (StringUtils.hasText(queryModel.getStatus())){
			param.add(Restrictions.eq("status", queryModel.getStatus()));
		}


	}

}
