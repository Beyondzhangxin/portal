package aiidc.com.cn.portal.entity;

import aiidc.com.cn.portal.util.BaseEntity;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "acl_user")
public class AclUser extends BaseEntity {
	
	private static final long serialVersionUID = 4119575697132385004L;
	
	@NotBlank
	private String loginName;
	
	@NotBlank
	private String name;
	
	//1_男，2_女
	@NotNull
	private Integer male;
	
	private String roleId;
	
	private AclRole role;

	@Pattern(regexp="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")
	private String mobile;
	
	private String passwd;
	
	//原始密码:admin
	private String gatePass;
	
	@Email
	private String email;
	
	private String status;

	private String fax;

	private String tel;
	
	//简介
	private String introduction;

	/**
	 * 类型（1,领导；2，普通用户)
	 */
	private String type;
	
	//职务
	private String position;
	
	//部门
	private String department;
	
	private String picName;
	
	//头像
	private String pic;
	
	//是否置顶 1置顶
	private String isTop = "0";
	
	
	@Column(name="is_top")
	public String getIsTop() {
		return isTop;
	}
	
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "MALE")
	public Integer getMale() {
		return male;
	}
	public void setMale(Integer male) {
		this.male = male;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWD")
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	public AclRole getRole() {
		return role;
	}
	public void setRole(AclRole role) {
		this.role = role;
	}

	@Column(name = "GATE_PASS")
	public String getGatePass() {
		return gatePass;
	}
	public void setGatePass(String gatePass) {
		this.gatePass = gatePass;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

	@Column(name = "pic")
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
//	@Column(name = "role_id")
	@Transient
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}