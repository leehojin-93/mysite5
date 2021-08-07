package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> getList() {
		System.out.println("GalleryDao: getList;");
		return sqlSession.selectList("gallery.getList");
	}
	
	public int addGalleryFile(GalleryVo galleryVo) {
		System.out.println("galleryDao: dadGalleryFile;");
		System.out.println(galleryVo);
		
		return sqlSession.insert("gallery.addGalleryFile", galleryVo);
	}
	
	public GalleryVo viewInfo(int no) {
		System.out.println("galleryDao: viewInfo;");
		
		return sqlSession.selectOne("gallery.galleryInfo", no);
	}

}
