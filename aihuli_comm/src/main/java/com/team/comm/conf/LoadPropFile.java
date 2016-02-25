package com.team.comm.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoadPropFile
{
  private static Log LOG = LogFactory.getLog(LoadPropFile.class);
  private static Properties prop;

  public int init(String propFilePath)
  {
    prop = new Properties();
    InputStream in = null;
    try {
      in = super.getClass().getResourceAsStream(propFilePath);
      prop.load(in);
      return 1;
    } catch (IOException e) {
      LOG.error("��ʼ�����������ļ�:", e);
    } finally {
      try {
        in.close();
      } catch (IOException ie) {
        LOG.error("��ʼ�����������ļ��رռ���������:", ie);
      }
    }
    return 0;
  }

  private Properties getProp()
  {
    return prop;
  }

  public String getValue(String key)
  {
    String value = getProp().getProperty(key);
    return value;
  }
}