package com.team.service.retirement.gmaitem.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.gmaitem.GMAitemManager;
import com.team.service.retirement.gmatype.GMATypeManager;

/** 
 * 说明： 评测题目
 * 创建人：jaychum
 * 创建时间：2016-03-02
 * @version
 */
@Service("gmaitemService")
public class GMAitemService implements GMAitemManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="gmatypeService")
	private GMATypeManager gmatypeService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		pd.put("GMAITEM_ID",UuidUtil.get32UUID());
		pd.put("GMAI_CTIME",Tools.date2Str(new Date()));
		pd.put("GMAI_UTIME",Tools.date2Str(new Date()));
		pd.put("GMAI_UTIME",Tools.date2Str(new Date()));
		
		dao.save("GMAitemMapper.save", pd);
	}
	public void zzySave(PageData pd)throws Exception{
		PageData zzyPd=new PageData();
		String gmatid=pd.getString("gmat_id");
		PageData gmat=gmatypeService.zzyFindById(gmatid);
		zzyPd.put("GMAI_NUMBER",pd.get("gmai_number"));
		zzyPd.put("GMAI_GMAT_ID",gmatid);
		zzyPd.put("GMAI_SCORE",pd.get("gmai_score"));
		zzyPd.put("GMAI_CTIME",Tools.date2Str(new Date()));
		zzyPd.put("GMAI_UTIME",Tools.date2Str(new Date()));
		zzyPd.put("GMAI_GMAT_NAME",gmat.get("GMAT_NAME"));
		zzyPd.put("GMAI_GMAT_NUM",gmat.get("GMAT_NUM"));
		zzyPd.put("GMAI_GM_ID",pd.get("gm_id"));
		zzyPd.put("GMAITEM_ID",UuidUtil.get32UUID());
		dao.save("GMAitemMapper.save",zzyPd);
	}
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMAitemMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMAitemMapper.edit", pd);
	}
	/**
	 * zzy修改 参数不固定 
	 * 因为此处修改只有分值和内容，无其他关联，故不做处理
	 */
	public void zzyEdit(PageData pd)throws Exception{
		dao.update("GMAitemMapper.zzyEdit", pd);
	}
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMAitemMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMAitemMapper.listAll", pd);
	}
	/**
	 * 评测题目列表
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData>zzyList(String gmid)throws Exception{
		return (List<PageData>)dao.findForList("GMAitemMapper.zzyList",gmid);
	}
	/**
	 * zzy列表 一类题目
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> zzyListByType(String gmatid) throws Exception {
		return (List<PageData>)dao.findForList("GMAitemMapper.zzyListByType",gmatid);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMAitemMapper.findById", pd);
	}
	/**
	 * zzy通过Id获取数据
	 */
	public PageData zzyFindById(String id)throws Exception{
		return (PageData)dao.findForObject("GMAitemMapper.zzyFindById",id);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMAitemMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

