package com.team.comm.log;

import org.apache.log4j.Logger;

public class JayChumLoggerImpl
  implements JayChumLogger
{
  private Logger log = null;

  public JayChumLoggerImpl(Logger log)
  {
    this.log = log;
  }

  public void biz(String info)
  {
    this.log.log(BizLevel.BIZ, info);
  }

  public void debug(String message) {
    this.log.debug(message);
  }

  public void info(String message) {
    this.log.info(message);
  }

  public void error(String message) {
    this.log.error(message);
  }

  public void warn(String message) {
    this.log.warn(message);
  }

  public void fatal(String message) {
    this.log.fatal(message);
  }

  public void debug(String message, Throwable t) {
    this.log.debug(message, t);
  }

  public void info(String message, Throwable ex) {
    this.log.info(message, ex);
  }

  public void error(String message, Throwable t) {
    this.log.error(message, t);
  }

  public void warn(String message, Throwable t) {
    this.log.warn(message, t);
  }

  public void fatal(String message, Throwable t) {
    this.log.fatal(message, t);
  }
}