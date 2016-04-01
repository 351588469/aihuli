package com.team.service.zzy.info.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.e;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.service.retirement.edplan.EDPlanManager;
import com.team.service.retirement.elder.ElderManager;
import com.team.service.retirement.gmaitem.GMAitemManager;
import com.team.service.retirement.gmaresult.GMAResultManager;
import com.team.service.retirement.gmberth.GMBerthManager;
import com.team.service.retirement.gmbresult.GMBResultManager;
import com.team.service.retirement.gmhealth.GMHealthManager;
import com.team.service.retirement.gmnurse.GMNurseManager;
import com.team.service.retirement.msg.MsgManager;
import com.team.service.zzy.info.InfoManager;
import com.team.util.AppUtil;
import com.team.util.DateUtil;
import com.team.util.PageData;
import com.team.util.Tools;
@Service("infoService")
public class InfoService implements InfoManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="elderService")
	private ElderManager elderService;
	@Resource(name="gmberthService")
	private GMBerthManager gmberthService;
	@Resource(name="gmnurseService")
	private GMNurseManager gmnurseService;
	@Resource(name="gmhealthService")
	private GMHealthManager gmhealthService;
	@Resource(name="edplanService")
	private EDPlanManager edplanService;
	@Resource(name="msgService")
	private MsgManager msgService;
	@Resource(name="gmbresultService")
	private GMBResultManager gmbresultService;
	@Resource(name="gmaitemService")
	private GMAitemManager gmaitemService;
	@Resource(name="gmaresultService")
	private GMAResultManager gmaresultService;
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> zzyElist(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		try{
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_elist", pd)){	//检查参数
					//pd = appuserService.findByUsername(pd);
					List<PageData>list=gmberthService.zzyListOfBerth(pd.getString("gmu_id"));
					Map<String,List<Map<String,Object>>>om=new HashMap<>();
					List<Map<String,Object>>roomList=new ArrayList<>();
					//List<String>keyList=new ArrayList<>();
					
					for(int i=0;i<list.size();i++){
						PageData tpd=list.get(i);
						Integer type=(Integer) tpd.get("GMB_TYPE");
						String roominfo=tpd.get("GMB_FLOOR")+"-"+
										tpd.get("GMB_LAYER")+"-"+
										tpd.get("GMB_ROOM");
						if(type==4){
							String eid=tpd.getString("GMB_E_ID");
							PageData einfo=elderService.zzyFindById(eid);
							tpd.putAll(einfo);
							List<Map<String,Object>>list2=om.get(roominfo);
							if(list2==null||list2.size()==0)list2=new ArrayList<>();
							list2.add(tpd);
							om.put(roominfo,list2);
						}else if(type==3){
							roomList.add(tpd);
						}
					}
					for(int i=0;i<roomList.size();i++){
						Map<String,Object>tm=roomList.get(i);
						String roominfo=tm.get("GMB_FLOOR")+"-"+
								tm.get("GMB_LAYER")+"-"+
								tm.get("GMB_ROOM");
						tm.put("ROOM_INFO",om.get(roominfo));
						//添加房间评测
						String roomid=(String) tm.get("GMBERTH_ID");
						PageData gmbr=gmbresultService.zzyRecent(roomid);
						tm.put("ROOM_ASSESS",gmbr);
					}
					
					map.put("pd",roomList);
					result = (null == list) ?  "02" :  "01";
				}else {
					result = "03";
				}
			//}else{result = "05";}
		}catch (Exception e){
			//logger.error(e.toString(), e);
		}finally{
			map.put("result", result);
		}
		return map;
	}
	@Override
	public Map<String, Object> zzyEinfo(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
	//	System.out.println("zzy:"+pd.toString());
				if(AppUtil.checkParam("appzzys_einfo", pd)){	//检查参数
					PageData data=elderService.zzyFindById(pd.getString("e_id"));
					if(data==null) result="02";
					else{
						result="01";
						map.put("pd",data);
					}
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	@Override
	public Map<String, Object> zzyGNlist(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_gmnlist", pd)){	//检查参数
					List<PageData>list=gmnurseService.zzyList(pd.getString("gm_id"));
					if(list==null||list.size()==0) result="02";
					else{
						result="01";
						map.put("pd",list);
					}
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	@Override
	public Map<String, Object> zzyGHlist(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_gmnlist", pd)){	//检查参数
					List<PageData>list=gmhealthService.zzyList(pd.getString("gm_id"));
					if(list==null||list.size()==0) result="02";
					else{
						result="01";
						map.put("pd",list);
					}
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	//护士端每日消息提示
	/**
	 * 先获取到护士负责的老人，再根据老人信息生成 用药，护理 消息
	 */
	@Override
	public Map<String,Object> zzyDMsg(PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_dmsg", pd)){	//检查参数
					//1，获取职工负责的老人列表
					List<PageData>list=gmberthService.zzyListOfElder(pd.getString("gmu_id"));
					//1.1获取老人列表(清除空参)
					List<String>eids=new ArrayList<>();
					for(int i=0;i<list.size();i++){
						String eid=(String)list.get(i).get("GMB_E_ID");
						if(eid.length()==32)eids.add(eid);
					}
					//2获取msg,type:edp用药计划 enp护理计划
					List<Map<String,Object>>yList=new ArrayList<>();
					for(int i=0;i<eids.size();i++){
						Map<String,Object>tmMap=new HashMap<>();
						String eid=eids.get(i);
						//获取当天用药消息
						PageData msg=msgService.zzyFindByEid(eid,"edp");
						if(msg==null){//不存在当天用药信息，重新生成
							msg=msgService.zzyCreateMsg(eid,"edp");
						}
						//获取当天护理消息
						PageData msg2=msgService.zzyFindByEid(eid,"enp");
						if(msg2==null){//不存在当天用药信息，重新生成
							msg2=msgService.zzyCreateMsg(eid,"enp");
						}
						tmMap.put("edp",msg);
						tmMap.put("enp",msg2);
						tmMap.put("elder",elderService.zzyFindById(eid));
						yList.add(tmMap);
					}
					if(yList==null||yList.size()==0) result="02";
					else{
						result="01";
						map.put("pd",yList);
					}
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	/** 
	 * 护士端房间评测BerthEvaluation
	 */
	@Override
	public Map<String, Object> zzyBEval(PageData pd,Map<Integer,String>fm) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_beval", pd)){	//检查参数
					if(fm.containsKey(1))pd.put("gmbr_photoa",fm.get(1));
					if(fm.containsKey(2))pd.put("gmbr_photob",fm.get(2));
					if(fm.containsKey(3))pd.put("gmbr_photoc",fm.get(3));
					if(fm.containsKey(4))pd.put("gmbr_photod",fm.get(4));
					gmbresultService.zzySave(pd);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	/**
	 * 房间评测历史信息
	 */
	@Override
	public Map<String, Object> zzyBPast(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_bpast", pd)){	//检查参数
					map.put("pd",gmbresultService.zzyPast(pd));
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	/**
	 *老人评测题目
	 */
	@Override
	public Map<String, Object> zzyAList(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_alist", pd)){	//检查参数
					List<PageData>list=gmaitemService.zzyList(pd.getString("gm_id"));
					map.put("pd",list);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	/**
	 * 老人评测结果新增
	 */
	@Override
	public Map<String, Object> zzyARAdd(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil.checkParam("appzzys_aradd", pd)){	//检查参数
					String Anlysis=gmaresultService.zzySaveMult(pd);
					map.put("pd",Anlysis);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
}
