package validationSystem;

import java.util.ArrayList;
import java.util.Hashtable;

public class Person {
	private Hashtable<String, Integer> orePositions = new Hashtable<String, Integer>();
	private ArrayList<Integer> transactions = new ArrayList<Integer>();
	private int cash;

	public Person(int cash, Hashtable<String, Integer> ores) {
		this.cash = cash;
		this.orePositions = ores;
	}

	public int getCash() {
		return cash;
	}

	public Hashtable<String, Integer> getOres() {
		return orePositions;
	}

	// Takes in a transaction and returns boolean if valid/invalid
	public boolean buy(int transactionID, int quantity, int price) {
		int totalCost = quantity * price;
		if (this.cash >= totalCost) {
			this.cash -= totalCost;
			transactions.add(transactionID);
			return true;
		} else {
			return false;
		}

	}

	// Takes in a transaction and returns boolean if valid/invalid
	public boolean sell(int transactionID, String ore, int quantity) {
		if (orePositions.containsKey(ore)) {
			if (orePositions.get(ore) >= quantity) {
				transactions.add(transactionID);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// Removes transaction from the list of transactions
	public boolean withdrawl(int transactionID) {
		if (transactions.contains(transactionID)) {
			transactions.remove(transactionID);
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
