package com.team.comm.conf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.Format.TextMode;
import org.jdom.output.XMLOutputter;

public class SystemConfig
  implements Runnable
{
  private InputStream is = null;
  private String configFileName;
  public Document sysDocument;
  private Element sysElement;
  private Element macroElement;
  private MacroDef macro;
  private Vector dataListeners = new Vector();

  public void setConfigFile(String file)
  {
    this.configFileName = file;
  }

  public String getConfigFileName() {
    return this.configFileName;
  }

  public void init() {
    new Thread(this).start();
  }

  public void run()
  {
  }

  public void load()
  {
    load(getConfigFileName());
  }

  public synchronized void load(String filepath)
  {
    this.configFileName = filepath;
    try {
      SAXBuilder builder = new SAXBuilder();
      this.is = new FileInputStream(filepath);
      this.sysDocument = builder.build(this.is);
    }
    catch (Exception ex) {
      throw new RuntimeException("SystemConfig load error!", ex);
    } finally {
      if (this.is != null)
        try {
          this.is.close();
        } catch (Exception e) {
          this.is = null;
        }
    }

    this.sysElement = this.sysDocument.getRootElement();
    this.macroElement = this.sysElement.getChild("MacroDefine");
    this.macro = new MacroDef(this.macroElement);
    doRefresh();
  }

  public void save()
    throws Exception
  {
    FileOutputStream outStream;
    try
    {
      outStream = new FileOutputStream(this.configFileName);

      Format fm = Format.getRawFormat();
      fm.setEncoding("GB2312");
      fm.setLineSeparator(System.getProperties().getProperty
        ("line.separator"));
      fm.setIndent("    ");
      fm.setTextMode(Format.TextMode.TRIM_FULL_WHITE);

      XMLOutputter outp = new XMLOutputter(fm);
      outp.output(this.sysDocument, outStream);
      outStream.close();
      doRefresh();
    }
    catch (IOException ex)
    {
      throw new Exception("�����ļ�����ʧ�ܣ�");
    }
  }

  public ModuleConfig getModuleConfig(String moduleName)
  {
    checkConfig(moduleName);

    ModuleConfig mConfig = null;
    String mName = null;
    List list = this.sysElement.getChildren("ModuleConfig");
    Iterator itrts = list.iterator();
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      mName = element.getAttribute("Name").getValue();
      if (mName.equalsIgnoreCase(moduleName)) {
        mConfig = new ModuleConfig(element);
        break;
      }
    }
    return mConfig;
  }

  private void checkConfig(String moduleName) {
    if ((this.sysDocument == null) || (this.sysElement == null)) {
      load();
      return;
    }
    List list = this.sysElement.getChildren("ModuleConfig");
    if (list == null)
      load();
  }

  public ModuleConfig[] getModuleConfigs()
  {
    ModuleConfig[] mConfigs = (ModuleConfig[])null;
    List list = this.sysElement.getChildren("ModuleConfig");
    mConfigs = new ModuleConfig[list.size()];
    Iterator itrts = list.iterator();
    int i = 0;
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      mConfigs[i] = new ModuleConfig(element);
      ++i;
    }
    return mConfigs;
  }

  public MacroDef getMacroConfig()
  {
    return this.macro;
  }

  public void addModuleConfig(ModuleConfig module)
    throws Exception
  {
    if (getModuleConfig(module.getModuleName()) != null)
      throw new Exception("���ظ���ģ����:" + module.getModuleName());

    this.sysElement.addContent(module.getElement());
    save();
  }

  public boolean removeModuleConfig(String moduleName)
    throws Exception
  {
    ModuleConfig mcfg = getModuleConfig(moduleName);
    if (mcfg == null)
      return false;

    boolean isSuccess = this.sysElement.removeContent(mcfg.getElement());
    if (isSuccess)
      save();

    return isSuccess;
  }

  public void modifyModuleConfig(ModuleConfig module)
    throws Exception
  {
    ModuleConfig mcfg = getModuleConfig(module.getModuleName());
    mcfg.setDescription(module.getDescription());
    save();
  }

  public void modifyItem(String moduleName, Item item)
    throws Exception
  {
    ModuleConfig mcfg = getModuleConfig(moduleName);
    mcfg.getItem(item.getName()).modify(item);

    save();
  }

  public void modifyArrayItem(String moduleName, ArrayItem aItem)
    throws Exception
  {
    ModuleConfig mcfg = getModuleConfig(moduleName);
    mcfg.getArrayItem(aItem.getName()).modify(aItem);
    save();
  }

  public void addItem(String moduleName, Item item)
    throws Exception
  {
    ModuleConfig mcfg = getModuleConfig(moduleName);
    if (mcfg.getItem(item.getName()) != null)
      throw new Exception("���ظ���������:" + item.getName());

    mcfg.getElement().addContent(item.getElement());
    save();
  }

  public void addArrayItem(String moduleName, ArrayItem aitem)
    throws Exception
  {
    ModuleConfig mcfg = getModuleConfig(moduleName);
    if (mcfg.getArrayItem(aitem.getName()) != null)
      throw new Exception("���ظ���������:" + aitem.getName());

    mcfg.getElement().addContent(aitem.getElement());
    save();
  }

  public boolean removeItem(String moduleName, String itemName)
    throws Exception
  {
    ModuleConfig mcfg = getModuleConfig(moduleName);
    Item item = mcfg.getItem(itemName);
    if (item != null) {
      boolean isok = mcfg.removeItem(itemName);
      save();
      return isok;
    }
    boolean isok = mcfg.removeArrayItem(itemName);
    save();
    return isok;
  }

  public synchronized void addConfigDataListener(ConfigDataListener listener)
  {
    Object obj = getConfigObj(listener.getListenerName());
    if (obj != null)
      removeListener(listener.getListenerName());

    this.dataListeners.add(listener);
  }

  public synchronized Object getConfigListener(String listenerName)
  {
    return getConfigObj(listenerName);
  }

  public synchronized void removeConfigDataListener(ConfigDataListener listener)
  {
    this.dataListeners.remove(listener);
  }

  private void doRefresh()
  {
    ConfigDataListener listener = null;
    int size = this.dataListeners.size();
    for (int i = 0; i < size; ) {
      try {
        listener = (ConfigDataListener)this.dataListeners.get(i);

        listener.doConfigRefresh();
      }
      catch (Throwable localThrowable)
      {
      }
      ++i;
    }
  }

  protected Document getDocument()
  {
    return this.sysDocument;
  }

  private void removeListener(String listenerName)
  {
    ConfigDataListener listener = null;
    Vector tmpListeners = (Vector)this.dataListeners.clone();
    int size = tmpListeners.size();
    for (int i = 0; i < size; ++i) {
      listener = (ConfigDataListener)tmpListeners.get(i);
      if (listener.getListenerName().equals(listenerName)) {
        this.dataListeners.remove(listener);

        return;
      }
    }
  }

  private Object getConfigObj(String listenerName)
  {
    Object obj = null;
    ConfigDataListener listener = null;
    int size = this.dataListeners.size();
    for (int i = 0; i < size; ++i) {
      listener = (ConfigDataListener)this.dataListeners.get(i);
      if (listener.getListenerName().equals(listenerName)) {
        obj = this.dataListeners.get(i);

        break;
      }
    }
    return obj;
  }

  public synchronized ConfigDataListener[] getListenerNames()
  {
    int size = this.dataListeners.size();
    ConfigDataListener[] names = new ConfigDataListener[size];
    ConfigDataListener listener = null;
    for (int i = 0; i < size; ++i) {
      listener = (ConfigDataListener)this.dataListeners.get(i);
      names[i] = listener;
    }
    return names;
  }

  public void clearListeners()
  {
    this.dataListeners.clear();
  }

  public int getListenerCount()
  {
    return this.dataListeners.size();
  }

  protected void finalize()
  {
    try
    {
      if (this.is == null) return;
      this.is.close();
    }
    catch (Exception localException)
    {
    }
  }

  public void addMacro(String name, String value, String desc)
    throws Exception
  {
    if (this.macroElement == null) {
      this.macroElement = new Element("MacroDefine");
      this.sysElement.addContent(this.macroElement);
      this.macro.setElement(this.macroElement);
    }
    this.macro.addMacro(name, value, desc);
    save();
  }

  public void modifyMacro(String name, String value, String desc)
    throws Exception
  {
    this.macro.modifyMacro(name, value, desc);
    save();
  }

  public boolean removeMacro(String name)
    throws Exception
  {
    if (isFileContains("${" + name + "}"))
      throw new Exception("����:" + name + "���ڱ�ʹ�ã�����ɾ��");

    boolean isok = this.macro.removeMacro(name);
    if (isok)
      save();

    return isok;
  }

  private boolean isFileContains(String ss)
  {
    boolean res = false;
    FileReader fr = null;
    try {
      fr = new FileReader(getConfigFileName());
      char[] cs = new char[1024];
      String tmp = null;
      while (fr.read(cs) != -1) {
        tmp = new String(cs);
        if (tmp.indexOf(ss) != -1) {
          res = true;
          break;
        }
      }
    } catch (Exception cs) {
    }
    try {
      label70: fr.close();
    } catch (IOException cs) {
    }
    return res;
  }

  public static void main(String[] s)
    throws Exception
  {
    String cfgFileName = "test-system-config.xml";
    SystemConfig sc = new SystemConfig();
    sc.setConfigFile("D:\\workspace\\cms\\src\\" + cfgFileName);
    sc.load();
    sc.removeItem("module1", "Item10");
    Item item = new Item("test");
    item.setId("111");
    item.setValue("http://asldfha.com:9&009/asldkfjh");
    try {
      sc.addItem("module1", item);
      sc.save();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    sc.removeItem("module1", "item11");
    ArrayItem ai = new ArrayItem("arraTest");
    ai.setDescription("description *************");
    ArrayValue av = new ArrayValue();
    av.setId("333");
    av.setValue("&KKKKKKKKKKKKKKKKK");
    av.setReserved("");
    ai.addArrayValue(av);
    ArrayValue av1 = new ArrayValue();
    av1.setId("333");
    av1.setValue("&KKKKKKKKKKKKKKKKK");
    av1.setReserved("");
    ai.addArrayValue(av1);
    try
    {
      sc.addArrayItem("module1", ai);
      sc.save();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}