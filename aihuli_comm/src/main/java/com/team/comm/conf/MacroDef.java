package com.team.comm.conf;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jdom.Element;

public class MacroDef
{
  private static MacroDef instance = null;
  private static Hashtable macros = new Hashtable();
  private static Hashtable macroDesp = new Hashtable();
  private static ArrayList macroNameAl = new ArrayList();
  private Element eleMacro = null;

  public ArrayList getMacroNameAl()
  {
    return macroNameAl;
  }

  public MacroDef(Element ele)
  {
    this.eleMacro = ele;
    readData();
    instance = this;
  }

  public static MacroDef getInstance()
  {
    return instance;
  }

  public Element getMacroRootElement()
  {
    return this.eleMacro;
  }

  public void setElement(Element ele)
  {
    this.eleMacro = ele;
    macros.clear();
    readData();
  }

  public Element getElement()
  {
    return this.eleMacro;
  }

  private void readData()
  {
    if (this.eleMacro == null)
      return;

    List list = this.eleMacro.getChildren("Macro");
    Iterator itrts = list.iterator();
    String name = null;
    String value = null;
    String desc = null;
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      name = element.getAttributeValue("name");
      value = element.getAttributeValue("value");
      desc = element.getAttributeValue("description");
      macros.put(name, value);
      macroDesp.put(name, desc);
      macroNameAl.add(name);
    }
  }

  private Element getMacroElement(String name)
  {
    Element ele = null;
    List list = this.eleMacro.getChildren("Macro");
    Iterator itrts = list.iterator();
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      if (element.getAttributeValue("name").equalsIgnoreCase(name)) {
        ele = element;
        break;
      }
    }
    return ele;
  }

  public Hashtable getMacros()
  {
    return macros;
  }

  public Hashtable getMacroDesps()
  {
    return macroDesp;
  }

  public String getMacroValue(String name)
  {
    return ((String)macros.get(name));
  }

  public boolean isDefined(String name)
  {
    return macros.containsKey(name);
  }

  public synchronized boolean removeMacro(String name)
  {
    macros.remove(name);
    macroDesp.remove(name);
    macroNameAl.remove(name);
    return this.eleMacro.removeContent(getMacroElement(name));
  }

  public synchronized void addMacro(String name, String value, String desc)
    throws Exception
  {
    if (getMacroElement(name) != null)
      throw new Exception("有重复的宏名");

    macros.put(name, value);
    macroDesp.put(name, desc);
    macroNameAl.add(name);
    Element ele = new Element("Macro");
    ele.setAttribute("name", name);
    ele.setAttribute("value", value);
    ele.setAttribute("description", desc);
    this.eleMacro.addContent(ele);
  }

  public synchronized void modifyMacro(String name, String value, String desc)
  {
    macros.remove(name);
    macros.put(name, value);
    macroDesp.remove(name);
    macroDesp.put(name, desc);
    macroNameAl.remove(name);
    macroNameAl.add(name);
    Element ele = getMacroElement(name);
    ele.setAttribute("value", value);
    ele.setAttribute("description", desc);
  }

  public String replaceByMacro(String value)
  {
    String res = value;
    if (value.indexOf("${") == -1)
      return res;

    res = replaceAll(res, macros);
    return res;
  }

  public static String replaceAll(String source, Hashtable mcs)
  {
    String res = source;
    String name = null;
    String macroValue = null;
    Iterator it = mcs.keySet().iterator();
    while (it.hasNext()) {
      name = (String)it.next();
      if (source.indexOf("${" + name + "}") == -1)
        continue;

      macroValue = (String)mcs.get(name);
      res = replaceString(res, "${" + name + "}", macroValue);
    }
    return res;
  }

  public static String replaceString(String source, String orig, String real)
  {
    String res = "";
    String prefix = null;
    String suffix = source;
    int index = source.indexOf(orig);
    if (index == -1)
      return source;
    do
    {
      prefix = suffix.substring(0, index);
      res = res + prefix + real;
      suffix = suffix.substring(index + orig.length());
      index = suffix.indexOf(orig);
      if (index == -1)
        res = res + suffix;
    }
    while (index != -1);

    return res;
  }

  public void showData()
  {
  }

  public static void main(String[] args)
  {
    Element eleRoot = new Element("MacroDefine");
    Element ele = new Element("Macro");
    ele.setAttribute("name", "HHHH");
    ele.setAttribute("value", "-99999-");
    eleRoot.addContent(ele);
    ele = new Element("Macro");
    ele.setAttribute("name", "KKKK");
    ele.setAttribute("value", "-88888-");
    eleRoot.addContent(ele);

    MacroDef md = new MacroDef(eleRoot);
    md.showData();
    String s = "$${{}$${${${}P{HHHH}abcd${${HHO}H}efghijk${HHHH}{$${{}{$}-KOIIII${KKKK}PPPPPPP${HHHH}hh${KKKK}}}$}";
    String res = md.replaceByMacro(s);
  }
}