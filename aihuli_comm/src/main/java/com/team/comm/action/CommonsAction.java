package com.team.comm.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.team.comm.dao.MyBatisDao;


public class CommonsAction extends ActionSupport implements SessionAware, RequestAware {
	private static final Log LOG = LogFactory.getLog(CommonsAction.class);
	private Map request;
	private Map session;
	protected String JSON="json";
	protected MyBatisDao myDao;
	protected Map<String, Object> dataMap;
	
	public MyBatisDao getMyDao() {
		return myDao;
	}

	public void setMyDao(MyBatisDao myDao) {
		this.myDao = myDao;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Map getRequest() {
		return request;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}
}
