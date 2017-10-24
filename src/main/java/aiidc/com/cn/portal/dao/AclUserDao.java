package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.parameter.AclUserModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Dao接口
 */
public interface AclUserDao extends AbstractDao<AclUser> {
	
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<AclUser> listQuery(AclUserModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(AclUserModel queryModel, int pageNo, int pageSize);
	
	
	
}
