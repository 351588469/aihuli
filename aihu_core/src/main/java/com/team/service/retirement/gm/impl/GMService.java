package com.team.service.retirement.gm.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.sun.tools.corba.se.idl.StringGen;
import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.DateUtil;
import com.team.util.Jurisdiction;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.gm.GMManager;
import com.team.service.system.user.UserManager;

/** 
 * 说明： 养老院信息
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("gmService")
public class GMService implements GMManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="userService")
	private UserManager userService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd,HttpSession session)throws Exception{
		//数据处理
		if(!pd.containsKey("GM_CKSTATUS"))pd.put("GM_CKSTATUS",3);//3:待审核
		dao.save("GMMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		if(pd.get("GM_BERTH_COUNT").equals(""))pd.put("GM_BERTH_COUNT",null);
		if(!pd.containsKey("GM_CKSTATUS"))pd.put("GM_CKSTATUS",3);//3:待审核
		pd.put("GM_UTIME",DateUtil.getTime().toString());
		dao.update("GMMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page,HttpSession session)throws Exception{
		PageData pd=page.getPd();
		//权限
		Integer role=(Integer) session.getAttribute("SYS_ZZY_ROLE");
		if(role==null)pd.put("SYS_ZZY_ROLE",-1);
		else if(role==2){
			pd.put("SYS_ZZY_ROLE",2);
			pd.put("GM_AU_ID",session.getAttribute("SYS_ZZY_USERID"));
		}
		return (List<PageData>)dao.findForList("GMMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd,HttpSession session)throws Exception{
		if(pd==null)pd=new PageData();
		//权限
		Integer role=(Integer) session.getAttribute("SYS_ZZY_ROLE");
		if(role==null)pd.put("SYS_ZZY_ROLE",-1);
		else if(role==2){
			pd.put("SYS_ZZY_ROLE",2);
			pd.put("GM_AU_ID",session.getAttribute("SYS_ZZY_USERID"));
		}
		return (List<PageData>)dao.findForList("GMMapper.listAll", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData>zzyList(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		if(pd.containsKey("city")&&pd.getString("city")!="")
			zzyPd.put("GM_CITY",pd.get("city"));
		zzyPd.put("GM_CKSTATUS",1);
		return (List<PageData>)dao.findForList("GMMapper.zzyList",zzyPd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMMapper.deleteAll", ArrayDATA_IDS);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> listByCreator() throws Exception {
		List<Map<String,Object>>list=(List<Map<String, Object>>) dao.findForList("GMMapper.listCreator",null);
		return list;
	}
	/**
	 * zzy
	 * 通过id获取名称
	 */
	@Override
	public String zzyFindNameById(String id) throws Exception {
		return (String) dao.findForObject("GMMapper.zzyFindNameById",id);
	}
	@Override
	public String zzyFindNameByAUId(String id) throws Exception {
		return (String) dao.findForObject("GMMapper.zzyFindNameByAUId",id);
	}
	/**
	 * zzy
	 * 通过名称获取Id 模糊搜索
	 */
	@Override
	public String zzyFindIdByName(String name) throws Exception {
		return (String) dao.findForObject("GMMapper.zzyFindIdByName",name);
	}
	/**
	 * 养老院列表
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_gmlist", pd)){	//检查参数
				if(pd.get("city")!=null&&pd.getString("city")!="")
					zzyPd.put("GM_CITY",pd.getString("city"));
				List<PageData>list=zzyList(zzyPd);
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}

	@Override
	public void zzyUpdatePraise(String gmid,Integer x) throws Exception {
		PageData zzyPd=new PageData();
		zzyPd.put("GM_ID",gmid);
		zzyPd.put("NUM",x);
		zzyPd.put("GM_UTIME",Tools.date2Str(new Date()));
		dao.update("GMMapper.zzyUpdatePraise",zzyPd);
	}
}

