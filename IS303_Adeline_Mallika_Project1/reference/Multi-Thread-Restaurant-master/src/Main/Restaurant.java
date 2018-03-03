import java.util.Scanner;

public class Restaurant {
	final static int NUM_COURSES = 3;
	private final static String file_name = "/general.txt";

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(Restaurant.class.getResourceAsStream(file_name));
			int numWaiters = scanner.nextInt();

			// Read from file and start customer & waiter threads
			for (int waiterNum = 0; waiterNum < numWaiters; ++waiterNum) {
				String waiterName = scanner.next();// get waiter name
				int numCustomers = scanner.nextInt();

				// create an array of tables
				Table[] tables = new Table[numCustomers];

				Customer[] customers = new Customer[numCustomers];
				String[] customerNames = new String[numCustomers];
				String[][] customersAndCourses = new String[NUM_COURSES][numCustomers];

				for (int customer = 0; customer < numCustomers; ++customer) {
					tables[customer] = new Table();
					String customerName = scanner.next();
					customerNames[customer] = customerName;
					customers[customer] = new Customer(tables[customer], customerName);
					for (int course = 0; course < NUM_COURSES; ++course) {
						customersAndCourses[course][customer] = scanner.next();
					}
				}

				Waiter waiter = new Waiter(tables, waiterName, customerNames, customersAndCourses);
				Thread waiterJob = new Thread(waiter);
				waiterJob.start();

				for (int x = 0; x < numCustomers; x++) {
					Thread cThread = new Thread(customers[x]);
					cThread.start();
				}

			}
			scanner.close();
		} catch (NullPointerException npe) {
			System.err.println(npe.getMessage());
		}
	}

}
