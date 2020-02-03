package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ISD_code_controller {
	@Autowired
	ISD_code_Repo repo;
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping("/isd")
	public ISD_code addISD(ISD_code isd_code) {
		repo.save(isd_code);
		return isd_code;
	}
	
	@GetMapping(path = "/isds")
	public List<ISD_code> getISDS(){
		return repo.findAll();
	}
	
	@RequestMapping("/phone_number/{phone}")
	public String getISD(@PathVariable("phone") String phone) {
		String sub = phone.substring(0, phone.length() - 10);
		List<ISD_code> all_possible = repo.findByisd(sub);
		if(all_possible.size() == 0)
			return ("Phone number is invalid");
		else {
			return all_possible.toString();
		}
	}
}
