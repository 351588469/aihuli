package com.team.controller.retirement.gmaitem;

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
import com.team.service.retirement.gm.impl.GMService;
import com.team.service.retirement.gmaitem.GMAitemManager;
import com.team.service.retirement.gmatype.GMATypeManager;
import com.team.util.AppUtil;
import com.team.util.Const;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：评测题目
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/gmaitem")
public class GMAitemController extends BaseController {
	
	String menuUrl = "gmaitem/list.do"; //菜单地址(权限用)
	@Resource(name="gmaitemService")
	private GMAitemManager gmaitemService;
	@Resource(name="gmatypeService")
	private GMATypeManager gmatypeService;
	@Resource(name="gmService")
	private GMService gmService;
	@Resource(name="sessionController")
	private SessionController sessionController;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增GMAitem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("GMAITEM_ID", this.get32UUID());	//主键
		pd.put("GMAI_CTIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("GMAI_UTIME", Tools.date2Str(new Date()));	//修改时间
		pd.put("GMAI_GMAT_NAME", "");	//题目类别名称
		if(pd.containsKey("GMAI_GMAT_ID")&&pd.get("GMAI_GMAT_ID")!=""){
			String typeid=pd.getString("GMAI_GMAT_ID");
			PageData tpd=gmatypeService.zzyFindById(typeid);
			pd.put("GMAI_GMAT_NAME",tpd.getString("GMAT_NAME"));
			pd.put("GMAI_GMAT_NUM",tpd.get("GMAT_NUM"));
		}
		gmaitemService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除GMAitem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		gmaitemService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改GMAitem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//gmaitemService.edit(pd);
		gmaitemService.zzyEdit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表GMAitem");
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
		String term=(String) zzyHs.getAttribute("ZZY_TERM_GMAI");
		if(term!=null&&term!=""){
			Gson gson=new Gson();
			PageData tpd=gson.fromJson(term,PageData.class);
			pd.putAll(tpd);
		}
		//Session取值
		if(pd.get("GMAI_GM_ID")!=""&&pd.get("GMAI_GM_ID")!=null){//重新赋值
			zzyHs.setAttribute("ZZY_GMID",pd.get("GMAI_GM_ID"));
		}else{
			pd.put("GMAI_GM_ID",zzyHs.getAttribute("ZZY_GMID"));
		}
		if(pd.get("GMAI_GMAT_ID")!=""&&pd.get("GMAI_GMAT_ID")!=null){//重新赋值
			zzyHs.setAttribute("ZZY_GMATID",pd.get("GMAI_GMAT_ID"));
		}else{
			pd.put("GMAI_GMAT_ID",zzyHs.getAttribute("GMAI_GMAT_ID"));
		}
		page.setPd(pd);
		List<PageData>varList = new ArrayList<>();	//列出GMAitem列表
		/*if(pd.containsKey("GMATYPE_ID")){
			varList = gmaitemService.zzyListByType(pd.getString("GMATYPE_ID"));
			String gmatype_id=(String)pd.getString("GMATYPE_ID");
			PageData tpd=gmatypeService.zzyFindById(gmatype_id);
			String gmid=(String) tpd.get("GMAT_GM_ID");
			String gm_name=gmService.zzyFindNameById(gmid);
			pd.put("GMAI_GMAT_ID",gmatype_id);
			pd.put("GMAI_GMAT_NAME",tpd.get("GMAT_NAME"));
			pd.put("GMAI_GM_ID",gmid);
			pd.put("GMAI_GM_NAME",gm_name);
		}else{*/
		varList = gmaitemService.list(page);
		
		mv.setViewName("retirement/gmaitem/gmaitem_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		//System.out.println("zzy:pd"+pd.toString());
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//System.out.println("zzy:"+pd.toString());
		String gmatype_id=(String)pd.getString("GMATYPE_ID");
		PageData tpd=gmatypeService.zzyFindById(gmatype_id);
		String gmid="",gm_name="";
		if(tpd!=null){
			gmid=(String) tpd.get("GMAT_GM_ID");
			gm_name=gmService.zzyFindNameById(gmid);
			pd.put("GMAI_GMAT_ID",gmatype_id);
			pd.put("GMAI_GMAT_NAME",tpd.get("GMAT_NAME"));
			pd.put("GMAI_GM_ID",gmid);
			pd.put("GMAI_GM_NAME",gm_name);
		}
		mv.setViewName("retirement/gmaitem/gmaitem_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		//类别
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
		pd = gmaitemService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/gmaitem/gmaitem_edit");
		mv.addObject("msg", "edit");
		String gm_name=gmService.zzyFindNameById(pd.getString("GMAI_GM_ID"));
		pd.put("GMAI_GM_NAME",gm_name);
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除GMAitem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			gmaitemService.deleteAll(ArrayDATA_IDS);
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
	public ModelAndView exportExcel(HttpServletRequest request,Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出GMAitem到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("养老院");	//1
		titles.add("第几题");	//2
		titles.add("类别");	//3
		titles.add("最大最小分值");	//4
		titles.add("内容");	//5
		dataMap.put("titles", titles);
		
		HttpSession zzyHs=request.getSession();
		String term=(String) zzyHs.getAttribute("ZZY_TERM_GMAI");
		if(term!=null&&term!=""){
			Gson gson=new Gson();
			PageData tpd=gson.fromJson(term,PageData.class);
			pd.putAll(tpd);
		}
		//Session取值
			pd.put("GMAI_GM_ID",zzyHs.getAttribute("ZZY_GMID"));
			pd.put("GMAI_GMAT_ID",zzyHs.getAttribute("ZZY_GMATID"));
		List<PageData> varOList;
		if(!pd.containsKey("all"))
			varOList= gmaitemService.list(page);	//列出Elder列表
		else 
			varOList= gmaitemService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			String gmid=varOList.get(i).getString("GMAI_GM_ID");
			String gmname=gmService.zzyFindNameById(gmid);
			vpd.put("var1", gmname);//1
			vpd.put("var2", varOList.get(i).get("GMAI_NUMBER").toString());	//2
			vpd.put("var3", varOList.get(i).getString("GMAI_GMAT_NAME"));	//3
			vpd.put("var4", varOList.get(i).get("GMAI_SCORE").toString());	//4
			vpd.put("var5", varOList.get(i).getString("GMAI_CONTENT"));	//5
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
		return list(page,request);
	}
	/**
	 * 去搜索页面
	 */
	@RequestMapping(value="/goSearch")
	public ModelAndView goSearch(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("retirement/gmaitem/gmaitem_search");
		mv.addObject("msg", "list");
		mv.addObject("pd", pd);
		HttpSession zzyHs=request.getSession();
		//Session取搜索条件
		String term=(String) zzyHs.getAttribute("ZZY_TERM_GMAI");
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
		if(pd.containsKey("TERM_CONTENT")&&pd.getString("TERM_CONTENT")!=""){
			zzyPd.put("TERM_CONTENT",pd.getString("TERM_CONTENT"));
		}
		if(pd.containsKey("TERM_SCORE_START")&&pd.getString("TERM_SCORE_START")!=""){
			zzyPd.put("TERM_SCORE_START",pd.getString("TERM_SCORE_START"));
		}
		if(pd.containsKey("TERM_SCORE_END")&&pd.getString("TERM_SCORE_END")!=""){
			zzyPd.put("TERM_SCORE_END",pd.getString("TERM_SCORE_END"));
		}
		Gson gson=new Gson();
		HttpSession session=request.getSession();
		session.setAttribute("ZZY_TERM_GMAI",gson.toJson(zzyPd));
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
}
