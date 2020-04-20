package com.douzone.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> postCount(String id) {
		return categoryRepository.postCount(id);
	}
	
	public boolean insert(Map<String, Object> map) {
		return categoryRepository.insert(map);
	}

	public boolean delete(String no, String id) {			
		if(!categoryRepository.findPostCount(no).equals("0")) {
			return false;
		}
		else if(categoryRepository.findCategoryCount(id).equals("1")) {
			return false;
		}
		else {
			return categoryRepository.delete(no);
		}
	}
	
	public String findNoByName(String name, String id) {
		return categoryRepository.findNoByName(name, id);
	}

	public Long getCategoryNo(String id) {
		return categoryRepository.getCategoryNo(id);
	}
}
