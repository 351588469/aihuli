package com.team.service.retirement.enplan;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 护理计划接口
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
public interface ENPlanManager{

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
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	/**
	 * zzy
	 * 列表
	 */
	public List<PageData> zzyList(String eid)throws Exception;
	/**
	 * 获取项目列表，包含常规项目和定制项目 
	 */
	public Map<String, Object> zzyMap(String eid) throws Exception;
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	/**
	 * zzy
	 * 通过id获取数据
	 */
	public PageData zzyFindById(String id)throws Exception;
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * zzy
	 * 获取项目名称
	 */
	public String zzyFindNNAMEById(String enpid)throws Exception;
	
	
}

