package edu.buffalo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fractalSets.JuliaSet;

/**
 * author @Bing Bing Zheng
 */
public class JuliaSetTest {
	
	/**
	 * an instance of JuliaSet
	 */
	private JuliaSet test = new JuliaSet();
	
	public JuliaSetTest(){
		test.setThread(4);
	}
	/**
	 * Translate a pixel's row to the associated x-coordinate in the fractal 
	 */
	@Test
	public void translateRowTest(){
		
		assertEquals(-1.7, test.xCoordinate(0), 0.001);
		assertEquals(1.7, test.xCoordinate(2047), 0.01);
		
	}
	
	/**
	 * Translate a pixel's column to the associated y-coordinate in the fractal
	 */
	@Test
	public void translateColumnTest(){
		assertEquals(-1.0, test.yCoordinate(0), 0.001);
		assertEquals(1.0, test.yCoordinate(2047), 0.01);
		
	}
	
	/**
	 * Calculates the escape time for a coordinate whose distance from the origin 
	 * never exceeds the escape distance
	 */
	@Test
	public void distanceNotExceedTest(){
		assertEquals(255, test.passes(1.0492187499999897, -0.234375));
	}
	
	/**
	 * Calculates the escape time for a coordinate whose distance from the origin 
	 * exceeds the escape distance after a single loop pass
	 */
	@Test
	public void distanceExceedTest(){
		assertEquals(1, test.passes(1.6933593749999853, 0.9765625));
	}
	
	/**
	 * The method called to calculate the fractal returns a 2-d array with 2048 rows and 2048 columns 
	 */
	@Test
	public void returnFractalTest(){
		assertEquals(2048,test.generateArray(0,2048).length);
		assertEquals(2048,test.generateArray(0,2048)[0].length);
	}
	
	/**
	 * When the escape distance is set to 3, 
	 * calculates the escape time for a coordinate whose distance 
	 * from the origin exceeds the escape distance after a 10 passes of the loop 
	 */
	@Test
	public void TestForEscapeDistance3(){
		test.changeDistance(3); // change escape distance to 3;
		assertEquals(10, test.passes(1.4538160469667272, -0.13502935420743645));
	}
	
	/**
	 * When the max escape distance is set to 3 and escape time to 135
	 * calculate the escape time for a coordinate whose distance from the origin never
	 * exceeds the escape distance
	 */
	@Test
	public void escapeTimeDidnotExceedDiscance(){
		test.changeDistance(2);
		test.changeTime(135);
		assertEquals(135, test.passes(1.0492187499999897, -0.234375));
	}

}
