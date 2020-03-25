package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserVo getUser(UserVo vo) {
		return userRepository.getUser(vo);
	}

	public boolean join(UserVo vo) {
		return userRepository.join(vo);
	}

	public boolean createBlog(UserVo vo) {
		return userRepository.createBlog(vo);
	}

	public boolean createCategory(UserVo vo) {
		return userRepository.createCategory(vo);
	}
}
