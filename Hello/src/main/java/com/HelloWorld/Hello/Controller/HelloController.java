package com.HelloWorld.Hello.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping
	public String Hello() {
		return "Para desenvolver essa atividade foram utilizadas a mentalidade de persistência e a habilidade de atenção aos detalhes. ";
	}

}
