package com.team.service.retirement.gmuser;

import java.util.List;
import java.util.Map;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 职工信息接口
 * 创建人：jaychum
 * 创建时间：2016-03-03
 * @version
 */
public interface GMUserManager{

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
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**
	 * zzy根据职工姓名检测职工信息是否存在
	 */
	public String zzyCheckByName(PageData pd)throws Exception;
	/**
	 * zzy
	 * 根据养老院编号获取职工姓名及编号列表
	 * @param GM_ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> zzyListForNameID(String GM_ID)throws Exception;
	/**
	 * zzy
	 * 根据职工编号获取职工姓名
	 */
	public String zzyFindNameById(String ID)throws Exception;
	/**
	 * zzy
	 * 医护端登录
	 */
	public PageData zzyLogin(String tel)throws Exception;
}

