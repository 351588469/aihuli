package com.team.service.retirement.gmaitem;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 评测题目接口
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
public interface GMAitemManager{

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
	/**
	 * zzy修改 参数不固定
	 */
	public void zzyEdit(PageData pd)throws Exception;
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
	public List<PageData>zzyList(String gmid)throws Exception;
	/**
	 * zzy列表 一类题目
	 */
	public List<PageData>zzyListByType(String gmatid)throws Exception;
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	/**
	 * 通过Id获取数据
	 */
	public PageData zzyFindById(String gmaiid)throws Exception;
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * 获取题目列表
	 */
	public Map<String, Object> app_zzyList(String gmid)throws Exception;

}



