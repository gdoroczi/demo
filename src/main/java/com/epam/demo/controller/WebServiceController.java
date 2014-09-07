package com.epam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.demo.domain.Food;
import com.epam.demo.service.FoodService;

@RestController
public class WebServiceController {
	
	@Autowired
	FoodService foodService;

    @RequestMapping("/hello")
    public Food getFoodName(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	Food f = foodService.getFood(12786734);
		
		return f;
    }

}
