package com.team.comm.log;

public abstract interface JayChumLogger
{
  public abstract void biz(String paramString);

  public abstract void debug(String paramString);

  public abstract void info(String paramString);

  public abstract void error(String paramString);

  public abstract void warn(String paramString);

  public abstract void fatal(String paramString);

  public abstract void debug(String paramString, Throwable paramThrowable);

  public abstract void info(String paramString, Throwable paramThrowable);

  public abstract void error(String paramString, Throwable paramThrowable);

  public abstract void warn(String paramString, Throwable paramThrowable);

  public abstract void fatal(String paramString, Throwable paramThrowable);
}