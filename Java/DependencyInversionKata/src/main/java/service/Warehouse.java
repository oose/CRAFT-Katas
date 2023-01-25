package service;

import coffee.CoffeeMachine;

public class Warehouse {
	private double amountOfStoredBeans = 0.0;

	public void requestRefill(CoffeeMachine machine) {
		System.out.println("Warehouse: Refill request received.");
		refillComponent(machine);
	}

	private void refillComponent(CoffeeMachine machine) {
		if (amountOfStoredBeans > 0.0) {
			System.out.printf("Warehouse: %.0f g beans in storage. Refilling machine.\n", amountOfStoredBeans);
			double fillAmount = Math.min(CoffeeMachine.MAX_BEANS, amountOfStoredBeans);
			machine.fillBeans(fillAmount);
			amountOfStoredBeans -= fillAmount;
		}
		else {
			System.out.println("Warehouse: Bean storage empty. Ordering new beans.");
			OrderService.orderBeans(receivedAmount -> {
				amountOfStoredBeans += receivedAmount;
				refillComponent(machine);
			});
		}
	}
}
