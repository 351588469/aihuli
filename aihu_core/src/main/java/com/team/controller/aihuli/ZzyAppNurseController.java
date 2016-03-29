package com.team.controller.aihuli;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.controller.base.BaseController;
import com.team.service.retirement.elder.ElderManager;
import com.team.service.retirement.gmaitem.GMAitemManager;
import com.team.service.retirement.gmberth.GMBerthManager;
import com.team.service.zzy.info.InfoManager;
import com.team.service.zzy.login.LoginManager;
import com.team.service.zzy.plan.PlanManager;
import com.team.service.zzy.record.RecordManager;
import com.team.service.zzy.tool.ToolManager;
import com.team.util.AppUtil;
import com.team.util.PageData;

@Controller
@RequestMapping(value="/appzzys")
public class ZzyAppNurseController extends BaseController{
	@Resource(name="elderService")
	private ElderManager elderService;
	@Resource(name="gmberthService")
	private GMBerthManager gmberthService;
	@Resource(name="loginService")
	private LoginManager loginService;
	@Resource(name="infoService")
	private InfoManager infoService;
	@Resource(name="planService")
	private PlanManager planService;
	@Resource(name="recordService")
	private RecordManager recordService;
	@Resource(name="toolService")
	private ToolManager toolService;
	
	@Resource(name="gmaitemService")
	private GMAitemManager gmaitemService;
	/**
	 * login elist dmsg  pall einfo nlist hlist padd radd rpast beval bpast ailist aradd
	 */
	/**
	 * 登录
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public Object login(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			 map=loginService.zzyLogin(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 护士获取我的老人（首页）
	 * @throws Exception 
	 */
	@RequestMapping(value="/elist")
	@ResponseBody
	public Object elist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			 map=infoService.zzyElist(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 消息
	 */
	@RequestMapping(value="/dmsg")
	@ResponseBody
	public Object dmsg(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			 map=infoService.zzyDMsg(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取老人护理计划
	 */
	@RequestMapping(value="/pall")
	@ResponseBody
	public Object ppast(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			 map=planService.zzyPlanList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 老人信息
	 */
	@RequestMapping(value="/einfo")
	@ResponseBody
	public Object einfo(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			 map=infoService.zzyEinfo(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取项目列表
	 */
	@RequestMapping(value="/nlist")
	@ResponseBody
	public Object nlist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			 map=infoService.zzyGNlist(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取健康类别列表
	 */
	@RequestMapping(value="/hlist")
	@ResponseBody
	public Object hlist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			 map=infoService.zzyGHlist(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 护理/用药计划
	 */
	@RequestMapping(value="/padd")
	@ResponseBody
	public Object padd(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=planService.zzyNewPlan(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 添加记录
	 */
	@RequestMapping(value="/radd")
	@ResponseBody
	public Object radd(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if(pd.containsKey("json"))map=recordService.zzyNewRecordMult(pd);
			else map=recordService.zzyNewRecord(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 历史记录
	 */
	@RequestMapping(value="/rpast")
	@ResponseBody
	public Object rpast(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=recordService.zzyPastRecord(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 房间评测，含有图片上传
	 */
	@RequestMapping(value="/beval")
	@ResponseBody
	public Object beval(HttpServletRequest request){
		Map<String,Object>map = null;
		Map<Integer,String>fm=new HashMap<>();
		PageData pd = new PageData();
		try {
			pd.put("gmb_id",request.getParameter("gmb_id"));
			pd.put("gmbr_environment",request.getParameter("gmbr_environment"));
			pd.put("gmbr_sanitation",request.getParameter("gmbr_sanitation"));
			pd.put("gmbr_safe",request.getParameter("gmbr_safe"));
			pd.put("gmbr_equipment",request.getParameter("gmbr_equipment"));
			pd.put("gmbr_cphoto",request.getParameter("gmbr_cphoto"));
			pd.put("gmbr_desc",request.getParameter("gmbr_desc"));
			pd.put("gmbr_gmu_id",request.getParameter("gmbr_gmu_id"));
			String cphoto_str=pd.getString("gmbr_cphoto");
			Integer cphoto=Integer.parseInt(cphoto_str);
			if(cphoto>0)fm=toolService.zzyUploadImg(request);
			map=infoService.zzyBEval(pd,fm);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(),map);
	}
	/**
	 *bpast房价评测历史
	 */
	@RequestMapping(value="/bpast")
	@ResponseBody
	public Object bpast(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=infoService.zzyBPast(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 评测题目列表
	 */
	@RequestMapping(value="/ailist")
	@ResponseBody
	public Object ailist(){
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=infoService.zzyAList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 评测结果新增
	 * @throws Exception 
	 */
	@RequestMapping(value="/aradd")
	@ResponseBody
	public Object  aradd() {
		Map<String,Object>map = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			map=infoService.zzyARAdd(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
}
