package com.example.demo3;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo3.data.entity.Posts;
import com.example.demo3.data.repository.PostsRepository;
import com.example.demo3.form.PostForm;
import com.example.demo3.service.AccountUserDetails;

@Controller
public class PostController {
	
	@Autowired
	private PostsRepository repo;
	
	@GetMapping("/posts")
	public String posts(Model model) {
		List<Posts> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		// Collections.reverse(list);
		model.addAttribute("posts", list);
		PostForm postForm = new PostForm();
		model.addAttribute("postForm", postForm);
		return "/posts";
	}
	
	@PostMapping("/posts/create")
	public String create(@Validated PostForm postForm, BindingResult bindingResult, 
			@AuthenticationPrincipal AccountUserDetails user, Model model) {
		if (bindingResult.hasErrors()) {
			List<Posts> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
			model.addAttribute("posts", list);
			model.addAttribute("postForm", postForm);
			return "/posts";
		}
		
		Posts post = new Posts();
		post.setName(user.getName());
		post.setTitle(postForm.getTitle());
		post.setText(postForm.getText());
		post.setDate(LocalDateTime.now());
		
		repo.save(post);
		
		return "redirect:/posts";
	}
	
	@PostMapping("/posts/delete/{id}")
	public String delete(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/posts";
	}

}
