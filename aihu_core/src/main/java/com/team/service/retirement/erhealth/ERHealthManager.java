package com.team.service.retirement.erhealth;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 健康记录接口
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
public interface ERHealthManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	/**
	 * zzy
	 * 新增
	 */
	public void zzySave(PageData pd)throws Exception;
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
	public List<PageData>zzyList(PageData pd)throws Exception;
	/**
	 * 历史
	 */
	public List<PageData> zzyPast(PageData pd)throws Exception;
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
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
	 * app新增健康记录
	 */
	public Map<String,Object> app_zzyAddNoGM(PageData pd)throws Exception;
	/**
	 * app历史健康记录
	 */
	public Map<String, Object> app_zzyListNoGM(PageData pd)throws Exception;
}

