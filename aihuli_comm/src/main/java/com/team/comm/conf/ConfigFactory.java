package com.team.comm.conf;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.team.comm.util.ServerUtils;


public class ConfigFactory
{
  private static ConfigFactory me = null;
  public static final String SYS_CFG_NAME = "conf";
  private SystemConfig systemConfig = null;
  private Document doc = null;
  private HashMap hmConf = new HashMap();
  private String path = ServerUtils.getConfigPath();
  private String fileName = "system-config.xml";

  private static synchronized ConfigFactory getInstance()
  {
    if (me == null)
      me = new ConfigFactory();
    return me;
  }

  public static void init(String path, String filename)
    throws Exception
  {
    getInstance();
    setPath(path);
    setFileName(filename);
    load();
  }

  public static SystemConfig getSystemConfig() {
    return getSystemConfig(getPath());
  }

  public static void load() throws Exception {
    SAXBuilder builder = new SAXBuilder();
    InputStream is = new FileInputStream(getPath() + getFileName());
    getInstance().doc = builder.build(is);
    if (is != null)
      try {
        is.close();
      } catch (Exception ex) {
        is = null;
      }

    Element root = getInstance().doc.getRootElement();
    if (root == null)
    {
      return;
    }
    List parent = root.getChildren();
    int emtcnt = parent.size();
    Element chd = null;
    String name = null;
    String type = null;
    String filename = null;
    String cfgpath = null;
    String filepath = null;
    for (int i = 0; i < emtcnt; ++i) {
      chd = (Element)parent.get(i);
      name = chd.getAttributeValue("name");
      type = chd.getAttributeValue("type");
      cfgpath = chd.getChild("path").getText();
      filename = chd.getChild("filename").getText();
      if ((cfgpath == null) || (cfgpath.length() == 0))
        filepath = getPath() + filename;
      else
        filepath = cfgpath + filename;

      Class cls = Class.forName(type);
      Class[] para = new Class[1];
      para[0] = String.class;
      Method md = cls.getMethod("load", para);
      Object[] obj = new Object[1];
      obj[0] = filepath;
      Object cfgobj = cls.newInstance();
      md.invoke(cfgobj, obj);
      if ((name != null) && (name.length() != 0))
        getInstance().hmConf.put(name, cfgobj);
    }
  }

  public static Object getConfByName(String name) throws Exception
  {
    Object obj = getInstance().hmConf.get(name);
    return ((obj != null) ? obj : null);
  }

  public static SystemConfig getSystemConfig(String path) {
    synchronized (SystemConfig.class) {
      if (getInstance().systemConfig == null) {
        getInstance().systemConfig = new SystemConfig();
        getInstance().systemConfig.setConfigFile(path + getFileName());
        getInstance().systemConfig.load();
      }
    }
    return getInstance().systemConfig;
  }

  public static String getFileName()
  {
    return getInstance().fileName;
  }

  public static void setFileName(String fileName)
  {
    getInstance().fileName = fileName;
  }

  public static String getPath()
  {
    return getInstance().path;
  }

  public static void setPath(String path)
  {
    getInstance().path = path;
  }
  
  public static void main(String[] args)
  {
    try {
      init("D:\\Pro168\\src", "configfactory.xml");

      SystemConfig sc = (SystemConfig)
        getConfByName("conf");
      getInstance().hmConf.get("conf");
      System.out.println("conf");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("conf");
  }
}