package aiidc.com.cn.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aiidc.com.cn.portal.util.BaseEntity;


/**
 * 
 */
@Entity
@Table(name = "acl_role")
public class AclRole extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1778507483826185389L;
	
	private String info;
	private Integer list;
	private String name;
	private Integer system;

	@Column(name = "INFO")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "LIST")
	public Integer getList() {
		return list;
	}
	public void setList(Integer list) {
		this.list = list;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SYSTEM")
	public Integer getSystem() {
		return system;
	}
	public void setSystem(Integer system) {
		this.system = system;
	}

}