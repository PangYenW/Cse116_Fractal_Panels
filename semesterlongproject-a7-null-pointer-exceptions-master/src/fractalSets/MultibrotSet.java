package fractalSets;


public class MultibrotSet extends Fractal{
	
	 
	private double _currentX = -1.0;
	/**
	 * an instance of double which represents the starting y point of the fractal
	 * -1.3 is the default value
	 */
	private double _currentY = -1.3;
	/**
	 * an instance of double which represents the x range
	 * 2.0 is the default value
	 */
	private double _rangeX = 2.0;
	/**
	 * an instance of double which represents the y range
	 * 2.6 is the default value		
	 */	
	private double _rangeY = 2.6;

	
	public MultibrotSet() {
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
			double xCalc1 = (xCalc * xCalc * xCalc) - (3 * xCalc * (yCalc * yCalc)) + currentX;
			double yCalc1 = (3 * (xCalc*xCalc) * yCalc)-(yCalc*yCalc*yCalc) + currentY;
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
			for(int j = 0; j < fractal[0].length; j++){
				fractal[i][j] = passes(xCoordinate(i+start), yCoordinate(j));
			}
		}
		return fractal;
	}
	
	/**
	 * This method sets everything back to the deault value
	 */
	public void original(){
		 _currentX = -1.0;
		 _currentY = -1.3;
		 _rangeX = 2.0;
		 _rangeY = 2.6;
		 super.setValue(_currentX,_currentY,_rangeX,_rangeY);
	}

}
