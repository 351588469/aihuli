package com.team.service.retirement.edplan.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.edplan.EDPlanManager;

/** 
 * 说明： 用药计划
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("edplanService")
public class EDPlanService implements EDPlanManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("EDPlanMapper.save", pd);
	}
	/**
	 * zzy
	 * 新增
	 */
	@Override 
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		zzyPd.put("EDPLAN_ID",UuidUtil.get32UUID());//主键
		zzyPd.put("EDP_E_ID",pd.getString("e_id"));//老人编号
		zzyPd.put("EDP_GMU_ID",pd.getString("gmu_id"));//计划申请职工编号
		zzyPd.put("EDP_GM_ID",pd.getString("gm_id"));//养老院编号
		zzyPd.put("EDP_SDATE",pd.getString("edp_sdate"));//计划开始时间
		zzyPd.put("EDP_EDATE",pd.getString("edp_edate"));//计划结束时间
		zzyPd.put("EDP_MHOUR",pd.getString("edp_mhour"));//用药时间点
		zzyPd.put("EDP_MDESC",pd.getString("edp_mdesc"));//用药时间点说明
		zzyPd.put("EDP_MNAME",pd.getString("edp_mname"));//药品名称
		zzyPd.put("EDP_MDOSAGE",pd.getString("edp_mdosage"));//药品剂量
		zzyPd.put("EDP_MCOUNT",pd.get("edp_mcount"));//药品数量
		zzyPd.put("EDP_DESC",pd.get("edp_desc"));//计划说明
		zzyPd.put("EDP_CTIME",Tools.date2Str(new Date()));//记录生成时间
		zzyPd.put("EDP_UTIME",Tools.date2Str(new Date()));//记录生成时间
		dao.save("EDPlanMapper.save",zzyPd);
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("EDPlanMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("EDPlanMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("EDPlanMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("EDPlanMapper.listAll", pd);
	}
	/**
	 * 列表(zzy)
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(String eid)throws Exception{
		return (List<PageData>)dao.findForList("EDPlanMapper.zzyList",eid);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("EDPlanMapper.findById", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData zzyFindById(String id) throws Exception {
		return (PageData)dao.findForObject("EDPlanMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("EDPlanMapper.deleteAll", ArrayDATA_IDS);
	}
}

