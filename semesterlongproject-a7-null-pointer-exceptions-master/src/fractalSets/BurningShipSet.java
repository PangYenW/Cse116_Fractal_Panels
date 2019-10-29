package fractalSets;


public class BurningShipSet extends Fractal{

	
	/** 
	 * an instance of double which represents the starting x point of the fractal
	 * -1.8 is the default value
	 */
	private double _currentX = -1.8;
	/**
	 * an instance of double which represents the starting y point of the fractal
	 * -0.08 is the default value
	 */
	private double _currentY = -0.08;
	/**
	 * an instance of double which represents the x range
	 * 0.1 is the default value
	 */
	private double _rangeX = 0.1;
	/**
	 * an instance of double which represents the y range
	 * 0.105 is the default value		
	 */
	private double _rangeY = 0.105;
	
	public BurningShipSet() {
		super.setValue(_currentX, _currentY, _rangeX, _rangeY); // set the values
	}

	/**
	 * This method takes in a y coordinate and x coordinate and calculates the distance
	 * using function. The while loop runs as long as the distance is less
	 * than a given int and the number of passes is less than 255. The while loop contains an
	 * escape time algorithm using the coordinates to calculate the number of passes which will be assigned 
	 * to a later array pixel.
	 * @param xcoord x coordinate used to calculate distance and pass value
	 * @param ycoord y coordinate used to calculate distance and pass value
	 * @return number of passes that while loop produces.
	 */
	public int passes(double currentX, double currentY) {
		double xCalc = currentX;
		double yCalc = currentY;

		double dist = Math.sqrt(xCalc * xCalc + yCalc * yCalc);
		int passes = 0;
		while (dist <= getDistance() && passes < getTime()) {
		
			double xCalc1 = (xCalc * xCalc) - (yCalc * yCalc) + currentX;
			double yCalc1 = Math.abs(2 * xCalc * yCalc) + currentY;
			yCalc = yCalc1;
			xCalc = xCalc1;
			passes++;
			dist = Math.sqrt((xCalc * xCalc) + (yCalc * yCalc));

		}
		return passes;
	}
	
	/**
	*This method generates an array which is 512X512 and every array value is stored
 	*a pass value which is determined by the xCoordinate and yCoordinate method 
	*working together. 
	 * @param row 
	 * @param row2 
	*
	*@return returns an array with pass values stored in each spot.
	*/
	public int[][] generateArray(int start, int row){
		int[][] fractal = new int [row][2048];
		for(int i = 0; i < fractal.length; i++){
			for(int j = 0; j < fractal[i].length; j++){
				fractal[i][j] = passes(xCoordinate(start+i), yCoordinate(j));
			}
		}
		return fractal;
	}

	/**
	 * This method sets everything back to the deault value
	 */
	public void original(){
		 _currentX = -1.8;
		 _currentY = -0.08;
		 _rangeX = 0.1;
		 _rangeY = 0.105;
		 super.setValue(_currentX,_currentY,_rangeX,_rangeY);
	}

	

}


