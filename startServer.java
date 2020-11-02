import java.io.IOException;
import java.util.*;

public class startServer {
	Buffer b; // Creation of buffer object

	int remainderUser;
	int remainderServ;
	int buffSize, userSize, serverSize, elementSize;
	int elemForUser, elemForServ;

	public startServer(int x, int y, int u, int z) { // Creates execution scenario between user and webservers on buffer
		buffSize = x;
		userSize = y;
		serverSize = u;
		elementSize = z;
		b = new Buffer(buffSize);

		long startTime = System.currentTimeMillis();

		/*remainderUser = elementSize % userSize;
		remainderServ = elementSize % serverSize;
		serverSize = serverSize + remainderUser;
		userSize = userSize + remainderServ;
		elemForServ = elementSize / serverSize;
		elemForUser = elementSize / userSize;
		*/

		elemForServ = elementSize / serverSize;
		elemForUser = elementSize / userSize;

		Thread[] userT = new Thread[userSize];
		Thread[] webserT = new Thread[serverSize];
		user[] users = new user[userSize];
		webserver[] webservers = new webserver[serverSize];

		for (int i = 0; i < userSize; i++) {
			users[i] = new user(i + 1, elemForUser, b);
			userT[i] = new Thread(users[i]);
			userT[i].start();
		}

		for (int i = 0; i < serverSize; i++) {
			webservers[i] = new webserver(i + 1, elemForServ, b);
			webserT[i] = new Thread(webservers[i]);
			webserT[i].start();
		}

		for (int i = 0; i < userSize; i++) {
			try {
				userT[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < serverSize; i++){
			try {
				webserT[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Equally subdivide user inputted elements across all user objects

			System.out.println("-----------------------");

			for(int i = 0; i < users.length; i ++){
				System.out.println("User " + i + " created a total of" + users[i].get() + " elements");
			}

			for(int i = 0; i < webservers.length; i ++){
				System.out.println("Consumer " + i + " consumed a total of" + webservers[i].get() + " elements");
			}

			System.out.println("-----------------------");

			System.out.println("Buffer has 0 elements remaining");

			System.out.println("-----------------------");
			// Checks if all users and web servers successfully finished

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
			System.out.println(numBuffer);
			
			System.out.println("Enter number of users");
			numUser = myObj.nextInt();
			System.out.println(numUser);
			
			System.out.println("Enter number of servers");
			numServer = myObj.nextInt();
			System.out.println(numServer);
			
			System.out.println("Enter total number of elements");
			numElement = myObj.nextInt();
			System.out.println(numElement);

			System.out.println("-----------------------");


		} finally {
			myObj.close();
	}
	startServer start = new startServer(numBuffer, numUser, numServer, numElement);
  }
}
