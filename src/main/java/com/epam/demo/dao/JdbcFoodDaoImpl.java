package com.epam.demo.dao;

import com.epam.demo.domain.Food;

public class JdbcFoodDaoImpl implements FoodDao {

	public Food getFood(int id) {
		Food f = new Food();
		f.setId(id);
		f.setName("Hambi");
		
		return f;
	}

}
