package Fluid;

import java.util.HashMap;
import java.util.Map;

public class Rule4 extends RuleA{
	
	 static Map<Integer,Integer> rule4= new HashMap<>();
	
	 
	 Rule4()
	 {
		    rule4.put(54, 27);
			rule4.put(27, 45);
			rule4.put(45, 54);
	 }

	 
	 

public static int createNextCell(int inVal) {
	
	
	if(rule4.containsKey(inVal))
		return rule4.get(inVal);
	else
	{
		int outVal=0;
		for (int dir = 0; dir < 6; dir++) 
		{
			if (ParticleCell.hasDirectionFlag(inVal, dir))
			{	
				outVal= ParticleCell.setFlag(outVal, ParticleCell.getOppositeDirection(dir));
				
		    }
		}
	return outVal;
	}
	
}
}
