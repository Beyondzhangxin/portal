package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;


/**
 * 公司新闻
 */
@Entity
@Table(name = "news")
public class News extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2794284779981919680L;
	/**
	 * 标题
	 */
	@NotBlank
	private String title;
	/**
	 * 栏目
	 */
	@NotBlank
	private String type;
	/**
	 * 内容
	 */
	@NotBlank
	private String content;
	/**
	 * 创建时间
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

	private String iscover;

	private String src;

	@Column(name = "is_cover")
	public String getIscover()
	{
		return iscover;
	}

	public void setIscover(String iscover)
	{
		this.iscover = iscover;
	}

	@Transient
	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
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

	@Column(name = "create_time")
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

}