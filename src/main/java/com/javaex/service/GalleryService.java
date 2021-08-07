package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	public List<GalleryVo> getList() {
		System.out.println("GalleryService: getList;");
		return galleryDao.getList();
	}
	
	public int addGalleryFile(GalleryVo galleryVo, MultipartFile file) {
		System.out.println("galleryService: addGalleryFile;");
		
		// 서버에 파일 저장 경로
		String saveDir = "S:\\javaStudy\\upload\\";
		
		// 원본 파일 이름
		String orgName = file.getOriginalFilename();
		
		// 파일 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		// 저장시 파일명 중복처리
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		// 파일 저장
		String filePath = saveDir + saveName;
		
		// 파일 크기
		long fileSize = file.getSize();
		
		// save the file in SERVER HDD
		try {
			byte[] fileData = file.getBytes();
//			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bfOut = new BufferedOutputStream(new FileOutputStream(filePath));
			
			bfOut.write(fileData);
			bfOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// save the file information in SERVER DB
		// galleryVo에 추가
		galleryVo.setFilePath(filePath);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFileSize(fileSize);
		
		System.out.println(galleryVo);
		
		return galleryDao.addGalleryFile(galleryVo);
	}
	
	public GalleryVo viewInfo(int no) {
		System.out.println("galleryService: viewInfo;");
		
		return galleryDao.viewInfo(no);
	}

}
