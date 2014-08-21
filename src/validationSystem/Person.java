package validationSystem;

import java.util.Hashtable;

public class Person {
	private Hashtable<String, Integer> oreOwned = new Hashtable<String, Integer>();
	private Hashtable<Integer, Order> transactions = new Hashtable<Integer, Order>();
	private double cashOwned;
	private String traderName;

	// Constructor
	public Person(String traderName, double cash,
			Hashtable<String, Integer> ores) {
		this.cashOwned = cash;
		this.oreOwned = ores;
		this.traderName = traderName;
	}

	public double getCash() {
		return cashOwned;
	}

	public Hashtable<String, Integer> getOres() {
		return oreOwned;
	}

	public String getName() {
		return this.traderName;
	}

	// Takes in a transaction to buy and returns boolean if valid/invalid
	public boolean buy(int transactionID, int quantityToBuy, double priceToBuy) {
		double costToBuy = quantityToBuy * priceToBuy;
		Order newOrder = new Order(transactionID, quantityToBuy, priceToBuy);

		if (this.cashOwned >= costToBuy) {
			this.cashOwned -= costToBuy;
			transactions.put(transactionID, newOrder);
			return true;
		} else {
			return false;
		}

	}

	// Takes in a transaction object and returns boolean
	public boolean sell(int transactionID, String oreTypetoBuy,
			int quantityToBuy, double orePriceToBuy) {
		int currentQuantity;
		Order newOrder = new Order(transactionID, oreTypetoBuy, quantityToBuy,
				orePriceToBuy);

		if (oreOwned.containsKey(oreTypetoBuy)) {
			currentQuantity = oreOwned.get(oreTypetoBuy);
			if (currentQuantity >= quantityToBuy) {
				oreOwned.put(oreTypetoBuy, currentQuantity - quantityToBuy);
				transactions.put(transactionID, newOrder);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// Removes transaction from the list of transactions and updates the
	// cash/position
	public boolean withdrawl(int transactionID) {
		Order newWithdrawl;
		if (transactions.contains(transactionID)) {
			// remove the transaction from the list of pending transactions
			transactions.remove(transactionID);

			newWithdrawl = transactions.get(transactionID);

			// if buy order, then add cash
			if (newWithdrawl.getOreType() == "buy") {
				double cost = newWithdrawl.getOrePrice()
						* newWithdrawl.getOreAmount();
				cashOwned += cost;
			}
			// if sell order, then add quantity of position to existing
			else {
				String withdrawlOreType = newWithdrawl.getOreType();
				oreOwned.put(withdrawlOreType, oreOwned.get(withdrawlOreType)
						+ newWithdrawl.getOreAmount());
			}

			return true;
		} else {
			return false;
		}
	}

	// removes transaction from list of transactions
	public boolean accept(int transactionID) {
		if (transactions.contains(transactionID)) {
			transactions.remove(transactionID);
			return true;
		} else {
			return false;
		}
	}
}
