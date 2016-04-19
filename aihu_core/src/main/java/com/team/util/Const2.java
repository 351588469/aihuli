package com.team.util;

import org.springframework.context.ApplicationContext;
/**
 * 项目名称：
 * 修改日期：2015/11/2
*/
public class Const2 {
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
	
	public static final String ZZY2_GM_ID="6994a259dc2c425a8d9ad0099c570d5f";//app volunteer养老院编号
	public static final Integer ZZY2_VA_IMG_REVIEW=1;
	public static final Integer ZZY2_VA_IMG_THEME=2;
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
	//用户注册
	public static final String[] ZZY2_USER_ADD_PARAM_ARRAY = new String[]{"USERNAME","PHONE","ADDRESS","BIRTH","GENDER","JOB","SIGN"};
	public static final String[] ZZY2_USER_ADD_VALUE_ARRAY = new String[]{"用户名","联系电话","联系地址","出生年月","性别","从事工作","签名"};
	//用户信息
	public static final String[] ZZY2_USER_INFO_PARAM_ARRAY = new String[]{"userid"};
	public static final String[] ZZY2_USER_INFO_VALUE_ARRAY = new String[]{"用户编号"};
	//团体注册
	//public static final String[] ZZY2_VT_ADD_PARAM_ARRAY = new String[]{"VT_NAME","VT_HTIME","VT_CITY","VT_ADDRESS","VT_DESCRIBE","VT_C_ID"};
	public static final String[] ZZY2_VT_ADD_PARAM_ARRAY = new String[]{"name","htime","city","address","describe","userid"};
	public static final String[] ZZY2_VT_ADD_VALUE_ARRAY = new String[]{"团体名称","成立时间","所在城市","通讯地址","团体描述","负责人编号"};
	//团体关注
	public static final String[] ZZY2_VT_CONCERN_PARAM_ARRAY = new String[]{"vtid","userid"};
	public static final String[] ZZY2_VT_CONCERN_VALUE_ARRAY = new String[]{"团体编号","用户编号"};
	//用户是否关注团体
	public static final String[] ZZY2_VTC_TEST_PARAM_ARRAY = new String[]{"vtid","userid"};
	public static final String[] ZZY2_VTC_TEST_VALUE_ARRAY = new String[]{"团体编号","用户编号"};
	//团体关注列表
	public static final String[] ZZY2_VTC_LIST_PARAM_ARRAY = new String[]{"vtid"};
	public static final String[] ZZY2_VTC_LIST_VALUE_ARRAY = new String[]{"团体编号"};
	//团体话题发表
	public static final String[] ZZY2_VTT_ADD_PARAM_ARRAY = new String[]{"vtid","userid","title","content"};
	public static final String[] ZZY2_VTT_ADD_VALUE_ARRAY = new String[]{"团体编号","用户编号","标题","内容"};
	//团体列表
	public static final String[] ZZY2_VT_LIST_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY2_VT_LIST_VALUE_ARRAY = new String[]{};
	//团体话题列表
	public static final String[] ZZY2_VTT_LIST_PARAM_ARRAY = new String[]{"vtid"};
	public static final String[] ZZY2_VTT_LIST_VALUE_ARRAY = new String[]{"团体编号"};
	//团体话题评论列表
	public static final String[] ZZY2_VTN_LIST_PARAM_ARRAY = new String[]{"vttid"};
	public static final String[] ZZY2_VTN_LIST_VALUE_ARRAY = new String[]{"话题编号"};
	//团体话题评论发表
	public static final String[] ZZY2_VTN_ADD_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY2_VTN_ADD_VALUE_ARRAY = new String[]{};
	
	//活动申报
	public static final String[] ZZY2_VA_ADD_PARAM_ARRAY = new String[]{"topic","city","address","content","schedule","userid","vtid","limit","stime","etime"};
	public static final String[] ZZY2_VA_ADD_VALUE_ARRAY = new String[]{"主题","所在城市","地址","活动内容","日程","用户编号","团体编号","人数上限","活动开始时间","活动结束时间"};
	//活动列表
	public static final String[] ZZY2_VA_LIST_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY2_VA_LIST_VALUE_ARRAY = new String[]{};
	//活动报名列表
	public static final String[] ZZY2_VAE_LIST_PARAM_ARRAY = new String[]{"vaid"};
	public static final String[] ZZY2_VAE_LIST_VALUE_ARRAY = new String[]{"活动编号"};
	//活动报名
	public static final String[] ZZY2_VAE_PARAM_ARRAY = new String[]{"vaid","userid"};
	public static final String[] ZZY2_VAE_VALUE_ARRAY = new String[]{"活动编号","用户编号"};
	//活动评论发表
	public static final String[] ZZY2_VAN_ADD_PARAM_ARRAY = new String[]{"vaid","userid","content"};
	public static final String[] ZZY2_VAN_ADD_VALUE_ARRAY = new String[]{"活动编号","用户编号","内容"};
	//活动评论列表
	public static final String[] ZZY2_VAN_LIST_PARAM_ARRAY = new String[]{"vaid"};
	public static final String[] ZZY2_VAN_LIST_VALUE_ARRAY = new String[]{"活动编号"};
	//活动点赞
	public static final String[] ZZY2_VAP_ADD_PARAM_ARRAY = new String[]{"vaid","userid"};
	public static final String[] ZZY2_VAP_ADD_VALUE_ARRAY = new String[]{"活动编号","用户编号"};
	//活动图片上传
	public static final String[] ZZY2_VAI_ADD_PARAM_ARRAY = new String[]{"vaid"};
	public static final String[] ZZY2_VAI_ADD_VALUE_ARRAY = new String[]{"活动编号"};
	//活动图片列表
	public static final String[] ZZY2_VAI_LIST_PARAM_ARRAY = new String[]{"vaid"};
	public static final String[] ZZY2_VAI_LIST_VALUE_ARRAY = new String[]{"活动编号"};
	//捐赠发布
	public static final String[] ZZY2_VD_ADD_PARAM_ARRAY = new String[]{"title","target","content","userid"};
	public static final String[] ZZY2_VD_ADD_VALUE_ARRAY = new String[]{"标题","捐赠对象","内容","用户编号"};
	//捐赠列表
	public static final String[] ZZY2_VD_LIST_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY2_VD_LIST_VALUE_ARRAY = new String[]{};
	//捐赠点赞
	public static final String[] ZZY2_VDP_ADD_PARAM_ARRAY = new String[]{"vdid","userid"};
	public static final String[] ZZY2_VDP_ADD_VALUE_ARRAY = new String[]{};
	//养老院列表
	public static final String[] ZZY2_GM_LIST_PARAM_ARRAY = new String[]{};
	public static final String[] ZZY2_GM_LIST_VALUE_ARRAY = new String[]{};
	//养老院点赞
	public static final String[] ZZY2_GMP_ADD_PARAM_ARRAY = new String[]{"userid","gmid"};
	public static final String[] ZZY2_GMP_ADD_VALUE_ARRAY = new String[]{"用户编号","养老院编号"};
	//养老院评论列表
	public static final String[] ZZY2_GMN_LIST_PARAM_ARRAY = new String[]{"gmid"};
	public static final String[] ZZY2_GMN_LIST_VALUE_ARRAY = new String[]{"养老院编号"};
	//养老院评论发表
	public static final String[] ZZY2_GMN_ADD_PARAM_ARRAY = new String[]{"gmid","userid","content"};
	public static final String[] ZZY2_GMN_ADD_VALUE_ARRAY = new String[]{"养老院编号","用户编号","评论内容"};
	//健康管家记录添加
	public static final String[] ZZY2_GMH_ADD_PARAM_ARRAY = new String[]{"userid","value"};
	public static final String[] ZZY2_GMH_ADD_VALUE_ARRAY = new String[]{"用户编号","键值串"};
	//健康管家历史记录
	public static final String[] ZZY2_GMH_LIST_PARAM_ARRAY = new String[]{"userid","type"};
	public static final String[] ZZY2_GMH_LIST_VALUE_ARRAY = new String[]{"用户编号","类别"};
	
	//评测结果添加
	public static final String[] ZZY2_GMAR_ADD_PARAM_ARRAY = new String[]{"userid","json"};
	public static final String[] ZZY2_GMAR_ADD_VALUE_ARRAY = new String[]{"用户编号","键值串"};
	//意见反馈
	public static final String[] ZZY2_VF_ADD_PARAM_ARRAY = new String[]{"userid","content"};
	public static final String[] ZZY2_VF_ADD_VALUE_ARRAY = new String[]{"用户编号","内容"};
}
