package com.zzj.user.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="_THEME")
public class Theme {
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="THEME_ID",length=64)
	private String themeId;
	@Column(name="_TITLE",length=4000)
	private String title;
	@Lob 
	@Basic(fetch=FetchType.LAZY) 
	@Column(name="_DESCRIBE", columnDefinition="BLOB", nullable=true)
	private byte[] describe;
	@Column(name="CREATE_TIME")
	private Date createTime;
	/**
	 * 关系维护端
	 * cascade:级联操作权限	fetch:懒加载设置 optional:true=可以使得即使外键为空时仍可以向表中添加数据。
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name = "USER_ID")
	private User user;
	
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public byte[] getDescribe() {
		return describe;
	}
	public void setDescribe(byte[] describe) {
		this.describe = describe;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
