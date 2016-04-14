package com.team.controller.retirement.elder;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.team.controller.base.BaseController;
import com.team.controller.system.session.SessionController;
import com.team.entity.Page;
import com.team.entity.system.User;
import com.team.service.retirement.elder.ElderManager;
import com.team.service.retirement.gm.GMManager;
import com.team.service.retirement.gmberth.GMBerthManager;
import com.team.util.AppUtil;
import com.team.util.Const;
import com.team.util.DateUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：老人信息
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/elder")
public class ElderController extends BaseController {
	
	String menuUrl = "elder/list.do"; //菜单地址(权限用)
	@Resource(name="elderService")
	private ElderManager elderService;
	@Resource(name="gmService")
	private GMManager gmService;
	@Resource(name="gmberthService")
	private GMBerthManager gmberthService;
	@Resource(name="sessionController")
	private SessionController sessionController;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Elder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ELDER_ID", this.get32UUID());	//主键
		//pd.put("E_LDATE", Tools.date2Str(new Date()));	//农历生日
		
		String lunar=DateUtil.zzyGetLunarFromSolar(pd.getString("E_SDATE"));//农历生日
		pd.put("E_LDATE",lunar);
		String day=(String) pd.get("E_SDATE");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(day);
		int age=DateUtil.zzyGetAgeByBirthday(date);//年纪
		pd.put("E_AGE",age);
		pd.put("E_CTIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("E_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		elderService.save(pd);
		
		
		
		
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Elder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		elderService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Elder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//System.out.println("zzy:"+pd.toString());
		if(pd.get("E_SDATE")!=""){
			String lunar=DateUtil.zzyGetLunarFromSolar(pd.getString("E_SDATE"));//农历生日
			pd.put("E_LDATE",lunar);
			String day=(String) pd.get("E_SDATE");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=sdf.parse(day);
			int age=DateUtil.zzyGetAgeByBirthday(date);//年纪
			pd.put("E_AGE",age);
		}
		elderService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");

		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Elder");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		HttpSession zzyHs=request.getSession();
		//Session取搜索条件
		String term=(String) zzyHs.getAttribute("ZZY_TERM_ELDER");
		if(term!=null&&term!=""){
			Gson gson=new Gson();
			PageData tpd=gson.fromJson(term,PageData.class);
			pd.putAll(tpd);
		}

		//Session取值
	
		if(pd.get("E_GM_ID")!=""&&pd.get("E_GM_ID")!=null){//重新赋值
			zzyHs.setAttribute("ZZY_GMID",pd.get("E_GM_ID"));
		}else{
			pd.put("E_GM_ID",zzyHs.getAttribute("ZZY_GMID"));
		}
		
		page.setPd(pd);
		
		List<PageData>	varList = elderService.list(page);	//列出Elder列表
		mv.setViewName("retirement/elder/elder_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		
		
		mv.addObject("GM_ID",pd.get("E_GM_ID"));
		mv.addObject("GM_NAME",gmService.zzyFindNameById((String) pd.get("E_GM_ID")));
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("retirement/elder/elder_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		
		/**
		 * 
		 */
		//养老院编号和名称
		HttpSession zzyHs=request.getSession();
		String gmid=(String) zzyHs.getAttribute("ZZY_GMID");
		mv.addObject("GM_ID",gmid);
		mv.addObject("GM_NAME",gmService.zzyFindNameById(gmid));
		//创建用户
		Session session = Jurisdiction.getSession();
		User user=(User)session.getAttribute(Const.SESSION_USER);
		mv.addObject("user",user);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = elderService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/elder/elder_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		
		/**
		 * zzy
		 */
		//养老院编号和名称
		HttpSession zzyHs=request.getSession();
		String gmid=(String) zzyHs.getAttribute("ZZY_GMID");
		mv.addObject("GM_ID",gmid);
		mv.addObject("GM_NAME",gmService.zzyFindNameById(gmid));
		//创建用户
		Session session = Jurisdiction.getSession();
		User user=(User)session.getAttribute(Const.SESSION_USER);
		mv.addObject("user",user);
		return mv;
	}	
	/**
	 * 去搜索页面
	 */
	@RequestMapping(value="/goSearch")
	public ModelAndView goSearch(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("retirement/elder/elder_search");
		mv.addObject("msg", "list");
		mv.addObject("pd", pd);
		HttpSession zzyHs=request.getSession();
		//Session取搜索条件
		String term=(String) zzyHs.getAttribute("ZZY_TERM_ELDER");
		if(term!=null&&term!=""){
			Gson gson=new Gson();
			PageData tpd=gson.fromJson(term,PageData.class);
			pd.putAll(tpd);
		}
		//养老院编号及名称
		String gmid=(String) zzyHs.getAttribute("ZZY_GMID");
		mv.addObject("GM_ID",gmid);
		mv.addObject("GM_NAME",gmService.zzyFindNameById(gmid));
		//养老院列表
		List<PageData>gmlist=gmService.listAll(null,request.getSession());
		mv.addObject("GM_LIST",gmlist);
		//创建用户
		Session session = Jurisdiction.getSession();
		User user=(User)session.getAttribute(Const.SESSION_USER);
		mv.addObject("user",user);
		return mv;
	}
	/**
	 *搜索信息
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="/zzySearch")
	public ModelAndView zzySearch(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData zzyPd=new PageData();
		pd=this.getPageData();
		//搜索信息
		if(pd.containsKey("TERM_GM_ID")&&pd.getString("TERM_GM_ID")!=""){
			zzyPd.put("TERM_GM_ID",pd.getString("TERM_GM_ID"));
			HttpSession zzyHs=request.getSession();
			zzyHs.setAttribute("ZZY_GMID",pd.get("TERM_GM_ID"));
		}
		if(pd.containsKey("TERM_GENDER")&&pd.getString("TERM_GENDER")!=""){
			zzyPd.put("TERM_GENDER",pd.getString("TERM_GENDER"));
		}
		if(pd.containsKey("TERM_INTAKE")&&pd.getString("TERM_INTAKE")!=""){
			zzyPd.put("TERM_INTAKE",pd.getString("TERM_INTAKE"));
		}
		if(pd.containsKey("TERM_IDATE_START")&&pd.getString("TERM_IDATE_START")!=""){
			zzyPd.put("TERM_IDATE_START",pd.getString("TERM_IDATE_START"));
		}
		if(pd.containsKey("TERM_IDATE_END")&&pd.getString("TERM_IDATE_END")!=""){
			zzyPd.put("TERM_IDATE_END",pd.getString("TERM_IDATE_END"));
		}
		if(pd.containsKey("TERM_SDATE_START")&&pd.getString("TERM_SDATE_START")!=""){
			zzyPd.put("TERM_SDATE_START",pd.getString("TERM_SDATE_START"));
		}
		if(pd.containsKey("TERM_SDATE_END")&&pd.getString("TERM_SDATE_END")!=""){
			zzyPd.put("TERM_SDATE_END",pd.getString("TERM_SDATE_END"));
		}
		if(pd.containsKey("TERM_LDATE_START")&&pd.getString("TERM_LDATE_START")!=""){
			zzyPd.put("TERM_LDATE_START",pd.getString("TERM_LDATE_START"));
		}
		if(pd.containsKey("TERM_LDATE_END")&&pd.getString("TERM_LDATE_END")!=""){
			zzyPd.put("TERM_LDATE_END",pd.getString("TERM_LDATE_END"));
		}
		if(pd.containsKey("TERM_AGE_START")&&pd.getString("TERM_AGE_START")!=""){
			zzyPd.put("TERM_AGE_START",pd.getString("TERM_AGE_START"));
		}
		if(pd.containsKey("TERM_AGE_END")&&pd.getString("TERM_AGE_END")!=""){
			zzyPd.put("TERM_AGE_END",pd.getString("TERM_AGE_END"));
		}
		Gson gson=new Gson();
		HttpSession session=request.getSession();
		session.setAttribute("ZZY_TERM_ELDER",gson.toJson(zzyPd));
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * zzy
	 * 根据老人姓名检测老人信息是否存在
	 */
	@RequestMapping(value="/zzyCheckByName")
	@ResponseBody
	public String zzyCheckByName()throws Exception{
		//System.out.println("zzy:come to check");
		PageData pd=new PageData();
		pd=this.getPageData();
		String elder_id=elderService.zzyCheckByName(pd);
		if(elder_id!=""&&elder_id!=null){//老人信息存在，判断老人是否有其他床位的入住信息
			String gm_id=pd.getString("E_GM_ID");
			PageData tpd=new PageData();
			tpd.put("GMB_E_ID",elder_id);
			tpd.put("GMB_GM_ID",gm_id);
			String gmb_id=gmberthService.zzyCheckByElderName(tpd);
			if(gmb_id!=""&&gmb_id!=null){//已入住
				return "2";
			}else {
				return elder_id;
			}
		}else return "1";
		
	}
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Elder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			elderService.deleteAll(ArrayDATA_IDS);
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
	public ModelAndView exportExcel(Page page,HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Elder到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		
		titles.add("老人姓名");	//1
		titles.add("老人性别");	//2
		titles.add("入住状态");	//3
		titles.add("身份证");	//4
		titles.add("入住时间");	//5
		titles.add("阳历生日");	//6
		titles.add("农历生日");	//7
		titles.add("老人年龄");	//8
		titles.add("老人户籍");	//9
		titles.add("老人住址");	//10
		titles.add("老人联系电话");	//11
		titles.add("监护人姓名");	//12
		titles.add("监护人关系");	//13
		titles.add("监护人电话");	//14
		titles.add("养老院");	//37
		dataMap.put("titles", titles);
		//List<PageData> varOList = elderService.listAll(pd);
		HttpSession zzyHs=request.getSession();
		//Session取搜索条件
		String term=(String) zzyHs.getAttribute("ZZY_TERM_ELDER");
		if(term!=null&&term!=""){
			Gson gson=new Gson();
			PageData tpd=gson.fromJson(term,PageData.class);
			pd.putAll(tpd);
		}
		//Session取值
			pd.put("E_GM_ID",zzyHs.getAttribute("ZZY_GMID"));
		page.setPd(pd);
		List<PageData>	varOList;
		if(!pd.containsKey("all"))
			varOList=elderService.list(page);	//列出Elder列表
		else 
			varOList=elderService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			String gmid=varOList.get(i).getString("E_GM_ID");
			String gmname=gmService.zzyFindNameById(gmid);
			vpd.put("var1", varOList.get(i).getString("E_NAME"));	//1
			vpd.put("var2", varOList.get(i).getString("E_GENDER"));	//2
			String intake=varOList.get(i).get("E_INTAKE").toString();
			String y_intake="";
			if(intake.equals("1"))y_intake="入住";
			else if(intake.equals("2"))y_intake="退住";
			if(intake.equals("3"))y_intake="试住";
			vpd.put("var3", y_intake);	//3
			vpd.put("var4", varOList.get(i).getString("E_IDENTITY"));	//4
			vpd.put("var5", varOList.get(i).getString("E_IDATE"));	//5
			vpd.put("var6", varOList.get(i).getString("E_SDATE"));	//6
			vpd.put("var7", varOList.get(i).getString("E_LDATE"));	//7
			vpd.put("var8", varOList.get(i).get("E_AGE").toString());	//8
			vpd.put("var9", varOList.get(i).getString("E_CENSUS"));	//9
			vpd.put("var10", varOList.get(i).getString("E_ADDRESS"));	//10
			vpd.put("var11", varOList.get(i).getString("E_TEL"));	//11
			vpd.put("var12", varOList.get(i).getString("E_G_NAME"));	//12
			vpd.put("var13", varOList.get(i).getString("E_G_REL"));	//13
			vpd.put("var14", varOList.get(i).getString("E_G_TEL"));	//14
			vpd.put("var15",gmname);	//37
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
	 
	@RequestMapping(value="/resetSession")
	@ResponseBody
	public Object resetSession(HttpServletRequest request,Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"重置Session");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		sessionController.resetSession(request.getSession(),"retirement");
		return list(page, request);
	}
}
