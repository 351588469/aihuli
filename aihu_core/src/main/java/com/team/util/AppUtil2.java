package com.team.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.util.JSONPObject;

/** 接口参数校验
 */
public class AppUtil2  {
	protected static Logger logger = Logger.getLogger(AppUtil2.class);
	
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
		}else if(method=="appzzy2_uadd"){// 注册
			paramArray =Const2.ZZY2_USER_ADD_PARAM_ARRAY;
			valueArray =Const2.ZZY2_USER_ADD_VALUE_ARRAY;
		}else if(method=="appzzy2_vtadd"){// 团体注册
			paramArray =Const2.ZZY2_VT_ADD_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VT_ADD_VALUE_ARRAY;
		}else if(method=="appzzy2_vtc"){// 团体关注
			paramArray =Const2.ZZY2_VT_CONCERN_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VT_CONCERN_VALUE_ARRAY;
		}else if(method=="appzzy2_vtclist"){// 团体关注列表
			paramArray =Const2.ZZY2_VTC_LIST_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VTC_LIST_VALUE_ARRAY;
		}else if(method=="appzzy2_vttadd"){// 团体话题发表
			paramArray =Const2.ZZY2_VTT_ADD_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VTT_ADD_VALUE_ARRAY;
		}else if(method=="appzzy2_vtlist"){// 团体列表
			paramArray =Const2.ZZY2_VT_LIST_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VT_LIST_VALUE_ARRAY;
		}else if(method=="appzzy2_vttlist"){//团体话题列表
			paramArray =Const2.ZZY2_VTT_LIST_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VTT_LIST_VALUE_ARRAY;
		}else if(method=="appzzy2_vtnlist"){//团体话题评论列表
			paramArray =Const2.ZZY2_VTN_LIST_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VTN_LIST_VALUE_ARRAY;
		}else if(method=="appzzy2_vtnadd"){//团体话题评论发表
			paramArray =Const2.ZZY2_VTN_ADD_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VTN_ADD_VALUE_ARRAY;
		}
		
		else if(method=="appzzy2_vaadd"){//活动申报
			paramArray =Const2.ZZY2_VA_ADD_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VA_ADD_VALUE_ARRAY;
		}else if(method=="appzzy2_valist"){//活动列表
			paramArray =Const2.ZZY2_VA_LIST_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VA_LIST_VALUE_ARRAY;
		}else if(method=="appzzy2_vaelist"){//活动报名列表
			paramArray =Const2.ZZY2_VAE_LIST_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VAE_LIST_VALUE_ARRAY;
		}else if(method=="appzzy2_vae"){//活动报名
			paramArray =Const2.ZZY2_VAE_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VAE_VALUE_ARRAY;
		}else if(method=="appzzy2_vanadd"){//活动评论发表
			paramArray =Const2.ZZY2_VAN_ADD_PARAM_ARRAY;
			valueArray =Const2.ZZY2_VAN_ADD_VALUE_ARRAY;
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
