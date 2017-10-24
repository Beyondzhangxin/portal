package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.AclResource;
import aiidc.com.cn.portal.parameter.AclResourceModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Dao接口
 */
public interface AclResourceDao extends AbstractDao<AclResource> {
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<AclResource> listQuery(AclResourceModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(AclResourceModel queryModel, int pageNo, int pageSize);
	
	/**
	 * 查询父级菜单(parentId = null)
	 * @param queryModel
	 * @return
	 */
	public List<AclResource> findParentResource(AclResourceModel queryModel);
}
