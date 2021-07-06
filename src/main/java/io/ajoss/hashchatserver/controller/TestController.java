package io.ajoss.hashchatserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@RequestMapping("/home")
	public String getHome() {
		return "Hello world!";
	}
}
