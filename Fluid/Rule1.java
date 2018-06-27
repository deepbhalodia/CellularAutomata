package Fluid;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Rule1{
	//private static Logger log = Logger.getLogger(Rule1.class.getName());
	

	
	private static Map<Integer,Integer> rule1= new HashMap<>();
	
	Rule1()
	{
		rule1.put(9, 36);
		rule1.put(36, 18);
		rule1.put(18, 9);
	}
	
	
	
	public static int createNextCell(int inVal) {
		//log.info("Rule 1");
		
		if(rule1.containsKey(inVal)) 
		{
			return rule1.get(inVal);
		}
		else
		{   int outVal=0;
			for (int dir = 0; dir < 6; dir++) 
		{
				if (ParticleCell.hasDirectionFlag(inVal, dir))
				{	
					outVal= ParticleCell.setFlag(outVal, ParticleCell.getOppositeDirection(dir)); // Just pass through in the opposite direction from where it came
			      
			    }
		}
		return outVal;
		
		}
	
	}
	
}
	

	

