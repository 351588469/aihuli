package com.team.service.retirement.elder.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.service.retirement.elder.ElderManager;

/** 
 * 说明： 老人信息
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("elderService")
public class ElderService implements ElderManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ElderMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ElderMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ElderMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ElderMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ElderMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ElderMapper.findById", pd);
	}
	/**
	 * zzy
	 */
	@Override
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("ElderMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ElderMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * zzy
	 *  根据老人姓名检测老人信息是否存在
	 *  return 老人编号
	 */
	@Override
	public String zzyCheckByName(PageData pd) throws Exception {
		return (String) dao.findForObject("ElderMapper.zzyCheckByName",pd);
	}
	/**
	 * zzy
	 * 根据编号获取老人姓名
	 */
	@Override
	public String zzyFindNameById(String id) throws Exception {
		return (String)dao.findForObject("ElderMapper.zzyFindNameById",id);
	}

	@Override
	public String zzyFindGmidById(String id) throws Exception {
		return (String)dao.findForObject("ElderMapper.zzyFindGmidById",id);
	}
	
}

