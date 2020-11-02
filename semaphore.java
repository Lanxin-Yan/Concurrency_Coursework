import java.util.*;
public class semaphore
  {
    private int count = 0;
    public semaphore(int val)
        {
          count = val;
        }
        
        public synchronized void P()
        {
          count = count - 1;
          if(count < 0) 
          try{
                      wait();           //when count = 0, meaning the queue is full, put the thread in wait queue.
          }
          catch(Exception e){
}
        }

        public synchronized void V()
        {
          count = count + 1;
          if(count <= 0)    
          notify();      //waking up any threads in the queue if there are any
        }
   //This is an example class for using a primitive synchronization (semaphore, lock). Please note that you
   //can ONLY put the synchronization keyword within these type of classes, and nowhere else within the program.

  }

