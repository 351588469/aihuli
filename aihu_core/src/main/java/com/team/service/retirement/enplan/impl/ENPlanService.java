package com.team.service.retirement.enplan.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.elder.ElderManager;
import com.team.service.retirement.enplan.ENPlanManager;
import com.team.service.retirement.gmnurse.GMNurseManager;

/** 
 * 说明： 护理计划
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("enplanService")
public class ENPlanService implements ENPlanManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="gmnurseService")
	private GMNurseManager gmnurseService;
	@Resource(name="elderService")
	private ElderManager elderService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ENPlanMapper.save", pd);
	}
	/**
	 * zzy
	 * 新增
	 */
	@Override
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		PageData pd_gmn=gmnurseService.zzyFindById(pd.getString("enp_gmn_id"));//根据编号获取护理项目信息
		String e_name=elderService.zzyFindNameById(pd.getString("e_id"));//根据老人编号获取老人姓名
		zzyPd.put("ENPLAN_ID",UuidUtil.get32UUID());//主键
		zzyPd.put("ENP_GMN_ID",pd.getString("enp_gmn_id"));//项目编号
		zzyPd.put("ENP_GM_ID",pd.getString("gm_id"));//养老院编号
		zzyPd.put("ENP_GMU_ID",pd.getString("gmu_id"));//计划申请职工编号
		zzyPd.put("ENP_GMN_NAME",pd_gmn.getString("GMN_NAME"));//项目名称
		zzyPd.put("ENP_GMN_CONTENT",pd_gmn.getString("GMN_CONTENT"));//项目内容
		zzyPd.put("ENP_E_ID",pd.getString("e_id"));//老人编号
		zzyPd.put("ENP_E_NAME",e_name);//老人姓名
		zzyPd.put("ENP_NEED",pd.getString("enp_need"));//单位时间需要护理次数
		zzyPd.put("ENP_UNIT",pd.getString("enp_unit"));//时间单位（1日，2周，3月）
		zzyPd.put("ENP_SDATE",pd.getString("enp_sdate"));//计划开始时间
		zzyPd.put("ENP_EDATE",pd.getString("enp_edate"));//计划结束时间
		zzyPd.put("ENP_CTIME", Tools.date2Str(new Date()));//记录生成时间
		zzyPd.put("ENP_UTIME", Tools.date2Str(new Date()));//记录生成时间
		zzyPd.put("ENP_STATUS", 1);
		dao.save("ENPlanMapper.save",zzyPd);
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ENPlanMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ENPlanMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ENPlanMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ENPlanMapper.listAll", pd);
	}
	/**
	 * 获取老人常规定制护理项目
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> zzyList(String eid)throws Exception{
		List<PageData>list =(List<PageData>)dao.findForList("ENPlanMapper.zzyList",eid);
		return list;
	}
	/**
	 * 获取老人护理项目列表 包括 定制和常规
	 */
	@Override
	public Map<String,Object>zzyMap(String eid)throws Exception{
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("Custom",zzyList(eid));//定制
		String gmid=elderService.zzyFindGmidById(eid);
		List<PageData>list=gmnurseService.zzyRoutineList(gmid);
		map.put("Routine",list);//常规
		return map;
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ENPlanMapper.findById", pd);
	}
	/**
	 * zzy
	 * 通过id获取数据
	 */
	@Override 
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("ENPlanMapper.zzyFindById",id);
	}	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ENPlanMapper.deleteAll", ArrayDATA_IDS);
	}
	@Override
	public String zzyFindNNAMEById(String enpid) throws Exception {
		return (String) dao.findForObject("ENPlanMapper.zzyFindNNAMEById",enpid);
	}
	
}

