package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;


/**
 * 解决方案
 */
@Entity
@Table(name = "solution")
public class Solution extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4370820697499609067L;
	/**
	 * 标题
	 */
	@NotBlank
	private String title;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 内容
	 */
	@NotBlank
	private String content;
	/**
	 * 作者
	 */
	private String creator;
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
	 * 最近的修改时间
	 */
	private Calendar lastModifiedTime;
	/**
	 * 小标题
	 */
	private String subtitle;

	private String src;

	@Transient
	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}

	@Column(name = "subtitle")
	public String getSubtitle()
	{
		return subtitle;
	}

	public void setSubtitle(String subtitle)
	{
		this.subtitle = subtitle;
	}

	@Column(name = "last_modified_time")
	public Calendar getLastModifiedTime()
	{
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Calendar lastModifiedTime)
	{
		this.lastModifiedTime = lastModifiedTime;
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

	@Column(name = "creator")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "create_time")
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

}