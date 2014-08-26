package com.zzj.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="_ROLE")
public class Role {
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="ROLE_ID",length=64)
	private String roleId;
	@Column(name="ROLE_CODE",length=200)
	private String roleCode;
	@Column(name="ROLE_NAME",length=200)
	private String roleName;
	
	/**
	 * 维护端设置
	 * targetEntity：指向的class类	cascade：级联操作权限 fetch: 加载方式=FetchType.LAZY懒加载；FetchType.EAGER急加载
	 * @JoinTable	name:关联中间表	joinColumns：维护的关联主键 inverseJoinColumns：被维护表的关联主键
	 * @Cache		usage:缓存模式
	 */
	@ManyToMany(targetEntity=User.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns={@JoinColumn(name="ROLE_ID")},inverseJoinColumns={@JoinColumn(name="USER_ID")})
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<User> userList=new ArrayList<User>();
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleCode=" + roleCode
				+ ", roleName=" + roleName + ", userList=" + userList + "]";
	}
}
