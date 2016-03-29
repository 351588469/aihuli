package com.team.service.retirement.gmnurse;

import java.util.List;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 护理项目接口
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
public interface GMNurseManager{

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
	/**
	 * zzy
	 * 列表
	 */
	public List<PageData> zzyList(String gmid)throws Exception;
	/**
	 * zzy
	 * 常规项目列表
	 */
	public List<PageData> zzyRoutineList(String gmid)throws Exception;
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
	public String zzyFindNNameById(String enpid)throws Exception;
	
}

