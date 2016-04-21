package com.team.util;

import java.util.HashMap;
import java.util.Map;

import com.cloopen.rest.sdk.CCPRestSDK;
public class ZzyValidate {
		public static Map<String,Object>  sendMsg(String mobile,String code){
			HashMap<String, Object> result =new HashMap<>();
			try {
				String sid ="aaf98f89512446e2015142f209f25bd5";
				String token ="702472b80602417babd826c5547f8d4f";
				String appid ="8a48b551528c18940152916bebee094f";
				//String appid ="8a48b551516c09cd01517b66e90f1ca6";//一幢大卡
				//String url ="app.cloopen.com";
				String url ="sandboxapp.cloopen.com";
				String port ="8883";
				//String smsmodelid="54909";//一幢大卡
				String smsmodelid="66361";
				CCPRestSDK restAPI = new CCPRestSDK();
				restAPI.init(url, port);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount(sid, token);// 初始化主帐号名称和主帐号令牌
				restAPI.setAppId(appid);// 初始化应用ID
				result = restAPI.sendTemplateSMS(mobile,smsmodelid,new String[]{code});
				//System.out.println(result.toString());
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
}
