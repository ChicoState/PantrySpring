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
	
	//HashMap that stored checkedout Ids
	private Map<UUID, Integer> checkedOut = new HashMap<UUID, Integer>();
	int count=0;
	
    public PatronHistory getInstance()
    {
        if (patronHistory == null)
        	patronHistory = new PatronHistory();

        return patronHistory;  
    }
    
    //Adds Items checked out to the Map
    public void addCheckout(UUID studentId)
    {
    	checkedOut.put(studentId,1);
    	count++;
    }
   
   //Returns total Number of Patrons
   public int getNumPatrons()
	{	   
	   
	   return checkedOut.size();
   
	}
   
   //Returns total number Number of checkouts
   public int getNumCheckouts()
  	{
  	   return count;
     
  	}
   	
}
