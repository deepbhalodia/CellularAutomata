package Fluid;

public class Average {
	
	
	private double[][] direction;
	private double[][] magnitude;
	private int size;


	public Average (int size) {
		direction = new double [size][size];
		magnitude = new double [size][size];
		this.size=size;
		resetArray();
	}
	
	public void setFluidFrame (FluidFrame inFrame) {
		int step = inFrame.size/size;
		System.out.println("Step size is: "+ step);
		
		for (int i =0; i<size; i++) {
			for (int j =0; j<size; j++) {
				inFrame.calcAvg(j*step,i*step, step);
				direction[j][i]=inFrame.getAvgDirection();
				magnitude[j][i]=inFrame.getAvgMagnitude();
			}
		}
	}
	
	private void resetArray() {
		for (int i =0; i<size; i++) {
			for (int j =0; j<size; j++) {
				direction[j][i]= 0;
				magnitude[j][i]= 0;
			}
		
	}

}

	public double getDirection(int j, int i) {
		return direction[j][i];
	}

	public double getMagnitude(int j, int i) {
		return magnitude[j][i];
	}

	public int getSize() {
		return size;
	}
}