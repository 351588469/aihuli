package com.team.util;

import org.springframework.context.ApplicationContext;
/**
 * 项目名称：
 * 修改日期：2015/11/2
*/
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	public static final String SESSION_USER = "sessionUser";			//session用的用户
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String sSESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//微信配置路径
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((uploadFiles)|(login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	
	/**
	 * zzy
	 */
	//护士端首页
	public static final String[] ZZY_ZZYS_ELIST_PARAM_ARRAY = new String[]{"gmu_id"};
	public static final String[] ZZY_ZZYS_ELIST_VALUE_ARRAY = new String[]{"职工编号"};
	
	//护士端登录
	public static final String[] ZZY_ZZYS_LOGIN_PARAM_ARRAY = new String[]{"tel","pwd"};
	public static final String[]ZZY_ZZYS_LOGIN_VALUE_ARRAY = new String[]{"手机号","密码"};
	
	//老人信息
	public static final String[] ZZY_ZZYS_EINFO_PARAM_ARRAY = new String[]{"e_id"};
	public static final String[] ZZY_ZZYS_EINFO_VALUE_ARRAY = new String[]{"老人编号"};
	
	//每日提醒
	public static final String[] ZZY_ZZYS_DMSG_PARAM_ARRAY = new String[]{"gmu_id"};
	public static final String[] ZZY_ZZYS_DMSG_VALUE_ARRAY = new String[]{"职工编号"};
	
	//新增记录
	public static final String[] ZZY_ZZYS_RNEW_PARAM_ARRAY = new String[]{"req_type","e_id","gm_id","gmu_id"};
	public static final String[] ZZY_ZZYS_RNEW_VALUE_ARRAY = new String[]{"记录类别","老人编号","养老院编号","记录职工编号"};
	
	//新增老人健康记录
	public static final String[] ZZY_ZZYS_RNEW_ERH_PARAM_ARRAY = new String[]{"erh_gmh_id","erh_value","erh_time"};
	public static final String[] ZZY_ZZYS_RNEW_ERH_VALUE_ARRAY = new String[]{"健康类别编号","记录值","记录时间"};
	
	//新增老人护理记录
	public static final String[] ZZY_ZZYS_RNEW_ERN_PARAM_ARRAY = new String[]{"ern_enp_type","ern_enp_id","ern_count","ern_time","ern_desc"};
	public static final String[] ZZY_ZZYS_RNEW_ERN_VALUE_ARRAY = new String[]{"护理项目类别","护理项目编号","护理次数","记录时间","异常描述"};
	
	//新增用药记录
	public static final String[] ZZY_ZZYS_RNEW_ERD_PARAM_ARRAY = new String[]{"erd_edp_id","erd_time","erd_value","erd_desc"};
	public static final String[] ZZY_ZZYS_RNEW_ERD_VALUE_ARRAY = new String[]{"用药计划编号","记录时间","记录值","记录说明"};
	
	//新增其他记录
	public static final String[] ZZY_ZZYS_RNEW_ERO_PARAM_ARRAY = new String[]{"ero_type","ero_content","ero_time"};
	public static final String[] ZZY_ZZYS_RNEW_ERO_VALUE_ARRAY = new String[]{"记录类别","记录内容","记录时间"};
	/**
	 * 
	 */
	//获取历史记录
	public static final String[] ZZY_ZZYS_RPAST_PARAM_ARRAY = new String[]{"req_type","e_id","gm_id","page"};
	public static final String[] ZZY_ZZYS_RPAST_VALUE_ARRAY = new String[]{"记录类别","老人编号","养老院编号","第几页"};
	//护士端获取历史健康记录
	public static final String[] ZZY_ZZYS_RPAST_ERH_PARAM_ARRAY = new String[]{"erh_gmh_id"};
	public static final String[] ZZY_ZZYS_RPAST_ERH_VALUE_ARRAY = new String[]{"健康类别"};
	//护士端获取历史护理记录
	public static final String[] ZZY_ZZYS_RPAST_ERN_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY_ZZYS_RPAST_ERN_VALUE_ARRAY = new String[]{};
	//护士端获取历史用药记录
	public static final String[] ZZY_ZZYS_RPAST_ERD_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY_ZZYS_RPAST_ERD_VALUE_ARRAY = new String[]{};
	//护士端获取历史其他记录
	public static final String[] ZZY_ZZYS_RPAST_ERO_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY_ZZYS_RPAST_ERO_VALUE_ARRAY = new String[]{};
	
	//新增计划
	public static final String[] ZZY_ZZYS_PNEW_PARAM_ARRAY = new String[]{"req_type","e_id","gm_id","gmu_id"};
	public static final String[] ZZY_ZZYS_PNEW_VALUE_ARRAY = new String[]{"记录类别","老人编号","养老院编号","记录职工编号"};
	
	//新增护理计划
	public static final String[] ZZY_ZZYS_PNEW_ENP_PARAM_ARRAY = new String[]{"enp_gmn_id","enp_need","enp_unit","enp_sdate","enp_edate","enp_ctime"};
	public static final String[] ZZY_ZZYS_PNEW_ENP_VALUE_ARRAY = new String[]{"护理项目编号","单位时间需要护理次数","时间单位","计划开始时间","计划结束时间","客户端记录生成时间"};
	
	//新增用药计划
	public static final String[] ZZY_ZZYS_PNEW_EDP_PARAM_ARRAY = new String[]{"edp_mhour","edp_mdesc","edp_mname","edp_mdosage","edp_mcount","edp_sdate","edp_edate","edp_desc"};
	public static final String[] ZZY_ZZYS_PNEW_EDP_VALUE_ARRAY = new String[]{"用药时间点","用药时间点说明","药品名称","药品剂量","药品数量","计划开始日期","计划结束时间","计划说明"};
	
	//列表 计划
	public static final String[] ZZY_ZZYS_PLIST_PARAM_ARRAY = new String[]{"req_type","e_id"};
	public static final String[] ZZY_ZZYS_PLIST_VALUE_ARRAY = new String[]{"类别","老人编号"};
		
	//列表 护理计划
	public static final String[] ZZY_ZZYS_PLIST_ENP_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY_ZZYS_PLIST_ENP_VALUE_ARRAY = new String[]{};
		
	//列表 用药计划
	public static final String[] ZZY_ZZYS_PLIST_EDP_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY_ZZYS_PLIST_EDP_VALUE_ARRAY = new String[]{};
	
	
	//护理项目列表
	public static final String[] ZZY_ZZYS_GMNLIST_PARAM_ARRAY = new String[]{"gm_id"};
	public static final String[] ZZY_ZZYS_GMNLIST_VALUE_ARRAY = new String[]{"养老院编号"};
	
	//护士端房间评测
	public static final String[] ZZY_ZZYS_BEVAL_PARAM_ARRAY = new String[]{"gmb_id","gmbr_environment","gmbr_sanitation","gmbr_safe","gmbr_equipment","gmbr_cphoto","gmbr_desc","gmbr_gmu_id"};
	public static final String[] ZZY_ZZYS_BEVAL_VALUE_ARRAY = new String[]{"房间编号","房间环境","房间卫生","房间安全","房间设备","房间照片数量","房间描述","职工编号"};
	
	//护士端房间评测历史
	public static final String[] ZZY_ZZYS_BPAST_PARAM_ARRAY = new String[]{"gmb_id","page"};
	public static final String[] ZZY_ZZYS_BPAST_VALUE_ARRAY = new String[]{"房间编号","分页"};
	
	//老人评测题目列表
	public static final String[] ZZY_ZZYS_ALIST_PARAM_ARRAY = new String[]{"gm_id"};
	public static final String[] ZZY_ZZYS_ALIST_VALUE_ARRAY = new String[]{"养老院编号"};
	
	//老人评测结果
	public static final String[] ZZY_ZZYS_ARADD_PARAM_ARRAY = new String[]{"json","e_id","gmu_id"};
	public static final String[] ZZY_ZZYS_ARADD_VALUE_ARRAY = new String[]{"List<Map<String,Object>>:Map_key:gmai_id,e_id","老人编号","职工编号"};
	
}
