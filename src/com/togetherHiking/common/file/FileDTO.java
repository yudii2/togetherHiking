package com.togetherHiking.common.file;

import java.sql.Date;

public class FileDTO {
	
	private String flIdx;
	private String typeIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private Date regDate;
	private int isDel;
	/**
	 * @return the flIdx
	 */
	public String getFlIdx() {
		return flIdx;
	}
	/**
	 * @param flIdx the flIdx to set
	 */
	public void setFlIdx(String flIdx) {
		this.flIdx = flIdx;
	}
	/**
	 * @return the typeIdx
	 */
	public String getTypeIdx() {
		return typeIdx;
	}
	/**
	 * @param typeIdx the typeIdx to set
	 */
	public void setTypeIdx(String typeIdx) {
		this.typeIdx = typeIdx;
	}
	/**
	 * @return the originFileName
	 */
	public String getOriginFileName() {
		return originFileName;
	}
	/**
	 * @param originFileName the originFileName to set
	 */
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	/**
	 * @return the renameFileName
	 */
	public String getRenameFileName() {
		return renameFileName;
	}
	/**
	 * @param renameFileName the renameFileName to set
	 */
	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}
	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}
	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the isDel
	 */
	public int getIsDel() {
		return isDel;
	}
	/**
	 * @param isDel the isDel to set
	 */
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
	public String getDownloadURL() {
		return "/file/" + savePath + renameFileName + "?originName=" + originFileName;
	}
	
	@Override
	public String toString() {
		return "FileDTO [flIdx=" + flIdx + ", typeIdx=" + typeIdx + ", originFileName=" + originFileName
				+ ", renameFileName=" + renameFileName + ", savePath=" + savePath + ", regDate=" + regDate + ", isDel="
				+ isDel + "]";
	}

	
	
	
	
	
	
	

}
