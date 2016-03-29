package com.team.service.zzy.plan;

import java.util.Map;

import com.team.util.PageData;

public interface PlanManager {
	//护士端新增计划
	public Map<String,Object> zzyNewPlan(PageData pd)throws Exception;
	//护士端获取计划
	public Map<String,Object> zzyPlanList(PageData pd)throws Exception;
}
