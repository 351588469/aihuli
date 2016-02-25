package com.team.pms.user.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.team.comm.dao.MyBatisDao;

public class UserDao extends MyBatisDao{
	private static final Log LOG = LogFactory.getLog(UserDao.class);
	public String selectUserById(int id){
		String name=null;
		try{
			name=this.getSqlSession().selectOne("selectUserById", id);
		}catch(Exception ce){
			LOG.error("chaxun",ce);
		}finally{
		}
		return name;
	}
	
}
