package com.team.comm.conf;

import java.io.Serializable;
import org.jdom.Element;

public class Item extends AbstractItem
  implements Serializable
{
  public Item()
  {
    this.element = new Element("ConfigItem");
  }

  public Item(Element element)
  {
    this.element = element;
  }

  public Item(String itemName)
  {
    this.element = new Element("ConfigItem");
    addElement("Name", itemName);
  }

  public String getOrigValue()
  {
    return this.element.getChild("Value").getText();
  }

  public String getValue()
  {
    MacroDef macro = MacroDef.getInstance();
    String value = this.element.getChild("Value").getText();
    return macro.replaceByMacro(value);
  }

  public void setValue(String value)
  {
    addElement("Value", value);
  }

  public String getId()
  {
    return this.element.getChild("Id").getText();
  }

  public void setId(String id)
  {
    addElement("Id", id);
  }

  public String getOrigReserved()
  {
    return this.element.getChild("Reserved").getText();
  }

  public String getReserved()
  {
    MacroDef macro = MacroDef.getInstance();
    String value = this.element.getChild("Reserved").getText();
    return macro.replaceByMacro(value);
  }

  public void setReserved(String value)
  {
    addElement("Reserved", value);
  }

  public void setAttributeValue(String attributeName, String value)
  {
    this.element.setAttribute(attributeName, value);
  }

  public void modify(Item item)
  {
    setId(item.getId());
    setDescription(item.getDescription());
    setValue(item.getOrigValue());
    setReserved(item.getOrigReserved());
    Attribute[] atts = item.getAttributes();
    if ((atts != null) && (atts.length > 0))
      for (int i = 0; i < atts.length; ++i)
        setAttributeValue(atts[i].getName(), 
          atts[i].getOrigValue());
  }
}