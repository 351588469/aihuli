package com.team.comm.system;

import java.io.File;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

import com.team.comm.system.cache.SystemCache;
import com.team.comm.util.ServerUtils;

public class SystemInitServer {
	private static Log LOG = null;

	public SystemInitServer() {
		init("");
	}

	public SystemInitServer(String path) {
		init(path);
	}

	/**
	 * 启动所有的服务,顺序不可以变动
	 */
	private void init(String path) {
		ServerUtils.setConfigPath(path); //设置绝对路径
		String logFilePath = path + "log4j.xml";
		configLog(logFilePath, 60);
		System.out.println("日志组件加载成功");
		LOG = LogFactory.getLog(SystemInitServer.class);
		LOG.debug("------------后台系统开始启动------------");
		
		// 将系统缺省时区设置为亚洲上海所在的时区。
		// 在UNIX系统中，java默认采用格林尼治时间，从而导致java时间和中国北京时间相差8个小时
		LOG.debug("------------系统缺省时区为" + TimeZone.getDefault().getDisplayName() + "------------");
		LOG.debug("当前时间:" + new Date());
		LOG.debug("------------设置系统缺省时区为Asia/Shanghai------------");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		LOG.debug("------------系统缺省时区为" + TimeZone.getDefault().getDisplayName() + "------------");
		LOG.debug("当前时间:" + new Date());
	
		
		LOG.debug("系统配置文件组件加载开始");
		SystemCache  sc = SystemCache.newInstance();
		sc.init("system-config.xml");
		LOG.debug("系统配置文件组件加载结束");
		
		LOG.debug("------------系统启动完成------------");
	}
	
	 private static boolean isConfigOk = false;
	 public static synchronized void configLog(String file, int refresh)
	  {
	    if (isConfigOk)
	      return;
	    if ((file == null) || (file.equals(""))) {
	      System.err.println("log4j.xml not set");
	      return;
	    }
	    System.out.println("Config file:" + file);
	    File confFile = new File(file);
	    if (!(confFile.exists())) {
	      System.err.println("log4j.xml not found");
	      return;
	    }
	    System.out.println("refresh time:" + refresh);
	    DOMConfigurator.configureAndWatch(file, refresh * 1000);
	    isConfigOk = true;
	    System.out.println("Config log successfully");
	  }
}
