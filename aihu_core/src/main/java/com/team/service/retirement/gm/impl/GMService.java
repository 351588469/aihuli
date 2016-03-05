package com.team.service.retirement.gm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.DateUtil;
import com.team.util.PageData;
import com.team.service.retirement.gm.GMManager;

/** 
 * 说明： 养老院信息
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("gmService")
public class GMService implements GMManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		//数据处理
		if(!pd.containsKey("GM_CKSTATUS"))pd.put("GM_CKSTATUS",3);//3:待审核
		if(!pd.containsKey("GM_BERTH_COUNT"))pd.put("GM_BERTH_COUNT",null);
		pd.put("GM_UTIME",null);//新数据无修改时间
		dao.save("GMMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		if(pd.get("GM_BERTH_COUNT").equals(""))pd.put("GM_BERTH_COUNT",null);
		if(!pd.containsKey("GM_CKSTATUS"))pd.put("GM_CKSTATUS",3);//3:待审核
		pd.put("GM_UTIME",DateUtil.getTime().toString());
		dao.update("GMMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMMapper.deleteAll", ArrayDATA_IDS);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> listByCreator() throws Exception {
		List<Map<String,Object>>list=(List<Map<String, Object>>) dao.findForList("GMMapper.listCreator",null);
		return list;
	}
	
}

