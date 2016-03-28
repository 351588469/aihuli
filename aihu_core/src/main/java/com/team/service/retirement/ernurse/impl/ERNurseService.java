package com.team.service.retirement.ernurse.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.DateUtil;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.enplan.ENPlanManager;
import com.team.service.retirement.ernurse.ERNurseManager;
import com.team.service.retirement.gmnurse.GMNurseManager;

/** 
 * 说明： 护理记录
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("ernurseService")
public class ERNurseService implements ERNurseManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="enplanService")
	private ENPlanManager enplanService;
	@Resource(name="gmnurseService")
	private GMNurseManager gmnuseService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ERNurseMapper.save", pd);
	}
	/**
	 * zzy
	 * 新增
	 */
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		String enpid=pd.getString("ern_enp_id");
		PageData enp=enplanService.zzyFindById(enpid);
		zzyPd.put("ERN_E_ID",pd.getString("e_id"));
		zzyPd.put("ERN_GMU_ID",pd.getString("gmu_id"));
		zzyPd.put("ERN_ENP_GM_ID",pd.getString("gm_id"));
		zzyPd.put("ERN_ENP_ID",enpid);
		zzyPd.put("ERN_ENP_NEED",enp.get("ENP_NEED"));
		zzyPd.put("ERN_ENP_UNIT",enp.get("ENP_UNIT"));
		zzyPd.put("ERN_ENP_SDATE",enp.get("ENP_SDATE"));
		zzyPd.put("ERN_ENP_EDATE",enp.get("ENP_EDATE"));
		zzyPd.put("ERN_COUNT",pd.get("ern_count"));

		
		zzyPd.put("ERN_GTIME",pd.get("ern_time"));
		zzyPd.put("ERN_CTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERN_UTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERNURSE_ID",UuidUtil.get32UUID());
		dao.save("ERNurseMapper.save",zzyPd);
	}
	/**
	 * 常规项目记录
	 */
	public void zzySaveRoutine(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		zzyPd.put("ERN_E_ID",pd.getString("e_id"));
		zzyPd.put("ERN_GMU_ID",pd.getString("gmu_id"));
		zzyPd.put("ERN_ENP_GM_ID",pd.getString("gm_id"));
		zzyPd.put("ERN_ENP_ID",pd.getString("ern_enp_id"));
		zzyPd.put("ERN_ENP_UNIT","0");
		zzyPd.put("ERN_ENP_NEED","0");
		zzyPd.put("ERN_COUNT",pd.get("ern_count"));
		zzyPd.put("ERN_DESC",pd.get("ern_desc"));
		zzyPd.put("ERN_GTIME",pd.get("ern_time"));
		zzyPd.put("ERN_UTIME",Tools.date2Str(new Date()));
		//判断当天的记录是否存在，若存在则覆盖，负责新建
		String gday=((String)pd.get("ern_time")).substring(0,9);
		zzyPd.put("STIME",gday+" 00:00:00");
		zzyPd.put("ETIME",gday+" 23:59:59");
		PageData cPd=zzyCheckData(zzyPd);
		if(cPd!=null){//存在
			zzyPd.put("ERNURSE_ID",cPd.get("ERNURSE_ID"));
			edit(zzyPd);
		}else{//不存在
			zzyPd.put("ERN_CTIME",Tools.date2Str(new Date()));
			zzyPd.put("ERNURSE_ID",UuidUtil.get32UUID());
			dao.save("ERNurseMapper.save",zzyPd);
		}
		
		
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ERNurseMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ERNurseMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ERNurseMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ERNurseMapper.listAll", pd);
	}
	/**
	 * zzy
	 * 历史
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyPast(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		Integer page=Integer.parseInt((String)pd.get("page"));
		String edate=DateUtil.getAfterDayDate(-(page-1)*30);
		String sdate=DateUtil.getAfterDayDate(-(page)*30);
		zzyPd.put("SDATE",sdate);
		zzyPd.put("EDATE",edate);
		zzyPd.put("ERN_GM_ID",pd.get("gm_id"));
		zzyPd.put("ERN_E_ID",pd.get("e_id"));
		List<PageData> list= (List<PageData>)dao.findForList("ERNurseMapper.zzyPast",zzyPd);
		List<PageData> yList=new ArrayList<PageData>();
		for(int i=0;i<list.size();i++){
			PageData tpd=(PageData) list.get(i);
			//判断是常规护理还是定制护理
			if((int)tpd.get("ERN_ENP_UNIT")!=0){
				String enpid=tpd.getString("ERN_ENP_ID");
				String name=enplanService.zzyFindNNAMEById(enpid);
				tpd.put("GMN_NAME",name);
			}else{
				String enpid=tpd.getString("ERN_ENP_ID");
				String name=gmnuseService.zzyFindNNameById(enpid);
				tpd.put("GMN_NAME",name);
			}
			yList.add(tpd);
		}
		List<String>dl=new ArrayList<>();
		Map<String,List<PageData>>map=new HashMap<>();
		for(int i=0;i<list.size();i++){
			PageData tpd=list.get(i);
			String date=tpd.getString("ERN_GTIME").substring(0,10);
			if(map.containsKey(date)){
				List<PageData>tl=map.get(date);
				tl.add(tpd);
			}else{
				dl.add(date);
				List<PageData>tl=new ArrayList<>();
				tl.add(tpd);
				map.put(date,tl);
			}
		}
		List<PageData>ol=new ArrayList<>();
		for(int i=0;i<dl.size();i++){
			PageData tpd=new PageData();
			String date=dl.get(i);
			tpd.put("date",date);
			tpd.put("record", map.get(date));
			ol.add(tpd);
		}
		
		return ol;
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ERNurseMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ERNurseMapper.deleteAll", ArrayDATA_IDS);
	}
	@Override
	public PageData zzyCheckData(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ERNurseMapper.zzyCheckData",pd);
		
	}
	
}

