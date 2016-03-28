package com.team.service.zzy.tool;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ToolManager {
	public Map<Integer,String> zzyUploadImg(HttpServletRequest request)throws Exception;
}
