package org.jlayer.app;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.jlayer.model.*;
import org.jlayer.util.IndexTools;
import org.jlayer.util.LayerBase;

/**
 * tests MatrixElem.
 * @author Gerd Kock
 */
@Test(groups = {"suiteC"})
public class MatrixTest {
	
	final int l = 500, m = 400, n = 800;
	
	double[][] matrixA, matrixB;
	Double[][] matrixC;
	
	MatrixUnit[][] arrayMatrixUnit;
	ALayer_MatrixUnit_ layerMatrixUnit;
	
	@BeforeClass
    void beforeClass() {
		// matrix A
		matrixA = new double[l][m];
		for(int il = 0; il < l; il++) {
			for(int im = 0; im < m; im++) {
				matrixA[il][im] = 2;
			}
		}
		// matrix B
		matrixB = new double[m][n];
		for(int im = 0; im < m; im++) {
			for(int in = 0; in < n; in++) {
				matrixB[im][in] = 0.5;
			}
		}
		// matrix C
		matrixC = new Double[l][n];
		
		// elemLayer
		arrayMatrixUnit = new MatrixUnit[l][n];
		for(int il = 0; il < l; il++) {
			for(int in = 0; in < n; in++) {
				arrayMatrixUnit[il][in] = new MatrixUnit();
			}
		}
		layerMatrixUnit = new Layer_MatrixUnit_(arrayMatrixUnit);
		layerMatrixUnit.setOperands(matrixA, matrixB);
		
		// test info
		System.out.printf("(l,m,n) == (%d, %d, %d)%n", l, m, n);
		printMatrixInfo(matrixA, "matrixA");
		printMatrixInfo(matrixB, "matrixB");
//		printMatrix(matrixA, "matrixA");
//		printMatrix(matrixB, "matrixB");
	}
	
	// TEST OUTPUT
	
	void printMatrixInfo(double[][] matrix, String name) {
		System.out.printf(name + "[0][0] = %2.2f%n", matrix[0][0]);
	}
	void printMatrixInfo(Double[][] matrix, String name) {
		System.out.printf(name + "[0][0] = %2.2f%n", (double)matrix[0][0]);
	}
	
	void printMatrix(double[][] matrix, String name) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.printf("%2.2f ", matrix[i][j]);
			}
			System.out.printf("%n");
		}
		System.out.printf("%n %n");
	}
	
	void printMatrix(Double[][] matrix, String name) {
		double[][] matrix2 = new double[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix2[i][j] = matrix[i][j];
			}
		}
		printMatrix(matrix2, name);
	}
	
	// MULTIPLY()
	
	void multiply() {
		for(int il = 0; il < l; il++) {
			for(int in = 0; in < n; in++) {
				double result = 0.0;
				for(int lm = 0; lm < m; lm++) {
					result += matrixA[il][lm] * matrixB[lm][in];
				}
				matrixC[il][in] = result;
			}
		}
	}
	
	// TEST 1
	
	@Test
	void test1() {
		long startTime = System.currentTimeMillis();
		multiply();
		long stopTime = System.currentTimeMillis();
		System.out.println("Calling multiply()                  >>> " + (stopTime - startTime) + " ms");
		printMatrixInfo(matrixC, "matrixC");
//		printMatrix(matrixC, "matrixC");
	}
	
	// TEST 2
	
	@Test
	void test2() {
		long startTime = System.currentTimeMillis();
		layerMatrixUnit.multiply();
		long stopTime = System.currentTimeMillis();
		System.out.println("Calling layerMatrixUnit.multiply()  >>> " + (stopTime - startTime) + " ms");
		matrixC = layerMatrixUnit.getResult().getD2().getBase();
		printMatrixInfo(matrixC, "matrixC");
//		printMatrix(matrixC, "matrixC");
	}
	
	@Test
	void test3() {
		long startTime = System.currentTimeMillis();
		layerMatrixUnit.multiplyP();
		long stopTime = System.currentTimeMillis();
		System.out.println("Calling layerMatrixUnit.multiplyP() >>> " + (stopTime - startTime) + " ms");
		matrixC = layerMatrixUnit.getResult().getD2().getBase();
		printMatrixInfo(matrixC, "matrixC");
//		printMatrix(matrixC, "matrixC");
	}
		
}
