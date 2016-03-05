package com.team.controller.retirement.gmuser;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.team.util.Const;
import com.team.controller.base.BaseController;
import com.team.entity.Page;
import com.team.entity.system.User;
import com.team.service.retirement.gm.impl.GMService;
import com.team.service.retirement.gmuser.GMUserManager;
import com.team.util.AppUtil;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：职工信息
 * 创建人：jaychum
 * 创建时间：2016-03-03
 */
@Controller
@RequestMapping(value="/gmuser")
public class GMUserController extends BaseController {
	
	String menuUrl = "gmuser/list.do"; //菜单地址(权限用)
	@Resource(name="gmuserService")
	private GMUserManager gmuserService;
	@Resource(name="gmService")
	private GMService gmService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增GMUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("GMUSER_ID", this.get32UUID());	//主键
		pd.put("GMU_CTIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("GMU_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		gmuserService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除GMUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		gmuserService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改GMUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		gmuserService.edit(pd);
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
		//logBefore(logger, Jurisdiction.getUsername()+"列表GMUser");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = gmuserService.list(page);	//列出GMUser列表
		mv.setViewName("retirement/gmuser/gmuser_list");
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
		mv.setViewName("retirement/gmuser/gmuser_edit");
		mv.addObject("msg", "save");
		//养老院编号
		mv.addObject("gm_info",gmService.listByCreator());
		mv.addObject("pd", pd);
		//创建用户
		Session session = Jurisdiction.getSession();
		User user=(User)session.getAttribute(Const.SESSION_USER);
		mv.addObject("user",user);
		//System.out.println("zzy: "+user);
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
		pd = gmuserService.findById(pd);	//根据ID读取
		mv.setViewName("retirement/gmuser/gmuser_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除GMUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			gmuserService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出GMUser到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("职工所属养老院编号");	//1
		titles.add("职工姓名");	//2
		titles.add("职工性别");	//3
		titles.add("职工生日");	//4
		titles.add("职工入职时间");	//5
		titles.add("职工职务");	//6
		titles.add("职工健康状况");	//7
		titles.add("职工婚姻状况");	//8
		titles.add("职工联系电话");	//9
		titles.add("职工电子邮箱");	//10
		titles.add("职工户籍");	//11
		titles.add("职工家庭住址");	//12
		titles.add("职工入职状态(1:入职2:离职3:请假)");	//13
		titles.add("职工学历");	//14
		titles.add("职工毕业院校");	//15
		titles.add("职工专业");	//16
		titles.add("职工英语水平");	//17
		titles.add("职工计算机水平");	//18
		titles.add("职工工资");	//19
		titles.add("职工头像");	//20
		titles.add("职工密码");	//21
		titles.add("创建用户");	//22
		titles.add("创建时间");	//23
		titles.add("最后修改时间");	//24
		dataMap.put("titles", titles);
		List<PageData> varOList = gmuserService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("GMU_GM_ID"));	//1
			vpd.put("var2", varOList.get(i).getString("GMU_NAME"));	//2
			vpd.put("var3", varOList.get(i).getString("GMU_GENDER"));	//3
			vpd.put("var4", varOList.get(i).getString("GMU_BIRTHDAY"));	//4
			vpd.put("var5", varOList.get(i).getString("GM_EDATE"));	//5
			vpd.put("var6", varOList.get(i).get("GMU_DUTIES").toString());	//6
			vpd.put("var7", varOList.get(i).getString("GMU_HEALTH"));	//7
			vpd.put("var8", varOList.get(i).getString("GMU_MARRIAGE"));	//8
			vpd.put("var9", varOList.get(i).getString("GMU_TEL"));	//9
			vpd.put("var10", varOList.get(i).getString("GMU_EMAIL"));	//10
			vpd.put("var11", varOList.get(i).getString("GMU_CENSUS"));	//11
			vpd.put("var12", varOList.get(i).getString("GMU_ADDRESS"));	//12
			vpd.put("var13", varOList.get(i).get("GMU_ESTATUS").toString());	//13
			vpd.put("var14", varOList.get(i).getString("GMU_EDUCATION"));	//14
			vpd.put("var15", varOList.get(i).getString("GMU_COLLAGE"));	//15
			vpd.put("var16", varOList.get(i).getString("GMU_MAJOR"));	//16
			vpd.put("var17", varOList.get(i).getString("GMU_LENGLISH"));	//17
			vpd.put("var18", varOList.get(i).getString("GMU_LCOMPUTER"));	//18
			vpd.put("var19", varOList.get(i).getString("GMU_WAGE"));	//19
			vpd.put("var20", varOList.get(i).getString("GMU_AVATER"));	//20
			vpd.put("var21", varOList.get(i).getString("GMU_PWD"));	//21
			vpd.put("var22", varOList.get(i).getString("GMU_GMUID"));	//22
			vpd.put("var23", varOList.get(i).getString("GMU_CTIME"));	//23
			vpd.put("var24", varOList.get(i).getString("GMU_UTIME"));	//24
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
