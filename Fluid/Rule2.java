package Fluid;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Rule2 extends RuleA {
    
     static List<Integer> rule2= new ArrayList<>();
	
	
	
	Rule2()
	{
		rule2= new ArrayList<>(Arrays.asList(26,13,38,19,41,52,44,22,11,37,49,25));	
			
	}
	
	
		
public static int createNextCell(int inVal) {
	
	if(rule2.contains(inVal))
	{
		return 63-inVal;
	}
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
