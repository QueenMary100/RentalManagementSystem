package com.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rental.RentalmanagementApplication;



@Controller
@ResponseBody
public class userController {
	RentalmanagementApplication rentalmanagementApplication = new RentalmanagementApplication();

    @PostMapping("/register")

    public String registerUser(@RequestParam("firstName") String firstName,
    		@RequestParam String lastName,
    		@RequestParam String contact
    		) {
    	rentalmanagementApplication.register1(firstName,lastName,contact);
    	   	
    	return "User registration was successful!";
    	
    }

}
