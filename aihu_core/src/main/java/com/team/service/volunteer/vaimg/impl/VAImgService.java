package com.team.service.volunteer.vaimg.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.Const2;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.volunteer.vaimg.VAImgManager;

/** 
 * 说明： 活动图片
 * 创建人：jaychum
 * 创建时间：2016-04-01
 * @version
 */
@Service("vaimgService")
public class VAImgService implements VAImgManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VAImgMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VAImgMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VAImgMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VAImgMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VAImgMapper.listAll", pd);
	}
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VAImgMapper.zzyList", pd);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VAImgMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VAImgMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 活动图片上传
	 */
	@Override
	public void zzyAdd(String vaid, Map<Integer, String> fm,Integer type) throws Exception {
				Iterator<Integer>it=fm.keySet().iterator();   
				while(it.hasNext()){
					Integer key=it.next();
				    PageData tpd=new PageData();
				    tpd.put("VAIMG_ID",UuidUtil.get32UUID());
				    tpd.put("VAI_VA_ID",vaid);
				    tpd.put("VAI_SRC",fm.get(key));
				    tpd.put("VAI_TYPE",type);
				    tpd.put("VAI_CTIME",Tools.date2Str(new Date()));
				    tpd.put("VAI_UTIME",Tools.date2Str(new Date()));
				    save(tpd);
				}
	}
	@Override
	public Map<String, Object> app_zzyAdd(PageData pd, Map<Integer, String> fm) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vaiadd", pd)){	//检查参数
				String vaid=pd.getString("vaid");
				Iterator<Integer>it=fm.keySet().iterator();   
				while(it.hasNext()){
					Integer key=it.next();
				    PageData tpd=new PageData();
				    tpd.put("VAIMG_ID",UuidUtil.get32UUID());
				    tpd.put("VAI_VA_ID",vaid);
				    tpd.put("VAI_SRC",fm.get(key));
				    tpd.put("VAI_TYPE",Const2.ZZY2_VA_IMG_REVIEW);
				    tpd.put("VAI_CTIME",Tools.date2Str(new Date()));
				    tpd.put("VAI_UTIME",Tools.date2Str(new Date()));
				    save(tpd);
				}
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	@Override
	public Map<String, Object> app_zzyList(PageData pd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		PageData zzyPd=new PageData();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vailist", pd)){	//检查参数
				zzyPd.put("VAI_VA_ID",pd.getString("vaid"));
				if(zzyPd.get("VAI_TYPE")==null||zzyPd.get("VAI_TYPE")=="")
					zzyPd.put("VAI_TYPE",Const2.ZZY2_VA_IMG_REVIEW);
				List<PageData>list=zzyList(zzyPd);
				map.put("pd",list);
				result="01";
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	
}

