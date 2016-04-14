package com.team.service.volunteer.vdimg.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.team.dao.DaoSupport;
import com.team.entity.Page;
import com.team.util.PageData;
import com.team.service.volunteer.vdimg.VDImgManager;

/** 
 * 说明： 捐赠图片
 * 创建人：jaychum
 * 创建时间：2016-04-05
 * @version
 */
@Service("vdimgService")
public class VDImgService implements VDImgManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("VDImgMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("VDImgMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("VDImgMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VDImgMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VDImgMapper.listAll", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> zzyList(String vdid)throws Exception{
		return (List<PageData>)dao.findForList("VDImgMapper.zzyList",vdid);
	}
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VDImgMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VDImgMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

