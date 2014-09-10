package com.epam.demo.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.epam.demo.dao.FoodDao;
import com.epam.demo.domain.Food;

public class JdbcFoodDaoImpl extends GenericJdbcDao implements FoodDao {

	public JdbcFoodDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Food getFood(int id) {
		String sql = "SELECT * FROM food WHERE id = ?";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (!rs.next()) {
					throw new RuntimeException("Food not found");
				} else {
					Food food = new Food();
					food.setId(rs.getInt("id"));
					food.setName(rs.getString("name"));

					return food;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int addFood(Food food) {
		String sql = "INSERT INTO food (name) VALUES (?)";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, food.getName());
			ps.execute();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				food.setId(rs.getInt(1));
			}
			
			return food.getId();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateFood(Food food) {
		String sql = "UPDATE food SET name = ? WHERE id = ?";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, food.getName());
			ps.setInt(2, food.getId());

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated != 1) {
				throw new RuntimeException("Zero or more than one row updated.");
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteFood(int id) {
		String sql = "DELETE FROM food where id = ?";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public List<Food> getFoodList() {

		String sql = "SELECT * FROM food";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			List<Food> foodList = new ArrayList<>();
			try (ResultSet rs = ps.executeQuery()) {
				Food food;
				while (rs.next()) {
					food = new Food();
					food.setId(rs.getInt("id"));
					food.setName(rs.getString("name"));
					foodList.add(food);
				}
			}
			return foodList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
