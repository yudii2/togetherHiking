package com.togetherHiking.common.file;

import java.util.List;
import java.util.Map;

public class MultiPartParams {
	
	private Map<String,List> params;
	private Map<String,FileDTO> param;
	
	public MultiPartParams(Map<String,List> params) {
		this.params = params;
	}
	
	public String getParameter(String name) {
		
		if(name.equals("com.togetherHiking.files")) {
			throw new RuntimeException("com.togetherHiking.files는 사용할 수 없는 파라미터 명입니다.");
		}
		
		return (String) params.get(name).get(0);
	}
	
	public String[] getParameterValues(String name) {
		
		if(name.equals("com.togetherHiking.files")) {
			throw new RuntimeException("com.togetherHiking.files는 사용할 수 없는 파라미터 명입니다.");
		}
		
		List<String> res = params.get(name);
		return res.toArray(new String[res.size()]);
	}

	public List<FileDTO> getFilesInfo(){
		return params.get("com.togetherHiking.files");
	}
	
	public FileDTO getProfile() {
		return param.get("com.togetherHiking.files");
	}
}
