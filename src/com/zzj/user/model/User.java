package com.zzj.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="_USER")
public class User {
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="USER_ID",length=64)
	private String userId;
	@Column(name="ACCOUNT",length=200)
	private String account;
	@Column(name="NAME",length=200)
	private String name;
	@Column(name="PASSWORD",length=64)
	private String password;
	@Column(name="PHONE",length=200)
	private String phone;
	@Lob 
	@Basic(fetch=FetchType.LAZY) 
	@Column(name="USER_DESCRIBE", columnDefinition="BLOB", nullable=true)
	private byte[] userDescribe;
	@Column(name="PHOTO",length=200)
	private String photo;
	@Column(name="CREATE_TIME")
	private Date createTime;
	/**
	 * 被维护端设置
	 * cascade：级联模式
	 * mappedBy：维护端对该表的描述（表示该类为被维护端）
	 * targetEntity：指向的class类
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},mappedBy="userList",targetEntity=Role.class)
	private List<Role> roleList=new ArrayList<Role>();
	/**
	 * 被维护端设置
	 * cascade：级联模式
	 * mappedBy：维护端对该表的描述（表示维护端可操作本张表）
	 * targetEntity：指向的class类
	 * @OrderBy 排序
	 */
	@OneToMany(targetEntity = Theme.class, cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="user")
	@OrderBy(value="createTime desc")
	private List<Theme> themeList=new ArrayList<Theme>();
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public byte[] getUserDescribe() {
		return userDescribe;
	}
	public void setUserDescribe(byte[] userDescribe) {
		this.userDescribe = userDescribe;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<Theme> getThemeList() {
		return themeList;
	}
	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}
	
}
