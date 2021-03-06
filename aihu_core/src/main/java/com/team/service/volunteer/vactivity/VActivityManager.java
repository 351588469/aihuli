package com.team.service.volunteer.vactivity;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 活动表接口
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
public interface VActivityManager{

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
	 * 列表
	 */
	public List<PageData> zzyList(PageData pd)throws Exception;
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	public PageData zzyFindById(String id)throws Exception;
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * 活动申报
	 */
	public Map<String,Object>app_zzyAdd(PageData pd,Map<Integer,String>fm)throws Exception;
	/**
	 * 活动列表
	 */
	public Map<String, Object> app_zzyList(PageData pd)throws Exception;
	public Map<String,Object>app_zzyList_byUserId(String userid)throws Exception;
	/**
	 * 活动信息
	 */
	public Map<String, Object> app_zzyInfo(PageData pd)throws Exception;
	//活动数量
	public Integer zzyCount_byUserId(String userid)throws Exception;
	/**
	 * 更新报名人数
	 */
	public void zzyUpdateEnroll(String vaid,Integer x)throws Exception;
	/**
	 * 更新点赞人数
	 */
	public void zzyUpdatePraise(String vaid,Integer x)throws Exception;



	


}

