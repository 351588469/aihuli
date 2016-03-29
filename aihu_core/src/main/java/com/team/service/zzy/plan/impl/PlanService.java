package com.team.service.zzy.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.service.retirement.edplan.EDPlanManager;
import com.team.service.retirement.enplan.ENPlanManager;
import com.team.service.retirement.gmnurse.GMNurseManager;
import com.team.service.zzy.plan.PlanManager;
import com.team.util.AppUtil;
import com.team.util.PageData;
@Service("planService")
public class PlanService implements PlanManager {
	@Resource(name="enplanService")
	ENPlanManager enplanService;
	@Resource(name="edplanService")
	EDPlanManager edplanService;
	//新增计划
	@Override
	public Map<String, Object> zzyNewPlan(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_pnew", pd)){	//检查参数
					String type=pd.getString("req_type");
					if(type.equals("enp")){
						if(AppUtil.checkParam("appzzys_pnew_enp", pd)){	//检查参数
							enplanService.zzySave(pd);
							result="01";
						}else result = "03";
					}else if(type.equals("edp")){
						if(AppUtil.checkParam("appzzys_pnew_edp", pd)){	//检查参数
							edplanService.zzySave(pd);
							result="01";
						}else result = "03";
					}
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	@Override
	public Map<String, Object> zzyPlanList(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_plist", pd)){	//检查参数
					String type=pd.getString("req_type");
					if(type.equals("enp")){
						if(AppUtil.checkParam("appzzys_plist_enp", pd)){	//检查参数
							//List<PageData> list=enplanService.zzyList(pd.getString("e_id"));
							Map<String,Object>map2=enplanService.zzyMap(pd.getString("e_id"));
							map.put("pd",map2);
							result="01";
						}else result = "03";
					}else if(type.equals("edp")){
						if(AppUtil.checkParam("appzzys_plist_edp", pd)){	//检查参数
							List<PageData> list=edplanService.zzyList(pd.getString("e_id"));
							map.put("pd", list);
							result="01";
						}else result = "03";
					}
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	
}
