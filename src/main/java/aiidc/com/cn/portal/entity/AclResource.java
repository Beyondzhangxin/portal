package aiidc.com.cn.portal.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import aiidc.com.cn.portal.util.BaseEntity;


/**
 * 
 */
@Entity
@Table(name = "acl_resource")
public class AclResource extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6419779690135168552L;
	
	private Integer list;
	private String logoPic;
	private String menuId;
	private String menuType;
	private String name;
	private String typeCode;
	private String url;
	private String requestMethod;
	private String parentId;
	private List<AclResource> childResource;

	@Column(name = "LIST")
	public Integer getList() {
		return list;
	}
	public void setList(Integer list) {
		this.list = list;
	}

	@Column(name = "LOGO_PIC")
	public String getLogoPic() {
		return logoPic;
	}
	public void setLogoPic(String logoPic) {
		this.logoPic = logoPic;
	}

	@Column(name = "MENU_ID")
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "MENU_TYPE")
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE_CODE")
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "REQUEST_METHOD")
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	@Column(name="parent_id")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Transient
	public List<AclResource> getChildResource() {
		return childResource;
	}
	public void setChildResource(List<AclResource> childResource) {
		this.childResource = childResource;
	}

	
	
}