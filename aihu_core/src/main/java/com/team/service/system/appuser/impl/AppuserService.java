package com.team.service.system.appuser.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.service.system.appuser.AppuserManager;
import com.team.service.volunteer.vactivity.VActivityManager;
import com.team.service.volunteer.vdonation.VDonationManager;
import com.team.service.volunteer.vteam.VTeamManager;
import com.team.util.AppUtil2;
import com.team.util.MD5;
import com.team.util.PageData;
import com.team.util.UuidUtil;


/**类名称：AppuserService
 */
@Service("appuserService")
public class AppuserService implements AppuserManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="vactivityService")
	private VActivityManager vactivityService;
	@Resource(name="vdonationService")
	private VDonationManager vdonationService;
	@Resource(name="vteamService")
	private VTeamManager vteamService;
	/**列出某角色下的所有会员
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAllAppuserByRorlid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("AppuserMapper.listAllAppuserByRorlid", pd);
	}
	
	/**会员列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPdPageUser(Page page)throws Exception{
		return (List<PageData>) dao.findForList("AppuserMapper.userlistPage", page);
	}
	
	/**通过用户名获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUsername(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppuserMapper.findByUsername", pd);
	}
	
	/**通过邮箱获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByEmail(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppuserMapper.findByEmail", pd);
	}
	
	/**通过编号获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByNumber(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppuserMapper.findByNumber", pd);
	}
	
	/**保存用户
	 * @param pd
	 * @throws Exception
	 */
	public void saveU(PageData pd)throws Exception{
		dao.save("AppuserMapper.saveU", pd);
	}
	
	/**删除用户
	 * @param pd
	 * @throws Exception
	 */
	public void deleteU(PageData pd)throws Exception{
		dao.delete("AppuserMapper.deleteU", pd);
	}
	
	/**修改用户
	 * @param pd
	 * @throws Exception
	 */
	public void editU(PageData pd)throws Exception{
		dao.update("AppuserMapper.editU", pd);
	}
	@Override
	public void zzyEditU(PageData pd)throws Exception{
		dao.update("AppuserMapper.zzyEditU", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUiId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppuserMapper.findByUiId", pd);
	}
	
	/**全部会员
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAllUser(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("AppuserMapper.listAllUser", pd);
	}
	
	/**批量删除用户
	 * @param USER_IDS
	 * @throws Exception
	 */
	public void deleteAllU(String[] USER_IDS)throws Exception{
		dao.delete("AppuserMapper.deleteAllU", USER_IDS);
	}
	
	/**获取总数
	 * @param pd
	 * @throws Exception
	 */
	public PageData getAppUserCount(String value)throws Exception{
		return (PageData)dao.findForObject("AppuserMapper.getAppUserCount", value);
	}
	//添加用户
	@Override
	public Map<String,Object> zzyAddUser(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil2.checkParam("appzzy2_uadd", pd)){	//检查参数
					pd.put("USER_ID",UuidUtil.get32UUID());
					String userid=zzyFindIdByTel(pd.getString("PHONE"));
					if(userid!=null&&userid!=""){
						map.put("result","06");
						map.put("info","一个手机号只能注册一个账户！");
						return map;
					}
					dao.save("AppuserMapper.saveU", pd);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}
	//添加用户
	@Override
	public Map<String,Object> zzyUpdateUser(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			editU(pd);
			result="01";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}

	@Override
	public PageData zzyFindById(String id) throws Exception {
		return (PageData)dao.findForObject("AppuserMapper.zzyFindById",id);
	}

	@Override
	public Map<String, Object> zzyUserInfo(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
			//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
				if(AppUtil2.checkParam("appzzy2_uinfo", pd)){	//检查参数
					String userid=pd.getString("userid");
					PageData user=zzyFindById(userid);
					Integer n_va=vactivityService.zzyCount_byUserId(userid);
					Integer n_vt=vteamService.zzyCount_byUserId(userid);
					Integer n_vd=vdonationService.zzyCount_byUserId(userid);
					PageData y=new PageData();
					//y.put("user",user);
					y.putAll(user);
					y.put("n_va",n_va);
					y.put("n_vt",n_vt);
					y.put("n_vd",n_vd);
					PageData tpd=(PageData) dao.findForObject("VTeamMapper.zzyCheckAdd",userid);
					if(tpd!=null){
						Integer status=(Integer) tpd.get("VT_STATUS");
						y.put("vt_status",status);
						if(status==1)y.put("vt_info","审核中");
						else if(status==2)y.put("vt_info","审核通过");
						y.put("vt_id",tpd.get("VTEAM_ID"));
					}else{
						y.put("vt_status",-1);
						y.put("vt_info","尚未认证团体");
					}
					map.put("pd",y);
					result="01";
				}else result = "03";
			//}else{result = "05";}
			map.put("result", result);
		return map;
	}

	@Override
	public Map<String, Object> zzyResetPassword(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		PageData zzyPd=new PageData();
		String userid=zzyFindIdByTel(pd.getString("tel"));
		if(userid==null||userid==""){
			map.put("result","06");
			Map<String,Object>tm=new HashMap<>();
			tm.put("info", "查无相关信息！");
			map.put("pd",tm);
			return map;
		}
		zzyPd.put("USER_ID",userid);
		zzyPd.put("PASSWORD",MD5.md5(pd.getString("pwd")));
		zzyEditU(zzyPd);
		map.put("result","01");
		return map;
	}

	public String zzyFindIdByTel(String tel) throws Exception {
		return (String)dao.findForObject("AppuserMapper.zzyFindIdByTel",tel);
	}
	/**
	 * tel
	 */
	@Override
	public Map<String, Object> zzyLogin(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String userid=zzyFindIdByTel(pd.getString("tel"));
		if(userid==null||userid==""){
			map.put("result","06");
			Map<String,Object>tm=new HashMap<>();
			tm.put("info", "查无相关信息！");
			map.put("pd",tm);
			return map;
		}
		PageData User=zzyFindById(userid);
		String pwd_t=User.getString("PASSWORD");
		if(pwd_t.equals(MD5.md5(pd.getString("pwd")))){
			map.put("result","01");
			Map<String,Object>tm=new HashMap<>();
			tm.put("info", "登录成功！");
			tm.put("user",User);
			map.put("pd",tm);
			return map;
		}else{
			Map<String,Object>tm=new HashMap<>();
			tm.put("info", "密码错误！");
			map.put("result","06");
			map.put("pd",tm);
			return map;
		}
	}
	
}

