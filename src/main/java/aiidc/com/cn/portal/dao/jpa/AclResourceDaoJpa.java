package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import aiidc.com.cn.portal.dao.AclResourceDao;
import aiidc.com.cn.portal.entity.AclResource;
import aiidc.com.cn.portal.parameter.AclResourceModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Dao Jpa实现
 */
@Repository("aclResourceDao")
public class AclResourceDaoJpa extends AbstractDaoJpa<AclResource> implements AclResourceDao {

	public List<AclResource> listQuery(AclResourceModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
        return super.queryList(param);
	}
	
	public Pagination pageQuery(AclResourceModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, AclResourceModel queryModel) {
		param.addOrder(Order.asc("list"));
		if (StringUtils.hasText(queryModel.getParentId())) {
			param.add(Restrictions.eq("parentId", queryModel.getParentId()));
		}
	}

	public List<AclResource> findParentResource(AclResourceModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		param.addOrder(Order.asc("list"));
		param.add(Restrictions.isNull("parentId"));
		return super.queryList(param);
	}

}
