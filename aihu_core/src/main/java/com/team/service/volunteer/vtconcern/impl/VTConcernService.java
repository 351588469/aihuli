package com.team.service.volunteer.vtconcern.impl;

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
import com.team.service.system.appuser.AppuserManager;
import com.team.service.volunteer.vtconcern.VTConcernManager;

/** 
 * 说明： 团体关注成员
 * 创建人：jaychum
 * 创建时间：2016-04-05
 * @version
 */
@Service("vtconcernService")
public class VTConcernService implements VTConcernManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VTConcernMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VTConcernMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VTConcernMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VTConcernMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTConcernMapper.listAll", pd);
	}
	/**
	 * 列表
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTConcernMapper.zzyList", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VTConcernMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VTConcernMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * app zzy
	 * 修改关注
	 */
	@Override
	public Map<String, Object> app_zzyUpdate(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vtc", pd)){	//检查参数
				
				zzyPd.put("VTC_VT_ID",pd.get("vtid"));
				zzyPd.put("VTC_USER_ID",pd.get("userid"));
				//判断用户是否已经关注
				String vtcid=(String) dao.findForObject("VTConcernMapper.zzyConfirm",zzyPd);
				if(vtcid!=null&& vtcid!=""){//用户已经关注
					//删除关注表记录
					dao.delete("VTConcernMapper.zzyDelete",vtcid);
					//团体表关注数-1
					dao.update("VTeamMapper.zzyMinusConcern",pd.get("vtid"));
					map.put("info","取消关注成功");
				}else{//用户未关注
					//vtconcern表保存
					zzyPd.put("VTCONCERN_ID",UuidUtil.get32UUID());
					PageData user=appuserService.zzyFindById(pd.getString("userid"));
					zzyPd.put("VTC_USER_NAME",user.get("USERNAME"));
					zzyPd.put("VTC_USER_AVATER",user.get("AVATER"));
					zzyPd.put("VTC_CTIME",Tools.date2Str(new Date()));
					zzyPd.put("VTC_UTIME",Tools.date2Str(new Date()));
					dao.save("VTConcernMapper.save",zzyPd);
					//vteam表 vt_concern+1
					dao.update("VTeamMapper.zzyAddConcern",pd.get("vtid"));
					map.put("info","关注成功");
				}
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 * 团体关注列表
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vtclist", pd)){	//检查参数
				zzyPd.put("VTC_VT_ID",pd.getString("vtid"));
				List<PageData>list=zzyList(zzyPd);
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	
}

