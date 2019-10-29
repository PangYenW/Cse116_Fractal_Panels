package fractalSets;


public class MandelBrotSet extends Fractal{
	 

	/**
	 * an instance of double which represents the starting x point of the fractal
	 * -2.15 is the default value
	 */
	private double _currentX = -2.15;
	/**
	 * an instance of double which represents the starting y point of the fractal
	 * -1.3 is the default value
	 */
	private double _currentY = -1.3;
	/**
	 * an instance of double which represents the x range
	 * 2.75 is the default value
	 */
	private double _rangeX = 2.75;
	/**
	 * an instance of double which represents the y range
	 * 2.6 is the default value		
	 */		
	private double _rangeY = 2.6;
	
	public MandelBrotSet(){
		super.setValue(_currentX, _currentY, _rangeX, _rangeY); // set the values
		
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
				fractal[i][j] = passes(xCoordinate(i+start), yCoordinate(j));
			}
		}
		return fractal;
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
	public int passes(double xcoord, double ycoord){
		double xCalc = xcoord;
		double yCalc = ycoord;
		double dist = Math.sqrt(xCalc*xCalc + yCalc*yCalc);
		int passes = 0;
		while(dist<= getDistance() && passes < getTime()){
			double x1 =((xCalc * xCalc) - (yCalc * yCalc)) + xcoord;
			double y1= (2*xCalc*yCalc) + ycoord;
			xCalc = x1;
			yCalc = y1;
			passes = passes + 1;
			dist = Math.sqrt(xCalc*xCalc + yCalc*yCalc);
		}
		return passes;		
	}
	
	
	/**
	 * This method sets everything back to the deault value
	 */
	public void original(){
		 _currentX = -2.15;
		 _currentY = -1.3;
		 _rangeX = 2.75;
		 _rangeY = 2.6;
		 super.setValue(_currentX,_currentY,_rangeX,_rangeY);
	}

	
}
