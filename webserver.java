import java.util.*;

class webserver implements Runnable
  {										//Web server removes elements from the buffer
    private int webSerID;
    private int numOfElements;
    private int countElem = 0;
    private Buffer buf;
    public webserver(int ID, int ElemNum, Buffer b)		
    {
      webSerID = ID;
      numOfElements = ElemNum;
      buf = b;
    }

    //public void remove(int webSerID){
      //buf.remove(webSerID);
    //}

    public void run() { 
      while(numOfElements > 0){
        if(buf.remove(webSerID) == true){
          numOfElements--;
          countElem ++;
        }
      }
    }

    public int get(){
      return countElem;
    }
  }   