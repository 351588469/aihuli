package com.team.service.zzy.record;

import java.util.Map;

import com.team.util.PageData;

public interface RecordManager {
	//护士端新增记录
	public Map<String,Object> zzyNewRecord(PageData pd)throws Exception;
	//历史记录
	public Map<String, Object> zzyPastRecord(PageData pd) throws Exception;
	public Map<String, Object> zzyNewRecordMult(PageData pd) throws Exception;
}
