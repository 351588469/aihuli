package com.team.comm.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerUtils
{
  private static String config_path;
  private static String appRootPath;
  private static final String enc = System.getProperty("file.encoding");
  private static final String FS = System.getProperty("file.separator");
  private static final String lSeparator = System.getProperty("line.separator");
  private static final boolean isGBK = enc.indexOf("GB") >= 0;
  private static String hostName = null;
  private static String hostIP = null;

  public static String getServerEncode()
  {
    return enc;
  }

  public static boolean isGBKsystem()
  {
    return isGBK;
  }

  public static String getUserDir() {
    return System.getProperty("user.dir");
  }

  public static String getSysFileSeparator()
  {
    return FS;
  }

  public static String getLineSeparator()
  {
    return lSeparator;
  }

  public static String getLocalIP()
    throws UnknownHostException
  {
    if (hostIP == null)
      hostIP = InetAddress.getLocalHost().getHostAddress();

    return hostIP;
  }

  public static String getHostName()
    throws UnknownHostException
  {
    if (hostName == null)
      hostName = InetAddress.getLocalHost().getHostName();

    return hostName;
  }

  public static String getConfigPath()
  {
    return config_path;
  }

  public static void setConfigPath(String configPath)
  {
    config_path = configPath;
  }

  public static String getAppRootPath()
  {
    return appRootPath;
  }

  public static void setAppRootPath(String appRootPath)
  {
    appRootPath = appRootPath;
  }
}