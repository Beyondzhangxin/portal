package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import aiidc.com.cn.portal.dao.CompanyDao;
import aiidc.com.cn.portal.entity.Company;
import aiidc.com.cn.portal.parameter.CompanyModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 公司Dao Jpa实现
 */
@Repository("companyDao")
public class CompanyDaoJpa extends AbstractDaoJpa<Company> implements CompanyDao {

	public List<Company> listQuery(CompanyModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}

	public Pagination pageQuery(CompanyModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, CompanyModel queryModel) {
//		if (StringUtils.hasText(queryModel.getLoginName())) {
//			param.add(Restrictions.eq("loginName", queryModel.getLoginName()));
//		}
	}

}
