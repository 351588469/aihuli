package com.team.comm.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PublicUtil
{
  private static final int BYTE_CHINESE = 255;
  private static final int TEMP_BUF_SIZE = 1024;
  private static final Log log = LogFactory.getLog(PublicUtil.class);

  public static String getUserDir()
  {
    return System.getProperty("user.dir");
  }

  public static String getFileSeparator() {
    return System.getProperty("file.separator");
  }

  public static String lPad(String s, int bitNumber)
  {
    if (s == null) {
      return null;
    }

    int length = s.length();

    if (length > bitNumber) {
      return null;
    }

    if (length == bitNumber) {
      return s;
    }

    int addNum = bitNumber - length;
    String addString = "0";
    for (int i = 1; i < addNum; ++i)
      addString = addString + '0';

    return addString + s;
  }

  public static String lPad(String s, int len, String pad) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < len - s.length(); i += pad.length())
      sb.append(pad);

    sb.append(s);
    return sb.toString();
  }

  public static String rPad(String s, int len, String pad) {
    StringBuffer sb = new StringBuffer();
    sb.append(s);
    for (int i = 0; i < len - s.length(); i += pad.length())
      sb.append(pad);

    return sb.toString();
  }

  public static String getGenFileName()
  {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String sf = sdf.format(date);
    return sf;
  }

  public static String StrReplace(String src, String str1, String str2)
  {
    if ((src.length() <= 0) || ("".equals(str1)))
      return src;

    StringBuffer str_temp = new StringBuffer().append(src);
    int start = 0;
    int position = str_temp.toString().indexOf(str1, start);
    while ((position > 0) && (position < str_temp.toString().length())) {
      str_temp = str_temp.replace(position, position + str1.length(), 
        str2);
      start = position + str2.length();
      position = str_temp.toString().indexOf(str1, start);
    }
    return str_temp.toString();
  }

  public static String StrReplaceForAmp(String src, String str1, String str2)
  {
    String tmp = StrReplace(src, str2, str1);
    return StrReplace(tmp, str1, str2);
  }

  public static String toOSDir(String path)
  {
    String osName = "";
    String retPath = "";
    osName = System.getProperty("os.name").toLowerCase();
    if (osName.indexOf("windows") != -1)
      retPath = path.replace('/', '\\');
    else
      retPath = path.replace('\\', '/');

    return retPath;
  }

  public static String getNowTime()
  {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String nowtime = sdf.format(date);
    return nowtime;
  }

  public static int getCurMin() {
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
    int currentTime = Integer.parseInt(simpleDateFormat.format(date));
    return currentTime;
  }

  public static int getCurHour() {
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("k");
    int currentTime = Integer.parseInt(simpleDateFormat.format(date));
    return currentTime;
  }

  public static String getCurTime() {
    return null;
  }

  public static String getCurDateTime() {
    Calendar now = Calendar.getInstance(TimeZone.getDefault());
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(
      DATE_FORMAT);
    sdf.setTimeZone(TimeZone.getDefault());
    return sdf.format(now.getTime());
  }

  public static String Now() {
    Calendar now = Calendar.getInstance(TimeZone.getDefault());
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    sdf.setTimeZone(TimeZone.getDefault());
    return sdf.format(now.getTime()); }

  public static Date getDate(String date) {
    SimpleDateFormat sdf;
    try {
      sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      return sdf.parse(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null; }

  public static Date getDateFormat(String date, String format) {
    SimpleDateFormat sdf;
    try {
      sdf = new SimpleDateFormat(format);
      return sdf.parse(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null; }

  public static String getCurDateTime(String dateFormate) {
    Calendar now;
    try {
      now = Calendar.getInstance(TimeZone.getDefault());
      SimpleDateFormat sdf = new SimpleDateFormat(
        dateFormate);
      sdf.setTimeZone(TimeZone.getDefault());
      return sdf.format(now.getTime()); } catch (Exception e) {
    }
    return getCurDateTime();
  }

  public static String getDateString(Date date, String dateFormate)
  {
    if (date == null)
      return "";

    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
      return sdf.format(date);
    } catch (Exception e) {
      e.printStackTrace(); }
    return getCurDateTime();
  }

  public static String getDateString(String date)
  {
    if (date == null)
      return "";
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return sdf.format(sdf.parse(date));
    }
    catch (Exception e) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(sdf.parse(date)); } catch (Exception dx) { }
    }
    return getCurDateTime();
  }

  public static String getDateString(String date, String dateFormate)
  {
    if (date == null)
      return "";

    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);

      return sdf.format(sdf.parse(date));
    } catch (Exception e) {
      e.printStackTrace(); }
    return getCurDateTime();
  }

  public static String getCurYear()
  {
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    return formatter.format(now);
  }

  public static String getCurDate() {
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    return formatter.format(now);
  }

  public static String getCurMonth() {
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
    return formatter.format(now);
  }

  public static String getCurMonth_jsp() {
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
    return formatter.format(now);
  }

  public static String getCur_MMDDHHMMSS() {
    Date now = new Date();
    System.out.println(now);
    SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
    return formatter.format(now);
  }

  public static String getLastMonth()
  {
    int currentMonth = Integer.parseInt(getCurMonth());
    if (currentMonth == 1)
      return "12";

    int lastMonth = currentMonth - 1;
    String lastMonthString = Integer.toString(lastMonth);

    if (lastMonthString.length() == 2)
      return lastMonthString;

    lastMonthString = "0" + lastMonthString;
    return lastMonthString;
  }

  public static final String replace(String line, String oldString, String newString)
  {
    if (line == null)
      return null;

    int i = 0;
    if ((i = line.indexOf(oldString, i)) >= 0) {
      char[] line2 = line.toCharArray();
      char[] newString2 = newString.toCharArray();
      int oLength = oldString.length();
      StringBuffer buf = new StringBuffer(line2.length);
      buf.append(line2, 0, i).append(newString2);
      i += oLength;
      int j = i;
      while ((i = line.indexOf(oldString, i)) > 0) {
        buf.append(line2, j, i - j).append(newString2);
        i += oLength;
        j = i;
      }
      buf.append(line2, j, line2.length - j);
      return buf.toString();
    }
    return line;
  }

  public static String htmlSpecialCharConvert(String htmlString)
  {
    if ((htmlString == null) || (htmlString.trim().equals("")))
      return "";

    StringBuffer newString = new StringBuffer();
    String specialChars = "\"<>";
    String[] charToString = { "&quot;", "&amp;", "&lt;", "&gt;", "&nbsp;" };

    for (int i = 0; i < htmlString.length(); ++i) {
      char cTemp = htmlString.charAt(i);
      int iTemp = specialChars.indexOf(cTemp);
      if (iTemp == -1)
      {
        newString.append(cTemp);
      }
      else
        newString.append(charToString[iTemp]);
    }

    return newString.toString();
  }

  public static String[] getFileListFromDir(String dir) throws Exception {
    File file = new File(dir);
    return file.list();
  }

  public static String getFixLengthStr(String str, int len)
  {
    if ((str == null) || (len <= 0))
      return "";

    int oldlen = str.length();
    if (oldlen <= len)
      return str;

    String newStr = str.substring(0, len);
    return newStr;
  }

  public static String trim(String str)
  {
    if (str == null)
      return "";

    return str.trim();
  }

  public static String getPageStr(String str)
  {
    if (str == null)
      return "";

    return str;
  }

  public static final int getLength(String strTemp)
  {
    int sum = 0;
    for (int i = 0; i < strTemp.length(); ++i)
      if ((strTemp.charAt(i) >= 0) && (strTemp.charAt(i) <= 255))
        ++sum;
      else
        sum += 2;


    return sum;
  }

  public static final boolean checkIllegalChar(String targetStr, String IllegalString)
  {
    for (int j = 0; j < IllegalString.length(); ++j)
      if (targetStr.indexOf(IllegalString.charAt(j)) >= 0)
        return false;


    return true;
  }

  public static final boolean checkDigital(String targetStr)
  {
    return Pattern.matches("[0-9]*", targetStr);
  }

  public static final boolean checkTel(String RegularExp)
  {
    boolean result = (((RegularExp.substring(0, 1).equals("+")) || (checkDigital(
      RegularExp.substring(0, 1))))) && 
      (checkDigital(RegularExp.substring(1)));
    return result;
  }

  public static ByteArrayOutputStream InputStreamToOutStream(InputStream in)
    throws Exception
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    byte[] buff = new byte[1025];
    int rc = 0;
    if (in != null)
      try {
        while ((rc = in.read(buff, 0, 1024)) > 0)
          out.write(buff, 0, rc);
      }
      catch (Exception e) {
        throw e;
      } finally {
        CloseInputStream(in);
      }

    return out;
  }

  public static void CloseInputStream(InputStream in)
  {
    if (in != null)
      try {
        in.close();
        in = null;
      } catch (Exception e) {
        log.error(e.getMessage());
      }
  }

  public static void CloseOutStream(OutputStream out)
  {
    if (out != null)
      try {
        out.close();
        out = null;
      } catch (Exception e) {
        log.error(e.getMessage());
      }
  }

  public static void CloseWriter(Writer wr)
  {
    if (wr != null)
      try {
        wr.close();
        wr = null;
      } catch (Exception e) {
        log.error(e.getMessage());
      }
  }

  public static void CloseReader(Reader rr)
  {
    if (rr != null)
      try {
        rr.close();
        rr = null;
      } catch (Exception e) {
        log.error(e.getMessage());
      }
  }

  public static String ISO2GBK(String s)
  {
    String str = s;
    try {
      byte[] b = str.getBytes("ISO-8859-1");
      str = new String(b, "GB2312");
      return str; } catch (Exception e) {
    }
    return "";
  }

  public static String GBK2ISO(String s)
  {
    String str = s;
    try {
      byte[] b = str.getBytes("GB2312");
      str = new String(b, "ISO-8859-1");
      return str; } catch (Exception e) {
    }
    return "";
  }

  public static String GBK2UTF8(String s)
  {
    String str = s;
    try {
      byte[] b = str.getBytes("GB2312");
      str = new String(b, "UTF-8");
      return str; } catch (Exception e) {
    }
    return "";
  }

  public static String UTF82GBK(String s)
  {
    String str = s;
    try {
      str = new String(str.getBytes(), "UTF-8");
      return new String(str.getBytes("GB2312")); } catch (Exception e) {
    }
    return "";
  }

  public static final void createDir(String dir)
  {
    File file = new File(dir);
    if (!(file.exists()))
      file.mkdirs();
  }

  public static void main(String[] args)
  {
    Calendar c = Calendar.getInstance();
    c.add(5, 1);
    System.out.println(getDateString(c.getTime(), "yyyyMMddHHmmss"));
    String ggfn = getGenFileName();
    System.out.println(ggfn.substring(13, ggfn.length()));
  }
}