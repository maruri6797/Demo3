package com.example.demo3.form;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostForm {
	@Size(min = 1, max = 200)
	private String title;
	
	@Size(min = 1, max = 200)
	private String text;
}
