package coffee;

import java.util.Optional;

import service.Warehouse;

public class CoffeeMachine {
	
	private double beansInGrams = 0.0;
	private Warehouse warehouse;

	private static final double BEANS_PER_COFFEE = 10.0;
	public static final double MAX_BEANS = 100.0;
	
	public CoffeeMachine(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	public Optional<Coffee> brewCoffee() {
		Coffee result = null;
		if (beansInGrams >= BEANS_PER_COFFEE) {
			beansInGrams -= BEANS_PER_COFFEE;
			result = new Coffee();
		}  else {
			orderNewBeans();
		}
		return Optional.ofNullable(result);
	}

	private void orderNewBeans() {
		System.out.println("Coffee machine: Not enough beans. Refill requested.");
		warehouse.requestRefill(this);
	}

	public void fillBeans(double amountInGrams) throws OverfillException {
		if (amountInGrams + beansInGrams > MAX_BEANS) {
			throw new OverfillException();
		}
		beansInGrams += amountInGrams;
		System.out.printf("Coffee machine: Beans refilled, %.0f g available.\n", beansInGrams);
	}
	
	public double getBeanLevelInGrams() {
		return beansInGrams;
	}
	
}
