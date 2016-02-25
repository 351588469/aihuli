package com.team.svc.user.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.team.comm.action.CommonsAction;
import com.team.svc.user.dao.UserDao;
import com.team.svc.user.vo.UserVo;

public class UserAction extends CommonsAction {
	private static final Log LOG = LogFactory.getLog(UserAction.class);
	private UserDao userDao;
	private UserVo userVo;
	public String login() {
		String name=userDao.selectUserById(1); 
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据
		dataMap = new HashMap<String, Object>();
		UserVo user = new UserVo();
		user.setName(name); 
		user.setPassword("123");
		dataMap.put("user", user);
		// 放入一个是否操作成功的标识
		dataMap.put("success", true);
		// 返回结果
		return SUCCESS;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
	
}
