package com.team.service.retirement.elder;

import java.util.List;
import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 老人信息接口
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
public interface ElderManager{

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
	 *  根据老人姓名检测老人信息是否存在
	 *  return 老人编号
	 */
	public String zzyCheckByName(PageData pd)throws Exception;
	/**
	 * zzy
	 * 根据编号获取老人姓名
	 */
	public String zzyFindNameById(String id)throws Exception;
	/**
	 * zzy
	 * 根据老人编号获取养老院编号
	 */
	public String zzyFindGmidById(String id)throws Exception;
}

