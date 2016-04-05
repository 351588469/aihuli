package com.team.controller.app.volunteer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.controller.base.BaseController;
import com.team.service.system.appuser.AppuserManager;
import com.team.service.volunteer.vteam.VTeamManager;
import com.team.service.zzy.tool.ToolManager;
import com.team.util.AppUtil;
import com.team.util.PageData;

/**
 * Volunteer 2.0
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/appzzy2")
public class ZzyAppVolunteerController extends BaseController{
	@Resource(name="toolService")
	private ToolManager toolService;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	@Resource(name="vteamService")
	private VTeamManager vteamService;
	/**
	 * uadd
	 * 用户注册
	 * @return
	 */
	@RequestMapping(value="/uadd")
	@ResponseBody
	public Object uadd(HttpServletRequest request){
		Map<String,Object>map = null;
		Map<Integer,String>fm=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//form-data无法通过框架中方法遍历获取参数
			pd.put("USERNAME",request.getParameter("username"));
			pd.put("PHONE",request.getParameter("phone"));
			pd.put("ADDRESS",request.getParameter("address"));
			pd.put("BIRTH",request.getParameter("birth"));
			pd.put("GENDER",request.getParameter("gender"));
			pd.put("JOB",request.getParameter("job"));
			pd.put("SIGN",request.getParameter("sign"));
			fm=toolService.zzyUploadImg(request);
			if(fm.containsKey(1)){
				pd.put("AVATER",fm.get(1));
			}
			map=appuserService.zzyAddUser(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 认证 公益团体
	 */
	@RequestMapping(value="/vtadd")
	@ResponseBody
	public Object vtadd(HttpServletRequest request){
		Map<String,Object>map = null;
		Map<Integer,String>fm=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//form-data无法通过框架中方法遍历获取参数
			pd.put("name",request.getParameter("name"));
			pd.put("htime",request.getParameter("htime"));
			pd.put("address",request.getParameter("address"));
			pd.put("describe",request.getParameter("describe"));
			pd.put("userid",request.getParameter("userid"));
			fm=toolService.zzyUploadImg(request);
			map=vteamService.zzyAdd(pd,fm);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
}
