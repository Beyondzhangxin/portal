package aiidc.com.cn.portal.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import aiidc.com.cn.portal.util.BaseEntity;
import aiidc.com.cn.portal.util.ProductSubType;

/**
 * 公司产品
 */
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1677288781578379480L;
	/**
	 * 标题
	 */
	@NotBlank
	private String name;
	/**
	 * 类型(1，平台类  2，行业类)
	 */
	private String type;
	/**
	 * 分类
	 */
	private ProductSubType subType;
	private String subTypeString;
	/**
	 * 内容
	 */
	@NotBlank
	private String content;
	/**
	 * 上传时间
	 */
	private Calendar createTime;
	private String creator;
	/**
	 * 审核状态：未审核，通过，不通过 0,1,2
	 */
	private String status = "0";
	/**
	 * 图片名称
	 */
	private String picName;
	/**
	 * 头像id
	 */
	private String picId;
	
	private MultipartFile file;
	
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

	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_time")
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sub_type")
	public ProductSubType getSubType() {
		return subType;
	}
	public void setSubType(ProductSubType subType) {
		this.subType = subType;
	}
	
	@Column(name="creator")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Transient
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Transient
	public String getSubTypeString() {
		return subTypeString;
	}
	public void setSubTypeString(String subTypeString) {
		this.subTypeString = subTypeString;
	}
	
	
}