package aiidc.com.cn.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.AclRoleDao;
import aiidc.com.cn.portal.entity.AclRole;

/**
 * 业务实现
 */
@Service("aclRoleManager")
public class AclRoleManagerImpl implements AclRoleManager {
	@Autowired
	private AclRoleDao aclRoleDao;


	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(AclRole entity) {
		aclRoleDao.persist(entity);
	}

	public void update(AclRole entity) {
		aclRoleDao.update(entity);
	}

	public AclRole findById(String entityId) {
		return aclRoleDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		aclRoleDao.deleteById(entityId);
	}

	public List<AclRole> findAllRole() {
		return aclRoleDao.getAll();
	}
	
}
