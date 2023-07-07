package executable;

import java.util.Scanner;

import jpaFuncs.DataBaseConnection;
import model.dao.impl.ItemQuantityDao;
import model.entities.Item;

public class Lista_de_compras 
{
	public static void main(String args[])
	{		
		boolean finished = false;
		
		Scanner in = new Scanner(System.in);
		
		ItemQuantityDao ItemsDB = new ItemQuantityDao(DataBaseConnection.getConnection());
		
		System.out.println("Welcome! Please, select what you want: \n"
				+ "\n1.Insert a new item, 2.Remove an item, 3.Change an item"
				+ ", 4.See your list\n"
				+ "\nor type 'exit' if you want to exit\n");
		
		while(finished == false) 
		{	
			String itemN; int quantity;
			
			System.out.print("Type something: ");
			String type = in.nextLine().strip();
			
			if(type.equals("exit"))
			{
				finished = true;
				DataBaseConnection.closeConnection();
			}
			else if(type.equals("1"))
			{
				System.out.print("\nEnter the item name: ");
				itemN = in.nextLine();
				
				System.out.print("Item quantity: ");
				quantity = in.nextInt();
				
				Item item = new Item(null, itemN, quantity);
				
				ItemsDB.insert(item);
			}
			else if(type.equals("2"))
			{
				
				System.out.print("\nEnter the name of the item you want to delete: ");
				itemN = in.nextLine();
				
				if(ItemsDB.findByName(itemN) == null) 
				{
					System.out.println("Name not found");
				}
				else ItemsDB.deleteById(ItemsDB.findByName(itemN).getId());
			}
			else if(type.equals("3"))
			{
				int id = 0;
				
				System.out.print("\nEnter the item you want to change: ");
				itemN = in.nextLine();
				
				if(ItemsDB.findByName(itemN) == null) 
				{
					System.out.println("Name not found");
				}
				else
				{
					System.out.print("\nEnter the new quantity of these items: ");
					quantity = in.nextInt();
					
					Item item = new Item(id, itemN, quantity);
					
					ItemsDB.update(item);
				}
				in.nextLine();	
			}
			else if(type.equals("4"))
			{
				System.out.println(ItemsDB.findAll());
			}
			else
			{
				System.out.println("Error, try again!");
			}
			
			System.out.println();
		}	
				
		in.close();
	}
}
