package com.team.service.volunteer.gmpraise.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;

import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.AppUtil2;
import com.team.util.PageData;
import com.team.util.Tools;
import com.team.util.UuidUtil;
import com.team.service.retirement.gm.GMManager;
import com.team.service.volunteer.gmpraise.GMPraiseManager;

/** 
 * 说明： 养老院点赞
 * 创建人：jaychum
 * 创建时间：2016-04-05
 * @version
 */
@Service("gmpraiseService")
public class GMPraiseService implements GMPraiseManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="gmService")
	private GMManager gmService;
	/**新增
	 * @param pd
	 * @throws Exception 
	 */
	public void save(PageData pd)throws Exception{
		dao.save("GMPraiseMapper.save", pd);
	}
		
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("GMPraiseMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("GMPraiseMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("GMPraiseMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("GMPraiseMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMPraiseMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("GMPraiseMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 点赞
	 */
	@Override
	public Map<String, Object> app_zzyAdd(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_gmpadd", pd)){	//检查参数
				String userid=pd.getString("userid");
				String gmid=pd.getString("gmid");
				zzyPd.put("GMP_GM_ID",gmid);
				zzyPd.put("GMP_USER_ID",userid);
				PageData gmp=zzyCheck(zzyPd);
				if(gmp!=null){//已经点赞
					result="06";
					map.put("info","不能重复点赞");
				}else{
					zzyPd.put("GMPRAISE_ID",UuidUtil.get32UUID());
					zzyPd.put("GMP_TYPE",1);
					zzyPd.put("GMP_STATUS",1);
					zzyPd.put("GMP_CTIME",Tools.date2Str(new Date()));
					zzyPd.put("GMP_UTIME",Tools.date2Str(new Date()));
					save(zzyPd);
					gmService.zzyUpdatePraise(gmid,1);
					result="01"; 	 	
				}
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	@Override
	public PageData zzyCheck(PageData pd)throws Exception{
		return (PageData)dao.findForObject("GMPraiseMapper.zzyCheck", pd);
	}
}

