package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import aiidc.com.cn.portal.dao.RecruitmentDao;
import aiidc.com.cn.portal.entity.Recruitment;
import aiidc.com.cn.portal.parameter.RecruitmentModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 人才招聘Dao Jpa实现
 */
@Repository("recruitmentDao")
public class RecruitmentDaoJpa extends AbstractDaoJpa<Recruitment> implements RecruitmentDao {

	public List<Recruitment> listQuery(RecruitmentModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}

	public Pagination pageQuery(RecruitmentModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, RecruitmentModel queryModel) {
		param.addOrder(Order.desc("isTop"));
		param.addOrder(Order.desc("createTime"));
		if (StringUtils.hasText(queryModel.getStatus())) {
			param.add(Restrictions.eq("status", queryModel.getStatus()));
		}
	}

	
}
