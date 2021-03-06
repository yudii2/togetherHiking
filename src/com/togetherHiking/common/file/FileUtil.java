package com.togetherHiking.common.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import com.togetherHiking.common.code.Config;
import com.togetherHiking.common.code.ErrorCode;
import com.togetherHiking.common.exception.HandleableException;

public class FileUtil {
	
	private static final int MAX_SIZE = 1024*1024*10;
	
	public MultiPartParams fileUpload(HttpServletRequest request){
		
		Map<String,List> res = new HashMap<String, List>();
		List<FileDTO> fileDTOs = new ArrayList<FileDTO>();
		
		try {
			MultipartParser parser = new MultipartParser(request, MAX_SIZE);
			parser.setEncoding("UTF-8");
			Part part = null;
			
			while((part = parser.readNextPart()) != null) {
				if(part.isFile()) {
					FilePart filePart = (FilePart) part;
					
					if(filePart.getFileName() != null) {
						FileDTO fileDTO = createFileDTO(filePart);
						filePart.writeTo(new File(getSavePath() + fileDTO.getRenameFileName()));
						fileDTOs.add(fileDTO);
					}
					
				}else if(part.isParam()) {
					ParamPart paramPart = (ParamPart) part;
					setParameterMap(paramPart, res);
				}
			}
			
			res.put("com.togetherHiking.files",fileDTOs);
			
		} catch (IOException e) {
			throw new HandleableException(ErrorCode.FAILED_FILE_UPLOAD_ERROR,e);
		}
		
		return new MultiPartParams(res);
	}
	
	public Map<String,FileDTO> profileUpload(HttpServletRequest request){
		
		Map<String,FileDTO> res = new HashMap<String, FileDTO>();
		FileDTO fileDTO = new FileDTO();
		
		try {
			MultipartParser parser = new MultipartParser(request, MAX_SIZE);
			parser.setEncoding("UTF-8");
			Part part = null;
			
			while((part = parser.readNextPart()) != null) {
				if(part.isFile()) {
					FilePart filePart = (FilePart) part;
					fileDTO = createFileDTO(filePart);
					filePart.writeTo(new File(getSavePath() + fileDTO.getRenameFileName())); //????????????????????????
				}
			}
					
			res.put("com.togetherHiking.files",fileDTO);
			
		} catch (IOException e) {
			throw new HandleableException(ErrorCode.FAILED_FILE_UPLOAD_ERROR,e);
		}
		
		return res;
	}
	


	private String getSavePath() {
		
		//2. ??????????????????????????? ?????????????????????????????????????? ?????????????????? ????????????
		//		 ???????????????????????????  ????????????????????? + /??????/??????/?????? ?????????????????? ????????????
		String subPath = getSubPath();
		String savePath = Config.UPLOAD_PATH.DESC + subPath;
		
		File dir = new File(savePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		return savePath;
	}
	
	private String getSubPath() {
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		return year + "\\" + month + "\\" + date + "\\";
	}
	
	private FileDTO createFileDTO(FilePart filePart) {
		FileDTO fileDTO = new FileDTO();
		String renameFileName = UUID.randomUUID().toString();
		String savePath = getSubPath();
		
		fileDTO.setOriginFileName(filePart.getFileName());
		fileDTO.setRenameFileName(renameFileName);
		fileDTO.setSavePath(savePath);
		
		return fileDTO;
	}
	
	private void setParameterMap(ParamPart paramPart, Map<String,List> res) throws UnsupportedEncodingException {
		if(res.containsKey(paramPart.getName())) {
			res.get(paramPart.getName()).add(paramPart.getStringValue());
		}else {
			List<String> param = new ArrayList<String>();
			param.add(paramPart.getStringValue());
			res.put(paramPart.getName(), param);
		}
	}
	
}
