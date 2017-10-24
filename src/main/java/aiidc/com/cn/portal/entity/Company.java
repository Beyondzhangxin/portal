package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 * 公司
 */
@Entity
@Table(name = "company")
public class Company extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1205224867359610150L;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 概况
	 */
	@NotBlank
	private String content;
	/**
	 * 联系人
	 */
	@NotBlank
	private String contact;
	/**
	 * 邮箱
	 */
	@NotBlank
	private String email;
	/**
	 * 公司地址
	 */
	@NotBlank
	private String address;
	/**
	 * 是否置顶 1置顶
	 */
	private String isTop = "0";
	/**
	 * 审核状态：未审核，通过，不通过 0,1,2
	 */
	private String status = "0";

	/**
	 * 首页概述
	 */
	@NotBlank
	private String overview;

	@Column(name = "overview")
	public String getOverview()
	{
		return overview;
	}

	public void setOverview(String overview)
	{
		this.overview = overview;
	}

	@Column(name="is_top")
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "contact")
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}