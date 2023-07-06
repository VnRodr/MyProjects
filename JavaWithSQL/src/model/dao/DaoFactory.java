package model.dao;

import java.sql.Connection;

import jpaFuncs.DataBaseConnection;
import model.dao.impl.ItemQuantityDao;

public class DaoFactory 
{
	
	public static ItemQuantityModelDao createItemQuantityDao()
	{
		return new ItemQuantityDao(DataBaseConnection.getConnection());
	}
}
