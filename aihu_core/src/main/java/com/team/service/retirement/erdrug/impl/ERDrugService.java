package com.team.service.retirement.erdrug.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.p;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.DateUtil;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.edplan.EDPlanManager;
import com.team.service.retirement.erdrug.ERDrugManager;

/** 
 * 说明： 用药记录
 * 创建人：jaychum
 * 创建时间：2016-03-12
 * @version
 */
@Service("erdrugService")
public class ERDrugService implements ERDrugManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="edplanService")
	private EDPlanManager edplanService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ERDrugMapper.save", pd);
	}
	/**
	 * zzy
	 * 新增
	 */
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		String edpid=pd.getString("erd_edp_id");
		PageData edp=edplanService.zzyFindById(edpid);
		zzyPd.put("ERD_GMU_ID",pd.get("gmu_id"));
		zzyPd.put("ERD_EDP_ID",edpid);
		zzyPd.put("ERD_EDP_E_ID",edp.get("EDP_E_ID"));
		zzyPd.put("ERD_EDP_GMU_ID",edp.get("EDP_GMU_ID"));
		zzyPd.put("ERD_EDP_GM_ID",edp.get("EDP_GM_ID"));
		zzyPd.put("ERD_EDP_SDATE",edp.get("EDP_SDATE"));
		zzyPd.put("ERD_EDP_EDATE",edp.get("EDP_EDATE"));
		zzyPd.put("ERD_EDP_MHOUR",edp.get("EDP_MHOUR"));
		zzyPd.put("ERD_EDP_MDESC",edp.get("EDP_MDESC"));
		zzyPd.put("ERD_EDP_MNAME",edp.get("EDP_MNAME"));
		zzyPd.put("ERD_EDP_MDOSAGE",edp.get("EDP_MDOSAGE"));
		zzyPd.put("ERD_EDP_MCOUNT",edp.get("EDP_MCOUNT"));
		zzyPd.put("ERD_GTIME",pd.get("erd_time"));
		zzyPd.put("ERD_CTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERD_UTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERD_VALUE",pd.get("erd_value"));
		zzyPd.put("ERD_DESC",pd.get("erd_desc"));
		zzyPd.put("ERDRUG_ID",UuidUtil.get32UUID());
		dao.save("ERDrugMapper.save",zzyPd);
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ERDrugMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ERDrugMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ERDrugMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ERDrugMapper.listAll", pd);
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
		zzyPd.put("ERD_GM_ID",pd.get("gm_id"));
		zzyPd.put("ERD_E_ID",pd.get("e_id"));
		List<PageData>list=(List<PageData>)dao.findForList("ERDrugMapper.zzyPast", zzyPd);
		List<String>dl=new ArrayList<>();
		Map<String,List<PageData>>map=new HashMap<>();
		for(int i=0;i<list.size();i++){
			PageData tpd=list.get(i);
			String date=tpd.getString("ERD_GTIME").substring(0,10);
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
		return (PageData)dao.findForObject("ERDrugMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ERDrugMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

