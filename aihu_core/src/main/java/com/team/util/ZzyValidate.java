package com.team.util;

import java.util.HashMap;
//import com.cloopen.rest.sdk.CCPRestSDK;
public class ZzyValidate {
	/*public class SMS_send {
		public Integer sendMsg(String mobile,String code){
			try {
				String sid ="aaf98f89512446e2015142f209f25bd5";
				String token ="702472b80602417babd826c5547f8d4f";
				String appid ="8a48b551528c18940152916bebee094f";
				//String appid ="8a48b551516c09cd01517b66e90f1ca6";//一幢大卡
				String url ="app.cloopen.com";
				String port ="8883";
				//String smsmodelid="54909";//一幢大卡
				String smsmodelid="66361";
				HashMap<String, Object> result = null;
				CCPRestSDK restAPI = new CCPRestSDK();
				restAPI.init(url, port);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount(sid, token);// 初始化主帐号名称和主帐号令牌
				restAPI.setAppId(appid);// 初始化应用ID
				result = restAPI.sendTemplateSMS(mobile,smsmodelid,new String[]{code});
				System.out.println(result.toString());
				if ("000000".equals(result.get("statusCode"))) {
					return 1;
				} else {
					return -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;   
		}
		public static void main(String[] args) {
			SMS_send s=new SMS_send();
			s.sendMsg("15267287489","123");
		}

	}*/
}
