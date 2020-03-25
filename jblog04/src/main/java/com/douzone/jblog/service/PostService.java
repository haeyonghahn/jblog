package com.douzone.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;


@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getPost(Long categoryNo) {
		return postRepository.getPost(categoryNo);
	}

	public boolean insert(Map<String, String> map) {
		return postRepository.insert(map);
	}

	public Long getPostNo(Long categoryNo) {
		return postRepository.getPostNo(categoryNo);
	}

	public PostVo getPostByNoSingle(Long postNo) {
		return postRepository.getPostByNoSingle(postNo);
	}
}
