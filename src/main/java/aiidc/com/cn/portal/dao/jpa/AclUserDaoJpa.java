package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import aiidc.com.cn.portal.dao.AclUserDao;
import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.parameter.AclUserModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Dao Jpa实现
 */
@Repository("aclUserDao")
public class AclUserDaoJpa extends AbstractDaoJpa<AclUser> implements AclUserDao {

	public List<AclUser> listQuery(AclUserModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}
	
	public Pagination pageQuery(AclUserModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, AclUserModel queryModel) {
		if (StringUtils.hasText(queryModel.getLoginName())) {
			param.add(Restrictions.eq("loginName", queryModel.getLoginName()));
		}
		if(StringUtils.hasText(queryModel.getPassword())){
			param.add(Restrictions.eq("passwd", queryModel.getPassword()));
		}
	}

}
