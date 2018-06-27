
package Fluid;

import java.util.Observable;
import java.util.logging.Logger;

/**
 * A single frame of a CA Fluid Frame
 * @author mgmunson
 */
public class FluidFrameSim extends Observable implements Runnable {
	
	private boolean done = false; // Set true to exit (i.e. stop) the simulation
	private boolean paused = false; // Set true to pause the simulation
	private boolean running = false;
	private static Logger log = Logger.getLogger(FluidFrameSim.class.getName());
	
	
	private int MAX_FRAME_SIZE = 256; // How big is the simulation frame
	public static int MAX_GENERATION = 30; // How many generations will we calculate before we're through?
	private int genCount = 0; // the count of the most recent generation

	private FluidFrame currentFrame = null;
	private Average avgFrame = null;
	private RuleA rule;
	
	//private RuleA rule = null;
	
	/**
	 * 
	 */
	public FluidFrameSim() {
		//MAX_GENERATION = App.maxSteps;
		currentFrame = new FluidFrame(MAX_FRAME_SIZE);
		currentFrame.addRandomParticles(0.50); // Only 20% of the cells should have a particle
		rule= new RuleA();
	
		avgFrame = new Average(MAX_FRAME_SIZE/10);
	}
	
	private void advanceSim() {
		log.info("Advance Sim");
		FluidFrame nextFrame = rule.createNextFrame(currentFrame);
		avgFrame.setFluidFrame(nextFrame);
		
		setChanged();
		notifyObservers(avgFrame);
		
		genCount++;
		System.out.println("\nFluidFrame: " + genCount);
		nextFrame.drawFrameToConsole();
		
		currentFrame = nextFrame;
		
		}
	
	public void delay(long duration) {
		try {
			Thread.sleep(400L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}

	
	
	public boolean isPaused() {
		return paused;
	}
	
	
	/**
	 * @param args
	 
	public static void main(String[] args) {
		FluidFrameSim ffSim = new FluidFrameSim();
		ffSim.run();

	}*/

	@Override
	public void run() {
		running= true;
		log.info("Run Sim");
		System.out.println("FluidFrame: 0");
		currentFrame.drawFrameToConsole();
		while(!done) {
			if(!paused) {
				advanceSim();
				delay(400L);
			}
			else {
				delay(500L);
			}
			if (genCount > MAX_GENERATION) done=true;
		}
		running = false;
		
	}

	public boolean isrunning() {
		
		return running;
	}

	public void setPaused(boolean b) {
		paused = b;
		
	}

	public void setDone(boolean b) {
		done = b;
		
	}

	public void setRunning(boolean b) {
		running= b;
		
	}

}
