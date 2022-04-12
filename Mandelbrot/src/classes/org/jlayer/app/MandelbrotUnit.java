package org.jlayer.app;

import java.awt.Color;

import org.jlayer.annotations.*;

@LayerUnit 
public class MandelbrotUnit {
	
	private static int width, height;
	private static int maxIterations;
	private static int[] palette;
	
	private int countIterations(int row, int col) {
    	double xmin, xmax, ymin, ymax;
        xmin = -1.6744096740931858;
        xmax = -1.674409674093473;
        ymin = 4.716540768697223E-5;
        ymax = 4.716540790246652E-5;
        
        double x, y;
        double dx, dy;
        dx = (xmax-xmin)/(width-1);
        dy = (ymax-ymin)/(height-1);
        y = ymax - dy*row;
        x = xmin + dx*col;
        
    	int count = 0;
        double xx = x;
        double yy = y;
        while (count < maxIterations && (xx*xx + yy*yy) < 4) {
            count++;
            double newxx = xx*xx - yy*yy + x;
            yy = 2*xx*yy + y;
            xx = newxx; 
        }
        return count;
    }
	
	private int getRGB(int count) {
        if (count == maxIterations)
            return 0;
        else
            return palette[count%palette.length];
    }
	
	private void setRgbValue() {
		int row = index[0];
		int col = index[1];
		int count = countIterations(row, col);
		rgbValue = getRGB(count);
	}
	
	@LayerField(isIndex = true)
	int[] index;
	
	@LayerField
	int rgbValue;
	
	static void setGlobalParams(int width, int height,
			                           int maxIterations) {
		MandelbrotUnit.width = width;
		MandelbrotUnit.height = height;
		MandelbrotUnit.maxIterations = maxIterations;
		
		palette = new int[256];
        for (int i = 0; i < 256; i++)
            palette[i] = Color.getHSBColor(i/255F, 1, 1).getRGB();
	}
	
	@LayerMethod
	void setRgbValue_LOOP(){
		setRgbValue();
	}
	
	@LayerMethod(runtimeMode=RuntimeMode.FORKJOIN)
	void setRgbValue_PARALLEL(){
		setRgbValue();
	}
	
}
