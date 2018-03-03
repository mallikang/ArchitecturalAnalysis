/*
 * @author Jared Heidt
 */
public class Table {
	private String course = "";// name of the course
	private boolean isEmpty = true;// a flag used to see if the table is empty or is not empty (i.e., has an
	// unfinished course

	public Table() {

	}

	/**
	 * // implements the Waiter serving a course
	 * 
	 * @param course
	 *            acts as the course being served to the customer
	 */
	public synchronized void serve(String course) {

		while (!isEmpty) {// wait while there is still a course on the table
			try {
				wait();
			} catch (InterruptedException ie) {
				System.err.println(ie.getMessage());
			}
		}
		
		this.isEmpty = false;
		notifyAll();
		this.course = course;

	}

	/**
	 * implements the Customer eating a course
	 * 
	 * @return a string of the course which was "ate"
	 */
	public synchronized String eat() {

		while (isEmpty) {// wait while there is not a course on the table
			try {
				wait();
			} catch (InterruptedException ie) {
				System.err.println(ie.getMessage());
			}
		}
		this.isEmpty = true;

		notifyAll();
		return course;
	}
}
