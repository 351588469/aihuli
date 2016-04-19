package com.team.controller.app.volunteer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.controller.base.BaseController;
import com.team.service.retirement.erhealth.ERHealthManager;
import com.team.service.retirement.gm.GMManager;
import com.team.service.retirement.gmaitem.GMAitemManager;
import com.team.service.retirement.gmaresult.GMAResultManager;
import com.team.service.system.appuser.AppuserManager;
import com.team.service.volunteer.gmnew.GMNewManager;
import com.team.service.volunteer.gmpraise.GMPraiseManager;
import com.team.service.volunteer.vactivity.VActivityManager;
import com.team.service.volunteer.vaenroll.VAEnrollManager;
import com.team.service.volunteer.vaimg.VAImgManager;
import com.team.service.volunteer.vanew.VANewManager;
import com.team.service.volunteer.vapraise.VAPraiseManager;
import com.team.service.volunteer.vdonation.VDonationManager;
import com.team.service.volunteer.vdpraise.VDPraiseManager;
import com.team.service.volunteer.vfeedback.VFeedbackManager;
import com.team.service.volunteer.vtconcern.VTConcernManager;
import com.team.service.volunteer.vteam.VTeamManager;
import com.team.service.volunteer.vtnew.VTNewManager;
import com.team.service.volunteer.vttheme.VTThemeManager;
import com.team.service.zzy.tool.ToolManager;
import com.team.util.AppUtil;
import com.team.util.Const2;
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
	@Resource(name="vapraiseService")
	private VAPraiseManager vapraiseService;
	@Resource(name="vdonationService")
	private VDonationManager vdonationService;
	@Resource(name="vdpraiseService")
	private VDPraiseManager vdpraiseService;
	@Resource(name="gmService")
	private GMManager gmService;
	@Resource(name="gmnewService")
	private GMNewManager gmnewService;
	@Resource(name="gmpraiseService")
	private GMPraiseManager gmpraiseService;
	@Resource(name="erhealthService")
	private ERHealthManager erhealthService;
	@Resource(name="gmaitemService")
	private GMAitemManager gmaitemService;
	@Resource(name="gmaresultService")
	private GMAResultManager gmaresultService;
	@Resource(name="vfeedbackService")
	private VFeedbackManager vfeedbackService;
	/**
	 * uadd  uinfo  vtadd vtlist vtctest     vtctest  vtclist  vttadd  vttlist vtnlist vtnadd
	 * 用户注册  用户信息    团体认证     团体列表  用户是否关注团体      团体                   关注列表         话题发表        话题列表         评论列表        评论发表
	 * vaadd valist vaelist vae   vanadd   vanlist  vapadd vaiadd   vailist
	 * 活动发布 活动列表      报名列表        活动报名 活动评论发表   活动评论列表    活动点赞     活动图片上传   图片列表
	 * vdadd vdlist vdpadd
	 * 捐赠发布  捐赠列表    捐赠点赞
	 * gmlist gmnlist  gmpadd  gmnadd  gmhlist  gmhadd    gmailist gmaradd
	 * 养老院列表  养老评论列表    养老院点赞   评论发表       健康历史记录    健康记录添加      评测列表          评测结果添加
	 * vfadd
	 * 意见反馈
	 */
	@RequestMapping(value="/uadd")
	@ResponseBody
	public Object uadd(HttpServletRequest request){
		Map<String,Object>map =new HashMap<>();
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
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	@RequestMapping(value="/uedit")
	@ResponseBody
	public Object uedit(HttpServletRequest request){
		Map<String,Object>map = new HashMap<>();
		Map<Integer,String>fm=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//form-data无法通过框架中方法遍历获取参数
			pd.put("USER_ID",request.getParameter("userid"));
			pd.put("USERNAME",request.getParameter("username"));
			//pd.put("PHONE",request.getParameter("phone"));
			pd.put("ADDRESS",request.getParameter("address"));
			pd.put("BIRTH",request.getParameter("birth"));
			pd.put("GENDER",request.getParameter("gender"));
			pd.put("JOB",request.getParameter("job"));
			pd.put("SIGN",request.getParameter("sign"));
			fm=toolService.zzyUploadImg(request);
			if(fm.containsKey(1)){
				pd.put("AVATER",fm.get(1));
			}
			map=appuserService.zzyUpdateUser(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 修改用户信息，无文件
	 */
	@RequestMapping(value="/uedit2")
	@ResponseBody
	public Object uedit2(){
		Map<String,Object>map = new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData zzyPd=new PageData();
		try {
			//form-data无法通过框架中方法遍历获取参数
			zzyPd.put("USER_ID",pd.get("userid"));
			zzyPd.put("USERNAME",pd.get("username"));
			zzyPd.put("ADDRESS",pd.get("address"));
			zzyPd.put("BIRTH",pd.get("birth"));
			zzyPd.put("GENDER",pd.get("gender"));
			zzyPd.put("JOB",pd.get("job"));
			zzyPd.put("SIGN",pd.get("sign"));
			map=appuserService.zzyUpdateUser(zzyPd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	@RequestMapping(value="/uinfo")
	@ResponseBody
	public Object uinfo(HttpServletRequest request){
		Map<String,Object>map = new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=appuserService.zzyUserInfo(pd);
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
		Map<String,Object>map = new HashMap<>();
		Map<Integer,String>fm=new HashMap<Integer,String>();
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
			//检测用户是否有正在认证的团队
			boolean flag=vteamService.zzyCheckAdd(request.getParameter("userid"));
			fm=toolService.zzyUploadImg(request);
			map=vteamService.app_zzyAdd(pd,fm,flag);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体列表
	 */
	@RequestMapping(value="/vtlist")
	@ResponseBody
	public Object vtlist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if(pd.containsKey("userid")&&pd.getString("userid")!="")
				map=vteamService.app_zzyList_byUserid(pd.getString("userid"));
			else
			map=vteamService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 关注/取消关注 公益团体 
	 */
	@RequestMapping(value="/vtc")
	@ResponseBody
	public Object vtc(){
		Map<String,Object>map = new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtconcernService.app_zzyUpdate(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 公益团体关注列表
	 */
	@RequestMapping(value="/vtclist")
	@ResponseBody
	public Object vtclist(){
		Map<String,Object>map = new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtconcernService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 用户是否关注团体
	 */
	@RequestMapping(value="/vtctest")
	@ResponseBody
	public Object vtctest(){
		Map<String,Object>map = new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtconcernService.app_zzyTest(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 发表团体话题
	 */
	@RequestMapping(value="/vttadd")
	@ResponseBody
	public Object vttadd(){
		Map<String,Object>map = new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtthemeService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体话题列表
	 */
	@RequestMapping(value="/vttlist")
	@ResponseBody
	public Object vttlist(){
		Map<String,Object>map = new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtthemeService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体话题评论发表
	 */
	@RequestMapping(value="/vtnadd")
	@ResponseBody
	public Object vtnadd(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtnewService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 团体话题评论列表
	 */
	@RequestMapping(value="/vtnlist")
	@ResponseBody
	public Object vtnlist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vtnewService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动发布vaadd
	 */
	@RequestMapping(value="/vaadd")
	@ResponseBody
	public Object vaadd(HttpServletRequest request){
		Map<String,Object>map =new HashMap<>();
		Map<Integer,String>fm=new HashMap<Integer,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//form-data无法通过框架中方法遍历获取参数
			pd.put("topic",request.getParameter("topic"));
			pd.put("city",request.getParameter("city"));
			pd.put("address",request.getParameter("address"));
			pd.put("content",request.getParameter("content"));
			pd.put("schedule",request.getParameter("schedule"));
			pd.put("userid",request.getParameter("userid"));
			pd.put("vtid",request.getParameter("vtid"));	
			pd.put("limit",request.getParameter("limit"));	
			pd.put("stime",request.getParameter("stime"));
			pd.put("etime",request.getParameter("etime"));	
			fm=toolService.zzyUploadImg(request);
			map=vactivityService.app_zzyAdd(pd,fm);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动列表
	 */
	@RequestMapping(value="/valist")
	@ResponseBody
	public Object valist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if(pd.containsKey("userid")&&pd.getString("userid")!="")
				map=vactivityService.app_zzyList_byUserId(pd.getString("userid"));
			else
				map=vactivityService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动报名列表
	 */
	@RequestMapping(value="/vaelist")
	@ResponseBody
	public Object vaelist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vaenrollService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
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
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vaenrollService.app_zzyUpdate(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动评论发表
	 */
	@RequestMapping(value="/vanadd")
	@ResponseBody
	public Object vanadd(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vanewService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动评论列表
	 */
	@RequestMapping(value="/vanlist")
	@ResponseBody
	public Object vanlist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vanewService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动点赞
	 */
	@RequestMapping(value="/vapadd")
	@ResponseBody
	public Object vapadd(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vapraiseService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动图片上传
	 */
	@RequestMapping(value="/vaiadd")
	@ResponseBody
	public Object vaiadd(HttpServletRequest request){
		Map<String,Object>map =new HashMap<>();
		Map<Integer,String>fm=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//form-data无法通过框架中方法遍历获取参数
			pd.put("vaid",request.getParameter("vaid"));
			fm=toolService.zzyUploadImg(request);
			map=vaimgService.app_zzyAdd(pd,fm);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 活动图片列表
	 */
	@RequestMapping(value="/vailist")
	@ResponseBody
	public Object vailist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vaimgService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 公益捐赠发布
	 */
	@RequestMapping(value="/vdadd")
	@ResponseBody
	public Object vdadd(HttpServletRequest request){
		Map<String,Object>map =new HashMap<>();
		Map<Integer,String>fm=new HashMap<Integer,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("title",request.getParameter("title"));
			pd.put("target",request.getParameter("target"));
			pd.put("content",request.getParameter("content"));
			pd.put("city",request.getParameter("city"));
			pd.put("userid",request.getParameter("userid"));
			fm=toolService.zzyUploadImg(request);
			map=vdonationService.app_zzyAdd(pd,fm);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 公益捐赠列表
	 */
	@RequestMapping(value="/vdlist")
	@ResponseBody
	public Object vdlist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if(pd.containsKey("userid")&&pd.getString("userid")!="")
				map=vdonationService.app_zzyList_byUserId(pd.getString("userid"));
			else 
				map=vdonationService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 公益捐赠点赞
	 */
	@RequestMapping(value="/vdpadd")
	@ResponseBody
	public Object vdpadd(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vdpraiseService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 养老院列表
	 */
	@RequestMapping(value="/gmlist")
	@ResponseBody
	public Object gmlist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=gmService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 养老院评论列表
	 */
	@RequestMapping(value="/gmnlist")
	@ResponseBody
	public Object gmnlist(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=gmnewService.app_zzyList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 养老院评论发表
	 */
	@RequestMapping(value="/gmnadd")
	@ResponseBody
	public Object gmnadd(){
		Map<String,Object>map =new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=gmnewService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 养老院点赞
	 */
	@RequestMapping(value="/gmpadd")
	@ResponseBody
	public Object gmpdd(){
		Map<String,Object>map=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=gmpraiseService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 健康管家记录添加
	 */
	@RequestMapping(value="/gmhadd")
	@ResponseBody
	public Object gmhadd(){
		Map<String,Object>map=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=erhealthService.app_zzyAddNoGM(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 健康管家记录历史
	 */
	@RequestMapping(value="/gmhlist")
	@ResponseBody
	public Object gmhlist(){
		Map<String,Object>map=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=erhealthService.app_zzyListNoGM(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取评测列表
	 */
	@RequestMapping(value="/gmailist")
	@ResponseBody
	public Object gmailist(){
		Map<String,Object>map=new HashMap<>();
		try {
			map=gmaitemService.app_zzyList(Const2.ZZY2_GM_ID);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 评测结果上传
	 */
	@RequestMapping(value="/gmaradd")
	@ResponseBody
	public Object gmaradd(){
		Map<String,Object>map=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=gmaresultService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 意见反馈
	 */
	@RequestMapping(value="/vfadd")
	@ResponseBody
	public Object vfadd(){
		Map<String,Object>map=new HashMap<>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=vfeedbackService.app_zzyAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			map.put("result","00");
		}
		return AppUtil.returnObject(new PageData(), map);
	}
}
