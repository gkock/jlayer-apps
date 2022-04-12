package org.jlayer.app;

import org.jlayer.annotations.*;

@LayerUnit 
public class Position {

	@LayerField(isIndex = true)
	int[]	index;
	
	Person	person = null;
	
	@LayerField 
	Position[]	neighbours;
	
}
