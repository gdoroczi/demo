package com.epam.demo.service;

import com.epam.demo.dao.FoodDao;
import com.epam.demo.domain.Food;

public class FoodService {
	
	private FoodDao foodDao;

	public FoodService(FoodDao foodDao) {
		super();
		this.foodDao = foodDao;
	}
	
	public Food getFood(int id) {
		return foodDao.getFood(id);
	}
	
	

}
