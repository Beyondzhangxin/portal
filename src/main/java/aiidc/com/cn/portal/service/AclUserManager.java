package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.parameter.AclUserModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 业务接口
 */
public interface AclUserManager {
	
	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(AclUserModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<AclUser> ListQuery(AclUserModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(AclUser entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(AclUser entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public AclUser findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
}
