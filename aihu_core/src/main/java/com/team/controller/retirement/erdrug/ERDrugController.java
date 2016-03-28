package com.team.controller.retirement.erdrug;

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
import com.team.service.retirement.erdrug.ERDrugManager;
import com.team.util.AppUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：用药记录
 * 创建人：jaychum
 * 创建时间：2016-03-12
 */
@Controller
@RequestMapping(value="/erdrug")
public class ERDrugController extends BaseController {
	
	String menuUrl = "erdrug/list.do"; //菜单地址(权限用)
	@Resource(name="erdrugService")
	private ERDrugManager erdrugService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增ERDrug");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ERDRUG_ID", this.get32UUID());	//主键
		pd.put("ERD_EDP_E_ID", "");	//服药老人编号
		pd.put("ERD_EDP_GMU_ID", "");	//计划申请职工编号
		pd.put("ERD_EDP_GM_ID", "");	//计划所属养老院编号
		pd.put("ERD_EDP_SDATE", Tools.date2Str(new Date()));	//用药开始日期
		pd.put("ERD_EDP_EDATE", Tools.date2Str(new Date()));	//用药截止日期
		pd.put("ERD_EDP_MHOUR", "");	//用药时间点
		pd.put("ERD_EDP_MDESC", "");	//用药时间说明
		pd.put("ERD_EDP_MNAME", "");	//药品名称
		pd.put("ERD_EDP_MDOSAGE", "");	//药品剂量
		pd.put("ERD_EDP_MCOUNT", "");	//药品数量
		pd.put("ERD_CTIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("ERD_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		erdrugService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除ERDrug");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		erdrugService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改ERDrug");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		erdrugService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表ERDrug");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = erdrugService.list(page);	//列出ERDrug列表
		mv.setViewName("retirement/erdrug/erdrug_list");
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
		mv.setViewName("retirement/erdrug/erdrug_edit");
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
		pd = erdrugService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/erdrug/erdrug_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除ERDrug");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			erdrugService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出ERDrug到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("记录职工编号");	//1
		titles.add("计划编号");	//2
		titles.add("服药老人编号");	//3
		titles.add("计划申请职工编号");	//4
		titles.add("计划所属养老院编号");	//5
		titles.add("用药开始日期");	//6
		titles.add("用药截止日期");	//7
		titles.add("用药时间点");	//8
		titles.add("用药时间说明");	//9
		titles.add("药品名称");	//10
		titles.add("药品剂量");	//11
		titles.add("药品数量");	//12
		titles.add("创建时间");	//13
		titles.add("最后修改时间");	//14
		titles.add("客户端记录时间");	//15
		titles.add("记录值");	//16
		titles.add("记录说明");	//17
		dataMap.put("titles", titles);
		List<PageData> varOList = erdrugService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ERD_GMU_ID"));	//1
			vpd.put("var2", varOList.get(i).getString("ERD_EDP_ID"));	//2
			vpd.put("var3", varOList.get(i).getString("ERD_EDP_E_ID"));	//3
			vpd.put("var4", varOList.get(i).getString("ERD_EDP_GMU_ID"));	//4
			vpd.put("var5", varOList.get(i).getString("ERD_EDP_GM_ID"));	//5
			vpd.put("var6", varOList.get(i).getString("ERD_EDP_SDATE"));	//6
			vpd.put("var7", varOList.get(i).getString("ERD_EDP_EDATE"));	//7
			vpd.put("var8", varOList.get(i).getString("ERD_EDP_MHOUR"));	//8
			vpd.put("var9", varOList.get(i).getString("ERD_EDP_MDESC"));	//9
			vpd.put("var10", varOList.get(i).getString("ERD_EDP_MNAME"));	//10
			vpd.put("var11", varOList.get(i).getString("ERD_EDP_MDOSAGE"));	//11
			vpd.put("var12", varOList.get(i).get("ERD_EDP_MCOUNT").toString());	//12
			vpd.put("var13", varOList.get(i).getString("ERD_CTIME"));	//13
			vpd.put("var14", varOList.get(i).getString("ERD_UTIME"));	//14
			vpd.put("var15", varOList.get(i).getString("ERD_GTIME"));	//15
			vpd.put("var16", varOList.get(i).getString("ERD_VALUE"));	//16
			vpd.put("var17", varOList.get(i).getString("ERD_DESC"));	//17
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
