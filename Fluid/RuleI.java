
package Fluid;

/**
 * A Rule defines how to generate a new FluidFrame from an existing one
 * @author mgmunson
 *
 */
public interface RuleI {

	public FluidFrame createNextFrame(FluidFrame inFrame);
	public int createNextCell(int inVal);
	
}
