package main;

import coffee.CoffeeMachine;
import service.Warehouse;

public class Main {

	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse();
		CoffeeMachine machine = new CoffeeMachine(warehouse);
		for (int i = 0; i < 100; i++) {
			machine.brewCoffee();
		}
	}
}
