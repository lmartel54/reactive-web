package com.martel.proto.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

//	@Autowired
//	private CustomerRepository repo;

	@RequestMapping("/")
	public String index(final Model model) {

//		// loads 1 and display 1, stream data, data driven mode.
//		IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(repo.findAll2(), 1);
//
//		model.addAttribute("movies", reactiveDataDrivenMode);
//
//		// classic, wait repository loaded all and display it.
//		// model.addAttribute("movies", movieRepository.findAll());

		return "index";
	}
}