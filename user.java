
public class user implements Runnable{			
	private int n = 0;		
	private int userID;
	private int numOfElements;
	private Buffer buf;

	/**
	 * This class adds elements onto the buffer
	 * @param ID The ID of the user, useful for later on display
	 * @param elementNum The amount of elements this user has to add
	 * @param b The buffer we are manipulating
	 */
	public user(int ID, int elementNum, Buffer b) {
		userID = ID;
		numOfElements = elementNum;
		buf = b;
	}

	public void run(){  				
		while (n < numOfElements){
		if(buf.add(n, userID) == true){
			n++;
			}
		}
	}

	public int get(){
		return n;
	}

	public void set(int remainder) { //remainder calculations
		numOfElements = numOfElements + remainder;
	}
}   