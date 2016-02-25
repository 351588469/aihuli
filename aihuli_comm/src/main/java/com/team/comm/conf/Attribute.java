package com.team.comm.conf;

import java.io.Serializable;

public class Attribute
  implements Serializable
{
  private org.jdom.Attribute attribute = null;
  private String name = null;
  private String value = null;

  public Attribute(String name, String value)
  {
    this.attribute = new org.jdom.Attribute(name, value);
  }

  public String getName()
  {
    return this.attribute.getName();
  }

  public String getOrigValue()
  {
    return this.attribute.getValue();
  }

  public String getValue()
  {
    MacroDef macro = MacroDef.getInstance();
    String value = this.attribute.getValue();
    return macro.replaceByMacro(value);
  }

  public void setValue(String value)
  {
    this.attribute.setValue(value);
  }
}