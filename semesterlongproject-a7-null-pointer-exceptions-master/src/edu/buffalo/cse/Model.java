package edu.buffalo.cse;

import java.awt.Point;
import java.awt.image.IndexColorModel;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import edu.buffalo.fractal.WorkerResult;
import fractalSets.*;

public class Model {

	/**
	 * an instance of GUI that helps to connect model and GUI
	 */
	private GUI _observer;

	/** 
	 * an instance of IndexColorModel that helps to change the color
	 */
	private IndexColorModel _icm;

	/**
	 * a 2D array that is going to be assigned to whatever user asks to be
	 */
	private int[][] _sets;

	/**
	 * an instance of BurningShipSet that can access methods of the class
	 */
	private BurningShipSet _bss;

	/**
	 * an instance of JuliaSet that can access methods of the class
	 */
	private JuliaSet _js;

	/**
	 * an instance of MultibrotSet that can access methods of the class
	 */
	private MultibrotSet _multiset;

	/**
	 * an instance of MandelBrotSet that can access methods of the class
	 */
	private MandelBrotSet _mandelset;

	/**
	 * each string represents one set and it is for checking whether the set is
	 * selected or not _j = julia, _b = burningship, _m = madelbrot, 
	 * _mmu = multibrot
	 */
	private String _j;
	private String _b;
	private String _madel;
	private String _multi;

	/**
	 * an arraylist that helps to check which set is selected
	 */
	private ArrayList<String> _track;
	
	/**
	 * An instance that remembers the point that user pressed
	 * (0,0) is default point
	 */
	private Point first = new Point(0,0);
	
	/**
	 * An instance that remembers the point that user released
	 * (511,511) is the default
	 */
	private Point second = new Point(511,511);
	
	private SwingWorker<WorkerResult,Void>[] _s;

	/**
	 * a constructor that initialize all instance variables and set default
	 */
	public Model() {
		_bss = new BurningShipSet();
		_js = new JuliaSet();
		_multiset = new MultibrotSet();
		_mandelset = new MandelBrotSet();
		
		_icm = ColorModelFactory.createAwsomeColorModel(255);
		_track = new ArrayList<String>();
		_j = "j";
		_b = "b";
		_madel = "m";
		_multi = "mu";
		_track.add(_j);
		_js.connect(this);
		_multiset.connect(this);
		_bss.connect(this);
		_mandelset.connect(this);
		_s = _js.changeThread(); 
	}

	/**
	 * Make connection between model and ui
	 * @param ui = the ui that calls this method and wants to be connected with this model
	 */
	public void addObserver(GUI ui) {
		_observer = ui;
	}

	/**
	 * this method sets the first element in _track to be _j which represents
	 * JuliaSet and assigns JuliaSet's escape time to _sets eventually update
	 * the fractal to become JuliaSet's image
	 */
	public void JuliaSet() {
		_track.clear();
		_track.add(_j);
		_s = _js.changeThread();
		_observer.update(_icm);
	}

	/**
	 * this method sets the first element in _track to be _b which represents
	 * BurningShipSet and assigns BurningShipSet's escape time to _sets
	 * eventually update the fractal to become BurningShipSet's image
	 */
	public void BurningShipSet() {
		_track.clear();
		_track.add(_b);
		_s = _bss.changeThread();
		_observer.update(_icm);

	}

	/**
	 * this method sets the first element in _track to be _mu which represents
	 * MultibrotSet and assigns MultibrotSet's escape time to _sets eventually
	 * update the fractal to become MultibrotSet's image
	 */
	public void MultibrotSet() {
		_track.clear();
		_track.add(_multi);
		_s = _multiset.changeThread();
		_observer.update(_icm);

	}

	/**
	 * this method sets the first element in _track to be _m which represents
	 * MandelBrotSet and assigns MandelBrotSet's escape time to _sets eventually
	 * update the fractal to become MandelBrotSet's image
	 */
	public void MandelBrotSet() {
		_track.clear();
		_track.add(_madel);
		_s = _mandelset.changeThread();
		_observer.update(_icm);
	}

	/**
	 * this method sets color to be rainbow and update the GUI to show the color
	 */
	public void Rainbow() {
		_icm = ColorModelFactory.createRainbowColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be "awesome" and update the GUI to show the
	 * color
	 */
	public void Awsome() {
		_icm = ColorModelFactory.createAwsomeColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be red and update the GUI to show the color
	 */
	public void Red() {
		_icm = ColorModelFactory.createRedColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be light green and update the GUI to show the
	 * color
	 */
	public void LightGreen() {
		_icm = ColorModelFactory.createLightGreenColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be white and update the GUI to show the color
	 */
	public void White() {
		_icm = ColorModelFactory.createWhiteColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be gray-yellow and update the GUI to show the color
	 */
	public void MetalGray() {
		_icm = ColorModelFactory.createMetalGrayColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be gray and update the GUI to show the color
	 */
	public void BetterGray() {
		_icm = ColorModelFactory.createBetterGrayColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be "cool" and update the GUI to show the color
	 */
	public void Cool() {
		_icm = ColorModelFactory.createCoolColorModel(255);
		updateThread();
	}

	/**
	 * this method sets color to be gray with red and update the GUI to show the color
	 */
	public void GrayWithRed() {
		_icm = ColorModelFactory.createGrayWithRedColorModel(255);
		updateThread();
	}

	/**
	 * Check if user input is a valid number if yes then change escape distance
	 * if no, throw an error message that tells users that the input is not
	 * valid
	 * 
	 * @param inputValue = user input
	 */
	public void checkInput(String inputValue) {

		try {
			int value = Integer.parseInt(inputValue);

			if (value <= 0) {
				_observer.ErrorMessage();
			} else {
				_js.changeDistance(value);
				_mandelset.changeDistance(value);
				_bss.changeDistance(value);
				_multiset.changeDistance(value);
				updateThread();

			}
		} catch (NumberFormatException n) {
			_observer.ErrorMessage();
		}

	}
	
	/**
	 * Check if user input is a valid number if yes then change escape time
	 * if no, throw an error message that tells users that the input is not
	 * valid
	 * 
	 * @param inputValue = user input
	 */
	public void checkInputForTime(String inputValue) {

		try {
			int value = Integer.parseInt(inputValue);

			if (value <= 0 || value > 255 ) {
				_observer.ErrorMessage();
				
			} else {
				_js.changeTime(value);
				_multiset.changeTime(value);
				_bss.changeTime(value);
				_mandelset.changeTime(value);
				updateThread();

			}
		} catch (NumberFormatException n) {
			_observer.ErrorMessage();
		}

	}
	/**
	 * Check if user input is a valid number if yes then change escape time
	 * if no, throw an error message that tells users that the input is not
	 * valid
	 * 
	 * @param inputValue = user input
	 */
	public void checkInputForThread(String inputValue) {

		try {
			int value = Integer.parseInt(inputValue);

			if (value < 1 || value > 128 ) {
				_observer.ErrorMessage();
			} else {
				_js.setThread(value);
				_mandelset.setThread(value);
				_bss.setThread(value);
				_multiset.setThread(value);
				updateThread();
			}
		} catch (NumberFormatException n) {
			_observer.ErrorMessage();
		}

	}
	
	/**
	 * This method changes the pressing point of the selected fractal
	 * @param x = x coordinate
	 * @param y = y coordinate
	 */
	public void currentXY(int x, int y){
		first = new Point(x,y);
	}
	
	/**
	 * This method tells where is releasing point
	 * to help finding the new range
	 * @param x = final x
	 * @param y = final y
	 */
	public void finalXY(int x, int y){
		second = new Point(x,y);
	}
	
	/**
	 * To check which set is the current image and then change its range 
	 * by recalculating the 2D array in order to zoom in
	 */
	public void zoomIn(){
		if (_track.get(0).equals(_j)) {
			_js.changeCurrentXY(first, second);
			JuliaSet();
				
		}
		if (_track.get(0).equals(_b)) {
			_bss.changeCurrentXY(first, second);
			BurningShipSet();
			
		}
		if (_track.get(0).equals(_madel)) {
			_mandelset.changeCurrentXY(first, second);
			MandelBrotSet();
			
		}
		if (_track.get(0).equals(_multi)){
			_multiset.changeCurrentXY(first, second);
			MultibrotSet();
			
		}
		
	}
	
	/**
	 * This method resets all user changes back to default
	 */
	public void reset(){
		if (_track.get(0).equals(_j)) {
			_js.original();
			JuliaSet();
		}
		if (_track.get(0).equals(_b)) {
			_bss.original();
			BurningShipSet();
		}
		if (_track.get(0).equals(_madel)) {
			_mandelset.original();
			MandelBrotSet();
		}
		if (_track.get(0).equals(_multi)){
			_multiset.original();
			MultibrotSet();
		}
	}
	
	/**
	 * a method that helps update the number of thread and the whole fractals
	 */
	public void updateThread(){
		if (_track.get(0).equals(_j)) {
			_s = _js.changeThread();
		}
		if (_track.get(0).equals(_b)) {
			_s = _bss.changeThread();
		}
		if (_track.get(0).equals(_multi)) {
			_s = _multiset.changeThread();
		}
		if (_track.get(0).equals(_madel)){
			_s = _mandelset.changeThread();
		}
		_observer.update(_icm);
		
	}
	
	/**
	 * an accessor method that gives the access to the worker array
	 * @return an array of workers
	 */
	public SwingWorker<WorkerResult,Void>[] getSwingWorkers() {
		return _s;
	}
	
	/**
	 * A method that return the whole 2D array that workers generated 
	 * @param start = starting row
	 * @param row = total number of rows that a worker needs to generate
	 * @return a full 2D array that will be used to generate the fractal image
	 */
	public int[][] workerArray(int start, int row){
		if (_track.get(0).equals(_j)) {
			_sets = _js.generateArray(start,row);
		}
		if (_track.get(0).equals(_b)) {
			_sets = _bss.generateArray(start,row);
		}
		if (_track.get(0).equals(_madel)) {
			_sets = _mandelset.generateArray(start,row);
		}
		if (_track.get(0).equals(_multi)){
			_sets = _multiset.generateArray(start,row);
		}
		return _sets;
	}
	
}
