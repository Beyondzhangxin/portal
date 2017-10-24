package aiidc.com.cn.portal.service;

import aiidc.com.cn.portal.entity.AclResource;
import aiidc.com.cn.portal.entity.AclUser;

import java.util.List;

/**
 * 业务接口
 */
public interface AclResourceManager {
	
	/**
	 * 查询用户资源列表
	 * @return
	 */
	public List<AclResource> findAllResource(AclUser loginUser);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(AclResource entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(AclResource entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public AclResource findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<AclResource> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public AclResource findByProperty(String Property , Object value);
	
}
