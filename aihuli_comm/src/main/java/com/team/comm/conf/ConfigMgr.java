package com.team.comm.conf;

import java.util.HashMap;
import java.util.Map;

public class ConfigMgr
{
  public static String getCfgFileName()
  {
    return ConfigFactory.getFileName();
  }

  public static synchronized String getItemValue(String moduleStr, String itemStr)
  {
    ModuleConfig mc = ConfigFactory.getSystemConfig().getModuleConfig(moduleStr);
    if (mc == null)
      return null;

    Item item = mc.getItem(itemStr);
    if (item == null)
      return null;

    return item.getValue();
  }

  public static synchronized Map getArrayItem4Map(String moduleStr, String itemStr) {
    Map ret = null;
    ModuleConfig mc = ConfigFactory.getSystemConfig().getModuleConfig(
      moduleStr);
    if (mc == null)
      return null;

    ArrayItem aitem = mc.getArrayItem(itemStr);
    if (aitem == null)
      return null;

    ArrayValue[] av = aitem.getArrayValues();
    if (av == null)
      return null;

    ret = new HashMap();
    for (int i = 0; i < av.length; ++i) {
      if (av[i].getReserved() != null)
        ret.put(av[i].getReserved(), av[i].getValue());

    }

    return ret;
  }

  public static synchronized String[] getArrayItemValue(String moduleStr, String itemStr) {
    ModuleConfig mc = ConfigFactory.getSystemConfig().getModuleConfig(moduleStr);
    if (mc == null)
      return null;

    ArrayItem aitem = mc.getArrayItem(itemStr);
    if (aitem == null)
      return null;

    return aitem.getValueList();
  }

  public static synchronized void modifyItemValue(String moduleStr, String itemStr, String val) throws Exception
  {
    SystemConfig sc = ConfigFactory.getSystemConfig();
    ModuleConfig mc = sc.getModuleConfig(moduleStr);
    if (mc == null)
      throw new Exception("修改配置失败");

    Item item = mc.getItem(itemStr);
    if (item == null)
      throw new Exception("修改配置失败");

    item.setValue(val);
    try {
      sc.save();
    } catch (Exception ce) {
      throw new Exception("修改配置失败");
    }
  }

  public static void main(String[] args) throws Exception
  {
    modifyItemValue("Email", "PassWord", "zeng");
  }
}