package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(Map<String, Object> map) {
		return sqlSession.insert("category.insert", map) == 1;
	}

	public boolean delete(String no) {
		return sqlSession.delete("category.delete", no) == 1;
	}

	public String findNoByName(String name, String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("id", id);
		return sqlSession.selectOne("category.findNoByName", map);
	}

	public List<CategoryVo> postCount(String id) {
		return sqlSession.selectList("category.postCount", id);
	}

	public String findPostCount(String no) {
		return sqlSession.selectOne("category.findPostCount", no);
	}

	public String findCategoryCount(String id) {
		return sqlSession.selectOne("category.findCategoryCount", id);
	}
	
	public Long getCategoryNo(String id) {
		return sqlSession.selectOne("category.getCategoryNo", id);
	}

	
}
