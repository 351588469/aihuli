package com.team.service.volunteer.vdonation.impl;

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
import com.team.service.volunteer.vdimg.VDImgManager;
import com.team.service.volunteer.vdonation.VDonationManager;

/** 
 * 说明： 捐赠
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
@Service("vdonationService")
public class VDonationService implements VDonationManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	@Resource(name="vdimgService")
	private VDImgManager vdimgService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VDonationMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VDonationMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VDonationMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VDonationMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VDonationMapper.listAll", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VDonationMapper.zzyList", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VDonationMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VDonationMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 捐赠发布
	 */
	@Override
	public Map<String, Object> app_zzyAdd(PageData pd,Map<Integer,String>fm) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vdadd", pd)){	//检查参数
				String vdid=UuidUtil.get32UUID();
				zzyPd.put("VDONATION_ID",vdid);
				zzyPd.put("VD_TITLE",pd.getString("title"));
				zzyPd.put("VD_TARGET",pd.getString("target"));
				zzyPd.put("VD_CONTENT",pd.getString("content"));
				if(pd.get("city")!=null&&pd.getString("city")!="")
					zzyPd.put("VD_CITY",pd.getString("city"));
				String userid=pd.getString("userid");
				zzyPd.put("VD_USER_ID",userid);
				PageData user=appuserService.zzyFindById(userid);
				zzyPd.put("VD_USER_AVATER",user.getString("AVATER"));
				zzyPd.put("VD_USER_NAME",user.getString("USERNAME"));
				zzyPd.put("VD_PRAISE",0);
				zzyPd.put("VD_STATUS",1);
				zzyPd.put("VD_CTIME",Tools.date2Str(new Date()));
				zzyPd.put("VD_UTIME",Tools.date2Str(new Date()));
				save(zzyPd);
				//存储图片
				Iterator<Integer>it=fm.keySet().iterator();   
				while(it.hasNext()){
					Integer key=it.next();
					PageData tpd=new PageData();
					tpd.put("VDIMG_ID",UuidUtil.get32UUID());
					tpd.put("VDI_VD_ID",vdid);
					tpd.put("VDI_SRC",fm.get(key));
					tpd.put("VDI_TYPE",1);
					tpd.put("VDI_CTIME",Tools.date2Str(new Date()));
					tpd.put("VDI_UTIME",Tools.date2Str(new Date()));
					vdimgService.save(tpd);
				}   
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 * 公益捐赠列表
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vdlist", pd)){	//检查参数
				if(pd.containsKey("city")&&pd.getString("city")!="")
					zzyPd.put("VD_CITY",pd.getString("city"));
				List<PageData>list=zzyList(zzyPd);
				for(int i=0;i<list.size();i++){
					PageData tpd=list.get(i);
					String vdid=tpd.getString("VDONATION_ID");
					List<PageData>imgs=vdimgService.zzyList(vdid);
					tpd.put("VD_IMGS",imgs);
				}
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	@Override
	public Map<String, Object> app_zzyList_byUserId(String userid)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			PageData zzyPd=new PageData();
			zzyPd.put("VD_USER_ID",userid);
			List<PageData>list=zzyList(zzyPd);
			for(int i=0;i<list.size();i++){
				PageData tpd=list.get(i);
				String vdid=tpd.getString("VDONATION_ID");
				List<PageData>imgs=vdimgService.zzyList(vdid);
				tpd.put("VD_IMGS",imgs);
			}
			map.put("pd",list);
			result="01";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	@Override
	public Integer zzyCount_byUserId(String userid) throws Exception {
		PageData zzyPd=new PageData();
		zzyPd.put("VD_USER_ID",userid);
		List<PageData>list=zzyList(zzyPd);
		return list.size();
	}
	@Override
	public void zzyUpdatePraise(String vdid, Integer x) throws Exception {
		PageData zzyPd=new PageData();
		zzyPd.put("VDONATION_ID",vdid);
		zzyPd.put("NUM",x);
		zzyPd.put("VD_UTIME",Tools.date2Str(new Date()));
		dao.update("VDonationMapper.zzyUpdatePraise",zzyPd);
	}



	
}

