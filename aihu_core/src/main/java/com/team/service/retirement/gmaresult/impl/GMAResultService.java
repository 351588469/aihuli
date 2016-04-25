package com.team.service.retirement.gmaresult.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.elder.ElderManager;
import com.team.service.retirement.gmaanlysis.GMAAnlysisManager;
import com.team.service.retirement.gmaitem.GMAitemManager;
import com.team.service.retirement.gmaresult.GMAResultManager;
import com.team.service.system.appuser.AppuserManager;

/** 
 * 说明： 评测结果
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("gmaresultService")
public class GMAResultService implements GMAResultManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="gmaitemService")
	private GMAitemManager gmaitemService;
	@Resource(name="elderService")
	private ElderManager elderService;
	@Resource(name="gmaanlysisService")
	private GMAAnlysisManager gmaanlysisService;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("GMAResultMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMAResultMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMAResultMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMAResultMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMAResultMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMAResultMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMAResultMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 *批量上传评测结果
	 *json {gmai_id,gmar_score}
	 * retirement:e_id,json
	 * volunteer:userid,json
	 */
	@Override
	public String zzySaveMult(PageData pd) throws Exception {
		String json=pd.getString("json");
		String E_ID = null,E_NAME = null;
		String GMU_ID=null;
		if(pd.containsKey("e_id")&&pd.getString("e_id")!=""){
			E_ID=pd.getString("e_id");
			E_NAME=elderService.zzyFindNameById(E_ID);
			 GMU_ID=pd.getString("gmu_id");
		}else if(pd.containsKey("userid")&&pd.getString("userid")!=""){
			E_ID=pd.getString("userid");
			PageData user=appuserService.zzyFindById(E_ID);
			E_NAME=user.getString("USERNAME");
			//GMU_ID=E_ID;
		}
	
		Gson gson=new Gson();
		@SuppressWarnings("unchecked")
		List<Map<String,Object>>list=gson.fromJson(json,List.class);
		List<Map<String,Object>>dl=new ArrayList<>();
		String GMAR_CODE=UuidUtil.get32UUID();
		Map<String,Map<String,Object>>resultMap=new HashMap<>();
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=list.get(i);
			///Double score=(Double) map.get("gmar_score");
			String score=map.get("gmar_score").toString();
			Integer is=Integer.parseInt(score);
			map.put("gmar_score",is);
			Map<String,Object>tm=new HashMap<String, Object>();
			tm.put("GMARESULT_ID",UuidUtil.get32UUID());
			tm.put("GMAR_E_ID",E_ID);
			tm.put("GMAR_SCORE",is);
			String gmaiid=(String) map.get("gmai_id");
			tm.put("GMAR_GMAI_ID",gmaiid);
			PageData gmai=gmaitemService.zzyFindById(gmaiid);
			tm.put("GMAR_GMAI_GM_ID",gmai.get("GMAI_GM_ID"));//
			tm.put("GMAR_GMAI_NUM",gmai.get("GMAI_NUMBER"));//
			tm.put("GMAR_GMAI_ITYPE",gmai.get("GMAI_GMAT_NUM"));//
			tm.put("GMAR_GMAI_STYPE",gmai.get("GMAI_GMAT_NAME"));//
			tm.put("GMAR_GMAI_SCORE",gmai.get("GMAI_SCORE"));//
			tm.put("GMAR_GMAI_CONTENT",gmai.get("GMAI_CONTENT"));//
			tm.put("GMAR_CTIME",Tools.date2Str(new Date()));
			tm.put("GMAR_UTIME",Tools.date2Str(new Date()));
			tm.put("GMAR_CODE",GMAR_CODE);
			dl.add(tm);
			//评测结果分析
			Map<String,Object>map2=resultMap.get(gmai.get("GMAI_GMAT_NAME"));
			if(map2==null){
				map2=new HashMap<>();
				map2.put("total",gmai.get("GMAI_SCORE"));
				map2.put("get",map.get("gmar_score"));
				resultMap.put((String) gmai.get("GMAI_GMAT_NAME"),map2);
			}else{
				Map<String,Object>map3=new HashMap<>();
				Integer total=(Integer)gmai.get("GMAI_SCORE")+(Integer)map2.get("total");
				map3.put("total",total);
				Integer get=(Integer)map.get("gmar_score")+(Integer)map2.get("get");
				map3.put("get",get);
				resultMap.put((String) gmai.get("GMAI_GMAT_NAME"),map3);
			}
		}	
		dao.save("GMAResultMapper.zzySaveMult",dl);
		/**
		 * 评测结果分析上传
		 */
		String Analysis="老人"+E_NAME+"的评测分析如下:";
		Integer t_total=0;
		Integer t_get=0;
		for (Map.Entry<String,Map<String,Object>> entry :resultMap.entrySet()) {
			//System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			Analysis+=entry.getKey()+"项目的总分为:"+entry.getValue().get("total")+
					",得分为:"+entry.getValue().get("get")+";";
			
			t_total+=(Integer) entry.getValue().get("total");
			t_get+=(Integer) entry.getValue().get("get");
		}
		
		Analysis+="评测合计总分为:"+t_total+",老人合计得分:"+t_get;
		PageData zzyPd_a=new PageData();
		zzyPd_a.put("GMAANLYSIS_ID",UuidUtil.get32UUID());
		zzyPd_a.put("GMAA_CODE",GMAR_CODE);
		zzyPd_a.put("GMAA_E_ID",E_ID);
		zzyPd_a.put("GMAA_SCORE",t_get);
		zzyPd_a.put("GMAA_ANALYSIS",Analysis);
		zzyPd_a.put("GMAA_GMU_ID",GMU_ID);
		zzyPd_a.put("GMAA_CTIME",Tools.date2Str(new Date()));
		zzyPd_a.put("GMAA_UTIME",Tools.date2Str(new Date()));
		gmaanlysisService.save(zzyPd_a);
		return Analysis;
	}

	@Override
	public Map<String, Object> app_zzyAdd(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_gmaradd", pd)){	//检查参数
				String info=zzySaveMult(pd);
				result="01";
				map.put("info",info);
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}

}

