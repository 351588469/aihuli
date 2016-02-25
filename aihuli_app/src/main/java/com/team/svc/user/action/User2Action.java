package com.team.svc.user.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.team.svc.user.dao.UserDao;
import com.team.svc.user.vo.UserVo;

@ParentPackage("base")   
@Namespace("/user")   
@Results({    
   @Result(name = "json",type="json", params={"root","dataMap"})   
})   
public class User2Action {
	private static final Log LOG = LogFactory.getLog(User2Action.class);
	private Map<String, Object> dataMap;
	private UserDao userDao;
	
	@Action(value="userLogin")  
	public String userLogin() {
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
		return "json";
	}
	
	//=============================================
	private String name;
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
}
