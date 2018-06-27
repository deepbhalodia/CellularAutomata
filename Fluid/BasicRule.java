/**
 * 
 */
package Fluid;

import java.util.logging.Logger;

/**
 * @author mgmunson
 *
 */
public class BasicRule{
	//private static Logger log = Logger.getLogger(BasicRule.class.getName());
	

	/**
	 * 
	 */
	public BasicRule() {
	}


	/* This is a steady state rule - nothing changes
	 * (non-Javadoc)
	 * @see edu.neu.csye6200.fluid.RuleI#createNextCell(int)
	 */
	
	public static int createNextCell(int inVal) {
		int outVal = 0;
		for (int dir = 0; dir < 6; dir++) {
			if (ParticleCell.hasDirectionFlag(inVal, dir))
				outVal = ParticleCell.setFlag(outVal, ParticleCell.getOppositeDirection(dir)); // Just pass through in the opposite direction from where it came
		}
		
		return outVal;
	}
}
	
	
