package com.team.service.volunteer.vaenroll.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.d;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.system.appuser.AppuserManager;
import com.team.service.volunteer.vactivity.VActivityManager;
import com.team.service.volunteer.vaenroll.VAEnrollManager;

/** 
 * 说明： 活动报名表
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
@Service("vaenrollService")
public class VAEnrollService implements VAEnrollManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="vactivityService")
	private VActivityManager vactivityService;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VAEnrollMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VAEnrollMapper.delete", pd);
	}
	@Override
	public void zzyDelete(String vaeid)throws Exception{
		dao.delete("VAEnrollMapper.zzyDelete",vaeid);
	}
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VAEnrollMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VAEnrollMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VAEnrollMapper.listAll", pd);
	}
	/**列表(全部)
	 */
	@SuppressWarnings("unchecked")
	public List<PageData>zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VAEnrollMapper.zzyList", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VAEnrollMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VAEnrollMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 报名列表
	 */
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vaelist", pd)){	//检查参数
				zzyPd.put("VAE_VA_ID",pd.getString("vaid"));
				List<PageData>list=zzyList(zzyPd);
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	/**
	 * 活动报名 
	 */
	@Override
	public Map<String, Object> app_zzyUpdate(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vae", pd)){	//检查参数
				String userid=pd.getString("userid");
				String vaid=pd.getString("vaid");
				zzyPd.put("VAE_VA_ID",vaid);
				zzyPd.put("VAE_USER_ID",userid);
				PageData vae=zzyCheck(zzyPd);
				if(vae!=null&&vae.getString("VAENROLL_ID")!=""){//已经报名
					String vaeid=vae.getString("VAENROLL_ID");
					if(pd.getString("cancel")!=null&&pd.getString("cancel")!=""){//取消报名
						zzyDelete(vaeid);//删除报名表中记录
						vactivityService.zzyUpdateEnroll(vaid,-1);//更新报名人数
						result="01";
						map.put("info","成功取消报名");
					}else{//不做处理
						result="06";
						map.put("info","您已报名过了");
					}
				}else {//尚未报名
					if(pd.getString("cancel")!=null&&pd.getString("cancel")!=""){//取消报名
						result="06";
						map.put("info","查询不到您的报名信息");
					}else{
						PageData va=vactivityService.zzyFindById(vaid);
						Integer limit=(Integer) va.get("VA_ENROLL_M");
						Integer own=(Integer) va.get("VA_ENROLL");
						if(own==limit){//报名人数达上限制
							result="06";
							map.put("info","报名人数达上限");
						}else{
							//报名表中添加记录
							zzyPd.put("VAENROLL_ID",UuidUtil.get32UUID());
							PageData user=appuserService.zzyFindById(userid);
							zzyPd.put("VAE_USER_NAME",user.getString("USERNAME"));
							zzyPd.put("VAE_USER_AVATER",user.getString("AVATER"));
							zzyPd.put("VAE_CTIME",Tools.date2Str(new Date()));
							zzyPd.put("VAE_UTIME",Tools.date2Str(new Date()));
							save(zzyPd);
							vactivityService.zzyUpdateEnroll(vaid,1);
							result="01";
						}
					}
				}
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}

	@Override
	public PageData  zzyCheck(PageData pd) throws Exception {
		return (PageData) dao.findForObject("VAEnrollMapper.zzyCheck", pd);
	}
}

