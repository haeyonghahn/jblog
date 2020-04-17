package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.security.Auth;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String main(@PathVariable String id, @PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, Model model) {

		Long categoryNo = 0L;
		Long postNo = 0L;

		BlogVo blogVo = blogService.getBlog(id);

		if (blogVo == null) {
			return "error/404";
		}

		if (pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = postService.getPostNo(categoryNo);
		} else {
			categoryNo = categoryService.getCategoryNo(id);
			postNo = postService.getPostNo(categoryNo);
		}

		List<CategoryVo> listCategory = categoryService.postCount(id);
		List<PostVo> listPost = postService.getPost(categoryNo);

		PostVo postVoSingle = postService.getPostByNoSingle(postNo);

		model.addAttribute("blogVo", blogVo);
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listPost", listPost);
		model.addAttribute("postVoSingle", postVoSingle);
		model.addAttribute("categoryNo", categoryNo);

		return "blog/blog-main";
	}

	@Auth
	@RequestMapping("/admin/basic")
	public String basic(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);

		model.addAttribute("blogVo", blogVo);

		return "blog/blog-admin-basic";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String basic(@PathVariable String id, @RequestParam("title") String title,
			@RequestParam("logo-file") MultipartFile multipartFile, Model model) {

		BlogVo blogVo = blogService.getBlog(id);

		String url = blogService.fileUpload(multipartFile);

		if (url == null) {
			blogVo = blogService.getBlog(id);
			url = blogVo.getLogo();
		}

		blogVo.setTitle(title);
		blogVo.setLogo(url);
		blogService.update(blogVo);

		model.addAttribute("blogVo", blogVo);

		return "redirect:/{id}";
	}

	@Auth
	@RequestMapping("admin/category")
	public String category(@PathVariable String id, Model model) {

		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryVo = categoryService.postCount(id);

		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryVo", categoryVo);

		return "blog/blog-admin-category";
	}

	@Auth
	@RequestMapping(value = "admin/category", method = RequestMethod.POST)
	public String category(@PathVariable String id, @RequestParam("name") String name,
			@RequestParam("desc") String description, Model model) {

		Map<String, String> map = new HashMap<>();

		map.put("id", id);
		map.put("name", name);
		map.put("description", description);

		categoryService.insert(map);

		return "redirect:/{id}";
	}

	@Auth
	@RequestMapping("/admin/delete/{no}")
	public String delete(@PathVariable String id, @PathVariable String no, Model model) {
		categoryService.delete(no, id);
		return "redirect:/{id}";
	}

	@Auth
	@RequestMapping("/admin/write")
	public String write(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryVo = categoryService.postCount(id);

		model.addAttribute("categoryVo", categoryVo);
		model.addAttribute("blogVo", blogVo);

		return "blog/blog-admin-write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable String id, @RequestParam("category") String name,
			@RequestParam("title") String title, @RequestParam("contents") String contents) {

		Map<String, String> map = new HashMap<>();
		String no = categoryService.findNoByName(name, id);

		map.put("no", no);
		map.put("title", title);
		map.put("contents", contents);

		postService.insert(map);

		return "redirect:/{id}";
	}
	
	@Auth
	@RequestMapping("admin/category/spa")
	public String indexSpa(@PathVariable String id, Model model) {

		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryVo = categoryService.postCount(id);

		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryVo", categoryVo);

		return "blog/blog-admin-category-spa";
	}
}
