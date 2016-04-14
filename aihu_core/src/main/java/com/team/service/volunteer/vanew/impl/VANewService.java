package com.team.service.volunteer.vanew.impl;

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
import com.team.service.volunteer.vanew.VANewManager;

/** 
 * 说明： 活动评论
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
@Service("vanewService")
public class VANewService implements VANewManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VANewMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VANewMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VANewMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VANewMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VANewMapper.listAll", pd);
	}
	/**
	 * 列表
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VANewMapper.zzyList", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> zzyListWithReply(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VANewMapper.zzyList_1", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> zzyListNoReply(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VANewMapper.zzyList_2", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VANewMapper.findById", pd);
	}
	@Override
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("VANewMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VANewMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public Map<String, Object> app_zzyAdd(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vanadd", pd)){	//检查参数
				zzyPd.put("VANEW_ID",UuidUtil.get32UUID());
				zzyPd.put("VAN_VA_ID",pd.getString("vaid"));
				zzyPd.put("VAN_P_ID", pd.getString("userid"));
				PageData up=appuserService.zzyFindById(pd.getString("userid"));
				zzyPd.put("VAN_P_AVATER",up.get("AVATER"));
				zzyPd.put("VAN_P_NAME",up.get("USERNAME"));
				if(pd.containsKey("newid")&&pd.get("newid")!=""){
					zzyPd.put("VAN_R_ID",pd.getString("newid"));
					PageData news=zzyFindById(pd.getString("newid"));
					String urid=news.getString("VAN_P_ID");
					PageData ur=appuserService.zzyFindById(urid);
					zzyPd.put("VAN_R_PID",ur.getString("USER_ID"));
					zzyPd.put("VAN_R_PAVATER",ur.getString("AVATER"));
					zzyPd.put("VAN_R_PNAME",ur.getString("USERNAME"));
				}
				zzyPd.put("VAN_CONTENT",pd.getString("content"));
				zzyPd.put("VAN_STATUS",1);
				zzyPd.put("VAN_CTIME",Tools.date2Str(new Date()));
				zzyPd.put("VAN_UTIME",Tools.date2Str(new Date()));
				save(zzyPd);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}

	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		PageData zzyPd=new PageData();
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil2.checkParam("appzzy2_vanlist", pd)){	//检查参数
					zzyPd.put("VAN_VA_ID",pd.get("vaid"));
					Map<String,Object>tl=new HashMap<String,Object>();
					tl.put("first", zzyListNoReply(zzyPd));
					tl.put("second", zzyListWithReply(zzyPd));
					map.put("pd",tl);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	
}

