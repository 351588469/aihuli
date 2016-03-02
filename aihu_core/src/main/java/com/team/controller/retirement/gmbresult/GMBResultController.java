package com.team.controller.retirement.gmbresult;

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
import com.team.service.retirement.gmbresult.GMBResultManager;
import com.team.util.AppUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：房间评测
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/gmbresult")
public class GMBResultController extends BaseController {
	
	String menuUrl = "gmbresult/list.do"; //菜单地址(权限用)
	@Resource(name="gmbresultService")
	private GMBResultManager gmbresultService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增GMBResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("GMBRESULT_ID", this.get32UUID());	//主键
		pd.put("GMBR_GMB_FLOOR", "");	//楼栋号
		pd.put("GMBR_GMB_LAYER", "");	//楼层号
		pd.put("GMBR_GMB_ROOM", "");	//房间号
		pd.put("GMBR_CTIME", Tools.date2Str(new Date()));	//房间评测创建时间
		pd.put("GMBR_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		gmbresultService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除GMBResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		gmbresultService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改GMBResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		gmbresultService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表GMBResult");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = gmbresultService.list(page);	//列出GMBResult列表
		mv.setViewName("retirement/gmbresult/gmbresult_list");
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
		mv.setViewName("retirement/gmbresult/gmbresult_edit");
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
		pd = gmbresultService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/gmbresult/gmbresult_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除GMBResult");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			gmbresultService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出GMBResult到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("房间编号");	//1
		titles.add("楼栋号");	//2
		titles.add("楼层号");	//3
		titles.add("房间号");	//4
		titles.add("房间环境");	//5
		titles.add("房间卫生");	//6
		titles.add("房间安全");	//7
		titles.add("房间设备");	//8
		titles.add("房间照片数量");	//9
		titles.add("第一张照片");	//10
		titles.add("第二张照片");	//11
		titles.add("第三张照片");	//12
		titles.add("第四张照片");	//13
		titles.add("房间评测描述");	//14
		titles.add("房间评测创建时间");	//15
		titles.add("最后修改时间");	//16
		titles.add("评测人编号");	//17
		dataMap.put("titles", titles);
		List<PageData> varOList = gmbresultService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("GMBR_GMB_ID"));	//1
			vpd.put("var2", varOList.get(i).get("GMBR_GMB_FLOOR").toString());	//2
			vpd.put("var3", varOList.get(i).get("GMBR_GMB_LAYER").toString());	//3
			vpd.put("var4", varOList.get(i).getString("GMBR_GMB_ROOM"));	//4
			vpd.put("var5", varOList.get(i).get("GMBR_ENVIRONMENT").toString());	//5
			vpd.put("var6", varOList.get(i).get("GMBR_SANITATION").toString());	//6
			vpd.put("var7", varOList.get(i).get("GMBR_SAFE").toString());	//7
			vpd.put("var8", varOList.get(i).get("GMBR_EQUIPMENT").toString());	//8
			vpd.put("var9", varOList.get(i).get("GMBR_CPHOTO").toString());	//9
			vpd.put("var10", varOList.get(i).getString("GMBR_PHOTOA"));	//10
			vpd.put("var11", varOList.get(i).getString("GMBR_PHOTOB"));	//11
			vpd.put("var12", varOList.get(i).getString("GMBR_PHOTOC"));	//12
			vpd.put("var13", varOList.get(i).getString("GMBR_PHOTOD"));	//13
			vpd.put("var14", varOList.get(i).getString("GMBR_DESC"));	//14
			vpd.put("var15", varOList.get(i).getString("GMBR_CTIME"));	//15
			vpd.put("var16", varOList.get(i).getString("GMBR_UTIME"));	//16
			vpd.put("var17", varOList.get(i).getString("GMBR_GMU_ID"));	//17
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
