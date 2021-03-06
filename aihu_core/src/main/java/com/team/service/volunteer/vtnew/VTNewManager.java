package com.team.service.volunteer.vtnew;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 团体消息表接口
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
public interface VTNewManager{

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
	public List<PageData>zzyListNoReply(PageData pd)throws Exception;
	public List<PageData>zzyListWithReply(PageData pd)throws Exception;
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	/**
	 * 通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData zzyFindById(String id)throws Exception;
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * 话题评论发表
	 */
	public Map<String, Object> app_zzyAdd(PageData pd)throws Exception;
	/**
	 * 某话题的评论列表
	 */
	public Map<String, Object> app_zzyList(PageData pd)throws Exception;


	
}

