package model.entities;

public class Item 
{
	private Integer id;
	private String item;
	private int quantity;
	
	public Item() {}
	
	public Item(Integer id, String item, int quantity)
	{
		this.id = id;
		this.item = item;
		this.quantity = quantity;
	}

	public String getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString()
	{
		return "|Item id: " + id + ", Item name: " + item + ", Item quantity: " + quantity + "| \n"; 
	}
}
