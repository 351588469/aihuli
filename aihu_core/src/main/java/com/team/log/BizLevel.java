package com.team.log;

import org.apache.log4j.Level;

public final class BizLevel extends Level
{
  public static final int BIZ_INT = 29999;
  private static String BIZ_STR = "BIZ";
  public static final BizLevel BIZ = new BizLevel(29999, BIZ_STR, 7);

  protected BizLevel(int level, String strLevel, int syslogEquiv)
  {
    super(level, strLevel, syslogEquiv);
  }
 
  public static Level toLevel(String sArg)
  {
    return toLevel(sArg, BIZ);
  }

  public static Level toLevel(String sArg, Level defaultValue)
  {
    if (sArg == null)
      return defaultValue;

    String stringVal = sArg.toUpperCase();
    if (stringVal.equals(BIZ_STR))
      return BIZ;

    return Level.toLevel(sArg, defaultValue);
  }

  public static Level toLevel(int i)
    throws IllegalArgumentException
  {
    if (i == 29999)
      return BIZ;

    return Level.toLevel(i);
  }
}