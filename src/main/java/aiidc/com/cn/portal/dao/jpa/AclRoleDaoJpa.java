package aiidc.com.cn.portal.dao.jpa;

import org.springframework.stereotype.Repository;

import aiidc.com.cn.portal.dao.AclRoleDao;
import aiidc.com.cn.portal.entity.AclRole;
import aiidc.com.cn.portal.util.AbstractDaoJpa;

/**
 * Dao Jpa实现
 */
@Repository("aclRoleDao")
public class AclRoleDaoJpa extends AbstractDaoJpa<AclRole> implements AclRoleDao {

}
