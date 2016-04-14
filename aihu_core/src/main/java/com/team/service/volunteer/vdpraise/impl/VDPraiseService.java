package com.team.service.volunteer.vdpraise.impl;

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
import com.team.service.volunteer.vdonation.VDonationManager;
import com.team.service.volunteer.vdpraise.VDPraiseManager;

/** 
 * 说明： 捐赠点赞
 * 创建人：jaychum
 * 创建时间：2016-04-05
 * @version
 */
@Service("vdpraiseService")
public class VDPraiseService implements VDPraiseManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name="vdonationService")
	private VDonationManager vdonationService;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VDPraiseMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VDPraiseMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VDPraiseMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VDPraiseMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VDPraiseMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VDPraiseMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VDPraiseMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 捐赠点赞
	 */
	@Override
	public Map<String, Object> app_zzyAdd(PageData pd) throws Exception {
		PageData zzyPd=new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "00";
		//if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
			if(AppUtil2.checkParam("appzzy2_vdpadd", pd)){	//检查参数
				String userid=pd.getString("userid");
				String vdid=pd.getString("vdid");
				zzyPd.put("VDP_VD_ID",vdid);
				zzyPd.put("VDP_USER_ID",userid);
				PageData vdp=zzyCheck(zzyPd);
				if(vdp!=null){//已经点赞
					result="06";
					map.put("info","不能重复点赞");
				}else{
					zzyPd.put("VDONATION_ID",UuidUtil.get32UUID());
					zzyPd.put("VDP_TYPE",1);
					zzyPd.put("VDP_STATUS",1);
					zzyPd.put("VDP_CTIME",Tools.date2Str(new Date()));
					zzyPd.put("VDP_UTIME",Tools.date2Str(new Date()));
					save(zzyPd);
					vdonationService.zzyUpdatePraise(vdid,1);
					result="01";
				}
			}else result = "03";
		//}else{result = "05";}
		map.put("result", result);
		return map;
	}
	@Override
	public PageData zzyCheck(PageData pd) throws Exception {
		return (PageData)dao.findForObject("VAPraiseMapper.zzyCheck", pd);
	}
}

