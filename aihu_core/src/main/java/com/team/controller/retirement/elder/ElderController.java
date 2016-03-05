package com.team.controller.retirement.elder;

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
import com.team.service.retirement.elder.ElderManager;

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
		pd.put("E_LDATE", Tools.date2Str(new Date()));	//农历生日
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
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Elder");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = elderService.list(page);	//列出Elder列表
		mv.setViewName("retirement/elder/elder_list");
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
		mv.setViewName("retirement/elder/elder_edit");
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
		pd = elderService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/elder/elder_edit");
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
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Elder到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("老人姓名");	//1
		titles.add("老人性别");	//2
		titles.add("入住状态(1入住2退住3试住)");	//3
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
		titles.add("身高");	//15
		titles.add("体重");	//16
		titles.add("血型");	//17
		titles.add("视力");	//18
		titles.add("听力");	//19
		titles.add("记忆力");	//20
		titles.add("神智");	//21
		titles.add("大小便");	//22
		titles.add("血压(收缩压/舒张压)");	//23
		titles.add("脉搏");	//24
		titles.add("药物过敏史");	//25
		titles.add("常服药名");	//26
		titles.add("慢性病史");	//27
		titles.add("其他健康情况");	//28
		titles.add("评估分数");	//29
		titles.add("评估等级");	//30
		titles.add("最近一次评估表");	//31
		titles.add("老人头像");	//32
		titles.add("老人密码");	//33
		titles.add("创建职工");	//34
		titles.add("创建时间");	//35
		titles.add("最后修改时间");	//36
		dataMap.put("titles", titles);
		List<PageData> varOList = elderService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("E_NAME"));	//1
			vpd.put("var2", varOList.get(i).getString("E_GENDER"));	//2
			vpd.put("var3", varOList.get(i).get("E_INTAKE").toString());	//3
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
			vpd.put("var15", varOList.get(i).getString("E_HEIGHT"));	//15
			vpd.put("var16", varOList.get(i).getString("E_WEIGHT"));	//16
			vpd.put("var17", varOList.get(i).getString("E_BTYPE"));	//17
			vpd.put("var18", varOList.get(i).getString("E_VISSION"));	//18
			vpd.put("var19", varOList.get(i).getString("E_HEARING"));	//19
			vpd.put("var20", varOList.get(i).getString("E_MEMORY"));	//20
			vpd.put("var21", varOList.get(i).getString("E_MIND"));	//21
			vpd.put("var22", varOList.get(i).getString("E_RELIEVE"));	//22
			vpd.put("var23", varOList.get(i).getString("E_STOLIC"));	//23
			vpd.put("var24", varOList.get(i).getString("E_PULSE"));	//24
			vpd.put("var25", varOList.get(i).getString("E_D_ALLERGY"));	//25
			vpd.put("var26", varOList.get(i).getString("E_D_COMMON"));	//26
			vpd.put("var27", varOList.get(i).getString("E_D_CHRONIC"));	//27
			vpd.put("var28", varOList.get(i).getString("E_HNOTE"));	//28
			vpd.put("var29", varOList.get(i).get("E_A_SCORE").toString());	//29
			vpd.put("var30", varOList.get(i).get("E_A_LEVEL").toString());	//30
			vpd.put("var31", varOList.get(i).getString("E_A_LEVELLIST"));	//31
			vpd.put("var32", varOList.get(i).getString("E_AVATER"));	//32
			vpd.put("var33", varOList.get(i).getString("E_PWD"));	//33
			vpd.put("var34", varOList.get(i).getString("E_GMU_ID"));	//34
			vpd.put("var35", varOList.get(i).getString("E_CTIME"));	//35
			vpd.put("var36", varOList.get(i).getString("E_UTIME"));	//36
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