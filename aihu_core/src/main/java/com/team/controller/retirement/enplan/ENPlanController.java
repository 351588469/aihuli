package com.team.controller.retirement.enplan;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.team.controller.base.BaseController;
import com.team.entity.Page;
import com.team.service.retirement.enplan.ENPlanManager;
import com.team.util.AppUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：护理计划
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/enplan")
public class ENPlanController extends BaseController {
	
	String menuUrl = "enplan/list.do"; //菜单地址(权限用)
	@Resource(name="enplanService")
	private ENPlanManager enplanService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增ENPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ENPLAN_ID", this.get32UUID());	//主键
		pd.put("ENP_GMN_NAME", "");	//护理项目名称
		pd.put("ENP_GMN_CONTENT", "");	//护理项目描述
		pd.put("ENP_E_NAME", "");	//护理老人姓名
		pd.put("ENP_CTIME", Tools.date2Str(new Date()));	//记录生成时间
		pd.put("ENP_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		enplanService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除ENPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		enplanService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改ENPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		enplanService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表ENPlan");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = enplanService.list(page);	//列出ENPlan列表
		for(int i=0;i<varList.size();i++){
			
		}
		mv.setViewName("retirement/enplan/enplan_list");
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
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("retirement/enplan/enplan_edit");
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
		pd = enplanService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/enplan/enplan_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除ENPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			enplanService.deleteAll(ArrayDATA_IDS);
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
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出ENPlan到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("护理项目编号");	//1
		titles.add("养老院编号");	//2
		titles.add("计划申请职工编号");	//3
		titles.add("护理项目名称");	//4
		titles.add("护理项目描述");	//5
		titles.add("护理老人编号");	//6
		titles.add("护理老人姓名");	//7
		titles.add("单位时间需护理次数");	//8
		titles.add("时间单位(1日2周3月)");	//9
		titles.add("计划开始日期");	//10
		titles.add("计划结束时间");	//11
		titles.add("计划状态");	//12
		titles.add("记录生成时间");	//13
		titles.add("最后修改时间");	//14
		dataMap.put("titles", titles);
		List<PageData> varOList = enplanService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ENP_GMN_ID"));	//1
			vpd.put("var2", varOList.get(i).getString("ENP_GM_ID"));	//2
			vpd.put("var3", varOList.get(i).getString("ENP_GMU_ID"));	//3
			vpd.put("var4", varOList.get(i).getString("ENP_GMN_NAME"));	//4
			vpd.put("var5", varOList.get(i).getString("ENP_GMN_CONTENT"));	//5
			vpd.put("var6", varOList.get(i).getString("ENP_E_ID"));	//6
			vpd.put("var7", varOList.get(i).getString("ENP_E_NAME"));	//7
			vpd.put("var8", varOList.get(i).get("ENP_NEED").toString());	//8
			vpd.put("var9", varOList.get(i).get("ENP_UNIT").toString());	//9
			vpd.put("var10", varOList.get(i).getString("ENP_SDATE"));	//10
			vpd.put("var11", varOList.get(i).getString("ENP_EDATE"));	//11
			vpd.put("var12", varOList.get(i).get("ENP_STATUS").toString());	//12
			vpd.put("var13", varOList.get(i).getString("ENP_CTIME"));	//13
			vpd.put("var14", varOList.get(i).getString("ENP_UTIME"));	//14
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
