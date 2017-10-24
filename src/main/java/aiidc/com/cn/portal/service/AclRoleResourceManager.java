package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.AclRoleResource;

/**
 * 业务接口
 */
public interface AclRoleResourceManager {

	
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(AclRoleResource entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(AclRoleResource entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public AclRoleResource findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 获取角色的资源列表
	 * @param roleId
	 * @return
	 */
	public List<AclRoleResource> findByRoleId(String roleId);
}
