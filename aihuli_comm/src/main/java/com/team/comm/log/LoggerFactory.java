package com.team.comm.log;

import java.io.File;
import java.io.PrintStream;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggerFactory
{
  private static boolean isConfigOk = false;

  public static JayChumLogger getBizLogger(String className)
  {
    Logger log = Logger.getLogger(className);
    JayChumLoggerImpl bizLog = new JayChumLoggerImpl(log);
    return bizLog;
  }

  public static JayChumLogger getLogger(Class className)
  {
    Logger log = Logger.getLogger(className);
    JayChumLoggerImpl logerimp = new JayChumLoggerImpl(log);
    return logerimp;
  }

  public static synchronized void configLog(String file, int refresh)
  {
    if (isConfigOk)
      return;
    if ((file == null) || (file.equals(""))) {
      System.err.println("log4j.xml not set");
      return;
    }
    System.out.println("Config file:" + file);
    File confFile = new File(file);
    if (!(confFile.exists())) {
      System.err.println("log4j.xml not found");
      return;
    }
    System.out.println("refresh time:" + refresh);
    DOMConfigurator.configureAndWatch(file, refresh * 1000);
    isConfigOk = true;
    System.out.println("Config log successfully");
  }
}