package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.ItemQuantityModelDao;
import model.entitie.Item;

public class ItemQuantityDao implements ItemQuantityModelDao
{
	private Connection conn;
	
	public ItemQuantityDao(Connection conn)
	{
		this.conn = conn;
	}
	
	@Override
	public void insert(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
