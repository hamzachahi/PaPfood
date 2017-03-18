package beans;

import java.util.ArrayList;

public class Cart {

	private int id;
	private ArrayList<FoodDish> orderedProducts;
	private Double subTotal;
	private int itemsNumber;

	public Cart() {

		orderedProducts = new ArrayList<FoodDish>();
		itemsNumber = orderedProducts.size();
		subTotal = calculerTotal(orderedProducts);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<FoodDish> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(ArrayList<FoodDish> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public int getItemsNumber() {
		return itemsNumber;
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	/* Fonction qui calcule le prix total des articles dans le panier */
	public Double calculerTotal(ArrayList<FoodDish> articles) {

		Double total = 0.0;

		for (int i = 0; i < articles.size(); i++) {

			total += articles.get(i).getPrice();
		}

		return total;
	}
}