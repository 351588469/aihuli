package com.team.controller.volunteer.vactivity;

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
import com.team.service.volunteer.vactivity.VActivityManager;
import com.team.util.AppUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：活动表
 * 创建人：jaychum
 * 创建时间：2016-04-05
 */
@Controller
@RequestMapping(value="/vactivity")
public class VActivityController extends BaseController {
	
	String menuUrl = "vactivity/list.do"; //菜单地址(权限用)
	@Resource(name="vactivityService")
	private VActivityManager vactivityService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增VActivity");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("VACTIVITY_ID", this.get32UUID());	//主键
		pd.put("VA_ENROLL", "0");	//报名人数
		pd.put("VA_CTIME", Tools.date2Str(new Date()));	//记录生成时间
		pd.put("VA_UTIME", Tools.date2Str(new Date()));	//记录更新时间
		pd.put("VA_STATUS", "1");	//状态
		vactivityService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除VActivity");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		vactivityService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改VActivity");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		vactivityService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表VActivity");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = vactivityService.list(page);	//列出VActivity列表
		mv.setViewName("volunteer/vactivity/vactivity_list");
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
		mv.setViewName("volunteer/vactivity/vactivity_edit");
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
		pd = vactivityService.findById(pd);	//根据ID读取
		mv.setViewName("volunteer/vactivity/vactivity_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除VActivity");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			vactivityService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出VActivity到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("主题");	//1
		titles.add("城市");	//2
		titles.add("地址");	//3
		titles.add("关键词");	//4
		titles.add("详情");	//5
		titles.add("日程");	//6
		titles.add("举办方团体编号");	//7
		titles.add("点赞数量");	//8
		titles.add("报名人数");	//9
		titles.add("报名人数上限");	//10
		titles.add("活动开始时间");	//11
		titles.add("活动结束时间");	//12
		titles.add("记录生成时间");	//13
		titles.add("记录更新时间");	//14
		titles.add("状态");	//15
		dataMap.put("titles", titles);
		List<PageData> varOList = vactivityService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("VA_TOPIC"));	//1
			vpd.put("var2", varOList.get(i).getString("VA_CITY"));	//2
			vpd.put("var3", varOList.get(i).getString("VA_ADDRESS"));	//3
			vpd.put("var4", varOList.get(i).getString("VA_KEYWORD"));	//4
			vpd.put("var5", varOList.get(i).getString("VA_CONTENT"));	//5
			vpd.put("var6", varOList.get(i).getString("VA_SCHEDULE"));	//6
			vpd.put("var7", varOList.get(i).getString("VA_VT_ID"));	//7
			vpd.put("var8", varOList.get(i).get("VA_PRAISE").toString());	//8
			vpd.put("var9", varOList.get(i).get("VA_ENROLL").toString());	//9
			vpd.put("var10", varOList.get(i).get("VA_ENROLL_M").toString());	//10
			vpd.put("var11", varOList.get(i).getString("VA_STIME"));	//11
			vpd.put("var12", varOList.get(i).getString("VA_ETIME"));	//12
			vpd.put("var13", varOList.get(i).getString("VA_CTIME"));	//13
			vpd.put("var14", varOList.get(i).getString("VA_UTIME"));	//14
			vpd.put("var15", varOList.get(i).get("VA_STATUS").toString());	//15
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
