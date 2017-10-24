package aiidc.com.cn.portal.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import aiidc.com.cn.portal.util.BaseEntity;


/**
 * 
 */
@Entity
@Table(name = "acl_role_resource")
public class AclRoleResource extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6116428891230642440L;
	
	private AclResource resource;
	private AclRole role;
	@ManyToOne
	@JoinColumn(name = "RESOURCE_ID" ,nullable = false)
	public AclResource getResource() {
		return resource;
	}
	public void setResource(AclResource resource) {
		this.resource = resource;
	}
	@ManyToOne
	@JoinColumn(name = "ROLE_ID" ,nullable = false)
	public AclRole getRole() {
		return role;
	}
	public void setRole(AclRole role) {
		this.role = role;
	}


}