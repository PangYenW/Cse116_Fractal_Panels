package edu.buffalo.cse;

import java.awt.Color;
import java.awt.image.IndexColorModel;

/**
 * This class contains the code necessary to build the color models for three
 * different sets of fractal coloring. Each factory method takes in the number
 * of color required and build the appropriate IndexColorModel object.
 * 
 * @author Matthew Hertz
 *
 */
public class ColorModelFactory {

	/**
	 * Create a color model that contains the colors of the rainbow. This is the
	 * model to which FractalPanel defaults and the one used to create the demo
	 * images from phase #1. The number of colors included in the model is set
	 * by the parameter. This should be 1 more than the maximum number of steps
	 * for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows a selection of colors chosen because Prof.
	 *         Hertz finds the combination pretty.
	 */
	public static IndexColorModel createRainbowColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
			reds[i] = (byte) ((rgb & 0xFF0000) >> 16);
			greens[i] = (byte) ((rgb & 0xFF00) >> 8);
			blues[i] = (byte) (rgb & 0xFF);
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}

	/**
	 * Create a color model that contains different shades of blue. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         blue.
	 */
	public static IndexColorModel createBluesColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			blues[i] = (byte) ((Math.log10(i) / Math.log10(blues.length)) * 256);
			greens[i] = 0;
			reds[i] = 0;
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}

	/**
	 * Create a color model that contains different shades of light blue. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         light blue to gray.
	 */
	public static IndexColorModel createAwsomeColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
			reds[i] = (byte) ((rgb) );
			greens[i] = (byte) ((rgb)>>8);
			blues[i] = (byte) ((rgb) >> 8);
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	/**
	 * Create a color model that contains different shades of red. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         red.
	 */
	public static IndexColorModel createRedColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			blues[i] = 0;
			greens[i] = 0;
			reds[i] = (byte) ((Math.log10(i) / Math.log10(blues.length)) * 256);
			;
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	
	/**
	 * Create a color model that contains different shades of green. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         green.
	 */
	public static IndexColorModel createGreenColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			reds[i] = 0;
			greens[i] = (byte) ((Math.log10(i) / Math.log10(reds.length)) * 256);
			blues[i] = 0;
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	
	/**
	 * Create a color model that contains different shades of puple. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         puple.
	 */
	public static IndexColorModel createPurpleColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];

		for (int i = 0; i < reds.length - 1; i++) {
			reds[i] = (byte) ((Math.log10(i) / Math.log10(blues.length)) * 256);
			greens[i] = 0;
			blues[i] = (byte) ((Math.log10(i) / Math.log10(blues.length)) * 256);

		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	
	/**
	 * Create a color model that contains different shades of light blue. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         light blue.
	 */
	public static IndexColorModel createLightGreenColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];

		for (int i = 0; i < greens.length - 1; i++) {
			reds[i] = 0;
			greens[i] = (byte) ((Math.log10(i) / Math.log10(reds.length)) * 256);
			blues[i] = (byte) ((Math.log10(i) / Math.log10(reds.length)) * 256);

		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	
	/**
	 * Create a color model that contains different shades of yellow. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         yellow from pure black to pure white.
	 */
	public static IndexColorModel createYellowColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];

		for (int i = 0; i < greens.length - 1; i++) {
			reds[i] = (byte) ((Math.log10(i) / Math.log10(reds.length)) * 256);
			greens[i] = (byte) ((Math.log10(i) / Math.log10(reds.length)) * 256);
			blues[i] = 0;

		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	/**
	 * Create a color model that contains different shades of white. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows pure white with shady gray.
	 */
	public static IndexColorModel createWhiteColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
			reds[i] = (byte) ((rgb) >> 16);
			greens[i] = (byte) ((rgb) >> 16);
			blues[i] = (byte) ((rgb) >> 16);
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	/**
	 * Create a color model that contains different shades of yellow and gray. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         yellow from light yellow to gray.
	 */
	public static IndexColorModel createMetalGrayColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
			reds[i] = (byte) ((rgb) >> 8);
			greens[i] = (byte) ((rgb) >> 8);
			blues[i] = (byte) (rgb);
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	/**
	 * Create a color model that contains different shades of gray. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         gray from pure black to pure white.
	 */
	public static IndexColorModel createBetterGrayColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
			reds[i] = (byte) ((rgb) >> 8);
			greens[i] = (byte) ((rgb) >> 8);
			blues[i] = (byte) ((rgb) >> 8);
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	/**
	 * Create a color model that contains different shades of gray and red. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         from red to gray.
	 */
	public static IndexColorModel createGrayWithRedColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
			reds[i] = (byte) ((rgb)>> 8);
			greens[i] = (byte) ((rgb));
			blues[i] = (byte) ((rgb)  );
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	/**
	 * Create a color model that contains different shades of yellow and blue. The number
	 * of colors included in the model is set by the parameter. This should be 1
	 * more than the maximum number of steps for the fractal.
	 * 
	 * @param numColors
	 *            Number of different color shades to use.
	 * @return Color model that shows all of the different possible shades of
	 *         blue-yellow's combination
	 */
	public static IndexColorModel createCoolColorModel(int numColors) {
		byte[] reds = new byte[numColors];
		byte[] greens = new byte[numColors];
		byte[] blues = new byte[numColors];
		for (int i = 0; i < reds.length - 1; i++) {
			int rgb = Color.HSBtoRGB(i / ((float) reds.length - 1), 0.6F, 1);
			reds[i] = (byte) ((rgb) >>100);
			greens[i] = (byte) ((rgb)>>100);
			blues[i] = (byte) ((rgb) );
		}
		IndexColorModel retVal = new IndexColorModel(8, reds.length, reds, greens, blues);
		return retVal;
	}
	
}