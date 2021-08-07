package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileupService {

	/*
	 * @Autowired private FileupDao fileupDao;
	 */

	// FILE UPLOAD
	public String restore(MultipartFile file) {
		System.out.println("FileupService: restore;");
		
		// 파일 저장 위치(path)
		String saveDir = "S:\\javaStudy\\upload\\";
		
		// 원본 파일 이름(페이지 출력 용도)
		String orgName = file.getOriginalFilename();
		System.out.println("원본 파일 이름: " + orgName); // fileName.extension
		
		// 파일 확장자(파일 옆 아이콘 출력 용도 등등)
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("파일 확장자: " + exName); // .extension
		
		// 저장할때 파일 이름(원본파일이름 중복 때문에)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("저장시 파일 이름: " + saveName); // currentTimeMillis + randomUUID + .extension
		
		// 파일 저장(path + name) = saveDir + saveName
		String filePath = saveDir + saveName;
		System.out.println("파일 저장 위치: " + filePath);
		
		// 파일 크기(size)
		long fileSize = file.getSize();
		System.out.println("저장 파일 크기: " + fileSize);
		
		// save the file in SERVER HDD
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bfOut = new BufferedOutputStream(out);
			
			bfOut.write(fileData);
			bfOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// save the file information in SERVER DB
		
		return saveName;
		
	}
}
