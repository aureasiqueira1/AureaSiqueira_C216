package br.inatel.labs.labrest.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ctrl + espaço = preenche a palavra

@RestController
@RequestMapping("/hello")
public class HelloController {

	// ctrl + 1 = dá dica sobre como resolver o erro
	@GetMapping
	public MyMessage processarGetHello() {
		MyMessage msg = new MyMessage();
		msg.setInfo("Olá mundo REST");
		return msg;
	}
}
