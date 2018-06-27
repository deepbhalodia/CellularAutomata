/**
 * 
 */
package Fluid;

import java.util.logging.Logger;

/**
 * @author mgmunson
 *
 */
public class RuleA{

	private static Logger log = Logger.getLogger(RuleA.class.getName());

	/**
	 * 
	 */
	public RuleA() {
	}

	/* (non-Javadoc)
	 * @see edu.neu.csye6200.fluid.RuleI#createNextFrame(edu.neu.csye6200.fluid.FluidFrame)
	 */
	public FluidFrame createNextFrame(FluidFrame inFrame) {
		
		FluidFrame nxtFrame = new FluidFrame(inFrame.getSize());
		
		for (int x = 0; x < inFrame.getSize(); x++) {
			for (int y = 0; y < inFrame.getSize(); y++) {
				int inboundVal = inFrame.getCellInValue(x, y); // Read all neighbors and create opposite inbound values from their outbound ones
				if(App.rule==0) {
					//log.info("RuleA");
					int nextOutCelVal = BasicRule.createNextCell(inboundVal);
					nxtFrame.setCellOutValue(x, y, nextOutCelVal);
					}
				if(App.rule==1) {
					//log.info("Rule1");
					int nextOutCelVal = Rule1.createNextCell(inboundVal);
					nxtFrame.setCellOutValue(x, y, nextOutCelVal);
					}
				if(App.rule==2) {
					//log.info("Rule2");
					int nextOutCelVal = Rule2.createNextCell(inboundVal);
					nxtFrame.setCellOutValue(x, y, nextOutCelVal);
					}
				if(App.rule==3) {
					//log.info("Rule3");
					int nextOutCelVal = Rule3.createNextCell(inboundVal);
					nxtFrame.setCellOutValue(x, y, nextOutCelVal);
					}
				if(App.rule==4) {
					//log.info("Rule4");
					int nextOutCelVal = Rule4.createNextCell(inboundVal);
					nxtFrame.setCellOutValue(x, y, nextOutCelVal);
					}
			}
		}
		return nxtFrame;
	}

	
	/* A pass-through rule, no collisions
	 * (non-Javadoc)
	 * @see edu.neu.csye6200.fluid.RuleI#createNextCell(int)
	
	
	public int createNextCell(int inVal) {
		
		return inVal;
	}  */

}
