package com.team.service.retirement.erother.impl;

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
import com.team.service.retirement.erother.EROtherManager;

/** 
 * 说明： 其他记录
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("erotherService")
public class EROtherService implements EROtherManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("EROtherMapper.save", pd);
	}
	/**
	 * zzy
	 * 新增
	 */
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		zzyPd.put("ERO_GM_ID",pd.get("gm_id"));
		zzyPd.put("ERO_E_ID",pd.get("e_id"));
		zzyPd.put("ERO_GMU_ID",pd.get("gmu_id"));
		zzyPd.put("ERO_TYPE",pd.get("ero_type"));
		zzyPd.put("ERO_CONTENT",pd.get("ero_content"));
		zzyPd.put("ERO_CTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERO_UTIME",Tools.date2Str(new Date()));
		zzyPd.put("ERO_GTIME",pd.get("ero_time"));
		zzyPd.put("EROTHER_ID",UuidUtil.get32UUID());
		dao.save("EROtherMapper.save",zzyPd);
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("EROtherMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("EROtherMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("EROtherMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("EROtherMapper.listAll", pd);
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
		zzyPd.put("ERO_GM_ID",pd.get("gm_id"));
		zzyPd.put("ERO_E_ID",pd.get("e_id"));
		return (List<PageData>)dao.findForList("EROtherMapper.zzyPast", zzyPd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("EROtherMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("EROtherMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

