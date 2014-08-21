package validationSystem;

public class Order {

	private String oreType;
	private int oreQuantity;
	private double orePrice;
	private int transID;
	private String orderType;

	// constructor for selling
	public Order(int transactionID, String oreType, int quantity,
			double orePrice) {
		this.orderType = "sell";
		this.oreType = oreType;
		this.oreQuantity = quantity;
		this.orePrice = orePrice;
		this.transID = transactionID;
	}

	// constructor for buying
	public Order(int transactionID, int quantity, double orePrice) {
		this.orderType = "buy";
		this.oreQuantity = quantity;
		this.orePrice = orePrice;
		this.transID = transactionID;
		this.oreType = null;
	}

	public int getTransID() {
		return transID;
	}

	public String getOreType() {
		return oreType;
	}

	public int getOreAmount() {
		return oreQuantity;
	}

	public double getOrePrice() {
		return orePrice;
	}

	public String getOrderType() {
		return orderType;
	}
}
