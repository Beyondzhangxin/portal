package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.AclRole;

/**
 * 业务接口
 */
public interface AclRoleManager {

	
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(AclRole entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(AclRole entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public AclRole findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所用的角色
	 * @return
	 */
	public List<AclRole> findAllRole();
}
