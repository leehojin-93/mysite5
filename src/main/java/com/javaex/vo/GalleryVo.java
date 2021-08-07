package com.javaex.vo;

public class GalleryVo {

	private int no;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	private int userNo;
	private String name;

	public GalleryVo() {
		
	}
	
	public GalleryVo(int no, String content, String filePath, String orgName, String saveName, long fileSize,
			int userNo, String name) {
		this.no = no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.userNo = userNo;
		this.name = name;
	}
	
	public GalleryVo(String content, String filePath, String orgName, String saveName, long fileSize,
			int userNo, String name) {
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.userNo = userNo;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", content=" + content + ", filePath=" + filePath + ", orgName=" + orgName
				+ ", saveName=" + saveName + ", fileSize=" + fileSize + ", userNo=" + userNo + ", name=" + name + "]";
	}

}
