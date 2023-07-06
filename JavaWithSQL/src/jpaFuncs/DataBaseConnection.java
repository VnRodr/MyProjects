package jpaFuncs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection 
{
	private static String name = "postgres";
	private static String url = "jdbc:postgresql://localhost:5432/dataBase";
	private static String password = "";
	
	private static Connection conn;
	
	public static Connection getConnection()
	{
		if (conn == null)
		{
			try 
			{
				conn = DriverManager.getConnection(url, name, password);
			}
			catch (SQLException e)
			{
				e.getMessage();
			}
		}
		return conn;
	}
	
	public static void closeConnection()
	{
		if (conn != null)
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e)
			{
				e.getMessage();
			}
		}
	}
	
	public static void closeStatement(Statement st) 
	{
		if(st != null)
		{
			try 
			{
				st.close();
			} 
			catch (SQLException e) 
			{
				e.getMessage();
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if(rs != null)
		{
			try 
			{
				rs.close();
			} 
			catch (SQLException e) 
			{
				e.getMessage();
			}
		}
	}
}
