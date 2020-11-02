import java.util.*;

public class user implements Runnable{			
	private int n = 0;		
	private int userID;
	private int numOfElements;
	private Buffer buf;

	public user(int ID, int elementNum, Buffer b) {	//Created user will add a certain number of elements to the buffer.
		userID = ID;
		numOfElements = elementNum;
		buf = b;
	}

	public void addElem(int n){
		buf.add(n, userID);

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
}   