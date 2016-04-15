package com.team.service.volunteer.vdonation;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 捐赠接口
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
public interface VDonationManager{

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
	public List<PageData> zzyList(PageData pd)throws Exception;
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * 公益捐赠发布
	 */
	public Map<String, Object> app_zzyAdd(PageData pd, Map<Integer, String>fm)throws Exception;
	/**
	 * 公益捐赠列表
	 */
	public Map<String, Object> app_zzyList(PageData pd)throws Exception;
	/**
	 * 我的捐赠列表
	 */
	public Map<String, Object> app_zzyList_byUserId(String userid)throws Exception;
	/**
	 * 我的捐赠数量
	 */
	public Integer zzyCount_byUserId(String userid)throws Exception;
	/**
	 * 更新点赞数
	 */
	public void zzyUpdatePraise(String vdid, Integer x)throws Exception;

	



	
	
}

