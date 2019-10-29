package fractalSets;

import java.awt.Point;

import javax.swing.SwingWorker;

import edu.buffalo.cse.Model;
import edu.buffalo.cse.Threading;
import edu.buffalo.fractal.WorkerResult;

public class Fractal {
	/**
	 * an instance of int which represents escape distance
	 */ 
	private int _x;
	
	/**
	 * an instance of int which represents escape time
	 */
	private int _y;
	
	/**
	 * an instance of double which represents the starting x point of the fractal
	 * -2.15 is the default value
	 */
	private double _currentX;
	
	/**
	 * an instance of double which represents the starting y point of the fractal
	 * -1.3 is the default value
	 */
	private double _currentY;
	
	/**
	 * an instance of double which represents the x range
	 * 2.75 is the default value
	 */
	private double _rangeX;
	
	/**
	 * an instance of double which represents the y range
	 * 2.6 is the default value		
	 */		
	private double _rangeY;
	
	/**
	 * an instance of int which represents the number of rows that each worker needs to calcualte
	 */
	private int _forEveryone = 0;
	/**
	 * an instance of int which helps to separate work equally to each worker
	 */
	private int _totalRow = 2048;
	/**
	 * an instance of int which represents the number of workers
	 */
	private int _thread = 4;
	/**
	 * an instance of Model that helps to connect this class with the Model
	 */
	private Model _m;
	
	public Fractal(){
		_x = 2;// default value of the escape distance
		_y = 255; // default value of the escape time
	}
	/**
	 * connect this class with Model
	 * @param m = Model
	 */
	public void connect(Model m) {
		_m = m;
	}
	
	/**
	 * Help to set the values to the specific set of fractal
	 * @param currentX = the left-upper corner's x coordinate
	 * @param currentY = the left-upper corner's y coordinate
	 * @param rangeX = the fractal's x range
	 * @param rangeY = the fractal's y range
	 */
	public void setValue(double currentX, double currentY, double rangeX, double rangeY){
		_currentX = currentX;
		_currentY = currentY;
		_rangeX = rangeX;
		_rangeY = rangeY;
	}
	
	
	/**
	 * This method translates each pixel to a x value in the range of -2.15 to .6
	 * @param row is the number of the pixel row that the array is currently dealing with
	 * @return returns the the translated x value
	 */
	public double xCoordinate(int row){
		double xcoord = _currentX + ((_rangeX/2048) * row);
		return xcoord;
	}
	
	/**
	 * This method translates each pixel to a y value in the range of -1.3 to 1.3
	 * @param column is the number of the pixel column that the aray is currently dealing with
	 * @return returns the translated y value
	 */
	public double yCoordinate(int column){
		double ycoord = _currentY + ((_rangeY/2048) * column);
		return ycoord;
	}
	
	/**
	 * This method helps to change escape distance as user input a value
	 * @param value = user input
	 */
	public void changeDistance(int value){
		_x = value;
	}
	
	/**
	 * An accessor method that helps subclasses to get the value of escape distance
	 * @return escape distance
	 */
	public int getDistance(){
		return _x;
	}
	
	/**
	 * This method helps to change escape time as user input a value
	 * @param value = user input
	 */
	public void changeTime(int value){
		_y = value;
	}
	
	/**
	 * An accessor method that helps subclasses to get the value of escape time
	 * @return escape distance
	 */
	public int getTime(){
		return _y;
	}
	
	/**
	 * An modifying method that set the number of threads
	 * @param t = the number of threads
	 */
	public void setThread(int t) {
		_thread = t;
	}
	
	/**
	 * An accessor method that return the number of threads
	 * @return the number of threads
	 */
	public int getThread(){
		return _thread;
	}
	
	/**
	 * An method that helps to assign work as equal as possible to each worker
	 * @return an array of workers
	 */
	public SwingWorker<WorkerResult,Void>[] changeThread(){
			_forEveryone = 2048/_thread;
			_totalRow = 2048%_thread;
		SwingWorker<WorkerResult, Void>[] workers = new Threading[_thread];
		int start = 0;
		for(int i=0; i<_thread; i++){
			Threading t = new Threading();
			int row = _forEveryone;
			if(_totalRow>0){
				row += 1;
				_totalRow -=1;
			}
			t.startingRow(start);
			t.results(_m.workerArray(start,row));
			workers[i] = t;
			start += row;
		}
		return workers;
	}
	
	/**
	 * This method change x and y range to whatever user wants
	 * and check for the correct starting point
	 * @param first - the point user pressed
	 * @param second - the point user released
	 */
	public void changeCurrentXY(Point first, Point second){
		double rx = Math.abs(xCoordinate(first.x)-xCoordinate(second.x));
		double ry = Math.abs(yCoordinate(first.y)-yCoordinate(second.y));
		if(first.x < second.x && first.y < second.y){
			_currentX = xCoordinate(first.x);
			_currentY = yCoordinate(first.y);
		}
		else if(first.x > second.x && first.y > second.y){
			_currentX = xCoordinate(second.x);
			_currentY = yCoordinate(second.y);
		}
		else if(first.x < second.x && first.y > second.y){
			_currentX = xCoordinate(first.x);
			_currentY = yCoordinate(second.y);
		}
		else{
			_currentX = xCoordinate(second.x);
			_currentY = yCoordinate(first.y);
		}
		_rangeX = rx;
		_rangeY = ry;
	}
	
}
