package com.team.comm.system.cache;

import java.util.HashMap;

import com.team.comm.conf.ConfigFactory;
import com.team.comm.conf.Item;
import com.team.comm.conf.ModuleConfig;
import com.team.comm.conf.SystemConfig;

public class SystemCache {
	private static SystemCache sc = null;
	private static HashMap<String, String> systemConfigCache = new HashMap<String, String>();
	
	/**
	 * 单例实现
	 * @return
	 */
	public static SystemCache newInstance() {
		if (sc == null) {
			sc = new SystemCache();
		}
		return sc;
	}
	
	/**
	 * 初始化调用
	 */
	public void init(String systemConf) {
		loadSystemConfig(systemConf);
	}
	
	/**
	 * 缓存系统配置文件中的内容
	 */
	private void loadSystemConfig(String systemConf) {
		systemConfigCache.clear();
		ConfigFactory.setFileName(systemConf);
		SystemConfig sc = ConfigFactory.getSystemConfig();
		
		ModuleConfig[] mcs = sc.getModuleConfigs();
		for(int i = 0; i < mcs.length; i++) {
			ModuleConfig mc = mcs[i];
			Item[] its = mc.getItems();
			for(int j = 0; j < its.length; j++) {
				Item it = its[j];
				systemConfigCache.put(it.getName(),it.getValue());
			}
		}
	}

	public static HashMap<String, String> getSystemConfigCache() {
		return systemConfigCache;
	}
}
