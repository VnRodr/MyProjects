package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jpaFuncs.DataBaseConnection;
import jpaFuncs.exception.ExceptionHandler;
import model.dao.ItemQuantityModelDao;
import model.entities.Item;

public class ItemQuantityDao implements ItemQuantityModelDao
{
	private Connection conn;
	
	public ItemQuantityDao(Connection conn)
	{
		this.conn = conn;
	}
	
	@Override
	public void insert(Item item) {
		PreparedStatement st = null
				;
		try
		{
			st = conn.prepareStatement("INSERT INTO To_buy (item, quantity) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, item.getItem());
			st.setInt(2, item.getQuantity());
		
			int rowsAfctd = st.executeUpdate();
			
			if (rowsAfctd > 0)
			{
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next())
				{
					int id = rs.getInt(1);
					item.setId(id);
				}
				DataBaseConnection.closeResultSet(rs);
			}
			else System.out.println("Error, something went wrong");		
		}
		catch (SQLException e)
		{
			throw new ExceptionHandler(e.getMessage());
		}
		finally
		{
			DataBaseConnection.closeStatement(st);
		}
	}

	@Override
	public void update(Item item) {
		PreparedStatement st = null;
		
		try
		{
			st = conn.prepareStatement("UPDATE To_buy SET item = ?, quantity = ?");
			
			st.setString(1, item.getItem());
			st.setInt(2, item.getQuantity());
			
			System.out.println("Update completed \n");
		}
		catch (SQLException e)
		{
			throw new ExceptionHandler(e.getMessage());
		}
		finally
		{
			DataBaseConnection.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) 
	{	
		try(PreparedStatement st = conn.prepareStatement("DELETE FROM To_buy WHERE id = ?"))
		{	
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e)
		{
			e.getMessage();
		}	
	}

	@Override
	public Item findById(Integer id) 
	{	
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try 
		{
			st = conn.prepareStatement("SELECT *"
					+ " FROM To_buy WHERE To_buy.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next())
			{
				Item item = instantiateItem(rs);
				return item;
			}
			else return null;
		}
		catch (SQLException e)
		{
			throw new ExceptionHandler(e.getMessage());
		}
		finally 
		{
			DataBaseConnection.closeStatement(st);	
		}

	}

	public Item findByName(String name) 
	{	
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try 
		{
			st = conn.prepareStatement("SELECT * FROM To_buy WHERE item LIKE ?");
			
			st.setString(1, "%" + name + "%");
			rs = st.executeQuery();
			if (rs.next())
			{
				Item item = instantiateItem(rs);
				return item;
			}
			else 
			{
				return null;
			}
		}
		catch (SQLException e)
		{
			throw new ExceptionHandler(e.getMessage());
		}
		finally 
		{
			DataBaseConnection.closeStatement(st);	
		}
	}
	
	@Override
	public List<Item> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			ps = conn.prepareStatement("SELECT * FROM To_buy ORDER BY item");
			rs = ps.executeQuery();
			
			List<Item> list = new ArrayList<>();
			
			while (rs.next())
			{
				Item item = instantiateItem(rs);
				list.add(item);
			}
			
			return list;
		}
		catch(SQLException e)
		{
			throw new ExceptionHandler(e.getMessage());
		}
		finally
		{
			DataBaseConnection.closeResultSet(rs);
			DataBaseConnection.closeStatement(ps);
		}
	}
	
	private Item instantiateItem(ResultSet rs) throws SQLException 
	{
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setItem(rs.getString("item"));
		
		return item;
	}
	
	
}
