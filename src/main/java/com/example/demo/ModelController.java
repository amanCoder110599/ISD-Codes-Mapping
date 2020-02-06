package com.example.demo;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {
	
	@Autowired
	ModelRepository repo;
	
	@RequestMapping("/phone_number/{phone}")
	@ResponseStatus(HttpStatus.OK)
	public List<Model> getISD(@PathVariable("phone") String phone) {
		String sub = phone.substring(0, phone.length() - 10);
		List<Model> all_possible = repo.findByisd(sub);
		if(all_possible.size() == 0) {
			throw new No_such_resource_found_Exception("The phone number is invalid");
		}
		return all_possible;
	}
}
