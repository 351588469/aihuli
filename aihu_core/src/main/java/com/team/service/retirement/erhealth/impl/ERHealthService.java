package com.team.service.retirement.erhealth.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.DateUtil;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.erhealth.ERHealthManager;
import com.team.service.retirement.gmhealth.GMHealthManager;

/** 
 * 说明： 健康记录
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("erhealthService")
public class ERHealthService implements ERHealthManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="gmhealthService")
	private GMHealthManager gmhealthService;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ERHealthMapper.save", pd);
	}
	/**
	 * zzy
	 * 新增
	 */
	@Override
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		String gmhid=pd.getString("erh_gmh_id");
		PageData gmh=gmhealthService.zzyFindById(gmhid);
		zzyPd.put("ERH_GMH_ID",gmhid);
		zzyPd.put("ERH_E_ID",pd.getString("e_id"));
		zzyPd.put("ERH_GMU_ID",pd.getString("gmu_id"));
		zzyPd.put("ERH_VALUE",pd.getString("erh_value"));
		zzyPd.put("ERH_CTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERH_UTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERH_GTIME",pd.get("erh_time"));
		zzyPd.put("ERH_GMH_NAME",gmh.get("GMH_NAME"));
		zzyPd.put("ERH_GMH_TYPE",gmh.get("GMH_TYPE"));
		zzyPd.put("ERH_GMH_CONTENT",gmh.get("GMH_CONTENT"));
		zzyPd.put("ERHEALTH_ID",UuidUtil.get32UUID());
		zzyPd.put("ERH_GM_ID",pd.getString("gm_id"));
		dao.save("ERHealthMapper.save", zzyPd);
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ERHealthMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ERHealthMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ERHealthMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ERHealthMapper.listAll", pd);
	}
	/**
	 * zzy
	 * 历史
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> zzyPast(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		Integer page=Integer.parseInt((String)pd.get("page"));
		String edate=DateUtil.getAfterDayDate(-(page-1)*30);
		String sdate=DateUtil.getAfterDayDate(-(page)*30);
		zzyPd.put("SDATE",sdate);
		zzyPd.put("EDATE",edate);
		zzyPd.put("ERH_GM_ID",pd.get("gm_id"));
		zzyPd.put("ERH_E_ID",pd.get("e_id"));
		zzyPd.put("ERH_GMH_ID",pd.get("erh_gmh_id"));
		return (List<PageData>)dao.findForList("ERHealthMapper.zzyPast", zzyPd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ERHealthMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ERHealthMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

