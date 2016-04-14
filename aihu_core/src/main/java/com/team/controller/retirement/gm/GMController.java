package com.team.controller.retirement.gm;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.team.controller.base.BaseController;
import com.team.entity.Page;
import com.team.entity.system.User;
import com.team.service.retirement.gm.GMManager;
import com.team.service.system.user.UserManager;
import com.team.service.system.user.impl.UserService;
import com.team.util.AppUtil;
import com.team.util.Const;
import com.team.util.DateUtil;
import com.team.util.FileUpload;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.PathUtil;
import com.team.util.Tools;

/** 
 * 说明：养老院信息
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/gm")
public class GMController extends BaseController {
	
	String menuUrl = "gm/list.do"; //菜单地址(权限用)
	@Resource(name="gmService")
	private GMManager gmService;
	@Resource(name="userService")
	private UserManager userService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpSession session) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增GM");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		System.out.println("zzy:"+pd.toString());
		pd.put("GM_ID", this.get32UUID());	//主键
		pd.put("GM_CTIME", Tools.date2Str(new Date()));	//创建时间
		//新增数据时无最后修改时间
		//pd.put("GM_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		//pagedata中存在但无传值
		if(pd.get("GM_BERTH_COUNT").equals(""))pd.put("GM_BERTH_COUNT", null);
		//pagedata中不存在但需要
		if(!pd.containsKey("GM_CKSTATUS"))pd.put("GM_CKSTATUS",3);//3:待审核
		gmService.save(pd,session);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除GM");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		gmService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改GM");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("GM_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		
		gmService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpSession session) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表GM");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)

	
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords);
		}
		String term=pd.getString("nav-search-term");
		if(null != term && !"".equals(term)){
			if(term.equals("1")){//审核状态
				pd.put("term",1);
				pd.put("KEY_GM_CKSTATUS",keywords);
			}
		}
		page.setPd(pd);
		List<PageData>	varList = gmService.list(page,session);	//列出GM列表
		mv.setViewName("retirement/gm/gm_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(HttpSession session)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		/**
		 * role 2 养老院管理人员 一个人员只能添一个
		 */
		Integer role=(Integer) session.getAttribute("SYS_ZZY_ROLE");
		if(role==2){
			PageData tpd=new PageData();
			tpd.put("USERNAME",Jurisdiction.getUsername());
			PageData user=userService.findByUsername(tpd);
			String gmid=gmService.zzyFindNameByAUId(user.getString("USER_ID"));
			if(gmid!=null)mv.setViewName("retirement/gm/gm_noedit");
			else mv.setViewName("retirement/gm/gm_edit");
		}else {
			mv.setViewName("retirement/gm/gm_edit");
		}
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = gmService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/gm/gm_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除GM");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			gmService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(HttpSession session) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出GM到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("名称");	//1
		titles.add("头像");	//2
		titles.add("地址");	//3
		titles.add("联系电话");	//4
		titles.add("联系人");	//5
		titles.add("性质");	//6
		titles.add("营业面积");	//7
		titles.add("床位数");	//8
		titles.add("简介");	//9
		titles.add("收住对象");	//10
		titles.add("收费标准");	//11
		titles.add("服务内容");	//12
		titles.add("营业执照图片");	//13
		titles.add("法人身份证正面");	//14
		titles.add("法人身份证反面");	//15
		titles.add("审核状态(1审核通过2审核未通过3待审核)");	//16
		titles.add("审核状态描述");	//17
		titles.add("创建用户id(app_user)");	//18
		titles.add("点赞数");//19
		titles.add("创建时间");	//20
		titles.add("最后修改时间");	//21
		dataMap.put("titles", titles);
		List<PageData> varOList = gmService.listAll(pd,session);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("GM_NAME"));	//1
			vpd.put("var2", varOList.get(i).getString("GM_AVATER"));	//2
			vpd.put("var3", varOList.get(i).getString("GM_ADDRESS"));	//3
			vpd.put("var4", varOList.get(i).getString("GM_TEL"));	//4
			vpd.put("var5", varOList.get(i).getString("GM_CONCAT"));	//5
			vpd.put("var6", varOList.get(i).getString("GM_NATURE"));	//6
			vpd.put("var7", varOList.get(i).getString("GM_SQUARE"));	//7
			Integer berth_count=(Integer) varOList.get(i).get("GM_BERTH_COUNT");
			if(berth_count!=null)
				vpd.put("var8",berth_count.toString());	//8
			else 
				vpd.put("var8","0");	//8
			vpd.put("var9", varOList.get(i).getString("GM_DESCRIPTION"));	//9
			vpd.put("var10", varOList.get(i).getString("GM_RECEIVE"));	//10
			vpd.put("var11", varOList.get(i).getString("GM_FEEDESC "));	//11
			vpd.put("var12", varOList.get(i).getString("GM_SERVEINFO"));	//12
			vpd.put("var13", varOList.get(i).getString("GM_LICENCE_PHOTO"));	//13
			vpd.put("var14", varOList.get(i).getString("GM_LEGALPERSON_PHOTOA"));	//14
			vpd.put("var15", varOList.get(i).getString("GM_LEGALPERSON_PHOTOB"));	//15
			vpd.put("var16", varOList.get(i).get("GM_CKSTATUS").toString());	//16
			vpd.put("var17", varOList.get(i).getString("GM_CKDESC"));	//17
			vpd.put("var18", varOList.get(i).getString("GM_AU_ID"));	//18
			vpd.put("var19", varOList.get(i).get("GM_PRAISE").toString());	//19
			vpd.put("var20", varOList.get(i).getString("GM_CTIME"));	//20
			vpd.put("var21", varOList.get(i).getString("GM_UTIME"));	//21
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
