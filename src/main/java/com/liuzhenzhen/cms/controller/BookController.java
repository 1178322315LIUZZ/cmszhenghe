package com.liuzhenzhen.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	@RequestMapping("b")
	public String book() {
		return "book";
	}
}
