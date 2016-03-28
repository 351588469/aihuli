package com.team.service.retirement.gmbresult.impl;

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
import com.team.service.retirement.gmberth.GMBerthManager;
import com.team.service.retirement.gmbresult.GMBResultManager;
import com.team.service.retirement.gmuser.GMUserManager;

/** 
 * 说明： 房间评测
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("gmbresultService")
public class GMBResultService implements GMBResultManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="gmberthService")
	private GMBerthManager gmberthService;
	@Resource(name="gmuserService")
	private GMUserManager gmuserService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("GMBResultMapper.save", pd);
	}
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		String gmbid=pd.getString("gmb_id");
		Integer cphoto=Integer.parseInt(pd.getString("gmbr_cphoto"));
		
		zzyPd.put("GMBRESULT_ID",UuidUtil.get32UUID());
		
		PageData gmb=gmberthService.zzyFindById(gmbid);
		zzyPd.put("GMBR_GMB_ID",gmbid);
		zzyPd.put("GMBR_GMB_FLOOR",gmb.get("GMB_FLOOR"));
		zzyPd.put("GMBR_GMB_LAYER",gmb.get("GMB_LAYER"));
		zzyPd.put("GMBR_GMB_ROOM",gmb.get("GMB_ROOM"));
		
		zzyPd.put("GMBR_ENVIRONMENT",pd.get("gmbr_enviroment"));
		zzyPd.put("GMBR_SANITATION", pd.get("gmbr_sanitation"));
		zzyPd.put("GMBR_SAFE", pd.get("gmbr_safe"));
		zzyPd.put("GMBR_EQUIPMENT",pd.get("gmbr_equipment"));
		zzyPd.put("GMBR_ENVIRONMENT", pd.get("gmbr_environment"));
		zzyPd.put("GMBR_DESC", pd.get("gmbr_desc"));
		zzyPd.put("GMBR_GMU_ID",pd.get("gmbr_gmu_id"));
		zzyPd.put("GMBR_CTIME",Tools.date2Str(new Date()));
		zzyPd.put("GMBR_UTIME",Tools.date2Str(new Date()));
		zzyPd.put("GMBR_CPHOTO",cphoto);
		if(cphoto>=1)zzyPd.put("GMBR_PHOTOA",pd.get("gmbr_photoa"));
		if(cphoto>=2)zzyPd.put("GMBR_PHOTOB",pd.get("gmbr_photob"));
		if(cphoto>=3)zzyPd.put("GMBR_PHOTOC",pd.get("gmbr_photoc"));
		if(cphoto>=4)zzyPd.put("GMBR_PHOTOD",pd.get("gmbr_photod"));
		//System.out.println("zzy:zzyPd;"+zzyPd.toString());
		dao.save("GMBResultMapper.save",zzyPd);
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMBResultMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMBResultMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMBResultMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMBResultMapper.listAll", pd);
	}
	/**
	 *房间评测历史记录
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyPast(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		Integer page=Integer.parseInt((String)pd.get("page"));
		String edate=DateUtil.getAfterDayDate(-(page-1)*30);
		String sdate=DateUtil.getAfterDayDate(-(page)*30);
		zzyPd.put("SDATE",sdate);
		zzyPd.put("EDATE",edate);
		zzyPd.put("GMBERTH_ID",pd.getString("gmb_id"));
		List<PageData>list=(List<PageData>)dao.findForList("GMBResultMapper.zzyPast",zzyPd);
		//添加职工名称
		for(int i=0;i<list.size();i++){
			PageData tpd=list.get(i);
			String gmuid=tpd.getString("GMBR_GMU_ID");
			String GMBR_GMU_NAME=gmuserService.zzyFindNameById(gmuid);
			tpd.put("GMBR_GMU_NAME",GMBR_GMU_NAME);
		}
		return list;
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMBResultMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMBResultMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * zzy
	 * 最近一次房间评价
	 */
	@Override
	public PageData zzyRecent(String roomid) throws Exception {
		return (PageData)dao.findForObject("GMBResultMapper.zzyRecent",roomid);
	}
	
}

