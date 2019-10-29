package edu.buffalo.cse;

import javax.swing.SwingWorker;

import edu.buffalo.fractal.WorkerResult;



public class Threading extends SwingWorker<WorkerResult, Void>{

	/**
	 * an instance that stores how many rows the worker needs to calculate
	 */ 
	private int row = 0;
	
	/**
	 * an instance that stores the result worker has calculated
	 */
	private int[][] result;
	
	@Override
	protected WorkerResult doInBackground() throws Exception {
		
		WorkerResult worker = new WorkerResult(row, result);
		
		return worker;
	}
	
	/**
	 * a method that receives the row number that the worker needs to begin calculation
	 * @param value = starting row
	 */
	public void startingRow(int value){
		row = value;
	}
	
	/**
	 * a method that receives the result that the worker has done
	 * @param value = a 2D array of partial-generated fractal
	 */
	public void results(int[][] value){
		result = value;
	}
}
