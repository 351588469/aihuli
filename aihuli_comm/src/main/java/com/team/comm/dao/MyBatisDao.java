package com.team.comm.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MyBatisDao extends SqlSessionDaoSupport {
	private static final Log LOG = LogFactory.getLog(MyBatisDao.class);
}
