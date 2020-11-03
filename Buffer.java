import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     								
  private int elements;							//Number of elements currently on the queue
  private int size;	  	
	private LinkedList<Object> buf_list;				
  private semaphore mutex = new semaphore(1);
  /**
   * A buffer structure represented as a list
   * @param n This is the size of the buffer
   */
     public Buffer(int n)						//Buffer creation, with n indicating the maximum capacity
	   {
      buf_list = new LinkedList<Object>();
      elements = 0;
      size = n;
     }

     /**
      * Adding method
      * @param n The element that the user is adding
      * @param userID The user that is adding the element
      * @return True if there are still elements left for user to add, false if else
      */
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
    
    /**
     * Removing method
     * @param webID The webserver that is removing element
     * @return True if there are still elements on the queue left to be add, false if else
     */
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
