package aiidc.com.cn.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.AclResourceDao;
import aiidc.com.cn.portal.entity.AclResource;
import aiidc.com.cn.portal.entity.AclRole;
import aiidc.com.cn.portal.entity.AclRoleResource;
import aiidc.com.cn.portal.entity.AclUser;

/**
 * 业务实现
 */
@Service("aclResourceManager")
public class AclResourceManagerImpl implements AclResourceManager {
	@Autowired
	private AclResourceDao aclResourceDao;
	@Autowired
	private AclRoleResourceManager aclRoleResourceManager;


	public void add(AclResource entity) {
		aclResourceDao.persist(entity);
	}

	public void update(AclResource entity) {
		aclResourceDao.update(entity);
	}

	public AclResource findById(String entityId) {
		return aclResourceDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		aclResourceDao.deleteById(entityId);
	}

	public List<AclResource> findAllResource(AclUser loginUser) {
		AclRole role = loginUser.getRole();
		List<AclRoleResource> RoleResourceList = aclRoleResourceManager.findByRoleId(role.getId());
		List<AclResource> list = new ArrayList<AclResource>();
		for (AclRoleResource aclRoleResource : RoleResourceList) {
			if (aclRoleResource.getResource().getParentId() == null ) {
				list.add(aclRoleResource.getResource());
			}
		}
		
		for (AclResource aclResource : list) {
			List<AclResource> childResource = new ArrayList<AclResource>();
			for (AclRoleResource aclRoleResource : RoleResourceList) {
				if (aclRoleResource.getResource().getParentId() !=null &&
						aclRoleResource.getResource().getParentId().equals(aclResource.getId()) ) {
					childResource.add(aclRoleResource.getResource());
//					aclResource.getChildResource().add(aclRoleResource.getResource());
				}
			}
			if (childResource != null  &&childResource.size() >0) {
				aclResource.setChildResource(childResource);
			}
		}
		
//		AclResourceModel queryModel = new AclResourceModel();
//		List<AclResource> parentResource = aclResourceDao.findParentResource(null );
//		for (AclResource pr : parentResource) {
//			queryModel.setParentId(pr.getId());
//			List<AclResource> list = aclResourceDao.listQuery(queryModel);
//			if (list !=null && list.size()>0) {
//				pr.setChildResource(list);
//			}
//		}
		return list;
	}

	public List<AclResource> getAll() {
		return aclResourceDao.getAll();
	}

	public AclResource findByProperty(String Property, Object value) {
		return aclResourceDao.findByProperty(Property, value);
	}
	
}
