package com.rental.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rental.RentalmanagementApplication;


@Controller
public class systemcontroller {
	
	// return home page
	@GetMapping("/")
	public String showHome(Model model) {
		String[][] result = new RentalmanagementApplication().showHouses();
		System.out.println(result[0][0]);
		model.addAttribute("houses", result);

		return "home";
	}
	@GetMapping("/westlands")
	public String getWestlandsPage(Model model) {
		String vacancies = new RentalmanagementApplication().getVacancies();
	    model.addAttribute("vacancies", vacancies);
		System.out.println(vacancies);

		List<String> locations = new RentalmanagementApplication().getLocation();
		model.addAttribute("locations", locations);

	    return "Westlands"; // Must match the template name in src/main/resources/templates
	}
	
}
