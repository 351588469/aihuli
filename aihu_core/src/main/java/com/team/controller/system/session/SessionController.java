package com.team.controller.system.session;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.controller.base.BaseController;

@Controller
@RequestMapping(value="/SYSsession")
public class SessionController  extends BaseController{
	@RequestMapping(value="/resetSession")
	@ResponseBody
	public void resetSession(HttpSession session,String version)throws Exception{
		@SuppressWarnings("unchecked")
		Enumeration<String> e=session.getAttributeNames(); 
		while(e.hasMoreElements()){ 
			String sessionName=(String)e.nextElement(); 
			//System.out.println("存在的session有："+sessionName); 
			if(sessionName.substring(0,3).equals("ZZY")){
				if(sessionName.equals("ZZY_GM_ID")){
					Integer role=(Integer) session.getAttribute("SYS_ZZY_ROLE");
					if(role==null||role!=2){
						session.removeAttribute(sessionName); 
					}
				}
			}
		}
	}
}
