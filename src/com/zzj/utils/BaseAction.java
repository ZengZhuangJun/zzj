package com.zzj.utils;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author JUM
 */
public class BaseAction extends ActionSupport {
	/**
	 * 版本标识
	 */
	private static final long serialVersionUID = -7338579592101301468L;
	/**
	 * 用户session标识
	 */
	protected static final String LOADED_USER_TAG = "USER";
	protected static final String LOADED_USER_ID = "USER_ID";
	/**
	 * 总共有几条
	 */
	protected int resultSize;
	/**
	 * 请求的页码
	 */
	protected int skipToPage = 1;
	/**
	 * 每页显示几条
	 */
	protected int pageSize = 10;
	/**
	 * 提示信息
	 */
	protected String message;
    
	/**
	 * spring  实例化一对象
	 * @param id
	 * @return
	 */
	public Object getBean(String id) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
        return ac.getBean(id);
	}

	/**
	 * 分页方法
	 */
	protected PageBean pageBean;

	/**
	 * 产生唯一文件名
	 * 
	 * @author JUM
	 * @return
	 */
	protected String getUUid() {
		String uuid=UUID.randomUUID()+"";
		String ret = uuid;
		return ret;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}


	/**
	 * 获取客户端IP
	 */
	protected String getClientIP(){
		String ip = this.getRequest().getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = this.getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = this.getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = this.getRequest().getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = this.getRequest().getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = this.getRequest().getRemoteAddr();
		}
		return ip;
	}
	
	public int getResultSize() {
		return resultSize;
	}

	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public int getSkipToPage() {
		return skipToPage;
	}

	public void setSkipToPage(int skipToPage) {
		this.skipToPage = skipToPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(Integer resultSize, Integer skipToPage,
			Integer pageSize) {
		pageBean = new PageBean(resultSize, skipToPage, pageSize);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
