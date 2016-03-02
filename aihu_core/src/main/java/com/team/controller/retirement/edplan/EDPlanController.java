package com.team.controller.retirement.edplan;

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
import com.team.util.AppUtil;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Jurisdiction;
import com.team.util.Tools;
import com.team.service.retirement.edplan.EDPlanManager;

/** 
 * 说明：用药计划
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/edplan")
public class EDPlanController extends BaseController {
	
	String menuUrl = "edplan/list.do"; //菜单地址(权限用)
	@Resource(name="edplanService")
	private EDPlanManager edplanService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增EDPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("EDPLAN_ID", this.get32UUID());	//主键
		pd.put("EDP_CTIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("EDP_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		edplanService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除EDPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		edplanService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改EDPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		edplanService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表EDPlan");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = edplanService.list(page);	//列出EDPlan列表
		mv.setViewName("retirement/edplan/edplan_list");
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
		mv.setViewName("retirement/edplan/edplan_edit");
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
		pd = edplanService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/edplan/edplan_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除EDPlan");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			edplanService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出EDPlan到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("老人编号");	//1
		titles.add("计划申请职工编号");	//2
		titles.add("计划所属养老院编号");	//3
		titles.add("用药开始日期");	//4
		titles.add("用药截止时间");	//5
		titles.add("用药时间点");	//6
		titles.add("用药时间说明");	//7
		titles.add("药品名称");	//8
		titles.add("药品剂量");	//9
		titles.add("药品数量");	//10
		titles.add("用药计划说明");	//11
		titles.add("创建时间");	//12
		titles.add("最后修改时间");	//13
		dataMap.put("titles", titles);
		List<PageData> varOList = edplanService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("EDP_E_ID"));	//1
			vpd.put("var2", varOList.get(i).getString("EDP_GMU_ID"));	//2
			vpd.put("var3", varOList.get(i).getString("EDP_GM_ID"));	//3
			vpd.put("var4", varOList.get(i).getString("EDP_SDATE"));	//4
			vpd.put("var5", varOList.get(i).getString("EDP_EDATE"));	//5
			vpd.put("var6", varOList.get(i).getString("EDP_MHOUR"));	//6
			vpd.put("var7", varOList.get(i).getString("EDP_MDESC"));	//7
			vpd.put("var8", varOList.get(i).getString("EDP_MNAME"));	//8
			vpd.put("var9", varOList.get(i).getString("EDP_MDOSAGE"));	//9
			vpd.put("var10", varOList.get(i).get("EDP_MCOUNT").toString());	//10
			vpd.put("var11", varOList.get(i).getString("EDP_DESC"));	//11
			vpd.put("var12", varOList.get(i).getString("EDP_CTIME"));	//12
			vpd.put("var13", varOList.get(i).getString("EDP_UTIME"));	//13
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
