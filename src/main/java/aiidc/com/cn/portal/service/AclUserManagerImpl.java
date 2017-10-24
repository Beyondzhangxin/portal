package aiidc.com.cn.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.AclUserDao;
import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.parameter.AclUserModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 业务实现
 */
@Service("aclUserManager")
public class AclUserManagerImpl implements AclUserManager {
	@Autowired
	private AclUserDao aclUserDao;


	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(AclUser entity) {
		aclUserDao.persist(entity);
	}

	public void update(AclUser entity) {
		aclUserDao.update(entity);
	}

	public AclUser findById(String entityId) {
		return aclUserDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		aclUserDao.deleteById(entityId);
	}

	public Pagination pageQuery(AclUserModel queryModel) {
		return aclUserDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<AclUser> ListQuery(AclUserModel queryModel) {
		return aclUserDao.listQuery(queryModel);
	}
	
}
