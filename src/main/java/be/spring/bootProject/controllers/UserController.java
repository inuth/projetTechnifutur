package be.spring.bootProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/unauthorized")
	public String notAccess() {
		return "unauthorized";
	}
}
