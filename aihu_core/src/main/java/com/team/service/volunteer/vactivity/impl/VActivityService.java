package com.team.service.volunteer.vactivity.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.Const2;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.volunteer.vactivity.VActivityManager;
import com.team.service.volunteer.vaenroll.VAEnrollManager;
import com.team.service.volunteer.vaimg.VAImgManager;
import com.team.service.volunteer.vteam.VTeamManager;

/** 
 * 说明： 活动表
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
@Service("vactivityService")
public class VActivityService implements VActivityManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="vteamService")
	private VTeamManager vteamService;
	@Resource(name="vaimgService")
	private VAImgManager vaimgService;
	@Resource(name="vaenrollService")
	private VAEnrollManager vaenrollService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VActivityMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VActivityMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VActivityMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VActivityMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VActivityMapper.listAll", pd);
	}
	/**
	 * 列表
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VActivityMapper.zzyList", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VActivityMapper.findById", pd);
	}
	@Override
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("VActivityMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VActivityMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 活动申报
	 */
	@Override
	public Map<String, Object> app_zzyAdd(PageData pd,Map<Integer,String>fm) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vaadd", pd)){	//检查参数
				boolean flag=vteamService.zzyCheckCreateUser(pd.getString("vtid"), pd.getString("userid"));
				if(flag==true){
					String vaid=UuidUtil.get32UUID();
					String time=Tools.date2Str(new Date());
					zzyPd.put("VACTIVITY_ID",vaid);
					zzyPd.put("VA_TOPIC",pd.get("topic"));
					zzyPd.put("VA_CITY",pd.getString("city"));
					zzyPd.put("VA_ADDRESS",pd.getString("address"));
					//zzyPd.put("VA_KEYWORD",pd.getString("keyword"));
					zzyPd.put("VA_CONTENT",pd.getString("content"));
					zzyPd.put("VA_SCHEDULE",pd.getString("schedule"));
					zzyPd.put("VA_VT_ID",pd.getString("vtid"));
					zzyPd.put("VA_PRAISE",0);
					zzyPd.put("VA_ENROLL",0);
					zzyPd.put("VA_ENROLL_M",pd.get("limit"));
					zzyPd.put("VA_STIME",pd.getString("stime"));
					zzyPd.put("VA_ETIME",pd.getString("etime"));
					zzyPd.put("VA_CTIME",time);
					zzyPd.put("VA_UTIME",time);
					zzyPd.put("VA_STATUS",1);
					save(zzyPd);
					vaimgService.zzyAdd(vaid, fm,Const2.ZZY2_VA_IMG_THEME);
					result="01";
				}else{
					result="07";
					map.put("info","只有团体负责人才能申报团体活动");
				}
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 *活动列表
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_valist", pd)){	//检查参数
				String city=pd.getString("city");
				if(city!=null&&city!="")zzyPd.put("VA_CITY",city);	
				List<PageData>list=zzyList(zzyPd);
				for(int i=0;i<list.size();i++){
					PageData tpd=list.get(i);
					String vtid=tpd.getString("VA_VT_ID");
					PageData vt=vteamService.zzyFindById(vtid);
					if(vt!=null){
						tpd.put("VA_VT_NAME",vt.getString("VT_NAME"));
					}
					PageData zti=new PageData();
					zti.put("VAI_VA_ID",tpd.getString("VACTIVITY_ID"));
					zti.put("VAI_TYPE",Const2.ZZY2_VA_IMG_THEME);
					List<PageData>imgs=vaimgService.zzyList(zti);
					tpd.put("VAI_IMG_THEME",imgs);
				}
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> app_zzyList_byUserId(String userid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				List<String>vaids=new ArrayList<>();
				vaids=(List<String>) dao.findForList("VAEnrollMapper.zzyVAIDList",userid);
				//System.out.println(vaids.toString());
				List<PageData>list=new ArrayList<>();
				if(vaids.size()>0)
					list=(List<PageData>) dao.findForList("VActivityMapper.zzyListWithMultId",vaids);
				for(int i=0;i<list.size();i++){
					PageData tpd=list.get(i);
					String vtid=tpd.getString("VA_VT_ID");
					PageData vt=vteamService.zzyFindById(vtid);
					tpd.put("VA_VT_NAME",vt.getString("VT_NAME"));
					PageData zti=new PageData();
					zti.put("VAI_VA_ID",tpd.getString("VACTIVITY_ID"));
					zti.put("VAI_TYPE",Const2.ZZY2_VA_IMG_THEME);
					List<PageData>imgs=vaimgService.zzyList(zti);
					tpd.put("VAI_IMG_THEME",imgs);
				}
				map.put("pd",list);
				result="01";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	@Override
	public Map<String,Object> app_zzyInfo(PageData pd)throws Exception{
		Map<String,Object>map=new HashMap<String,Object>();
		PageData opd=new PageData();
		PageData zzyPd=new PageData();
		String vaid=pd.getString("vaid");
		PageData va=zzyFindById(vaid);
		opd.put("va_info",va);
		PageData enroll=new PageData();
		if(pd.containsKey("userid")&&pd.get("userid")!=""){
			zzyPd.put("VAE_VA_ID",vaid);
			zzyPd.put("VAE_USER_ID",pd.get("userid"));
			PageData tpd=vaenrollService.zzyCheck(zzyPd);
			if(tpd!=null){//用户已经报名
				enroll.put("status","1");
				enroll.put("info","取消报名");
			}else{
				enroll.put("status","2");
				enroll.put("info","立刻报名");
			}
			
		}else{
			enroll.put("status",2);
			enroll.put("info","立刻报名");
		}
		opd.put("enroll_info", enroll);
		map.put("pd",opd);
		map.put("result","01");
		return map;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Integer zzyCount_byUserId(String userid) throws Exception {
		List<String>vaids=new ArrayList<>();
		vaids=(List<String>) dao.findForList("VAEnrollMapper.zzyVAIDList",userid);
		if(vaids.size()==0)return 0;
		return (Integer) dao.findForObject("VActivityMapper.zzyCountWithMultId",vaids);
	}
	@Override
	public void zzyUpdateEnroll(String vaid, Integer x) throws Exception {
		PageData zzyPd=new PageData();
		zzyPd.put("VACTIVITY_ID",vaid);
		zzyPd.put("NUM",x);
		zzyPd.put("VA_UTIME",Tools.date2Str(new Date()));
		dao.update("VActivityMapper.zzyUpdateEnroll",zzyPd);
	}
	@Override
	public void zzyUpdatePraise(String vaid, Integer x) throws Exception {
		PageData zzyPd=new PageData();
		zzyPd.put("VACTIVITY_ID",vaid);
		zzyPd.put("NUM",x);
		zzyPd.put("VA_UTIME",Tools.date2Str(new Date()));
		dao.update("VActivityMapper.zzyUpdatePraise",zzyPd);
	}

	
	
}

