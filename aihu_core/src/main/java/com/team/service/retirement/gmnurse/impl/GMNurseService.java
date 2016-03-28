package com.team.service.retirement.gmnurse.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.service.retirement.gmnurse.GMNurseManager;

/** 
 * 说明： 护理项目
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("gmnurseService")
public class GMNurseService implements GMNurseManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("GMNurseMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMNurseMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMNurseMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMNurseMapper.datalistPage", page);
	}
	/**
	 * zzy
	 * 列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> zzyList(String gmid)throws Exception{
		return (List<PageData>)dao.findForList("GMNurseMapper.zzyList",gmid);
	}
	/**
	 * zzy
	 * 常规项目列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> zzyRoutineList(String gmid)throws Exception{
		return (List<PageData>)dao.findForList("GMNurseMapper.zzyRoutineList",gmid);
	}
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMNurseMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMNurseMapper.findById", pd);
	}
	/**
	 * zzy
	 * 通过id获取数据
	 */
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("GMNurseMapper.zzyFindById", id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMNurseMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public String zzyFindNNameById(String enpid) throws Exception {
		return (String) dao.findForObject("GMNurseMapper.zzyFindNNameById",enpid);
	}
	
}

