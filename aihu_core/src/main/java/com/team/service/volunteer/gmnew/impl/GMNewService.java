package com.team.service.volunteer.gmnew.impl;

import java.util.Date;
import java.util.HashMap;
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
import com.team.service.volunteer.gmnew.GMNewManager;

/** 
 * 说明： 养老院评论
 * 创建人：jaychum
 * 创建时间：2016-04-05
 * @version
 */
@Service("gmnewService")
public class GMNewService implements GMNewManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("GMNewMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMNewMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMNewMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMNewMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMNewMapper.listAll", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMNewMapper.zzyList", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData>zzyListWithReply(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMNewMapper.zzyListWithReply",pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData>zzyListNoReply(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMNewMapper.zzyListNoReply",pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMNewMapper.findById", pd);
	}
	@Override
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("GMNewMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMNewMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 评论列表
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_gmnlist", pd)){	//检查参数
				zzyPd.put("GMN_GM_ID",pd.getString("gmid"));
				Map<String,Object>tl=new HashMap<String,Object>();
				tl.put("first", zzyListNoReply(zzyPd));
				tl.put("second", zzyListWithReply(zzyPd));
				map.put("pd",tl);
				result = "01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}

	@Override
	public Map<String, Object> app_zzyAdd(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_gmnadd", pd)){	//检查参数
				zzyPd.put("GMNEW_ID",UuidUtil.get32UUID());
				zzyPd.put("GMN_GM_ID",pd.getString("gmid"));
				zzyPd.put("GMN_P_ID", pd.getString("userid"));
				PageData up=appuserService.zzyFindById(pd.getString("userid"));
				zzyPd.put("GMN_P_AVATER",up.get("AVATER"));
				zzyPd.put("GMN_P_NAME",up.get("USERNAME"));
				if(pd.containsKey("newid")&&pd.get("newid")!=""){
					zzyPd.put("GMN_R_ID",pd.getString("newid"));
					PageData news=zzyFindById(pd.getString("newid"));
					String urid=news.getString("GMN_P_ID");
					PageData ur=appuserService.zzyFindById(urid);
					zzyPd.put("GMN_R_PID",ur.getString("USER_ID"));
					zzyPd.put("GMN_R_PAVATER",ur.getString("AVATER"));
					zzyPd.put("GMN_R_PNAME",ur.getString("USERNAME"));
				}
				zzyPd.put("GMN_CONTENT",pd.getString("content"));
				zzyPd.put("GMN_STATUS",1);
				zzyPd.put("GMN_CTIME",Tools.date2Str(new Date()));
				zzyPd.put("GMN_UTIME",Tools.date2Str(new Date()));
				save(zzyPd);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	
}

