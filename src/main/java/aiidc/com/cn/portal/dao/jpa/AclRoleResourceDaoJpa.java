package aiidc.com.cn.portal.dao.jpa;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import aiidc.com.cn.portal.dao.AclRoleResourceDao;
import aiidc.com.cn.portal.entity.AclRoleResource;
import aiidc.com.cn.portal.util.AbstractDaoJpa;

/**
 * Dao Jpa实现
 */
@Repository("aclRoleResourceDao")
public class AclRoleResourceDaoJpa extends AbstractDaoJpa<AclRoleResource> implements AclRoleResourceDao {

	public List<AclRoleResource> findByRoleId(String roleId) {
		param = DetachedCriteria.forClass(clazz);
		param.add(Restrictions.eq("role.id", roleId));
		return super.queryList(param);
	}

}
