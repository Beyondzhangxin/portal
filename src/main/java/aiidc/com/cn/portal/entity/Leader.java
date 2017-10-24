package aiidc.com.cn.portal.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import aiidc.com.cn.portal.util.BaseEntity;


/**
 * 
 */
@Entity
@Table(name = "leader")
public class Leader extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4119575697332385004L;
	/**
	 * 名字
	 */
	@NotBlank(message="请输入")
	private String name;
	private String status = "0";
	/**
	 * 职务
	 */
	@NotBlank(message="请输入")
	private String position;
	/**
	 * 部门
	 */
	@NotBlank(message="请输入")
	private String department;
	/**
	 * 简介
	 */
	private String introduction;
	/**
	 * 图片名称
	 */
	private String picName;
	/**
	 * 头像id
	 */
	private String picId;
	/**
	 * 是否置顶 1置顶
	 */
	private String isTop = "0";
	private Calendar createTime;
	
	private CommonsMultipartFile file;
	
	@Column(name="is_top")
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "pic_name")
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}

	@Column(name = "pic_id")
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	
	@Column(name = "create_time")
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	
	@Transient
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	

}