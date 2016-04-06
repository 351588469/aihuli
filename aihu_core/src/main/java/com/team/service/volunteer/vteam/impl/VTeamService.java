package com.team.service.volunteer.vteam.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.volunteer.vteam.VTeamManager;
import com.team.service.volunteer.vtimg.VTImgManager;

/** 
 * 说明： 义工
 * 创建人：jaychum
 * 创建时间：2016-04-05
 * @version
 */
@Service("vteamService")
public class VTeamService implements VTeamManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="vtimgService")
	private VTImgManager vtimgService;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	
	public void save(PageData pd)throws Exception{
		dao.save("VTeamMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VTeamMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VTeamMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VTeamMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTeamMapper.listAll", pd);
	}
	/**
	 * 列表
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTeamMapper.zzyList", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VTeamMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VTeamMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 认证公益团体
	 */
	public Map<String,Object> app_zzyAdd(PageData pd,Map<Integer,String>fm)throws Exception{
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vtadd", pd)){	//检查参数
				String vteam_id=UuidUtil.get32UUID();
				zzyPd.put("VTEAM_ID",vteam_id);
				zzyPd.put("VT_NAME",pd.get("name"));
				zzyPd.put("VT_HTIME",pd.get("htime"));
				zzyPd.put("VT_CITY",pd.get("city"));
				zzyPd.put("VT_ADDRESS",pd.get("address"));
				zzyPd.put("VT_DESCRIBE",pd.get("describe"));
				zzyPd.put("VT_C_ID",pd.get("userid"));
				zzyPd.put("VT_THEME",0);
				zzyPd.put("VT_CONCERN",0);
				zzyPd.put("VT_STATUS",1);
				zzyPd.put("VT_CTIME",Tools.date2Str(new Date()));
		    	zzyPd.put("VT_UTIME",Tools.date2Str(new Date()));
				Iterator<Integer>it=fm.keySet().iterator();   
				while(it.hasNext()){
					Integer key=it.next();
				    if(key==1)
				    	zzyPd.put("VT_LOGO",fm.get(1));
				    else{
				    	PageData tpd=new PageData();
				    	tpd.put("VTIMG_ID",UuidUtil.get32UUID());
				    	tpd.put("VTI_VT_ID",vteam_id);
				    	tpd.put("VTI_SRC",fm.get(key));
				    	tpd.put("VTI_TYPE",1);
				    	tpd.put("VTI_CTIME",Tools.date2Str(new Date()));
				    	tpd.put("VTI_UTIME",Tools.date2Str(new Date()));
				    	vtimgService.save(tpd);
				    }
				}   
				dao.save("VTeamMapper.save",zzyPd);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 * app zzy 团体列表
	 */
	public Map<String,Object> app_zzyList(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vtlist", pd)){	//检查参数
				if(pd.containsKey("city"))zzyPd.put("VT_CITY",pd.get("city"));
				List<PageData>list=zzyList(zzyPd);
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 *zzy 修改话题数量
	 */
	@Override
	public void zzyUpdateTheme(String vtid, Integer x) throws Exception {
		if(x==1) dao.update("VTeamMapper.zzyAddTheme",vtid);
		else if(x==-1)dao.update("VTeamMapper.zzyMinusTheme",vtid);
	}
	
}

