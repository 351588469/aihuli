package com.team.comm.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.team.comm.util.PublicUtil;


public class SystemInitProperty extends HttpServlet implements
		ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		try {
//			String path = System.getProperty("user.dir");
//			if(path.lastIndexOf("bin")>0){
//				path=path.substring(0, path.lastIndexOf("bin"));
//				path+="webapps"+PublicUtil.getFileSeparator() +"hzsp_admin";
//			}
//			String admin_conf=sce.getServletContext().getInitParameter("confdir").trim();
//			path += PublicUtil.getFileSeparator()+admin_conf+PublicUtil.getFileSeparator();
			
			String path = sce.getServletContext().getInitParameter("confdir").trim();
			if(!path.endsWith(PublicUtil.getFileSeparator())){
				path=path+PublicUtil.getFileSeparator();
			}
			System.out.println("IPTV系统配置文件路径：" + path);
			SystemInitServer initServer = new SystemInitServer(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("IPTV系统关闭开始!");
	}

	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
}
