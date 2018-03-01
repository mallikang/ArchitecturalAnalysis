import java.util.Random;

/**
 * 
 * @author Jared Heidt
 *
 */
public class Customer implements Runnable {
	private final static int MAX_CUSTOMER_MILLIS = 6000;// must wait for between 0-4 seconds

	private Table table;
	private String customerName;
	
	public Customer(Table table, String customerName) {
		this.table = table;
		this.customerName = customerName;
	}

	/**
	 * This method has the customer "eat" as until they're told "DONE"
	 */
	public void run() {
		String courseName = table.eat();

		while (!courseName.equals("DONE")) {
			System.out.println(customerName + " is eating: " + courseName);

			try {
				Random random = new Random();
				Thread.sleep(random.nextInt(MAX_CUSTOMER_MILLIS));
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			courseName = table.eat();
		}
	}
}
