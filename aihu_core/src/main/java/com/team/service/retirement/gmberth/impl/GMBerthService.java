package com.team.service.retirement.gmberth.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.service.retirement.gmberth.GMBerthManager;

/** 
 * 说明： 房间信息
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("gmberthService")
public class GMBerthService implements GMBerthManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("GMBerthMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMBerthMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMBerthMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMBerthMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMBerthMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMBerthMapper.findById", pd);
	}
	/**
	 * 通过id获取数据
	 */
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("GMBerthMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMBerthMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**
	 * zzy
	 * 获取负责床位列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> zzyListOfBerth(String GMU_ID) throws Exception {
		return (List<PageData>) dao.findForList("GMBerthMapper.zzyListOfBerth",GMU_ID);
	}
	/**
	 * zzy
	 * 获取负责老人列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> zzyListOfElder(String GMU_ID) throws Exception {
		return (List<PageData>) dao.findForList("GMBerthMapper.zzyListOfElder",GMU_ID);
	}
	/**
	 * zzy
	 * 检查床位信息表中老人信息是否存在 
	 * 返回GMBERTH_ID
	 */
	@Override
	public String zzyCheckByElderName(PageData tpd) throws Exception{
		return (String) dao.findForObject("GMBerthMapper.zzyCheckByElderName",tpd);
	}
}

