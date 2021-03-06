package com.team.service.volunteer.vteam;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 义工接口
 * 创建人：jaychum
 * 创建时间：2016-04-05
 * @version
 */
public interface VTeamManager{
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	/**
	 * 列表 zzy
	 */
	public List<PageData>zzyList(PageData pd)throws Exception;
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	/**
	 * 通过id获取数据
	 */
	public PageData zzyFindById(String id)throws Exception;
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * app zzy
	 * 新增
	 * @param flag 
	 */
	public Map<String,Object> app_zzyAdd(PageData pd,Map<Integer,String>fm, boolean flag)throws Exception;
	/**
	 * app zzy
	 * 列表
	 */
	public Map<String, Object> app_zzyList(PageData pd)throws Exception;
	/**
	 * 团体信息
	 */
	public Map<String, Object> app_zzyInfo(PageData pd)throws Exception;
	/**
	 * 用户关注列表
	 */
	public Map<String, Object> app_zzyList_byUserid(String userid)throws Exception;
	/**
	 * 用户关注列表数量
	 */
	public Integer zzyCount_byUserId(String userid)throws Exception;
	/**
	 *zzy 修改话题数量
	 */
	public void zzyUpdateTheme(String vtid,Integer x)throws Exception;
	/**
	 * zzy 检验用户是否为团体负责人 只有负责人才能申报活动
	 */
	public boolean zzyCheckCreateUser(String vtid,String userid)throws Exception;

	boolean zzyCheckAdd(String userid) throws Exception;
	
	

	
}

