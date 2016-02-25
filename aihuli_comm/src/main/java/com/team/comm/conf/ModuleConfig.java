package com.team.comm.conf;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.Format.TextMode;
import org.jdom.output.XMLOutputter;

public class ModuleConfig
  implements Serializable
{
  private Element module = null;
  private String moduleName = null;

  public ModuleConfig(String moduleName)
  {
    Element element = new Element("ModuleConfig");
    element.setAttribute(new Attribute("Name", moduleName));
    this.moduleName = moduleName;
    this.module = element;
  }

  public ModuleConfig(Element element)
  {
    this.module = element;
    this.moduleName = element.getAttribute("Name").getValue();
  }

  public String getModuleName()
  {
    return this.moduleName;
  }

  public String getDescription()
  {
    return this.module.getAttribute("Description").getValue();
  }

  public void setDescription(String desc)
  {
    this.module.setAttribute(new Attribute("Description", desc));
  }

  public String getModuleAttributeValue(String attName)
  {
    return this.module.getAttributeValue(attName);
  }

  public Item getItem(String itemName)
  {
    Item item = null;
    List list = this.module.getChildren("ConfigItem");
    Iterator itrts = list.iterator();
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      if (element.getChild("Name").getText().equalsIgnoreCase(itemName)) {
        item = new Item(element);
        break;
      }
    }

    return item;
  }

  public Item[] getItems()
  {
    List list = this.module.getChildren("ConfigItem");
    Item[] items = new Item[list.size()];
    Iterator itrts = list.iterator();
    int i = 0;
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      Item item = new Item(element);
      items[i] = item;
      ++i;
    }
    return items;
  }

  public ArrayItem getArrayItem(String itemName)
  {
    ArrayItem arrayItem = null;
    List list = this.module.getChildren("ArrayConfigItem");
    Iterator itrts = list.iterator();
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      if (element.getChild("Name").getText().equalsIgnoreCase(itemName)) {
        arrayItem = new ArrayItem(element);
        break;
      }
    }
    return arrayItem;
  }

  public ArrayItem[] getArrayItems()
  {
    List list = this.module.getChildren("ArrayConfigItem");
    ArrayItem[] arrayItems = new ArrayItem[list.size()];
    Iterator itrts = list.iterator();
    int i = 0;
    while (itrts.hasNext()) {
      Element element = (Element)itrts.next();
      ArrayItem arrayItem = new ArrayItem(element);
      arrayItems[i] = arrayItem;
      ++i;
    }
    return arrayItems;
  }

  public String getItemValue(String itemName)
  {
    Item item = getItem(itemName);
    if (item == null)
      return null;

    return item.getValue();
  }

  public String getItemAttributeValue(String itemName, String attributeName)
  {
    Item item = getItem(itemName);
    if (item == null)
      return null;

    return item.getAttributeValue(attributeName);
  }

  public String[] getItemValueList(String itemName)
  {
    ArrayItem arrayItem = getArrayItem(itemName);
    if (arrayItem == null)
      return null;

    return arrayItem.getValueList();
  }

  public void addItem(Item item)
    throws Exception
  {
    if (getItem(item.getName()) != null)
      throw new Exception("有重复的配置项名:" + item.getName());

    this.module.addContent(item.getElement());
  }

  public void addArrayItem(ArrayItem arrayItem)
    throws Exception
  {
    if (getArrayItem(arrayItem.getName()) != null)
      throw new Exception("有重复的配置项名:" + arrayItem.getName());

    this.module.addContent(arrayItem.getElement());
  }

  public boolean removeItem(String itemName)
  {
    Item item = getItem(itemName);
    if (item == null)
      return false;

    return this.module.removeContent(item.getElement());
  }

  public boolean removeArrayItem(String itemName)
  {
    ArrayItem ai = getArrayItem(itemName);
    if (ai == null)
      return false;

    return this.module.removeContent(ai.getElement());
  }

  protected Element getElement()
  {
    return this.module;
  }

  public String toString()
  {
    String xml = null;
    try {
      StringWriter sw = new StringWriter(1000);

      Format fm = Format.getRawFormat();
      fm.setEncoding("UTF-8");
      fm.setLineSeparator(System.getProperties().getProperty
        ("line.separator"));
      fm.setIndent("    ");
      fm.setTextMode(Format.TextMode.TRIM_FULL_WHITE);

      XMLOutputter out = new XMLOutputter(
        fm);

      out.output(this.module, sw);
      xml = sw.toString();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return xml;
  }

  public void showData() {
    Format fm;
    try {
      fm = Format.getRawFormat();
      fm.setEncoding("UTF-8");
      fm.setLineSeparator(System.getProperties().getProperty
        ("line.separator"));
      fm.setIndent("    ");
      fm.setTextMode(Format.TextMode.TRIM_FULL_WHITE);

      XMLOutputter out = new XMLOutputter(
        fm);

      out.output(this.module, System.out);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}