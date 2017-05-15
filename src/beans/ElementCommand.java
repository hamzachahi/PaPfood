package beans;

public class ElementCommand {
	private int Quantity;
	private Salable mProduct;

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Salable getmProduct() {
		return mProduct;
	}

	public void setmProduct(Salable mProduct) {
		this.mProduct = mProduct;
	}

}
