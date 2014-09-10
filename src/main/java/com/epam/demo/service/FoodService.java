package com.epam.demo.service;

import java.util.List;

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

	public int addFood(Food food) {
		return foodDao.addFood(food);
	}

	public void updateFood(Food food) {
		foodDao.updateFood(food);
	}

	public void deleteFood(int id) {
		foodDao.deleteFood(id);
	}

	public List<Food> getFoodList() {
		return foodDao.getFoodList();
	}
	
	

}
