package com.team.service.zzy.login;

import java.util.Map;

import com.team.util.PageData;

public interface LoginManager {
	//护士端登录（手机号）
	public Map<String,Object> zzyLogin(PageData pd)throws Exception;
}
