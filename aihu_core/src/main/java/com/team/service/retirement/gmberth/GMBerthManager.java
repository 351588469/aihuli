package com.team.service.retirement.gmberth;

import java.util.List;
import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 房间信息接口
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
public interface GMBerthManager{

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
	 * 获取负责床位列表
	 */
	public List<PageData> zzyListOfBerth(String GMU_ID)throws Exception;
	/**
	 * zzy
	 * 获取老人列表
	 */
	public List<PageData> zzyListOfElder(String GMU_ID)throws Exception;
	/**
	 * zzy
	 * 检查床位信息表中老人信息是否存在 
	 * 返回GMBERTH_ID
	 */
	public String zzyCheckByElderName(PageData tpd) throws Exception;
}

