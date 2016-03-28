package com.team.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.util.JSONPObject;

/** 接口参数校验
 */
public class AppUtil  {
	
	protected static Logger logger = Logger.getLogger(AppUtil.class);
	
	/**检查参数是否完整
	 * @param method
	 * @param pd
	 * @return
	 */
	public static boolean checkParam(String method, PageData pd){
		boolean result = false;
		
		int falseCount = 0;
		String[] paramArray = new String[20];
		String[] valueArray = new String[20];
		String[] tempArray  = new String[20];  //临时数组
		
		if(method=="registered"){// 注册
			paramArray = Const.APP_REGISTERED_PARAM_ARRAY;  //参数
			valueArray = Const.APP_REGISTERED_VALUE_ARRAY;  //参数名称
		}else if(method=="getAppuserByUsernmae"){//根据用户名获取会员信息
			paramArray = Const.APP_GETAPPUSER_PARAM_ARRAY;  
			valueArray = Const.APP_GETAPPUSER_VALUE_ARRAY;
		}else if(method=="appzzys_elist"){//护士端首页
			paramArray=Const.ZZY_ZZYS_ELIST_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_ELIST_VALUE_ARRAY;
		}else if(method=="appzzys_login"){//护士端登录
			paramArray=Const.ZZY_ZZYS_LOGIN_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_LOGIN_VALUE_ARRAY;
		}else if(method=="appzzys_einfo"){//护士端老人信息
			paramArray=Const.ZZY_ZZYS_EINFO_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_EINFO_VALUE_ARRAY;
		}else if(method=="appzzys_dmsg"){//护士端每日消息
			paramArray=Const.ZZY_ZZYS_DMSG_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_DMSG_VALUE_ARRAY;
		}
		/**
		 * 新增记录
		 */
		else if(method=="appzzys_rnew"){//护士端新增记录
			paramArray=Const.ZZY_ZZYS_RNEW_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RNEW_VALUE_ARRAY;
		}else if(method=="appzzys_rnew_erh"){//护士端新增健康记录
			paramArray=Const.ZZY_ZZYS_RNEW_ERH_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RNEW_ERH_VALUE_ARRAY;
		}else if(method=="appzzys_rnew_ern"){//护士端新增护理记录
			paramArray=Const.ZZY_ZZYS_RNEW_ERN_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RNEW_ERN_VALUE_ARRAY;
		}else if(method=="appzzys_rnew_erd"){//护士端新增用药记录
			paramArray=Const.ZZY_ZZYS_RNEW_ERD_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RNEW_ERD_VALUE_ARRAY;
		}else if(method=="appzzys_rnew_ero"){//护士端新增其他记录
			paramArray=Const.ZZY_ZZYS_RNEW_ERO_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RNEW_ERO_VALUE_ARRAY;
		}
		/**
		 * 获取历史记录
		 */
		else if(method=="appzzys_rpast"){//护士端获取历史记录
			paramArray=Const.ZZY_ZZYS_RPAST_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RPAST_VALUE_ARRAY;
		}else if(method=="appzzys_rpast_erh"){//护士端获取历史健康记录
			paramArray=Const.ZZY_ZZYS_RPAST_ERH_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RPAST_ERH_VALUE_ARRAY;
		}else if(method=="appzzys_rpast_ern"){//护士端获取历史护理记录
			paramArray=Const.ZZY_ZZYS_RPAST_ERN_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RPAST_ERN_VALUE_ARRAY;
		}else if(method=="appzzys_rpast_erd"){//护士端获取历史用药记录
			paramArray=Const.ZZY_ZZYS_RPAST_ERD_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RPAST_ERD_VALUE_ARRAY;
		}else if(method=="appzzys_rpast_ero"){//护士端获取历史其他记录
			paramArray=Const.ZZY_ZZYS_RPAST_ERO_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_RPAST_ERO_VALUE_ARRAY;
		}
		/**
		 * 新增计划
		 */
		else if(method=="appzzys_pnew"){//护士端新增计划
			paramArray=Const.ZZY_ZZYS_PNEW_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_PNEW_VALUE_ARRAY;
		}else if(method=="appzzys_pnew_enp"){//护士端新增老人护理计划
			paramArray=Const.ZZY_ZZYS_PNEW_ENP_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_PNEW_ENP_VALUE_ARRAY;
		}else if(method=="appzzys_pnew_edp"){//护士端新增老人用药计划
			paramArray=Const.ZZY_ZZYS_PNEW_EDP_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_PNEW_EDP_VALUE_ARRAY;
		}
		/**
		 * 计划列表
		 */
		else if(method=="appzzys_plist"){//护士端计划列表
			paramArray=Const.ZZY_ZZYS_PLIST_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_PLIST_VALUE_ARRAY;
		}else if(method=="appzzys_plist_enp"){//护士端老人护理计划列表
			paramArray=Const.ZZY_ZZYS_PLIST_ENP_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_PLIST_ENP_VALUE_ARRAY;
		}else if(method=="appzzys_plist_edp"){//护士端老人用药计划列表
			paramArray=Const.ZZY_ZZYS_PLIST_EDP_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_PLIST_EDP_VALUE_ARRAY;
		}
		
		
		else if(method=="appzzys_gmnlist"){//护理项目列表
			paramArray=Const.ZZY_ZZYS_GMNLIST_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_GMNLIST_VALUE_ARRAY;
		}
		
		else if(method=="appzzys_beval"){//护士端房间评测
			paramArray=Const.ZZY_ZZYS_BEVAL_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_BEVAL_VALUE_ARRAY;
		}else if(method=="appzzys_bpast"){//护士端房间评测
			paramArray=Const.ZZY_ZZYS_BPAST_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_BPAST_VALUE_ARRAY;
		}
		
		else if(method=="appzzys_alist"){//评测题目列表
			paramArray=Const.ZZY_ZZYS_ALIST_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_ALIST_VALUE_ARRAY;
		}else if(method=="appzzys_aradd"){//新增评测结果
			paramArray=Const.ZZY_ZZYS_ARADD_PARAM_ARRAY;
			valueArray =Const.ZZY_ZZYS_ARADD_VALUE_ARRAY;
		}
		int size = paramArray.length;
		for(int i=0;i<size;i++){
			String param = paramArray[i];
			if(!pd.containsKey(param)){
				tempArray[falseCount] = valueArray[i]+"--"+param;
				falseCount += 1;
			}
		}
		if(falseCount>0){
			logger.error(method+"接口，请求协议中缺少 "+falseCount+"个 参数");
			for(int j=1;j<=falseCount;j++){
				logger.error("   第"+j+"个："+ tempArray[j-1]);
			}
		} else {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 设置分页的参数
	 * @param pd
	 * @return
	 */
	public static PageData setPageParam(PageData pd){
		String page_now_str = pd.get("page_now").toString();
		int pageNowInt = Integer.parseInt(page_now_str)-1;
		String page_size_str = pd.get("page_size").toString(); //每页显示记录数
		int pageSizeInt = Integer.parseInt(page_size_str);
		String page_now = pageNowInt+"";
		String page_start = (pageNowInt*pageSizeInt)+"";
		pd.put("page_now", page_now);
		pd.put("page_start", page_start);
		return pd;
	}
	
	/**设置list中的distance
	 * @param list
	 * @param pd
	 * @return
	 */
	public static List<PageData>  setListDistance(List<PageData> list, PageData pd){
		List<PageData> listReturn = new ArrayList<PageData>();
		String user_longitude = "";
		String user_latitude = "";
		try{
			user_longitude = pd.get("user_longitude").toString(); //"117.11811";
			user_latitude  = pd.get("user_latitude").toString();  //"36.68484";
		} catch(Exception e){
			logger.error("缺失参数--user_longitude和user_longitude");
			logger.error("lost param：user_longitude and user_longitude");
		}
		PageData pdTemp = new PageData();
		int size = list.size();
		for(int i=0;i<size;i++){
			pdTemp = list.get(i);
			String longitude = pdTemp.get("longitude").toString();
			String latitude = pdTemp.get("latitude").toString();
			String distance = MapDistance.getDistance(
						user_longitude,	user_latitude,
						longitude,		latitude
					);
			pdTemp.put("distance", distance);
			pdTemp.put("size", distance.length());
			listReturn.add(pdTemp);
		}
		return listReturn;
	}
	
	/**
	 * @param pd
	 * @param map
	 * @return
	 */
	public static Object returnObject(PageData pd, Map map){
		if(pd.containsKey("callback")){
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}else{
			return map;
		}
	}
}
