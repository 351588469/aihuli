package com.team.controller.app.volunteer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.controller.base.BaseController;
import com.team.service.system.appuser.AppuserManager;
import com.team.service.volunteer.vactivity.VActivityManager;
import com.team.service.volunteer.vaenroll.VAEnrollManager;
import com.team.service.volunteer.vaimg.VAImgManager;
import com.team.service.volunteer.vanew.VANewManager;
import com.team.service.volunteer.vtconcern.VTConcernManager;
import com.team.service.volunteer.vteam.VTeamManager;
import com.team.service.volunteer.vtnew.VTNewManager;
import com.team.service.volunteer.vttheme.VTThemeManager;
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
	@Resource(name="vtconcernService")
	private VTConcernManager vtconcernService;
	@Resource(name="vtthemeService")
	private VTThemeManager vtthemeService;
	@Resource(name="vtnewService")
	private VTNewManager vtnewService;
	@Resource(name="vactivityService")
	private VActivityManager vactivityService;
	@Resource(name="vanewService")
	private VANewManager vanewService;
	@Resource(name="vaimgService")
	private VAImgManager vaimgService;
	@Resource(name="vaenrollService")
	private VAEnrollManager vaenrollService;
	/**
	 * uadd  vtadd vtlist vtc    vtclist  vttadd  vttlist vtnlist vtnadd
	 * 用户注册  团体认证     团体列表    团体关注     关注列表            话题发表    话题列表         评论列表        评论发表
	 * vaadd valist vaelist vae   vanadd   vanlist  
	 * 活动发布 活动列表      报名列表        活动报名 活动评论发表   活动评论列表
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
			pd.put("city",request.getParameter("city"));
			pd.put("address",request.getParameter("address"));
			pd.put("describe",request.getParameter("describe"));
			pd.put("userid",request.getParameter("userid"));
			fm=toolService.zzyUploadImg(request);
			map=vteamService.app_zzyAdd(pd,fm);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体列表
	 */
	@RequestMapping(value="/vtlist")
	@ResponseBody
	public Object vtlist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vteamService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 关注/取消关注 公益团体 
	 */
	@RequestMapping(value="/vtc")
	@ResponseBody
	public Object vtc(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtconcernService.app_zzyUpdate(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 公益团体关注列表
	 */
	@RequestMapping(value="/vtclist")
	@ResponseBody
	public Object vtclist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtconcernService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 发表团体话题
	 */
	@RequestMapping(value="/vttadd")
	@ResponseBody
	public Object vttadd(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtthemeService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体话题列表
	 */
	@RequestMapping(value="/vttlist")
	@ResponseBody
	public Object vttlist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtthemeService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体话题评论发表
	 */
	@RequestMapping(value="/vtnadd")
	@ResponseBody
	public Object vtnadd(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtnewService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体话题评论列表
	 */
	@RequestMapping(value="/vtnlist")
	@ResponseBody
	public Object vtnlist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtnewService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动发布vaadd
	 */
	@RequestMapping(value="/vaadd")
	@ResponseBody
	public Object vaadd(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vactivityService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动列表
	 */
	@RequestMapping(value="/valist")
	@ResponseBody
	public Object valist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vactivityService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动报名列表
	 */
	@RequestMapping(value="/vaelist")
	@ResponseBody
	public Object vaelist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vaenrollService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动报名
	 * 若存有 cancel参数则为取消报名
	 */
	@RequestMapping(value="/vae")
	@ResponseBody
	public Object vae(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vaenrollService.app_zzyUpdate(pd);
			System.out.println(map.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动评论发表
	 */
	@RequestMapping(value="/vanadd")
	@ResponseBody
	public Object vanadd(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vanewService.app_zzyAdd(pd);
			System.out.println(map.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
}
