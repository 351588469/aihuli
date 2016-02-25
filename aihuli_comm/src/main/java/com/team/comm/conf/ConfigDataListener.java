package com.team.comm.conf;

public abstract interface ConfigDataListener
{
  public abstract void doConfigRefresh();

  public abstract String getListenerName();
}