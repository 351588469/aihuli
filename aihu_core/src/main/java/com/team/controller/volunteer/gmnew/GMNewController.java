package com.team.controller.volunteer.gmnew;

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
import com.team.service.volunteer.gmnew.GMNewManager;
import com.team.util.AppUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：养老院评论
 * 创建人：jaychum
 * 创建时间：2016-04-05
 */
@Controller
@RequestMapping(value="/gmnew")
public class GMNewController extends BaseController {
	
	String menuUrl = "gmnew/list.do"; //菜单地址(权限用)
	@Resource(name="gmnewService")
	private GMNewManager gmnewService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增GMNew");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("GMNEW_ID", this.get32UUID());	//主键
		pd.put("GMN_P_NAME", "");	//评论用户姓名
		pd.put("GMN_P_AVATER", "");	//发表人头像
		pd.put("GMN_R_PID", "");	//回复人编号
		pd.put("GMN_R_PNAME", "");	//回复人姓名
		pd.put("GMN_R_PAVATER", "");	//回复人头像
		pd.put("GMN_STATUS", "1");	//状态
		pd.put("GMN_CTIME", Tools.date2Str(new Date()));	//记录生成时间
		pd.put("GMN_UTIME", Tools.date2Str(new Date()));	//记录更新时间
		gmnewService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除GMNew");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		gmnewService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改GMNew");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		gmnewService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表GMNew");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = gmnewService.list(page);	//列出GMNew列表
		mv.setViewName("volunteer/gmnew/gmnew_list");
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
		mv.setViewName("volunteer/gmnew/gmnew_edit");
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
		pd = gmnewService.findById(pd);	//根据ID读取
		mv.setViewName("volunteer/gmnew/gmnew_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除GMNew");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			gmnewService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出GMNew到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("养老院编号");	//1
		titles.add("评论用户编号");	//2
		titles.add("评论用户姓名");	//3
		titles.add("发表人头像");	//4
		titles.add("回复消息编号");	//5
		titles.add("回复人编号");	//6
		titles.add("回复人姓名");	//7
		titles.add("回复人头像");	//8
		titles.add("内容");	//9
		titles.add("状态");	//10
		titles.add("记录生成时间");	//11
		titles.add("记录更新时间");	//12
		dataMap.put("titles", titles);
		List<PageData> varOList = gmnewService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("GMN_GM_ID"));	//1
			vpd.put("var2", varOList.get(i).getString("GMN_P_ID"));	//2
			vpd.put("var3", varOList.get(i).getString("GMN_P_NAME"));	//3
			vpd.put("var4", varOList.get(i).getString("GMN_P_AVATER"));	//4
			vpd.put("var5", varOList.get(i).getString("GMN_R_ID"));	//5
			vpd.put("var6", varOList.get(i).getString("GMN_R_PID"));	//6
			vpd.put("var7", varOList.get(i).getString("GMN_R_PNAME"));	//7
			vpd.put("var8", varOList.get(i).getString("GMN_R_PAVATER"));	//8
			vpd.put("var9", varOList.get(i).getString("GMN_CONTENT"));	//9
			vpd.put("var10", varOList.get(i).get("GMN_STATUS").toString());	//10
			vpd.put("var11", varOList.get(i).getString("GMN_CTIME"));	//11
			vpd.put("var12", varOList.get(i).getString("GMN_UTIME"));	//12
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
