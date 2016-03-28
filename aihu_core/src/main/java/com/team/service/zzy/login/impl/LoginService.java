package com.team.service.zzy.login.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.service.retirement.gmuser.GMUserManager;
import com.team.service.zzy.login.LoginManager;
import com.team.util.AppUtil;
import com.team.util.PageData;
@Service("loginService")
public class LoginService implements LoginManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="gmuserService")
	private GMUserManager gmuserService;
	@Override
	public Map<String, Object> zzyLogin(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_login", pd)){	//检查参数
					PageData data=gmuserService.zzyLogin(pd.getString("tel"));
					if(data==null) result="02";
					else{
						if(data.getString("GMU_PWD").equals(pd.getString("pwd"))){
							result="01";
							map.put("pd",data);
						}
						else result="04";
					}
				}else {
					result = "03";
				}
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}

}
