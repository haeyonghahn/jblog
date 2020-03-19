package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo getUser(UserVo vo) {
		return sqlSession.selectOne("user.getUser", vo);
	}

	public boolean join(UserVo vo) {
		return sqlSession.insert("user.insert", vo) == 1;
	}

	public boolean createBlog(UserVo vo) {
		return sqlSession.insert("user.createBlog", vo) == 1;
	}

	public boolean createCategory(UserVo vo) {
		return sqlSession.insert("user.createCategory", vo) == 1;
	}
}
