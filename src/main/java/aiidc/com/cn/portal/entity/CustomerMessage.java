package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Calendar;


/**
 * 客户留言
 */
@Entity
@Table(name = "customer_message")
public class CustomerMessage extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2613409307729280504L;
	/**
	 * 称呼
	 */
	@NotNull
	private String name;
	/**
	 * 电话
	 */
	private String phoneNumber;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 留言内容
	 */
	@NotNull
	private String content;
	/**
	 * 回复留言
	 */
	private String reply;
	/**
	 * 是否可见(默认为隐藏)
	 */
	private String isVisible = "0";
	/**
	 * 留言时间
	 */
	private Calendar createTime;
	/**
	 * 回复留言时间
	 */
	private Calendar replyTime;
	/**
	 * 是否置顶 1置顶
	 */
	private String isTop = "0";
	/**
	 * 回复状态：未回复，回复 0,1
	 */
	private String status = "0";
	
	
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

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "qq")
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "is_visible")
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

	@Column(name = "create_time")
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "reply")
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	@Column(name = "reply_time")
	public Calendar getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Calendar replyTime) {
		this.replyTime = replyTime;
	}
	

}