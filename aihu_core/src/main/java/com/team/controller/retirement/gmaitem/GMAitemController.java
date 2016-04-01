package com.team.controller.retirement.gmaitem;

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
import com.team.service.retirement.gm.impl.GMService;
import com.team.service.retirement.gmaitem.GMAitemManager;
import com.team.service.retirement.gmatype.GMATypeManager;
import com.team.util.AppUtil;
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
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表GMAitem");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>varList = new ArrayList<>();	//列出GMAitem列表
		if(pd.containsKey("GMATYPE_ID")){
		varList = gmaitemService.zzyListByType(pd.getString("GMATYPE_ID"));
		String gmatype_id=(String)pd.getString("GMATYPE_ID");
		PageData tpd=gmatypeService.zzyFindById(gmatype_id);
		String gmid=(String) tpd.get("GMAT_GM_ID");
		String gm_name=gmService.zzyFindNameById(gmid);
		pd.put("GMAI_GMAT_ID",gmatype_id);
		pd.put("GMAI_GMAT_NAME",tpd.get("GMAT_NAME"));
		pd.put("GMAI_GM_ID",gmid);
		pd.put("GMAI_GM_NAME",gm_name);
		}else{
			varList = gmaitemService.list(page);
		}
		mv.setViewName("retirement/gmaitem/gmaitem_list");
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
		//System.out.println("zzy:"+pd.toString());
		
		String gmatype_id=(String)pd.getString("GMATYPE_ID");
		PageData tpd=gmatypeService.zzyFindById(gmatype_id);
		String gmid=(String) tpd.get("GMAT_GM_ID");
		String gm_name=gmService.zzyFindNameById(gmid);
		pd.put("GMAI_GMAT_ID",gmatype_id);
		pd.put("GMAI_GMAT_NAME",tpd.get("GMAT_NAME"));
		pd.put("GMAI_GM_ID",gmid);
		pd.put("GMAI_GM_NAME",gm_name);
		
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
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出GMAitem到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("第几题");	//1
		titles.add("题目所属类别编号");	//2
		titles.add("题目最大或最小分值");	//3
		titles.add("题目具体内容");	//4
		titles.add("创建时间");	//5
		titles.add("修改时间");	//6
		titles.add("题目类别名称");	//7
		titles.add("养老院编号");	//8
		dataMap.put("titles", titles);
		List<PageData> varOList = gmaitemService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("GMAI_NUMBER").toString());	//1
			vpd.put("var2", varOList.get(i).getString("GMAI_GMAT_ID"));	//2
			vpd.put("var3", varOList.get(i).get("GMAI_SCORE").toString());	//3
			vpd.put("var4", varOList.get(i).getString("GMAI_CONTENT"));	//4
			vpd.put("var5", varOList.get(i).getString("GMAI_CTIME"));	//5
			vpd.put("var6", varOList.get(i).getString("GMAI_UTIME"));	//6
			vpd.put("var7", varOList.get(i).getString("GMAI_GMAT_NAME"));	//7
			vpd.put("var8", varOList.get(i).getString("GMAI_GM_ID"));	//8
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
