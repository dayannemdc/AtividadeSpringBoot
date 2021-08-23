package com.HelloWorld.Hello2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello2")
public class HelloController {

	@GetMapping
	public String hello() {
		return "Os objetivos de aprendizagem desta semana são voltados para a framework Spring boot";
	}
	
}
