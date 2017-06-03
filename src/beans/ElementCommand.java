package beans;

public class ElementCommand {
	private int Quantity=0;
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

	@Override
	public String toString() {
		return "ElementCommand [Quantity=" + Quantity + ", mProduct=" + mProduct.toString() + "]";
	}

}
