package com.team.service.zzy.info;

import java.util.Map;

import com.team.util.PageData;

public interface InfoManager {
	
	//护士端首页
	public 	Map<String,Object> zzyElist(PageData pd) throws Exception;
	//护士端老人信息查看
	public Map<String,Object> zzyEinfo(PageData pd)throws Exception;
	//护士端获取项目列表
	public Map<String,Object> zzyGNlist(PageData pd)throws Exception;
	//护士端获取健康项目列表
	public Map<String,Object> zzyGHlist(PageData pd)throws Exception;
	//护士端每日消息提示
	public Map<String,Object> zzyDMsg(PageData pd)throws Exception;
	//护士端房间评测BerthEvaluation
	public Map<String,Object> zzyBEval(PageData pd,Map<Integer,String>map)throws Exception;
	//房间评测历史信息
	public Map<String,Object> zzyBPast(PageData pd)throws Exception;
	//老人评测题目
	public Map<String, Object> zzyAList(PageData pd)throws Exception;
	//老人评测结果
	public Map<String, Object> zzyARAdd(PageData pd)throws Exception;
}
