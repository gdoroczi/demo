package com.epam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.demo.domain.Food;
import com.epam.demo.service.FoodService;

@RestController
public class WebServiceController {

	private static final String FOOD_URI = "/food";
	private static final String FOODS_URI = "/foods";
	private static final String FOOD_WITH_ID_URI = "/food/{id}";

	@Autowired
	FoodService foodService;

	@RequestMapping(value = FOOD_WITH_ID_URI, method = RequestMethod.GET)
	public Food getFood(@PathVariable("id") int id) {
		Food f = foodService.getFood(id);
		return f;
	}

	@RequestMapping(value = FOOD_URI, method = RequestMethod.POST)
	public int CreateFood(@RequestBody Food food) {
		return foodService.addFood(food);
	}

	@RequestMapping(value = FOOD_WITH_ID_URI, method = RequestMethod.PUT)
	public void updateFood(@PathVariable("id") int id, @RequestBody Food food) {
		food.setId(id);
		foodService.updateFood(food);
	}

	@RequestMapping(value = FOOD_WITH_ID_URI, method = RequestMethod.DELETE)
	public void deleteFood(@PathVariable("id") int id) {
		foodService.deleteFood(id);
	}

	@RequestMapping(value = FOODS_URI, method = RequestMethod.GET)
	public List<Food> getFoodList() {
		List<Food> foodList = foodService.getFoodList();
		return foodList;
	}

}
