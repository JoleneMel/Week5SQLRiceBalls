package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ExtraIngredient;

public class ExtraIngredientDao {
	private Connection connection;
	private final String GET_EXTRA_INGREDIENTS_BY_RICEBALL_ID_QUERY = "SELECT * FROM extraIngredients WHERE riceBall_id = ?";
	private final String DELETE_EXTRA_INGREDIENTS_BY_RICEBALL_ID_QUERY = "DELETE FROM extraIngredients WHERE riceBall_id = ?";
	private final String CREATE_NEW_EXTRA_INGREDIENT_QUERY = "INSERT INTO extraIngredients(ingredient, riceBall_id)"
			+ " VALUES(?,?)";
	private final String DELETE_EXTRA_INGREDIENT_BY_ID_QUERY = "DELETE FROM extraIngredients WHERE id = ?";
	private final String UPDATE_EXTRA_INGREDIENT_BY_ID_QUERY = "UPDATE extraIngredients SET ingredient = ? WHERE id =?";
	
	public ExtraIngredientDao() {
		connection = DBConnection.getConnection();
	}

	public List<ExtraIngredient> getExtraIngredientsByRiceBallId(int riceBallId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_EXTRA_INGREDIENTS_BY_RICEBALL_ID_QUERY);
		ps.setInt(1, riceBallId);
		ResultSet rs = ps.executeQuery();
		List<ExtraIngredient> extraIngredients = new ArrayList<ExtraIngredient>();
		
		while (rs.next()) {
			extraIngredients.add(new ExtraIngredient(rs.getInt(1), rs.getString(2)));
		}
		return extraIngredients;
	}
	
	public void createNewExtraIngredient(String ingredient, int riceBallId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_EXTRA_INGREDIENT_QUERY);
		ps.setString(1, ingredient);
		ps.setInt(2, riceBallId);
		ps.executeUpdate();
	}
	
	public void deleteExtraIngredientsByRiceBallId(int riceBallId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_EXTRA_INGREDIENTS_BY_RICEBALL_ID_QUERY);
		ps.setInt(1, riceBallId);
		ps.executeUpdate();
	}
	
	public void updateExtraIngredientById(int id, String ingredient) throws SQLException  {
		PreparedStatement ps = connection.prepareStatement(UPDATE_EXTRA_INGREDIENT_BY_ID_QUERY);
		ps.setInt(2, id);
		ps.setString(1, ingredient);
		ps.executeUpdate();
	}
	
	public void deleteExtraIngredientById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_EXTRA_INGREDIENT_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

}
