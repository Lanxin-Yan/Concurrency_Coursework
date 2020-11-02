import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     								
  private int elements;							//Number of elements currently on the queue
  private int size;	  	
	private LinkedList<Object> buf_list;				
  private semaphore mutex = new semaphore(1);
  
     public Buffer(int n)						//Buffer creation, with n indicating the maximum capacity
	   {
      buf_list = new LinkedList<Object>();
      elements = 0;
      size = n;
     }

     public boolean add(int n, int userID){
      if (elements < size){ 
        try{
          mutex.P();
          buf_list.add(n);
          elements ++; 
          Thread.sleep(50);
          System.out.println("User " + userID + " added on element " + elements + "/" + size);
          }catch(Exception e){}
          mutex.V();
          return true;
        }
        else {
        System.out.println("Buffer full - User now sleeping");
        return false;

      }
    }
  
     public boolean remove(int webID){
      if (elements > 0){
        try{
        mutex.P();
        buf_list.removeLast();
        elements --;
        System.out.println("Serv " + webID + " removed element " + elements + "/" + size);
      }catch(Exception e){} 
        mutex.V();
        return true;
      }
        else{
        System.out.println("Buffer empty – web server wait");
        return false;
    }        
  }
}	  
