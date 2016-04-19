package com.team.controller.retirement.gmberth;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.team.controller.base.BaseController;
import com.team.controller.system.session.SessionController;
import com.team.entity.Page;
import com.team.entity.system.User;
import com.team.service.retirement.elder.ElderManager;
import com.team.service.retirement.gm.GMManager;
import com.team.service.retirement.gm.impl.GMService;
import com.team.service.retirement.gmberth.GMBerthManager;
import com.team.service.retirement.gmuser.GMUserManager;
import com.team.service.retirement.gmuser.impl.GMUserService;
import com.team.util.AppUtil;
import com.team.util.Const;
import com.team.util.Jurisdiction;
import com.team.util.ObjectExcelView;
import com.team.util.PageData;
import com.team.util.Tools;

/** 
 * 说明：房间信息
 * 创建人：jaychum
 * 创建时间：2016-03-02
 */
@Controller
@RequestMapping(value="/gmberth")
public class GMBerthController extends BaseController {
	
	String menuUrl = "gmberth/list.do"; //菜单地址(权限用)
	@Resource(name="gmberthService")
	private GMBerthManager gmberthService;
	@Resource(name="gmService")
	private GMManager gmService;
	@Resource(name="gmuserService")
	private GMUserManager gmUserService;
	@Resource(name="elderService")
	private ElderManager elderService;
	@Resource(name="sessionController")
	private SessionController sessionController;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增GMBerth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//System.out.println("zzy:"+pd.toString());
		pd.put("GMBERTH_ID", this.get32UUID());	//主键
		pd.put("GMB_CTIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("GMB_UTIME", Tools.date2Str(new Date()));	//最后修改时间
		gmberthService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除GMBerth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		gmberthService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改GMBerth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		gmberthService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表GMBerth");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpSession zzyHs=request.getSession();
		//通过gmberth_id获取该房间内床位信息
		if(pd.containsKey("GMBERTH_ID")){
			PageData room=gmberthService.zzyFindById(pd.getString("GMBERTH_ID"));
			pd.put("GMB_FLOOR",room.get("GMB_FLOOR"));
			pd.put("GMB_LAYER",room.get("GMB_LAYER"));
			pd.put("GMB_ROOM",room.get("GMB_ROOM"));
			pd.put("GMB_TYPE",4);
			mv.addObject("GMBERTH_ID",pd.get("GMBERTH_ID"));
		}else{//通过gm_id获取房间信息
			//Session取值
			if(pd.get("GMB_GM_ID")!=""&&pd.get("GMB_GM_ID")!=null){//重新赋值
				zzyHs.setAttribute("ZZY_GMID",pd.get("GMB_GM_ID"));
			}else{
				pd.put("GMB_GM_ID",zzyHs.getAttribute("ZZY_GMID"));
			}
			pd.put("GMB_TYPE",3);
			mv.addObject("GM_ID",pd.get("GMB_GM_ID"));
			mv.addObject("GM_NAME",gmService.zzyFindNameById((String) pd.get("GMB_GM_ID")));
		}
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		//搜索信息
		String term=(String) zzyHs.getAttribute("ZZY_TERM_GMB");
		if(term!=null&&term!=""){
			Gson gson=new Gson();
			PageData tpd=gson.fromJson(term,PageData.class);
			pd.putAll(tpd);
			pd.put("GMB_TYPE",4);
		}
		page.setPd(pd);
		List<PageData>	varList = gmberthService.list(page);	//列出GMBerth列表
		/**
		 * zzy
		 * list处理
		 */
		List<PageData>varList2=new ArrayList<>();
		for(int i=0;i<varList.size();i++){
			PageData pd2=varList.get(i);
			String eid=pd2.getString("GMB_E_ID");//老人编号
			String name=elderService.zzyFindNameById(eid);
			pd2.put("GMB_E_NAME",name);
			String uids=pd2.getString("GMB_GMU_ID");
			String uid[]=uids.split(";");
			String unames="";
			for(int j=0;j<uid.length;j++){
				if(uid[j].length()>1){//不为';'
					String name2=gmUserService.zzyFindNameById(uid[j]);
					unames+=name2+"&emsp;";
				}
			}
			pd2.put("GMB_GMU_NAME",unames);
			varList2.add(pd2);
		}
		if(!pd.containsKey("GMBERTH_ID")&&(term==null||term==""))
			mv.setViewName("retirement/gmberth/gmberth_list_room");
		else 
			mv.setViewName("retirement/gmberth/gmberth_list");
		mv.addObject("varList", varList2);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		/**
		 * zzy
		 */
		
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		/**
		 * 视图控制
		 */
		if(!pd.containsKey("GMBERTH_ID"))
			mv.setViewName("retirement/gmberth/gmberth_edit_room");
		else{
			mv.addObject("GMBERTH_ID",pd.get("GMBERTH_ID"));
			PageData gmb=gmberthService.zzyFindById(pd.getString("GMBERTH_ID"));
			mv.addObject("gmb",gmb);
			mv.setViewName("retirement/gmberth/gmberth_edit");
		}
		
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		//养老院编号及名称
		HttpSession zzyHs=request.getSession();
		String gmid=(String) zzyHs.getAttribute("ZZY_GMID");
		mv.addObject("GM_ID",gmid);
		mv.addObject("GM_NAME",gmService.zzyFindNameById(gmid));
		//医院护士姓名及编号列表
		List<Map<String,Object>>list=gmUserService.zzyListForNameID(gmid);
		//System.out.println("zzy:"+list.toString());
		mv.addObject("staffList",list);
		//创建用户
		Session session = Jurisdiction.getSession();
		User user=(User)session.getAttribute(Const.SESSION_USER);
		mv.addObject("user",user);
		return mv;
	}	
	/**
	 * 去搜索页面
	 */
	@RequestMapping(value="/goSearch")
	public ModelAndView goSearch(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("retirement/gmberth/gmberth_search_room");
		mv.addObject("msg", "list");
		mv.addObject("pd", pd);
		HttpSession zzyHs=request.getSession();
		//Session取搜索条件
		String term=(String) zzyHs.getAttribute("ZZY_TERM_GMB");
		if(term!=null&&term!=""){
			Gson gson=new Gson();
			PageData tpd=gson.fromJson(term,PageData.class);
			pd.putAll(tpd);
		}
		//养老院编号及名称
		String gmid=(String) zzyHs.getAttribute("ZZY_GMID");
		mv.addObject("GM_ID",gmid);
		mv.addObject("GM_NAME",gmService.zzyFindNameById(gmid));
		//养老院列表
		List<PageData>gmlist=gmService.listAll(null,request.getSession());
		mv.addObject("GM_LIST",gmlist);
		//创建用户
		Session session = Jurisdiction.getSession();
		User user=(User)session.getAttribute(Const.SESSION_USER);
		mv.addObject("user",user);
		return mv;
	}
	/**
	 *搜索信息
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="/zzySearch")
	public ModelAndView zzySearch(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData zzyPd=new PageData();
		pd=this.getPageData();
		//搜索信息
		if(pd.containsKey("TERM_GM_ID")&&pd.getString("TERM_GM_ID")!=""){
			zzyPd.put("TERM_GM_ID",pd.getString("TERM_GM_ID"));
			HttpSession zzyHs=request.getSession();
			zzyHs.setAttribute("ZZY_GMID",pd.get("TERM_GM_ID"));
		}
		if(pd.containsKey("TERM_FLOOR")&&pd.getString("TERM_FLOOR")!=""){
			zzyPd.put("TERM_FLOOR",pd.getString("TERM_FLOOR"));
		}
		if(pd.containsKey("TERM_LAYER")&&pd.getString("TERM_LAYER")!=""){
			zzyPd.put("TERM_LAYER",pd.getString("TERM_LAYER"));
		}
		if(pd.containsKey("TERM_ROOM")&&pd.getString("TERM_ROOM")!=""){
			zzyPd.put("TERM_ROOM",pd.getString("TERM_ROOM"));
		}
		if(pd.containsKey("TERM_E_NAME")&&pd.getString("TERM_E_NAME")!=""){
			zzyPd.put("TERM_E_NAME",pd.getString("TERM_E_NAME"));
		}
			
		Gson gson=new Gson();
		HttpSession session=request.getSession();
		session.setAttribute("ZZY_TERM_GMB",gson.toJson(zzyPd));
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(HttpServletRequest request)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = gmberthService.findById(pd);	//根据ID读取
		//老人编号-》姓名
		String eid=(String) pd.get("GMB_E_ID");
		String ename=elderService.zzyFindNameById(eid);
		pd.put("GMB_E_NAME",ename);
		Integer type=(Integer) pd.get("GMB_TYPE");
		if(type==3)
			mv.setViewName("retirement/gmberth/gmberth_edit_room");
		else {
			mv.addObject("GMBERTH_ID",pd.get("GMBERTH_ID"));
			PageData gmb=gmberthService.zzyFindById(pd.getString("GMBERTH_ID"));
			mv.addObject("gmb",gmb);
			mv.setViewName("retirement/gmberth/gmberth_edit");
		}
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		/**
		 * zzy
		 */
		//养老院编号和名称
		HttpSession zzyHs=request.getSession();
		String gmid=(String) zzyHs.getAttribute("ZZY_GMID");
		mv.addObject("GM_ID",gmid);
		mv.addObject("GM_NAME",gmService.zzyFindNameById(gmid));
		//创建用户
		Session session = Jurisdiction.getSession();
		User user=(User)session.getAttribute(Const.SESSION_USER);
		mv.addObject("user",user);
		//医院护士姓名及编号列表
		List<Map<String,Object>>list=gmUserService.zzyListForNameID(gmid);
		//System.out.println("zzy:"+list.toString());
		//处理list,为选中的加状态
		String uids=(String) pd.get("GMB_GMU_ID");
		String uid[]=uids.split(";");
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=list.get(i);
			String id=(String) map.get("GMUSER_ID");
			for(int j=0;j<uid.length;j++){
				if(id.equals(uid[j])){
					map.put("checked",1);
				}
			}
		}
		mv.addObject("staffList",list);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除GMBerth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			gmberthService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出GMBerth到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("房间所属养老院编号");	//1
		titles.add("数据类别(1:仅有楼栋2:至楼层3:至房间4：至床位)");	//2
		titles.add("楼栋号");	//3
		titles.add("楼层号");	//4
		titles.add("房间号");	//5
		titles.add("床位号");	//6
		titles.add("居住老人编号");	//7
		titles.add("房间负责职工编号");	//8
		titles.add("床位状态(1:可以入住2:不能入住)");	//9
		titles.add("说明");	//10
		titles.add("创建时间");	//11
		titles.add("最后修改时间");	//12
		dataMap.put("titles", titles);
		List<PageData> varOList = gmberthService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("GMB_GM_ID"));	//1
			vpd.put("var2", varOList.get(i).get("GMB_TYPE").toString());	//2
			vpd.put("var3", varOList.get(i).get("GMB_FLOOR").toString());	//3
			vpd.put("var4", varOList.get(i).get("GMB_LAYER").toString());	//4
			vpd.put("var5", varOList.get(i).getString("GMB_ROOM"));	//5
			vpd.put("var6", varOList.get(i).get("GMB_BERTH").toString());	//6
			vpd.put("var7", varOList.get(i).getString("GMB_E_ID"));	//7
			vpd.put("var8", varOList.get(i).getString("GMB_GMU_ID"));	//8
			vpd.put("var9", varOList.get(i).get("GMB_STATUS").toString());	//9
			vpd.put("var10", varOList.get(i).getString("GMB_DESC"));	//10
			vpd.put("var11", varOList.get(i).getString("GMB_CTIME"));	//11
			vpd.put("var12", varOList.get(i).getString("GMB_UTIME"));	//12
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
	
	@RequestMapping(value="/resetSession")
	@ResponseBody
	public Object resetSession(HttpServletRequest request,Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"重置Session");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		sessionController.resetSession(request.getSession(),"retirement");
		return list(page, request);
	}
}
