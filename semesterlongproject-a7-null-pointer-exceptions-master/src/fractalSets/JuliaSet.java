package fractalSets;


/**
 * This class implements the concept of Escape-Time Algorithms on Julia Set
 * which has x range from -1.7 to 1.7 and y range from -1.0 to 1.0
 * 
 * Escape-time algorithms imagine the picture is a Cartesian plane which point
 * is defined by its x- and y-coordinates Each point(pixel) is then colored
 * using the following process
 * 
 * @author Bing Bing Zheng
 *
 */
public class JuliaSet extends Fractal{
	

	/**
	 * an instance of double which represents the starting x point of the fractal
	 * -1.7 is the default value
	 */
	private double _currentX = -1.7;
	
	/**
	 * an instance of double which represents the starting y point of the fractal
	 * -1.0 is the default value
	 */
	private double _currentY = -1.0;
	
	/**
	 * an instance of double which represents the x range
	 * 3.4 is the default value
	 */
	private double _rangeX = 3.4;
	
	/**
	 * an instance of double which represents the y range
	 * 2.0 is the default value		
	 */	
	private double _rangeY = 2.0;
	
	public JuliaSet(){
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
	public int passes(double currentX, double currentY) {
		double xCalc = currentX;
		double yCalc = currentY;
		double dist = Math.sqrt(xCalc * xCalc + yCalc * yCalc);
		int passes = 0;
		while (dist <= getDistance() && passes < getTime()) {
			double xCalc1 = (xCalc * xCalc) - (yCalc * yCalc) + (-0.72689);
			double yCalc1 = (2 * xCalc * yCalc) + (0.188887);
			yCalc = yCalc1;
			xCalc = xCalc1;
			passes++;
			dist = Math.sqrt((xCalc * xCalc) + (yCalc * yCalc));
		}
		return passes;
	}
	
	/**
	 * This method sets everything back to the deault value
	 */
	public void original(){
		 _currentX = -1.7;
		 _currentY = -1.0;
		 _rangeX = 3.4;
		 _rangeY = 2.0;
		 super.setValue(_currentX,_currentY,_rangeX,_rangeY);
	}
	
	

}
