package main.java.pantry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class PatronHistory {
	private static PatronHistory patronHistory = null;
	protected PatronHistory() {
    }
	private Map<UUID, Integer> myMap = new HashMap<UUID, Integer>();
	int count=0;
	
    public PatronHistory getInstance()
    {
        if (patronHistory == null)
        	patronHistory = new PatronHistory();

        return patronHistory;  
    }
    
    public void addCheckout(UUID studentId)
    {
    	myMap.put(studentId,1);
    	count++;
    }
   
   public int getNumPatrons()
	{	   
	   
	   return myMap.size();
   
	}
   
   public int getNumCheckouts()
  	{
  	   return count;
     
  	}
   	
}
