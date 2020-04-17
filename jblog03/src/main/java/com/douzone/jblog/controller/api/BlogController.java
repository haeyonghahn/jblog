package com.douzone.jblog.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;

@RestController("BlogApiController")
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/api/category/list")
	public JsonResult list(@PathVariable("id") String id) {
		List<CategoryVo> list = categoryService.postCount(id);
		return JsonResult.success(list);
	}
	
	@PostMapping("/api/category/add")
	public JsonResult add(@PathVariable("id") String id
			, @RequestBody CategoryVo vo) {
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", vo.getName());
		map.put("description", vo.getDescription());

		categoryService.insert(map);
		vo.setId(id);
		
		System.out.println(vo);
		
		return JsonResult.success(vo);
	}
	
	@PostMapping("/api/category/delete")
	public JsonResult delete(@PathVariable("id") String id) {
		return JsonResult.success(null);
	}
}
