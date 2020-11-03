class webserver implements Runnable
  {										
    private int webSerID;
    private int numOfElements;
    private int countElem = 0;
    private Buffer buf;


    /**
     * This class removes elements from the buffer
     * @param ID The ID of the webserver, useful for later on display
     * @param ElemNum the amount of elements this webserver has to remove
     * @param b The buffer we are manipulating
     */
    public webserver(int ID, int ElemNum, Buffer b)		
    {
      webSerID = ID;
      numOfElements = ElemNum;
      buf = b;
    }

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

    public void set(int remainder) {
      numOfElements = numOfElements + remainder;
    }
  }   