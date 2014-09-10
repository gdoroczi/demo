package com.epam.demo.dao;

import java.util.List;

import com.epam.demo.domain.Food;

public interface FoodDao {
	
	int addFood(Food food);
	
	Food getFood(int id);
	
	void updateFood(Food food);
	
	void deleteFood(int id);
	
	List<Food> getFoodList();
	
}
