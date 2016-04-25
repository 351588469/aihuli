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
import com.team.service.volunteer.vtconcern.VTConcernManager;
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
	@Resource(name="vtconcernService")
	private VTConcernManager vtconcernService;
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
	/**
	 * 通过id获取数据
	 */
	@Override
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("VTeamMapper.zzyFindById",id);
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
	public Map<String,Object> app_zzyAdd(PageData pd,Map<Integer,String>fm,boolean flag)throws Exception{
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		if(flag){
			map.put("result","06");
			map.put("info","一个用户只能认证一个团体！");
		}
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
				zzyPd.put("VT_STATUS",2);
				List<PageData>list=zzyList(zzyPd);
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 * app zzy 团体列表 用户关注
	 */
	public Map<String,Object> app_zzyList_byUserid(String userid)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
		@SuppressWarnings("unchecked")
		List<String>vaids=(List<String>) dao.findForList("VTConcernMapper.zzyVTIDList",userid);
		@SuppressWarnings("unchecked")
		List<PageData>list=(List<PageData>) dao.findForList("VTeamMapper.zzyListWithMultId",vaids);
		map.put("pd",list);
		result="01";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 * 团体信息
	 */
	@Override
	public Map<String,Object> app_zzyInfo(PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vtinfo", pd)){	//检查参数
				String id=pd.getString("vtid");
				PageData vt=zzyFindById(id);
				if(pd.containsKey("userid")&&pd.getString("userid")!=""){
					PageData tPd=new PageData();
					tPd.put("VTC_VT_ID",pd.getString("vtid"));
					tPd.put("VTC_USER_ID",pd.getString("userid"));
					String vtcid=(String) dao.findForObject("VTConcernMapper.zzyConfirm",tPd);
					if(vtcid!=null&&vtcid!=""){//用户已关注
						vt.put("USER_VT_CONCERN","1");//已关注
					}else{
						vt.put("USER_VT_CONCERN","2");//未关注
					}
				}else{
					vt.put("USER_VT_CONCERN","2");
				}
				map.put("pd",vt);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 * 用户关注团体数量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer zzyCount_byUserId(String userid) throws Exception {
		List<String>vaids=(List<String>) dao.findForList("VTConcernMapper.zzyVTIDList",userid);
		if(vaids.size()==0)return 0;
		return (Integer) dao.findForObject("VTeamMapper.zzyCountWithMultId",vaids);
	}
	/**
	 *zzy 修改话题数量
	 */
	@Override
	public void zzyUpdateTheme(String vtid, Integer x) throws Exception {
		if(x==1) dao.update("VTeamMapper.zzyAddTheme",vtid);
		else if(x==-1)dao.update("VTeamMapper.zzyMinusTheme",vtid);
	}

	@Override
	public boolean zzyCheckCreateUser(String vtid, String userid)
			throws Exception {
		PageData pd=zzyFindById(vtid);
		if(pd==null)return false;
		String uid=pd.getString("VT_C_ID");
		if(uid!=null&&uid.equals(userid))return true;
		return false;
	}

	@Override
	public boolean zzyCheckAdd(String userid)throws Exception{
		PageData pd=(PageData) dao.findForObject("VTeamMapper.zzyCheckAdd",userid);
		if(pd!=null)return true;
		else return false;
	}
	
}

