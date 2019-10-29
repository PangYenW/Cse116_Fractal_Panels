package edu.buffalo.test;
import static org.junit.Assert.*;

import org.junit.Test;

import fractalSets.MultibrotSet;


public class MultibrotTest {
	
	MultibrotSet mult;
	public MultibrotTest(){
		mult = new MultibrotSet();
		mult.setThread(4);
	}
	
	@Test
	public void xcooTest() {
		assertEquals(-1, mult.xCoordinate(0), .001);
		assertEquals(1, mult.xCoordinate(2048),.01);
	}	
	@Test 
	public void ycooTest(){
		assertEquals(-1.3, mult.yCoordinate(0), .001);
		assertEquals(1.3, mult.yCoordinate(2048), .01);
	}
	
	@Test 
	public void arrayGenTest(){
		int[][] rayray = mult.generateArray(0,2048);
		assertEquals(2048, rayray.length);
		assertEquals(2048, rayray[0].length);
	}
	
	@Test
	public void escapeTimeDistanceTest(){
		assertEquals(1,mult.passes(0.9921875, 1.05625));
	}
	
	@Test
	public void escapeTimeExceedDistanceTest(){
		assertEquals(255,mult.passes(0.5859375, 0.24375000000000108));
	}
	
	/**
	 * When the escape distance is set to 3, 
	 * calculates the escape time for a coordinate whose distance 
	 * from the origin exceeds the escape distance after a 10 passes of the loop 
	 */
	@Test
	public void TestForEscapeDistance3(){
		mult.changeDistance(3); // change escape distance to 3;
		assertEquals(10, mult.passes(0.7025440313111545, -0.5520547945205528));
	}
	/**
	 * When the max escape distance is set to 3 and escape time to 135
	 * calculate the escape time for a coordinate whose distance from the origin never
	 * exceeds the escape distance
	 */
	@Test
	public void escapeTimeDidnotExceedDiscance(){
		mult.changeDistance(2);
		mult.changeTime(135);
		assertEquals(135, mult.passes(0.5859375, 0.24375000000000108));
	}
}
