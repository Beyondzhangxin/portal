package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 */
@Entity
@Table(name = "pic")
public class Pic extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4119575697332385004L;
	
	private String name;
	private String list;
	private String realName;
	private String type;
	private String itemId;
	private Calendar createTime;
	
	@Column(name="item_id")
	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="list")
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	
	@Column(name="real_name")
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "create_time")
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

}