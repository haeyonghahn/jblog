package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getPost(Long categoryNo) {
		return sqlSession.selectList("post.getPost", categoryNo);
	}

	public boolean insert(Map<String, String> map) {
		return sqlSession.insert("post.insert", map) == 1;
	}

	public Long getPostNo(Long categoryNo) {
		return sqlSession.selectOne("post.getPostNo", categoryNo);
	}

	public PostVo getPostByNoSingle(Long postNo) {
		return sqlSession.selectOne("post.getPostByNoSingle", postNo);
	}
}
