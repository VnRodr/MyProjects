package model.dao;

import java.util.List;

import model.entities.Item;

public interface ItemQuantityModelDao 
{
	public void insert(Item item);
	void update(Item item);
	void deleteById(Integer id);
	Item findById(Integer id);
	List<Item> findAll();
}
