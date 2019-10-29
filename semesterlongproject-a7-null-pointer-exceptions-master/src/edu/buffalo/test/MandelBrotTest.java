package edu.buffalo.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fractalSets.MandelBrotSet;

	public class MandelBrotTest{
	
	private MandelBrotSet man;
	
	public MandelBrotTest(){
		man = new MandelBrotSet();
		man.setThread(4);
	}

	@Test
	public void xcoordinatetest() {
		assertEquals(-2.15, man.xCoordinate(0), .001);
		assertEquals(.6, man.xCoordinate(2048), .001);
		
	}
	@Test
	public void ycoordinatetest() {
		assertEquals(-1.3, man.yCoordinate(0), .001);
		assertEquals(1.3, man.yCoordinate(2048), .001);
		
	}
	@Test
	public void generateArrayTest(){
		int [][] manarray = man.generateArray(0,2048);
		assertEquals(2048, manarray.length);
		assertEquals(2048, manarray[0].length);
		
	}
	@Test
	public void escapeTimeDoesntExceedDistanceTest(){
		assertEquals(255, man.passes(0.3207031250000001, -0.07109374999999386));
	}
	@Test
	public void escapeTimeExceedsDistanceTest(){
		assertEquals(1, man.passes(0.5946289062500001, 1.2949218750000122));
	}
	
	/**
	 * When the escape distance is set to 3, 
	 * calculates the escape time for a coordinate whose distance 
	 * from the origin exceeds the escape distance after a 10 passes of the loop 
	 */
	@Test
	public void testForEscapeDistance3(){
		MandelBrotSet j = new MandelBrotSet();
		j.changeDistance(3); // change escape distance to 3;
		assertEquals(10, j.passes(0.46007827788650374, -0.3383561643835661));
	}
	
	/**
	 * When the max escape distance is set to 3 and escape time to 135
	 * calculate the escape time for a coordinate whose distance from the origin never
	 * exceeds the escape distance
	 */
	@Test
	public void escapeTimeDidnotExceedDiscance(){
		man.changeDistance(2);
		man.changeTime(135);
		assertEquals(135, man.passes(0.3207031250000001 , -0.07109374999999386));
	}
	
}

