/*
 * 
 */
package io.github.rocsg.fijirelax.lma;

/**
 * The Class ArrayConverter.
 * 	Copyright (C) 2022  <io.github.rocsg>
 *	//Initially joalho.data.lma, see  https://zenodo.org/record/4281064 , with licence (Open)
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>
 */
public class ArrayConverter {
	
	/**
	 * The Class SeparatedData.
	 */
	public static class SeparatedData {
		
		/** The y data points. */
		public double[] yDataPoints;
		
		/** The x data points. */
		public double[][] xDataPoints;	
	}
	
	
	/**
	 * Get float array as a double array.
	 *
	 * @param a the a
	 * @return the double[][]
	 */
	public static double[][] asDoubleArray(float[][] a) {
		double[][] result = new double[a.length][];
		for (int i = 0; i < a.length; i++) {
			//a[i].class.getName()
			//if (a[i].getClass().getName())
			result[i] = new double[a[i].length];
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = (float) a[i][j];
			}
		}
		return result;
	}
	
	/**
	 * Get float array as a double array. 
	 *
	 * @param a the a
	 * @return the double[]
	 */
	public static double[] asDoubleArray(float[] a) {
		if (a == null) return null;
		return asDoubleArray(new float[][] {a})[0];
	}
	
	/**
	 * Get double array as float array. 
	 *
	 * @param a the a
	 * @return the float[]
	 */
	public static float[] asFloatArray(double[] a) {
		float[] result = new float[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = (float) a[i];
		}
		return result;
	}
	
	/**
	 * Separates data from dataPoints[N][K+1] to yDataPoints[N] and xDatapoints[N][K].
	 * dataPoints[j] = yj xj0 xj1 xj2 ... xj[K-1]
	 * =>
	 * yDataPoints    = y0 y1 y2 ... yN
	 * xDataPoints[i] = xi0 xi1 xi2 ... xi[K-1]
	 *
	 * @param dataPoints the data points
	 * @return the separated data
	 */
	public static SeparatedData separateMultiDimDataToXY(double[][] dataPoints) {
		SeparatedData result = new SeparatedData();
		result.yDataPoints = new double[dataPoints.length];
		result.xDataPoints = new double[dataPoints.length][dataPoints[0].length - 1];
		for (int i = 0; i < dataPoints.length; i++) {
			result.yDataPoints[i] = dataPoints[i][0];
			for (int j = 1; j < dataPoints[i].length; j++) {
				result.xDataPoints[i][j - 1] = dataPoints[i][j]; 
			}
		}
		return result;
	}
	
	/**
	 * Combines K-dimensional input data into one 2d-array:
	 * 
	 * yDataPoints    = y0 y1 y2 ... yN
	 * xDataPoints[i] = xi0 xi1 xi2 ... xi[K-1]
	 * =>
	 * result[j] = yj xj0 xj1 xj2 ... xj[K-1]
	 *
	 * @param yDataPoints the y data points
	 * @param xDataPoints the x data points
	 * @return the double[][]
	 */
	public static double[][] combineMultiDimDataPoints(double[] yDataPoints, double[][] xDataPoints) {
		double[][] result = new double[yDataPoints.length][xDataPoints.length + 1];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = yDataPoints[i];
			for (int j = 1; j < result[i].length; j++) {
				result[i][j] = xDataPoints[i][j - 1];
			}
		}
		return result;
	}
	
	/**
	 * Combines K-dimensional input data into one 2d-array:
	 * 
	 * yDataPoints    = y0 y1 y2 ... yN
	 * xDataPoints[i] = xi0 xi1 xi2 ... xi[K-1]
	 * =>
	 * result[j] = yj xj0 xj1 xj2 ... xj[K-1]
	 *
	 * @param yDataPoints the y data points
	 * @param xDataPoints the x data points
	 * @return the double[][]
	 */
	public static double[][] combineMultiDimDataPoints(float[] yDataPoints, float[][] xDataPoints) {
		double[][] result = new double[yDataPoints.length][xDataPoints.length + 1];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = yDataPoints[i];
			for (int j = 1; j < result[i].length; j++) {
				result[i][j] = xDataPoints[i][j - 1];
			}
		}
		return result;
	}
	
	/**
	 * Transpose.
	 *
	 * @param a the a
	 * @return the double[][]
	 */
	public static double[][] transpose(double[] a) {
		double[][] result = new double[a.length][1];
		for (int i = 0; i < a.length; i++) {
			result[i][0] = a[i];
		}
		return result;
	}

}
