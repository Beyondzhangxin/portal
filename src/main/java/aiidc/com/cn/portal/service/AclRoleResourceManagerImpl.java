package aiidc.com.cn.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.AclRoleResourceDao;
import aiidc.com.cn.portal.entity.AclRoleResource;

/**
 * 业务实现
 */
@Service("aclRoleResourceManager")
public class AclRoleResourceManagerImpl implements AclRoleResourceManager {
	@Autowired
	private AclRoleResourceDao aclRoleResourceDao;

	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(AclRoleResource entity) {
		aclRoleResourceDao.persist(entity);
	}

	public void update(AclRoleResource entity) {
		aclRoleResourceDao.update(entity);
	}

	public AclRoleResource findById(String entityId) {
		return aclRoleResourceDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		aclRoleResourceDao.deleteById(entityId);
	}

	public List<AclRoleResource> findByRoleId(String roleId) {
		return aclRoleResourceDao.findByRoleId(roleId);
	}
	
}
