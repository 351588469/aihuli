package com.team.service.retirement.msg.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.DateUtil;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.edplan.EDPlanManager;
import com.team.service.retirement.elder.ElderManager;
import com.team.service.retirement.enplan.ENPlanManager;
import com.team.service.retirement.msg.MsgManager;

/** 
 * 说明： 消息总类
 * 创建人：jaychum
 * 创建时间：2016-03-16
 * @version
 */
@Service("msgService")
public class MsgService implements MsgManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="edplanService")
	private EDPlanManager edplanService;
	@Resource(name="enplanService")
	private ENPlanManager enplanService;
	@Resource(name="elderService")
	private ElderManager elderService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MsgMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("MsgMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MsgMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MsgMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MsgMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MsgMapper.findById", pd);
	}
	/**
	 * 通过e_id获取数据
	 */
	public PageData zzyFindByEid(String eid,String type)throws Exception{
		Map<String,String>tm=new HashMap<String, String>();
		tm.put("MSG_E_ID",eid);
		tm.put("MSG_TYPE",type);
		String day=DateUtil.getDay();
		tm.put("STIME",day+" 00:00:00");
		tm.put("ETIME",day+" 23:59:59");
		return (PageData)dao.findForObject("MsgMapper.zzyFindByEid",tm);
	}
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MsgMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * zzy
	 * 生成新消息
	 */
	@Override
	public PageData zzyCreateMsg(String eid, String type) throws Exception {
		//根据eid获取gmid
		String gmid=elderService.zzyFindGmidById(eid);
		PageData pd=new PageData();
		if(type.equals("edp")){//用药计划
			String content="";
			List<PageData>list=edplanService.zzyList(eid);
			for(int i=0;i<list.size();i++)
				content+=list.get(i).getString("EDP_MNAME");
			pd.put("MSG_ID",UuidUtil.get32UUID());
			pd.put("MSG_TYPE","edp");
			pd.put("MSG_CONTENT",content);
			pd.put("MSG_GM_ID", gmid);
			pd.put("MSG_E_ID",eid);
			pd.put("MSG_CTIME",Tools.date2Str(new Date()));
			pd.put("MSG_UTIME",Tools.date2Str(new Date()));
			save(pd);
		}else if(type.equals("enp")){//护理计划
			String content="";
			List<PageData>list=enplanService.zzyList(eid);
			for(int i=0;i<list.size();i++){
				content+=list.get(i).getString("ENP_GMN_NAME");
			}
			pd.put("MSG_ID",UuidUtil.get32UUID());
			pd.put("MSG_TYPE","enp");
			pd.put("MSG_CONTENT",content);
			pd.put("MSG_GM_ID", gmid);
			pd.put("MSG_E_ID",eid);
			pd.put("MSG_CTIME",Tools.date2Str(new Date()));
			pd.put("MSG_UTIME",Tools.date2Str(new Date()));
			save(pd);
		}
		return pd;
	}
}

