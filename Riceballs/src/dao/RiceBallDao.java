package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.RiceBall;

public class RiceBallDao {
	private Connection connection;
	private ExtraIngredientDao extraIngredientDao;
	private final String GET_RICEBALLS_QUERY = "SELECT * FROM riceBalls";
	private final String GET_RICEBALL_BY_ID_QUERY = "SELECT * FROM riceBalls WHERE id = ?";
	private final String CREATE_NEW_RICEBALL_QUERY = "INSERT INTO riceBalls(name) VALUES (?)";
	private final String DELETE_RICEBALL_BY_ID_QUERY = "DELETE FROM riceBalls WHERE id =?";
	private final String UPDATE_RICEBALL_BY_ID_QUERY = "UPDATE riceBalls SET name = ? WHERE id=?";
	public RiceBallDao() {
		connection = DBConnection.getConnection();
		extraIngredientDao = new ExtraIngredientDao(); 
	}
	
	public List<RiceBall> getRiceBalls() throws SQLException {
		ResultSet rs= connection.prepareStatement(GET_RICEBALLS_QUERY).executeQuery();
		List<RiceBall> riceBalls = new ArrayList<RiceBall>();
		
		while (rs.next()) {
			riceBalls.add(populateRiceBall(rs.getInt(1), rs.getString(2)));
		}
		return riceBalls;
		
	}
	
	public RiceBall getRiceBallbyId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_RICEBALL_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateRiceBall(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewRiceBall(String riceBallName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_RICEBALL_QUERY);
		ps.setString(1, riceBallName);
		ps.executeUpdate();
	}
	
	public void deleteRiceBallById(int id) throws SQLException {
		extraIngredientDao.deleteExtraIngredientsByRiceBallId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_RICEBALL_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateRiceBallById(int id, String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_RICEBALL_BY_ID_QUERY);
		ps.setInt(2, id);
		ps.setString(1, name);
		ps.executeUpdate();
	}
	
	private RiceBall populateRiceBall(int id, String name) throws SQLException {
		return new RiceBall(id, name, extraIngredientDao.getExtraIngredientsByRiceBallId(id));
	}

}