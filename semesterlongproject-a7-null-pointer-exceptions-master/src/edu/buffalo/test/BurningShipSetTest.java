package edu.buffalo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fractalSets.BurningShipSet;

/**
 * author @Pang Yen Wu
 */
public class BurningShipSetTest {

	/**
	 * an instance of BurningShipSet
	 */ 
	private BurningShipSet b = new BurningShipSet();
	
	public BurningShipSetTest(){
		b.setThread(4);
	}
	/**
	 * the escape time for a coordinate whose distance from the origin never
	 * exceeds the escape distance
	 */
	@Test
	public void InfiniteLoopForBurningShipSet() {
		assertEquals(255, b.passes(-1.7443359374999874, -0.017451171875000338));
	}

	/**
	 * that none of the pixels in the Burning Ship set have an escape time of 0
	 * or 1
	 */
	@Test
	public void TestforNoZeroAndOneinTheArrayOfPasses() {
		int[][] array = b.generateArray(0,2048);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {

				assertTrue("1 appears at " + array[i][j], array[i][j] != 0);
				assertTrue(0 != array[i][j]);
			}
		}
	}

	/**
	 * Translate a pixel's row to the associated x-coordinate in the fractal
	 */
	@Test
	public void TestForTranslatePixelRowtoXcordinates() {
		assertEquals(-1.8, b.xCoordinate(0), 0.01);
		assertEquals(-1.7, b.xCoordinate(2048), 0.01);
	}
	
	/**
	 * Translate a pixel's column to the associated y-coordinate in the fractal
	 */
	@Test
	public void TestForTranslatePixelColumntoYcordinates() {
		assertEquals(-0.08, b.yCoordinate(0), 0.01);
		assertEquals(0.025, b.yCoordinate(2048), 0.01);
	}

	/**
	 * The method called to calculate the fractal returns a 2-d array with 2048
	 * rows and 2048 columns
	 */
	@Test
	public void TestMethodReturn2dArray2048Rows() {
		assertEquals(2048, b.generateArray(0,2048).length);
	}

	/**
	 * The method called to calculate the fractal returns a 2-d array with 2048
	 * rows and 512 columns
	 */
	@Test
	public void TestMethodReturn2dArray2048Columns() {
		assertEquals(2048, b.generateArray(0,2048)[0].length);
	}

	/**
	 * When the escape distance is set to 3, calculates the escape time for a
	 * coordinate whose distance from the origin exceeds the escape distance
	 * after a 10 passes of the loop
	 */
	@Test
	public void TestForEscapeDistance3() {
		b.changeDistance(3); // change escape distance to 3;
		assertEquals(10, b.passes(-1.6999999999999802, 0.0030136986301371603));
	}
	
	/**
	 * When the max escape distance is set to 3 and escape time to 135
	 * calculate the escape time for a coordinate whose distance from the origin never
	 * exceeds the escape distance
	 */
	@Test
	public void escapeTimeDidnotExceedDiscance(){
		b.changeDistance(2);
		b.changeTime(135);
		assertEquals(135, b.passes(-1.7443359374999874, -0.017451171875000338));
	}

}
