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
		String itemN; int quantity; boolean equals;
		Scanner in = new Scanner(System.in);
		
		ItemQuantityDao ItemsDB = new ItemQuantityDao(DataBaseConnection.getConnection());
		
		System.out.println("Welcome! Please, select what you want: \n"
				+ "\n1.Insert a new item, 2.Remove an item, 3.Change an item"
				+ ", 4.See your list\n"
				+ "\nor type 'exit' if you want to exit\n");
		
		while(finished == false) 
		{	
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
				
				in.nextLine();
				
				Item item = new Item(null, itemN, quantity);
				
				ItemsDB.insert(item);
				
			}
			else if(type.equals("2"))
			{
				equals = false;
				
				System.out.print("\nEnter the name of the item you want to delete: ");
				itemN = in.nextLine();
				
				while(equals == false)
				{
					for(int i = 1; i <= ItemsDB.findAll().size();)
					{
						if(itemN.equals(ItemsDB.findById(i).getItem()))
						{
							equals = true;
							break;
						}
					}
					System.out.println("Item not found.");
				}
				
				ItemsDB.deleteById(ItemsDB.findByName(itemN).getId());
			}
			else if(type.equals("3"))
			{
				equals = false; int id = 0;
				
				System.out.print("\nEnter the item you want to change: ");
				itemN = in.nextLine();
				
				while(equals == false)
				{
					for(int i = 1; i <= ItemsDB.findAll().size();)
					{
						if(itemN.equals(ItemsDB.findById(i).getItem()))
						{
							id = i;
							equals = true;
							break;
						}
					}
					System.out.println("Item not found.");
				}
				
				System.out.print("\nEnter the new quantity of these items: ");
				quantity = in.nextInt();
				
				in.nextLine();
				
				Item item = new Item(id, itemN, quantity);
				
				ItemsDB.update(item);
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
