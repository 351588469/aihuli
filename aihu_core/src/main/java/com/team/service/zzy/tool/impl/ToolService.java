package com.team.service.zzy.tool.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.team.service.zzy.tool.ToolManager;
import com.team.util.Const;
import com.team.util.DateUtil;
import com.team.util.FileUpload;
import com.team.util.PathUtil;
import com.team.util.UuidUtil;
@Service("toolService")
public class ToolService implements ToolManager{
	@Override
	public Map<Integer,String> zzyUploadImg(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		//CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		Iterator<String> iter = multiRequest.getFileNames();
		Integer x=1;
		Map<Integer,String>ans=new HashMap<Integer, String>();
		while (iter.hasNext()) {
			MultipartFile file = multiRequest.getFile(iter.next());
			if (file!=null) {
				String  ffile = DateUtil.getDays(), fileName = "";
				if (null != file && !file.isEmpty()) {
					String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;	//文件上传路径
					fileName = FileUpload.fileUp(file, filePath,UuidUtil.get32UUID());			//执行上传
				}
				ans.put(x++, DateUtil.getDays()+"/"+fileName);
			}
		}
		return ans;
	}

}
