package org.jlayer.app;

import org.jlayer.annotations.*;
import org.jlayer.app.MyUtils.Signal;

@LayerUnit 
public class Input {
	
	@LayerField	Signal y = new Signal();   // fanout signal
	
	@LayerMethod
	public void setInput(@LayerParam double input){
		y.val = input;
	}

}
