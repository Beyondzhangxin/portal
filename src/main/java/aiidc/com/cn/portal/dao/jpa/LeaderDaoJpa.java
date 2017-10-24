package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import aiidc.com.cn.portal.dao.LeaderDao;
import aiidc.com.cn.portal.entity.Leader;
import aiidc.com.cn.portal.parameter.LeaderModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Dao Jpa实现
 */
@Repository("LeaderDao")
public class LeaderDaoJpa extends AbstractDaoJpa<Leader> implements LeaderDao {

	public List<Leader> listQuery(LeaderModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}
	
	public Pagination pageQuery(LeaderModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, LeaderModel queryModel) {
		param.addOrder(Order.desc("createTime"));
		if (StringUtils.hasText(queryModel.getStatus())) {
			param.add(Restrictions.eq("status", queryModel.getStatus()));
		}
	}


}
