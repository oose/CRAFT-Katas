package service;

public class OrderService {
	public static void orderBeans(BeansOrder order) {
		System.out.println("OrderService: Beans order received.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		double amount = 250.0f;
		System.out.printf("OrderService: Delivering %.0f beans.\n", amount);
		order.onBeansReceived(amount);
	}

}
