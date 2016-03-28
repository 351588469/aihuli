package com.team.service.retirement.gmuser.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.service.retirement.gmuser.GMUserManager;

/** 
 * 说明： 职工信息
 * 创建人：jaychum
 * 创建时间：2016-03-03
 * @version
 */
@Service("gmuserService")
public class GMUserService implements GMUserManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("GMUserMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMUserMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMUserMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMUserMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMUserMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMUserMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMUserMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * zzy
	 * 根据职工姓名检测职工信息是否存在
	 */
	@Override
	public String zzyCheckByName(PageData pd) throws Exception {
		return  (String)dao.findForObject("GMUserMapper.zzyCheckByName",pd);
	}
	/**
	 * zzy
	 * 根据养老院编号获取职工姓名及编号列表
	 * @param GM_ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> zzyListForNameID(String GM_ID) throws Exception {
		return (List<Map<String, Object>>) dao.findForList("GMUserMapper.zzyListForNameID",GM_ID);
	}
	/**
	 * zzy
	 * 根据职工编号获取职工姓名
	 */
	@Override
	public String zzyFindNameById(String ID) throws Exception {
		return (String) dao.findForObject("GMUserMapper.zzyFindNameById",ID);
	}
	/**
	 * zzy
	 * 登录
	 */
	@Override
	public PageData zzyLogin(String tel) throws Exception {
		return (PageData) dao.findForObject("GMUserMapper.zzyLogin",tel);
	}
	
}

