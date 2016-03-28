package com.team.service.zzy.record.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.team.service.retirement.erdrug.ERDrugManager;
import com.team.service.retirement.erhealth.ERHealthManager;
import com.team.service.retirement.ernurse.ERNurseManager;
import com.team.service.retirement.erother.EROtherManager;
import com.team.service.zzy.record.RecordManager;
import com.team.util.AppUtil;
import com.team.util.PageData;
@Service("recordService")
public class RecordService implements RecordManager{
	@Resource(name="erhealthService")
	private ERHealthManager erhealthService;
	@Resource(name="ernurseService")
	private ERNurseManager ernurseService;
	@Resource(name="erdrugService")
	private ERDrugManager erdrugService;
	@Resource(name="erotherService")
	private EROtherManager erotherService;
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> zzyNewRecordMult(PageData pd) throws Exception {
		String json=pd.getString("json");
		Gson gson=new Gson();
		List<PageData>list=new ArrayList<>();
		list = gson.fromJson(json,List.class);
		Integer t=0;
		Integer f=0;
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=zzyNewRecord(list.get(i));
			if(map.get("result").equals("01"))t++;
			else f++;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(t==list.size()){
			map.put("result","01");
		}else{
			map.put("result","00");
		}
		return map;
	}
	//护士端新增记录
	@Override
	public Map<String, Object> zzyNewRecord(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_rnew", pd)){	//检查参数
					String type=pd.getString("req_type");
					if(type.equals("erh")){//健康记录
						if(AppUtil.checkParam("appzzys_rnew_erh", pd)){
							erhealthService.zzySave(pd);
							result="01";
						}
					}else if(type.equals("ern")){//护理记录
						if(AppUtil.checkParam("appzzys_rnew_ern", pd)){
							if(pd.getString("ern_enp_type").equals("1")){//定制项目
								ernurseService.zzySave(pd);
								result="01";
							}
							else if(pd.getString("ern_enp_type").equals("2")){//常规项目
								ernurseService.zzySaveRoutine(pd);
								result="01";
							}
						}
					}else if(type.equals("erd")){
						if(AppUtil.checkParam("appzzys_rnew_erd", pd)){
							erdrugService.zzySave(pd);
							result="01";
						}
					}else if(type.equals("ero")){
						if(AppUtil.checkParam("appzzys_rnew_ero", pd)){
							erotherService.zzySave(pd);
							result="01";
						}
					}else return map;
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	//护士端历史记录
	@Override
	public Map<String,Object>zzyPastRecord(PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_rpast", pd)){	//检查参数
					String type=pd.getString("req_type");
					if(type.equals("erh")){//健康记录
						if(AppUtil.checkParam("appzzys_rpast_erh", pd)){
							List<PageData>list=erhealthService.zzyPast(pd);
							map.put("pd",list);
							result="01";
						}
					}else if(type.equals("ern")){//护理记录
						if(AppUtil.checkParam("appzzys_rpast_ern", pd)){
							List<PageData>list=ernurseService.zzyPast(pd);
							map.put("pd",list);
							result="01";
						}
					}else if(type.equals("erd")){//用药记录
						if(AppUtil.checkParam("appzzys_rpast_erd", pd)){
							List<PageData>list=erdrugService.zzyPast(pd);
							map.put("pd",list);
							result="01";
						}
					}else if(type.equals("ero")){//其他记录
						if(AppUtil.checkParam("appzzys_rpast_ero", pd)){
							List<PageData>list=erotherService.zzyPast(pd);
							map.put("pd",list);
							result="01";
						}
					}
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}

}
