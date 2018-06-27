
package Fluid;

import Fluid1.ParticleCell;

/**
 * A class that hold a hexagonal fluid frame
 * @author mgmunson
 */
public class FluidFrame {

	
	int frame[][] = null;
	int size = 16; // the current grid size
	private double aMagnitude;
	private double aDirection;
	private boolean edge;
	private boolean onedge = false;
	
	/**
	 * Constructor
	 */
	public FluidFrame(int size) {
		this.size = size;
		frame = new int[size][size]; // Dynamically build the grid based on the input size
	}

	
	/**
	 * How big is this frame
	 * @return the grid size (square)
	 */
    public int getSize() {
		return size;
	}


	/**
     * get the outbound particle state (direction of eminating particles)
     * @param x
     * @param y
     * @return a FluidCell integer value
     */
	public int getCellOutValue(int x, int y) {
		if (x < 0) x = 0;
		if (x >= size) x = size-1;
		if (y < 0) y = 0;
		if (y >= size) y = size-1;
		
		return frame[x][y];
	}
	
	/**
	 * Set the outbound particle state after an evaluation
	 * @param x
	 * @param y
	 * @param val
	 */
	public void setCellOutValue(int x, int y, int val) {
		if (x < 0) return;
		if (x >= size) return;
		if (y < 0) return;
		if (y >= size) return;
		frame[x][y] = val;
	}
	
	/**
	 * For a specified direction, set the output flag, indicating an exiting particle
	 * @param x
	 * @param y
	 * @param direction
	 */
	public void addCellOutParticle(int x, int y, int direction) {
		int curVal = getCellOutValue(x,y);
		curVal = ParticleCell.setFlag(curVal, direction);
		setCellOutValue(x,y,curVal);
	}
	
	/**
	 * Calculate the inbound particles that are headed towards a cell
	 * Do this by evaluating all neighbor cells, and calculating the input vectors 
	 * @param x
	 * @param y
	 */
	public int getCellInValue(int x, int y) {
		
		// Walk through all surrounding cells, and get the input value (header towards us or not)
		// Add this to our value
		// return the summarized result
		
		
		
		int inVal = 0;
		//Implement edge condition
		
		int outval=getCellOutValue(x, y);
	
		if(y==0)
		{
			
			
			if(x==0)
			{
			   if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
			   {
				  inVal+=1;   
			   }
			   
			   if(ParticleCell.hasDirectionFlag(outval, 1))  //Direction 1
			   {
				   inVal+=2;
			   }
			   
			   if(ParticleCell.hasDirectionFlag(outval, 2))  //Direction 2
			   {
				   inVal+=4;
			   }
			   
			   if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
			   {
				   inVal+=32;
			   }
			   
			   int neighborOutCell = getNeighborCellOutValue(x, y, 3);
				// Does it have a particle in the opposite direction?
				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 3
					inVal = ParticleCell.setFlag(inVal, 3); // If so, then add that direction to our inValue
			     
				neighborOutCell=getNeighborCellOutValue(x,y,4);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
					inVal =ParticleCell.setFlag(inVal, 4);
			}
			
			else if(x==size-1)
			{
				  if(ParticleCell.hasDirectionFlag(outval, 1))  //Direction 1
				   {
					   inVal+=2;
				   }
				   
				   if(ParticleCell.hasDirectionFlag(outval, 2))  //Direction 2
				   {
					   inVal+=4;
				   }
				   
				   if(ParticleCell.hasDirectionFlag(outval, 3))
				   {
					   inVal+=8;
				   }
				   
				    int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,4);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 4);
				   
                    neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 4);
				   
			}
			else
			{
				
				  if(ParticleCell.hasDirectionFlag(outval, 1))
				  {
					  inVal+=2;
				  }
				  
				  if(ParticleCell.hasDirectionFlag(outval, 2))  
				   {
					   inVal+=4;
				   }
				  
				  int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,3);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 3);
				   
                   neighborOutCell=getNeighborCellOutValue(x,y,4);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 4);
                   
					neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(5)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 5);			  
				  
			}
						
		}
		
		
		
		
		
		
		else if(y==size-1)
		{
			 if(x==0)
			 {
				 if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
				   {
					  inVal+=1;   
				   }
				 
				 if(ParticleCell.hasDirectionFlag(outval, 4))
				 {
					 inVal+=16;
				 }
				 if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
				   {
					   inVal+=32;
				   }
				   
				 
				 if((size-1)%2==0&&ParticleCell.hasDirectionFlag(outval, 1)) 
					 { 
					 inVal+=2;
					
					 }
				 else
				 {
					    int neighborOutCell = getNeighborCellOutValue(x, y, 1);
						// Does it have a particle in the opposite direction?
						if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 3
							inVal = ParticleCell.setFlag(inVal, 1); // If so, then add that direction to our inValue				     
				 }
				 
				 
				 
				 
				   int neighborOutCell = getNeighborCellOutValue(x, y, 2);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,3);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 3);				 
			 }
			 else if(x==size-1)
			 {
				 
					 if(ParticleCell.hasDirectionFlag(outval, 3))  //Direction 0
					   {
						  inVal+=8;   
					   }
					 
					 if(ParticleCell.hasDirectionFlag(outval, 4))
					 {
						 inVal+=16;
					 }
					 
					 if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
					   {
						   inVal+=32;
					   }
					   
					 
					 if((size-1)%2==0&&ParticleCell.hasDirectionFlag(outval, 2)) 
						 { inVal+=4;
						
						 }
					 else
					 {
						    int neighborOutCell = getNeighborCellOutValue(x, y, 2);
							// Does it have a particle in the opposite direction?
							if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  //Direction 3
								inVal = ParticleCell.setFlag(inVal, 2); // If so, then add that direction to our inValue				     
					 }
					 
					 
					 
					 
					   int neighborOutCell = getNeighborCellOutValue(x, y, 0);
						// Does it have a particle in the opposite direction?
						if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
							inVal= ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
					     
						neighborOutCell=getNeighborCellOutValue(x,y,1);
						
						if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 4
							inVal=ParticleCell.setFlag(inVal, 1);	
									 
				 
			 }
			 else
			 {
				 if(ParticleCell.hasDirectionFlag(outval, 5))  //Direction 0
				   {
					  inVal+=32;   
				   }
				 
				 if(ParticleCell.hasDirectionFlag(outval, 4))
				 {
					 inVal+=16;
				 }
				 
				 
				    int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,1);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 1);
					
                    neighborOutCell=getNeighborCellOutValue(x,y,2);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 2);
					
                   neighborOutCell=getNeighborCellOutValue(x,y,3);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 3);			 
				 
			 }
			 
			 			   								
		}
			 
		
		
		
		
			 
			 
		else if(x==0 && y!=0 && y!=size-1)
		{
			
           if(y%2==0)// y==even
           {
        	   if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
			   {
				  inVal+=1;   
			   }
			 
			  if(ParticleCell.hasDirectionFlag(outval, 1))
			  {
				 inVal+=2;
			  }
			 
			 if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
			   {
				   inVal+=32;
			   }
			   
			 
			    int neighborOutCell = getNeighborCellOutValue(x, y, 2);
				// Does it have a particle in the opposite direction?
				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  
					inVal = ParticleCell.setFlag(inVal,2); // If so, then add that direction to our inValue
			     
				neighborOutCell=getNeighborCellOutValue(x,y,3);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  
					inVal=ParticleCell.setFlag(inVal, 3);
				
                neighborOutCell=getNeighborCellOutValue(x,y,4);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  
					inVal=ParticleCell.setFlag(inVal, 4);
				 	 
           }
           else  //y==odd
           {
        	   if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
			   {
				  inVal+=1;   
			   }
        	   
        	   int neighborOutCell = getNeighborCellOutValue(x, y, 3);
				// Does it have a particle in the opposite direction?
				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  
					inVal = ParticleCell.setFlag(inVal,3); // If so, then add that direction to our inValue
			     
				neighborOutCell=getNeighborCellOutValue(x,y,1);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  
					inVal=ParticleCell.setFlag(inVal, 1);
				
               neighborOutCell=getNeighborCellOutValue(x,y,2);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  
					inVal=ParticleCell.setFlag(inVal, 2);
				
              neighborOutCell=getNeighborCellOutValue(x,y,4);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  
					inVal=ParticleCell.setFlag(inVal, 4);
				
                neighborOutCell=getNeighborCellOutValue(x,y,5);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(5)))  
					inVal+=ParticleCell.setFlag(inVal,5);
        	   
        	   
           }
			
			
		}
		
		
		
			 
			 
			 
		else if(x==size-1 && y!=0 && y!=size-1)
		{
			
			
			if(y%2==0) //y even 
			{
				if(ParticleCell.hasDirectionFlag(outval, 3))  //Direction 0
				   {
					  inVal+=8;   
				   }
				   
				   int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  
						inVal = ParticleCell.setFlag(inVal,0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,1);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  
						inVal=ParticleCell.setFlag(inVal, 1);
					
	                neighborOutCell=getNeighborCellOutValue(x,y,2);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  
						inVal=ParticleCell.setFlag(inVal, 2);
					
                   neighborOutCell=getNeighborCellOutValue(x,y,4);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  
						inVal=ParticleCell.setFlag(inVal, 4);
					
                     neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(5)))  
						inVal+=ParticleCell.setFlag(inVal,5);
									 	 
			}
			else   //y odd
			{
				 if(ParticleCell.hasDirectionFlag(outval, 2))  //Direction 0
				   {
					  inVal+=4;   
				   }
				 
				  if(ParticleCell.hasDirectionFlag(outval, 3))
				  {
					 inVal+=8;
				  }
				 
				 if(ParticleCell.hasDirectionFlag(outval, 4)) //Direction 5
				   {
					   inVal+=16;
				   }
				 
				 int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  
						inVal = ParticleCell.setFlag(inVal,0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,1);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  
						inVal=ParticleCell.setFlag(inVal, 1);
					
	                neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(5)))  
						inVal=ParticleCell.setFlag(inVal, 5);
				 
				 
			}
			
		}
			 
			 
			 
		else
		{
			for (int dir = 0; dir < 6; dir++) 
			    {
				// Get the cell in a given direction from our current cell
				int neighborOutCell = getNeighborCellOutValue(x, y, dir);
				// Does it have a particle in the opposite direction?
				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(dir)))
					inVal = ParticleCell.setFlag(inVal, dir); // If so, then add that direction to our inValue
				}
						
		}
	
		
		
		
		
		return inVal;
	}   
	
	
	
	public void calcAvg(int x, int y, int step) {
		double sumX = 0.0;
		double sumY= 0.0;
		int yEnd = y+step;
		int xEnd = x+step;
		
		for (int i=x; i<xEnd; i++) {
			for(int j=y; j<yEnd; j++) {
				int inVal = getCellInValue(j,i);
				if (ParticleCell.hasDirectionFlag(inVal, 0)) {
					sumX+=-1;
				}
				if (ParticleCell.hasDirectionFlag(inVal, 1)) {
					sumX+= -0.5;
					sumY+= -0.87;
				}
				if (ParticleCell.hasDirectionFlag(inVal, 2)) {
					sumX+= 0.5;
					sumY+= -0.87;
				}
				if (ParticleCell.hasDirectionFlag(inVal, 3)) {
					sumX+= 1;	
				}
				if (ParticleCell.hasDirectionFlag(inVal, 4)) {
					sumX+= 0.5;
					sumY+= 0.87;
				}
				if (ParticleCell.hasDirectionFlag(inVal, 5)) {
					sumX+= -0.5;
					sumY+= 0.87;
				}
			}
		}
		
		double divisor = step*step*2;
		double avgX= sumX/divisor;
		double avgY =sumY/divisor;
		
		aMagnitude = Math.sqrt(avgX*avgX + avgY*avgY);
		aDirection = Math.sqrt(avgX/avgY);
		
	}
	
	/**
	 * Based on a given position and direction, locate a neighbor cell using a supplied direction
	 * and return its outbound particle values
	 * @param x input frame x coordinate
	 * @param y input frame y coordinate
	 * @param direction the direction to look in
	 * @return the outbound particle value for the neighbor cell
	 */
	private int getNeighborCellOutValue(int x, int y, int direction) {
		if ((y & 1) == 0) { // y is even
			switch (direction) {
			  default:
			  case 0: return (getCellOutValue(x-1, y));   // Left
			  case 1: return (getCellOutValue(x-1, y-1)); // UL
			  case 2: return (getCellOutValue(x  , y-1)); // UR
			  case 3: return (getCellOutValue(x+1, y));   // Right
			  case 4: return (getCellOutValue(x  , y+1)); // LR
			  case 5: return (getCellOutValue(x-1, y+1)); // LL	
			}
		}
		else { // y is odd
			switch (direction) {
			  default:
			  case 0: return (getCellOutValue(x-1, y));   // Left
			  case 1: return (getCellOutValue(x+1, y-1)); // UL
			  case 2: return (getCellOutValue(x+2, y-1)); // UR
			  case 3: return (getCellOutValue(x+1, y));   // Right
			  case 4: return (getCellOutValue(x+2, y+1)); // LR
			  case 5: return (getCellOutValue(x+1, y+1));   // LL	
			}
		}
	}
	
	/**
	 * Fill up the current frame up a a specified percentage (i.e. 100% yields an average of one particle direction per cell)
	 * @param percent
	 */
	public void addRandomParticles(double percent) {
		if (percent > 1.0) percent = 1.0; // Don't allow us to fill all cells
		
		int total = size * size; // This is the maximum number of cells
		
		total *= percent;
		
		for (int i = 0; i < total; i++)
			addRandomParticle();
	}
	
	/**
	 * Add a single random particle - give it a direction value
	 */
	private void addRandomParticle() {
		double maxSize = size + .99; // Well want a range from 0.00 to size.99. When integerized we'll get 0, 1, 2, ... size
		// Create a random X
		int x = (int) (Math.random() * maxSize);
		int y = (int) (Math.random() * maxSize);
		// Create a random y
		// Create a random direction
		int direction = (int) (Math.random() * 6.99);
		//direction = 3; // Test - force all particle to move right
		
		// Add the CellOutParticle
	    addCellOutParticle(x,y,direction); // add it, or if the particle already exists, just overlay it
		
	}
	
	/**
	 * Draw the current frame to the console
	 */
	public void drawFrameToConsole() {
		char dispChar = ' ';
		for (int y = 0; y <getSize(); y++) {

			if ((y & 1) > 0) // y is odd if true
				System.out.print(" ");
			for (int x = 0; x < getSize(); x++) {
				
		       int cel = getCellOutValue(x, y);
		       if (ParticleCell.hasDirectionFlag(cel, 0)) dispChar = '0';
		       else if (ParticleCell.hasDirectionFlag(cel, 1)) dispChar = '1';
		       else if (ParticleCell.hasDirectionFlag(cel, 2)) dispChar = '2';
		       else if (ParticleCell.hasDirectionFlag(cel, 3)) dispChar = '3';
		       else if (ParticleCell.hasDirectionFlag(cel, 4)) dispChar = '4';
		       else if (ParticleCell.hasDirectionFlag(cel, 5)) dispChar = '5';
		       else dispChar = '-';
		    	 
		       System.out.print(dispChar + " ");
			}
			System.out.println(""); // Carriage return
		}
	}
	
	

	public double getAvgDirection() {
		// TODO Auto-generated method stub
		return aDirection;
	}
	
	public double getAvgMagnitude() {
		return aMagnitude;
	}
	
	


	
}
