package com.team.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

public class TimeSizeRollingFileAppender extends FileAppender
  implements ErrorCode
{
  protected long maxFileSize = 20971520L;
  protected int maxBackupIndex = 1;
  protected int logInServer = 0;
  static final int LOG_IN_SERVER = 1;
  static final int NOT_LOG_IN_SERVER = 0;
  static final int TOP_OF_TROUBLE = -1;
  static final int TOP_OF_MINUTE = 0;
  static final int TOP_OF_HOUR = 1;
  static final int HALF_DAY = 2;
  static final int TOP_OF_DAY = 3;
  static final int TOP_OF_WEEK = 4;
  static final int TOP_OF_MONTH = 5;
  private String datePattern = "'.'yyyy-MM-dd";
  private String scheduledFilename;
  private long nextCheck = System.currentTimeMillis() - -6560593837771718655L;
  Date now = new Date();
  SimpleDateFormat sdf;
  RollingCalendar rc = new RollingCalendar();
  int checkPeriod = -1;
  static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
  private File currFile;

  public TimeSizeRollingFileAppender()
  {
  }

  public TimeSizeRollingFileAppender(Layout layout, String filename, String datePattern)
    throws IOException
  {
    super(layout, filename, true);
    this.datePattern = datePattern;
    activateOptions();
  }

  public void setDatePattern(String pattern)
  {
    this.datePattern = pattern;
  }

  public String getDatePattern()
  {
    return this.datePattern;
  }

  public int getMaxBackupIndex()
  {
    return this.maxBackupIndex;
  }

  public long getMaximumFileSize()
  {
    return this.maxFileSize;
  }

  public void setLogInServer(int server) {
    this.logInServer = server;
  }

  @Override
public void setFile(String file)
  {
    String val = file.trim();
    String tmpfileName = val.replace('/', LogConstants.FILE_SEP.charAt(0));

    if (tmpfileName != null) {
      if (tmpfileName.startsWith("/"))
        this.logInServer = 0;
      else if (tmpfileName.indexOf(":") != -1)
        this.logInServer = 0;

    }

    if (this.logInServer == 1) {
      String weblogicPath = System.getProperty("user.dir");
      System.out.println(weblogicPath);
      tmpfileName = weblogicPath + System.getProperty("file.separator") + 
        tmpfileName;
      System.out.println(tmpfileName);
    }

    this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    int a = tmpfileName.lastIndexOf(LogConstants.FILE_SEP);
    if (a > 0) {
      String temp = tmpfileName.substring(a + 1, tmpfileName.length());
      String qianStr = tmpfileName.substring(0, a + 1);
      int i = temp.lastIndexOf(".");
      if (i > 0) {
        String temp1 = temp.substring(0, i);
        String houStr = temp.substring(i, temp.length());
        tmpfileName = qianStr + temp1 + "_" + 
          this.sdf.format(new Date(System.currentTimeMillis())) + 
          houStr;
      }
    }

    this.fileName = tmpfileName;

    LogLog.debug("fileName:" + tmpfileName);

    int index = this.fileName.lastIndexOf(LogConstants.FILE_SEP);
    if (index > 0) {
      String sPath = this.fileName.substring(0, index);
      File path = new File(sPath);
      if (!(path.exists()))
        path.mkdirs();

    }

    LogLog.debug("File set:" + this.fileName);
  }

  @Override
public synchronized void setFile(String pFileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException
  {
    try {
      reset();
      this.fileName = pFileName;
      LogLog.debug("setFile called: " + this.fileName + ", " + append);

      if (bufferedIO) {
        setImmediateFlush(false);
      }

      Writer fw = createWriter(new FileOutputStream(this.fileName, append));
      if (bufferedIO)
        fw = new BufferedWriter(fw, bufferSize);

      setQWForFiles(fw);
      this.fileAppend = append;
      this.bufferedIO = bufferedIO;
      this.bufferSize = bufferSize;
      writeHeader();

      if (append) {
        this.currFile = new File(this.fileName);
        ((CountingQuietWriter)this.qw).setCount(this.currFile.length());
      }
      LogLog.debug("setFile ended");
    }
    catch (IOException e) {
      this.errorHandler.error("Create log File error", e, 4);
    }
  }

  @Override
public void activateOptions() {
    super.activateOptions();
    if ((this.datePattern != null) && (this.fileName != null)) {
      this.now.setTime(System.currentTimeMillis());
      this.sdf = new SimpleDateFormat(this.datePattern);
      int type = computeCheckPeriod();
      printPeriodicity(type);
      this.rc.setType(type);
      this.currFile = new File(this.fileName);

      this.scheduledFilename = this.fileName + 
        this.sdf.format(new Date(this.currFile.lastModified()));
      LogLog.debug("scheduledFilename generated:" + this.scheduledFilename);
    }
    else
    {
      LogLog.error("Either File or DatePattern options are not set for appender [" + 
        this.name + "].");
    }
  }

  public void setMaxBackupIndex(int maxBackups)
  {
    this.maxBackupIndex = maxBackups;
  }

  public void setMaxFileSize(String value)
  {
    this.maxFileSize = OptionConverter.toFileSize(value, this.maxFileSize + -6560591466949771263L);
  }

  public void setMaximumFileSize(long value)
  {
    this.maxFileSize = value;
  }

  @Override
protected void setQWForFiles(Writer writer) {
    this.qw = new CountingQuietWriter(writer, this.errorHandler);
  }

  void printPeriodicity(int type) {
    switch (type)
    {
    case 0:
      LogLog.debug("Appender [" + this.name + "] to be rolled every minute.");
      break;
    case 1:
      LogLog.debug("Appender [" + this.name + 
        "] to be rolled on top of every hour.");
      break;
    case 2:
      LogLog.debug("Appender [" + this.name + 
        "] to be rolled at midday and midnight.");
      break;
    case 3:
      LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
      break;
    case 4:
      LogLog.debug("Appender [" + this.name + 
        "] to be rolled at start of week.");
      break;
    case 5:
      LogLog.debug("Appender [" + this.name + 
        "] to be rolled at start of every month.");
      break;
    default:
      LogLog.warn("Unknown periodicity for appender [" + this.name + "].");
    }
  }

  int computeCheckPeriod()
  {
    RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone, 
      Locale.ENGLISH);

    Date epoch = new Date(-6560592154144538624L);
    if (this.datePattern != null)
      for (int i = 0; i <= 5; ++i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
          this.datePattern);
        simpleDateFormat.setTimeZone(gmtTimeZone);

        String r0 = simpleDateFormat.format(epoch);
        rollingCalendar.setType(i);
        Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
        String r1 = simpleDateFormat.format(next);
        LogLog.debug("Type = " + i + ", r0 = " + r0 + ", r1 = " + r1);
        if ((r0 != null) && (r1 != null) && (!(r0.equals(r1))))
          return i;
      }


    return -1;
  }

  public void rollOverForTime()
    throws IOException
  {
    if (this.datePattern == null) {
      this.errorHandler.error("Missing DatePattern option in rollOver().");
      return;
    }

    String datedFilename = this.fileName + this.sdf.format(this.now);
    LogLog.debug("datedFilename:" + datedFilename);

    if (this.scheduledFilename.equals(datedFilename)) {
      return;
    }

    closeFile();

    File target = new File(this.scheduledFilename + 
      ".bak");
    if (target.exists())
      target.delete();

    target = new File(this.fileName + ".0");
    File file = new File(this.fileName);

    boolean result = file.renameTo(target);
    if (result)
      LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
    else {
      LogLog.error("Failed to rename [" + this.fileName + "] to [" + 
        this.scheduledFilename + "].");
    }

    try
    {
      System.out.println(this.fileName);

      this.sdf = new SimpleDateFormat("yyyy-MM-dd");
      int a = this.fileName.lastIndexOf(LogConstants.FILE_SEP);
      if (a > 0) {
        String temp = this.fileName.substring(a + 1, this.fileName.length());
        String qianStr = this.fileName.substring(0, a + 1);
        int i = temp.lastIndexOf(".");
        if (i > 0) {
          String temp1 = temp.substring(0, i);
          int j = temp1.indexOf("_");
          temp1 = temp1.substring(0, j);
          String houStr = temp.substring(i, temp.length());
          this.fileName = qianStr + temp1 + "_" + 
            this.sdf.format(new Date(System.currentTimeMillis())) + 
            houStr;
        }

      }

      setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
    } catch (IOException e) {
      this.errorHandler.error("setFile(" + this.fileName + ", false) call failed.");
    }
    this.scheduledFilename = datedFilename;
    LogLog.debug("scheduledFilename after roll:" + this.scheduledFilename);
  }

  public void rollOverForSize()
  {
    LogLog.debug("rolling over count=" + 
      ((CountingQuietWriter)this.qw).getCount());
    LogLog.debug("maxBackupIndex=" + this.maxBackupIndex);

    if (this.maxBackupIndex > 0)
    {
      File file = new File(this.fileName + '.' + this.maxBackupIndex);
      if (file.exists()) {
        file.delete();
      }

      for (int i = this.maxBackupIndex - 1; i >= 1; --i) {
        file = new File(this.fileName + "." + i);
        if (file.exists()) {
          File target = new File(this.fileName + '.' + (i + 1));
          LogLog.debug("Renaming file " + file + " to " + target);
          file.renameTo(target);
        }

      }

      File target = new File(this.fileName + "." + 1);

      closeFile();

      file = new File(this.fileName);
      LogLog.debug("Renaming file " + file + " to " + target);
      file.renameTo(target);
    }

    try
    {
      setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
    } catch (IOException e) {
      LogLog.error("setFile(" + this.fileName + ", false) call failed.", e);
    }
  }

  @Override
protected void subAppend(LoggingEvent event)
  {
    if ((this.fileName != null) && 
      (((CountingQuietWriter)this.qw).getCount() >= this.maxFileSize))
      rollOverForSize();

    long n = System.currentTimeMillis();
    if (n >= this.nextCheck) {
      this.now.setTime(n);
      this.nextCheck = this.rc.getNextCheckMillis(this.now);
      try {
        rollOverForTime();
      } catch (IOException ioe) {
        LogLog.error("rollOver() failed.", ioe);
      }

    }

    super.subAppend(event);
  }

  public static void main(String[] args) {
    TimeSizeRollingFileAppender tmes = new TimeSizeRollingFileAppender();
    try
    {
      tmes.setFile("F:\\log\\log.log", true, true, 1024);
    }
    catch (IOException localIOException)
    {
    }
  }
}