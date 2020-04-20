package com.douzone.jblog.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		
		Map<String, Object> map = new HashMap<>();
		map.put("no", vo.getNo());
		map.put("id", id);
		map.put("name", vo.getName());
		map.put("description", vo.getDescription());

		categoryService.insert(map);
		
		return JsonResult.success(map);
	}
	
	@DeleteMapping("/api/category/delete/{no}")
	public JsonResult delete(@PathVariable("id") String id, @PathVariable("no") String no) {
		boolean result = categoryService.delete(no, id);
		return JsonResult.success(result ? no : -1);
	}
}
