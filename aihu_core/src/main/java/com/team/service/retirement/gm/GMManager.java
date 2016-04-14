package com.team.service.retirement.gm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.team.entity.Page;
import com.team.util.PageData;

/** 
 * 说明： 养老院信息接口
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
public interface GMManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd,HttpSession session)throws Exception;
	
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
	public List<PageData> list(Page page,HttpSession session)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd,HttpSession session)throws Exception;
	public List<PageData> zzyList(PageData pd)throws Exception;
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**
	 * zzy
	 * 通过id获取名称
	 */
	public String zzyFindNameById(String id)throws Exception;
	/**
	 * zzy
	 * 通过名称获取id 模糊搜索
	 */
	public String zzyFindIdByName(String name)throws Exception;
	/**
	 * 创建用户
	 * @param string
	 * @return
	 */
	public String zzyFindNameByAUId(String string)throws Exception;
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	/**
	 * zzy
	 * 根据
	 * 创建人获取养老院名称和编号列表
	 */
	public List<Map<String,Object>> listByCreator()throws Exception;
	/**
	 * 养老院列表
	 */
	public Map<String,Object>app_zzyList(PageData pd)throws Exception;
	/**
	 * 更新点赞
	 */
	public void zzyUpdatePraise(String gmid,Integer x)throws Exception;

	
}

