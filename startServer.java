import java.io.IOException;
import java.util.*;


public class startServer {
	Buffer b; // Creation of buffer object

	int remainderUser;
	int remainderServ;
	int buffSize, userSize, serverSize, elementSize;
	int elemForUser, elemForServ;
	/**
	 * 
	 * @param x The size of the buffer that we scanned from users input
	 * @param y The number of users that we scanned from users input
	 * @param u The number of webservers that we scanned from users input
	 * @param z The number of total elements we scanned from users input
	 * 
	 *          This class will use user/webserver classes to manipulate(add,
	 *          consume) with buffer class.
	 */
	public startServer(int x, int y, int u, int z) {
		buffSize = x;
		userSize = y;
		serverSize = u;
		elementSize = z;
		b = new Buffer(buffSize); // initiallized all variables

		long startTime = System.currentTimeMillis();

		elemForServ = elementSize / serverSize;
		elemForUser = elementSize / userSize; // assign workload to each user/serv

		remainderServ = elementSize % serverSize;
		remainderUser = elementSize % userSize;
		int counterUser = userSize - 1;
		int counterServer = serverSize - 1; // remainder calculations

		Thread[] userT = new Thread[userSize];
		Thread[] webserT = new Thread[serverSize];
		user[] users = new user[userSize];
		webserver[] webservers = new webserver[serverSize]; // creating arrays of threads and arrays user/webser objects

		for (int i = 0; i < userSize; i++) {
			users[i] = new user(i + 1, elemForUser, b);
		}

		for (int i = 0; i < remainderUser; i++) { // evenlly distribute remainders for users
			users[counterUser].set(1);
			counterUser--;
		}

		for (int i = 0; i < serverSize; i++) {
			webservers[i] = new webserver(i + 1, elemForServ, b);
		}

		for (int i = 0; i < remainderServ; i++) { // evenlly distribute remainders for servers
			webservers[counterServer].set(1);
			counterServer--;
		}

		for (int i = 0; i < serverSize; i++) {
			webserT[i] = new Thread(webservers[i]);
			webserT[i].start();
		}

		for (int i = 0; i < userSize; i++) {
			userT[i] = new Thread(users[i]);
			userT[i].start();
		}

		for (int i = 0; i < serverSize; i++){
			try {
				webserT[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < userSize; i++) {
			try {
				userT[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

			System.out.println("-----------------------"); //output final messages of processes

			for(int i = 0; i < users.length; i ++){
				System.out.println("User " + i + " created a total of " + users[i].get() + " elements");
			}

			for(int i = 0; i < webservers.length; i ++){
				System.out.println("Consumer " + i + " consumed a total of " + webservers[i].get() + " elements");
			}

			System.out.println("-----------------------");

			System.out.println("Buffer has 0 elements remaining");

			System.out.println("-----------------------");

			long endTime = System.currentTimeMillis();
			System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");

		}

public static void main(String[] args) throws IOException {

	int numBuffer;
	int numUser;
	int numServer;
	int numElement;
		Scanner myObj = new Scanner(System.in);
			try {

			System.out.println("Enter buffer capacity"); // Insert user inputted values for program execution
			numBuffer = myObj.nextInt();
			
			System.out.println("Enter number of users");
			numUser = myObj.nextInt();
			
			System.out.println("Enter number of servers");
			numServer = myObj.nextInt();
			
			System.out.println("Enter total number of elements");
			numElement = myObj.nextInt();

			System.out.println("-----------------------");


		} finally {
			myObj.close();
	}
	startServer start = new startServer(numBuffer, numUser, numServer, numElement);
  }
}
