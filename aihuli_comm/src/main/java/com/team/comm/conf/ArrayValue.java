package com.team.comm.conf;

import java.io.Serializable;
import org.jdom.CDATA;
import org.jdom.Element;

public class ArrayValue
  implements Serializable
{
  private Element arrayElement = null;

  public ArrayValue(Element element)
  {
    this.arrayElement = element;
  }

  public ArrayValue()
  {
    this.arrayElement = new Element("ArrayValue");
  }

  public String getId()
  {
    return this.arrayElement.getChildText("Id");
  }

  public void setId(String id)
  {
    setValueItem("Id", id);
  }

  public String getOrigValue()
  {
    return this.arrayElement.getChildText("Value");
  }

  public String getValue()
  {
    MacroDef macro = MacroDef.getInstance();
    String value = this.arrayElement.getChildText("Value");
    return macro.replaceByMacro(value);
  }

  public void setValue(String value)
  {
    setValueItem("Value", value);
  }

  public String getOrigReserved()
  {
    return this.arrayElement.getChildText("Reserved");
  }

  public String getReserved()
  {
    MacroDef macro = MacroDef.getInstance();
    String value = this.arrayElement.getChildText("Reserved");
    return macro.replaceByMacro(value);
  }

  public void setReserved(String resv)
  {
    setValueItem("Reserved", resv);
  }

  protected Element getElement()
  {
    return this.arrayElement;
  }

  private void setValueItem(String name, String value)
  {
    this.arrayElement.removeChild(name);
    Element element = new Element(name);
    if ((value.indexOf("&") != -1) || (value.indexOf("<") != -1) || 
      (value.indexOf(">") != -1)) {
      CDATA cdata = new CDATA(value);
      element.addContent(cdata);
    } else {
      element.setText(value);
    }
    this.arrayElement.addContent(element);
  }
}