package com.team.comm.conf;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

public class ArrayItem extends AbstractItem
  implements Serializable
{
  public ArrayItem()
  {
    this.element = new Element("ArrayConfigItem");
  }

  public ArrayItem(Element element)
  {
    this.element = element;
  }

  public ArrayItem(String name)
  {
    this.element = new Element("ArrayConfigItem");
    this.element.addContent(new Element("Name"));
    this.element.getChild("Name").setText(name);
  }

  public void addArrayValue(ArrayValue av) {
    this.element.addContent(av.getElement());
  }

  public ArrayValue[] getArrayValues()
  {
    List list = this.element.getChildren("ArrayValue");
    ArrayValue[] avs = new ArrayValue[list.size()];
    Iterator itrts = list.iterator();
    int i = 0;
    while (itrts.hasNext()) {
      Element ele = (Element)itrts.next();
      ArrayValue av = new ArrayValue(ele);
      avs[i] = av;
      ++i;
    }
    return avs;
  }

  public String[] getValueList()
  {
    List list = this.element.getChildren("ArrayValue");
    String[] avs = new String[list.size()];
    Iterator itrts = list.iterator();
    int i = 0;
    while (itrts.hasNext()) {
      Element ele = (Element)itrts.next();
      String s = new ArrayValue(ele).getValue();
      avs[i] = s;
      ++i;
    }
    return avs;
  }

  public void setAttributeValue(String attributeName, String value)
  {
    this.element.setAttribute(attributeName, value);
  }

  public void modify(ArrayItem aItem)
  {
    setDescription(aItem.getDescription());

    Attribute[] atts = aItem.getAttributes();
    if ((atts != null) && (atts.length > 0)) {
      for (int i = 0; i < atts.length; ++i)
        addAttribute(atts[i].getName(), atts[i].getOrigValue());

    }

    this.element.removeChildren("ArrayValue");

    ArrayValue[] arrValues = aItem.getArrayValues();
    ArrayValue tmpValue = null;
    if ((arrValues != null) && (arrValues.length > 0))
      for (int i = 0; i < arrValues.length; ++i) {
        tmpValue = new ArrayValue();
        tmpValue.setId(arrValues[i].getId());
        tmpValue.setValue(arrValues[i].getOrigValue());
        if (arrValues[i].getOrigReserved() != null)
          tmpValue.setReserved(arrValues[i].getOrigReserved());

        try
        {
          addArrayValue(tmpValue);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
  }
}