package com.team.comm.conf;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.CDATA;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.Format.TextMode;
import org.jdom.output.XMLOutputter;

public class AbstractItem
  implements Serializable
{
  protected Element element = null;

  public String getDescription()
  {
    Element e = this.element.getChild("Description");
    if (e == null)
      return null;

    String desc = e.getText();
    return desc;
  }

  public void setDescription(String desc)
  {
    addElement("Description", desc);
  }

  public void addAttribute(String name, String value)
  {
    this.element.setAttribute(name, value);
  }

  public void removeAttribute(String attName)
  {
    this.element.removeAttribute(attName);
  }

  public String getName()
  {
    Element e = this.element.getChild("Name");
    if (e == null)
      return null;

    return e.getText();
  }

  public void setName(String itemName)
  {
    addElement("Name", itemName);
  }

  public Attribute getAttribute(String attribName)
  {
    org.jdom.Attribute tempAtt = this.element.getAttribute(attribName);
    return new Attribute(tempAtt.getName(), 
      tempAtt.getValue());
  }

  public String getOrigAttributeValue(String attributeName)
  {
    org.jdom.Attribute a = this.element.getAttribute(attributeName);
    if (a == null)
      return null;

    return a.getValue();
  }

  public String getAttributeValue(String attributeName)
  {
    MacroDef macro = MacroDef.getInstance();
    String value = this.element.getAttribute(attributeName).getValue();
    return macro.replaceByMacro(value);
  }

  public Attribute[] getAttributes()
  {
    List list = this.element.getAttributes();
    Attribute[] attriubutes = new Attribute[list.size
      ()];
    Iterator itrts = list.iterator();
    int i = 0;
    while (itrts.hasNext()) {
      org.jdom.Attribute tempAattribute = 
        (org.jdom.Attribute)itrts.next
        ();
      Attribute myAttr = new Attribute(
        tempAattribute.getName(), tempAattribute.getValue());
      attriubutes[i] = myAttr;
      ++i;
    }
    return attriubutes;
  }

  public void showData()
  {
    try
    {
      Format fm = Format.getRawFormat();
      fm.setEncoding("UTF-8");
      fm.setLineSeparator(System.getProperties().getProperty
        ("line.separator"));
      fm.setIndent("    ");
      fm.setTextMode(Format.TextMode.TRIM_FULL_WHITE);

      XMLOutputter out = new XMLOutputter(
        fm);

      out.output(this.element, System.out);
    }
    catch (IOException fm)
    {
    }
  }

  protected void addElement(String name, String value)
  {
    this.element.removeChild(name);
    Element ele = new Element(name);

    this.element.addContent(ele);
    if ((value.indexOf("&") != -1) || (value.indexOf("<") != -1) || 
      (value.indexOf(">") != -1)) {
      CDATA cdata = new CDATA(value);
      ele.addContent(cdata);
    } else {
      ele.setText(value);
    }
  }

  public void setElement(Element ele)
  {
    this.element = ele;
  }

  protected Element getElement()
  {
    return this.element;
  }

  public String toString() {
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

      out.output(this.element, sw);
      xml = sw.toString();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return xml;
  }
}