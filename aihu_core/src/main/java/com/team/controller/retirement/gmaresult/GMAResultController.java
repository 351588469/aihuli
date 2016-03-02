package com.team.controller.retirement.gmaresult;

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
import com.team.service.retirement.gmaresult.GMAResultManager;
import com.team.util.AppUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：评测结果
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/gmaresult")
public class GMAResultController extends BaseController {
	
	String menuUrl = "gmaresult/list.do"; //菜单地址(权限用)
	@Resource(name="gmaresultService")
	private GMAResultManager gmaresultService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增GMAResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("GMARESULT_ID", this.get32UUID());	//主键
		pd.put("GMAR_GMAI_GM_ID", "");	//题目所属养老院编号
		pd.put("GMAR_GMAI_NUM", "");	//第几题
		pd.put("GMAR_GMAI_ITYPE", "");	//题目所属类别(int)
		pd.put("GMAR_GMAI_STYPE", "");	//题目所属类别(String)
		pd.put("GMAR_GMAI_SCORE", "");	//题目最大或最小分值
		pd.put("GMAR_GMAI_CONTENT", "");	//题目具体内容
		pd.put("GMAR_CTIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("GMAR_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		gmaresultService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除GMAResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		gmaresultService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改GMAResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		gmaresultService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表GMAResult");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = gmaresultService.list(page);	//列出GMAResult列表
		mv.setViewName("retirement/gmaresult/gmaresult_list");
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
		mv.setViewName("retirement/gmaresult/gmaresult_edit");
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
		pd = gmaresultService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/gmaresult/gmaresult_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除GMAResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			gmaresultService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出GMAResult到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("本次评估标记");	//1
		titles.add("评测老人编号");	//2
		titles.add("该题评测得分");	//3
		titles.add("题目编号");	//4
		titles.add("题目所属养老院编号");	//5
		titles.add("第几题");	//6
		titles.add("题目所属类别(int)");	//7
		titles.add("题目所属类别(String)");	//8
		titles.add("题目最大或最小分值");	//9
		titles.add("题目具体内容");	//10
		titles.add("创建时间");	//11
		titles.add("最后修改时间");	//12
		dataMap.put("titles", titles);
		List<PageData> varOList = gmaresultService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("GMAR_CODE"));	//1
			vpd.put("var2", varOList.get(i).getString("GMAR_E_ID"));	//2
			vpd.put("var3", varOList.get(i).get("GMAR_SCORE").toString());	//3
			vpd.put("var4", varOList.get(i).getString("GMAR_GMAI_ID"));	//4
			vpd.put("var5", varOList.get(i).getString("GMAR_GMAI_GM_ID"));	//5
			vpd.put("var6", varOList.get(i).get("GMAR_GMAI_NUM").toString());	//6
			vpd.put("var7", varOList.get(i).get("GMAR_GMAI_ITYPE").toString());	//7
			vpd.put("var8", varOList.get(i).getString("GMAR_GMAI_STYPE"));	//8
			vpd.put("var9", varOList.get(i).get("GMAR_GMAI_SCORE").toString());	//9
			vpd.put("var10", varOList.get(i).getString("GMAR_GMAI_CONTENT"));	//10
			vpd.put("var11", varOList.get(i).getString("GMAR_CTIME"));	//11
			vpd.put("var12", varOList.get(i).getString("GMAR_UTIME"));	//12
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
