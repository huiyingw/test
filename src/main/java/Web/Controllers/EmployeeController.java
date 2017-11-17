package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController{

	@RequestMapping("/employee")
	public String route(Model model){
    // TODO fill me out!
    return "employee";
	}
}
