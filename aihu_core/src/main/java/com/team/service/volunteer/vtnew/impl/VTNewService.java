package com.team.service.volunteer.vtnew.impl;

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
import com.team.service.volunteer.vtnew.VTNewManager;

/** 
 * 说明： 团体消息表
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
@Service("vtnewService")
public class VTNewService implements VTNewManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VTNewMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VTNewMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VTNewMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VTNewMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTNewMapper.listAll", pd);
	}
	/**
	 * 列表
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTNewMapper.zzyList", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> zzyListWithReply(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTNewMapper.zzyList_1", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> zzyListNoReply(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VTNewMapper.zzyList_2", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VTNewMapper.findById", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("VTNewMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VTNewMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**
	 * 某话题的评论列表
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> app_zzyAdd(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		PageData zzyPd=new PageData();
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil2.checkParam("appzzy2_vtnadd", pd)){	//检查参数
					zzyPd.put("VTNEW_ID",UuidUtil.get32UUID());
					zzyPd.put("VTN_VTT_ID",pd.getString("vttid"));
					zzyPd.put("VTN_P_ID", pd.getString("userid"));
					PageData up=appuserService.zzyFindById(pd.getString("userid"));
					zzyPd.put("VTN_P_AVATER",up.get("AVATER"));
					zzyPd.put("VTN_P_NAME",up.get("USERNAME"));
					if(pd.containsKey("newid")&&pd.get("newid")!=""){
						zzyPd.put("VTN_R_ID",pd.getString("newid"));
						PageData news=zzyFindById(pd.getString("newid"));
						String urid=news.getString("VTN_P_ID");
						PageData ur=appuserService.zzyFindById(urid);
						zzyPd.put("VTN_R_PID",ur.getString("USER_ID"));
						zzyPd.put("VTN_R_PAVATER",ur.getString("AVATER"));
						zzyPd.put("VTN_R_PNAME",ur.getString("USERNAME"));
					}
					zzyPd.put("VTN_CONTENT",pd.getString("content"));
					zzyPd.put("VTN_STATUS",1);
					zzyPd.put("VTN_CTIME",Tools.date2Str(new Date()));
					zzyPd.put("VTN_UTIME",Tools.date2Str(new Date()));
					save(zzyPd);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	
	/**
	 * 某话题的评论列表
	 * @throws Exception n
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		PageData zzyPd=new PageData();
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil2.checkParam("appzzy2_vtnlist", pd)){	//检查参数
					zzyPd.put("VTN_VTT_ID",pd.get("vttid"));
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

