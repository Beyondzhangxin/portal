package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企业文化与资质
 */
@Entity
@Table(name = "culture")
public class Culture extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1677288781578379480L;
	/**
	 * 标题
	 */
	@NotBlank
	private String title;
	/**
	 * 类型(1，企业文化  2，荣誉与资质)
	 */
	private String type;
	/**
	 * 内容
	 */
	@NotBlank
	private String content;
	/**
	 * 上传时间
	 */
	private Calendar createTime;
	/**
	 * 是否置顶 1置顶
	 */
	private String isTop = "0";
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
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	@Transient
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}