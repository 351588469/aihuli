package com.team.service.volunteer.vttheme.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.system.appuser.AppuserManager;
import com.team.service.volunteer.vteam.VTeamManager;
import com.team.service.volunteer.vttheme.VTThemeManager;

/** 
 * 说明： 团体话题表
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
@Service("vtthemeService")
public class VTThemeService implements VTThemeManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	@Resource(name="vteamService")
	private VTeamManager vteamService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VTThemeMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VTThemeMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VTThemeMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VTThemeMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTThemeMapper.listAll", pd);
	}
	/**
	 * 列表
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTThemeMapper.zzyList", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VTThemeMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VTThemeMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * app zzy
	 * 发表团体话题
	 */
	@Override
	public Map<String, Object> app_zzyAdd(PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil2.checkParam("appzzy2_vttadd", pd)){	//检查参数
					PageData zzyPd=new PageData();
					String vtid=UuidUtil.get32UUID();
					zzyPd.put("VTTHEME_ID",vtid);
					zzyPd.put("VTT_VT_ID",pd.getString("vtid"));
					zzyPd.put("VTT_TITLE",pd.getString("title"));
					zzyPd.put("VTT_CONTENT",pd.getString("content"));
					String userid=pd.getString("userid");
					zzyPd.put("VTT_P_ID",userid);
					PageData user=appuserService.zzyFindById(userid);
					zzyPd.put("VTT_P_NAME",user.getString("USERNAME"));
					zzyPd.put("VTT_P_AVATER",user.getString("AVATER"));
					zzyPd.put("VTT_PRAISE",0);
					zzyPd.put("VTT_STATUS",1);
					zzyPd.put("VTT_CTIME",Tools.date2Str(new Date()));
					zzyPd.put("VTT_UTIME",Tools.date2Str(new Date()));
					save(zzyPd);
					//团体表话题数量+1
					vteamService.zzyUpdateTheme(pd.getString("vtid"),1);
					
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	/**
	 * 团体话题列表
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		PageData zzyPd=new PageData();
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil2.checkParam("appzzy2_vttlist", pd)){	//检查参数
					zzyPd.put("VTT_VT_ID",pd.get("vtid"));
					List<PageData>list=zzyList(zzyPd);
					map.put("pd",list);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
}

