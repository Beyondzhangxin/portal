package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.AclRoleResource;
import aiidc.com.cn.portal.util.AbstractDao;

/**
 * Dao接口
 */
public interface AclRoleResourceDao extends AbstractDao<AclRoleResource> {
	
	/**
	 * 获取角色的资源列表
	 * @param roleId
	 * @return
	 */
	public List<AclRoleResource> findByRoleId(String roleId);
}
