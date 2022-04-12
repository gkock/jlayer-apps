package org.jlayer.app;

import static org.jlayer.util.IndexTools.Orientation;
import static org.jlayer.util.IndexTools.getOrientation;

import org.jlayer.util.JLayerException;

public class Decider {
	
//	enum Strategy {	A_Me_B, Me_A_B }
	enum Strategy {	A_Me_B, AMe_B, A_MeB, Me_A_B, MeA_B }
	
	Decider(int lines, int columns) {
		this.lines = lines;
		this.columns = columns;
	}
	
	int lines, columns;
	
	private int[] getInnerTrial(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{1, 0, 2, 4, 3}; break;
			case  S: order = new int[]{6, 7, 5, 3, 4}; break;
			case  E: order = new int[]{4, 2, 7, 6, 1}; break;
			case  W: order = new int[]{3, 0, 5, 6, 1}; break;
			case  NE: order = new int[]{2, 1, 4, 7, 0}; break;
			case  NW: order = new int[]{0, 1, 3, 5, 2}; break;
			case  SE: order = new int[]{7, 4, 6, 5, 2}; break;
			case  SW: order = new int[]{5, 3, 6, 7, 0}; break;
			case  NNE: order = new int[]{1, 2, 4, 0, 7}; break;
			case  NEE: order = new int[]{4, 2, 1, 7, 0}; break;
			case  NNW: order = new int[]{1, 0, 3, 2, 5}; break;
			case  NWW: order = new int[]{3, 0, 1, 5, 2}; break;
			case  SSE: order = new int[]{6, 7, 4, 5, 4}; break;
			case  SEE: order = new int[]{4, 7, 6, 2, 5}; break;
			case  SSW: order = new int[]{6, 5, 3, 7, 0}; break;
			case  SWW: order = new int[]{3, 5, 6, 0, 7}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getLeftTrial(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{0, 1, 2}; break;
			case  S: order = new int[]{3, 4, 2}; break;
			case  E: order = new int[]{2, 1, 4}; break;
			case  W: order = new int[]{}; break;
			case  NE: order = new int[]{1, 0, 2}; break;
			case  NW: order = new int[]{0}; break;
			case  SE: order = new int[]{4, 3, 2}; break;
			case  SW: order = new int[]{3}; break;
			case  NNE: order = new int[]{1, 0, 2}; break;
			case  NEE: order = new int[]{1, 2, 0}; break;
			case  NNW: order = new int[]{0}; break;
			case  NWW: order = new int[]{}; break;
			case  SSE: order = new int[]{4, 3, 2}; break;
			case  SEE: order = new int[]{4, 2, 3}; break;
			case  SSW: order = new int[]{3}; break;
			case  SWW: order = new int[]{}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getRightTrial(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{1, 0, 2}; break;
			case  S: order = new int[]{4, 3, 2}; break;
			case  E: order = new int[]{}; break;
			case  W: order = new int[]{2, 0, 3}; break;
			case  NE: order = new int[]{1}; break;
			case  NW: order = new int[]{0, 1, 2}; break;
			case  SE: order = new int[]{4}; break;
			case  SW: order = new int[]{3, 2, 4}; break;
			case  NNE: order = new int[]{1}; break;
			case  NEE: order = new int[]{}; break;
			case  NNW: order = new int[]{0, 1, 2}; break;
			case  NWW: order = new int[]{0, 2, 1}; break;
			case  SSE: order = new int[]{4}; break;
			case  SEE: order = new int[]{}; break;
			case  SSW: order = new int[]{3, 4, 2}; break;
			case  SWW: order = new int[]{3, 2, 4}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getUpperTrial(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{}; break;
			case  S: order = new int[]{3, 2, 4}; break;
			case  E: order = new int[]{1, 4}; break;
			case  W: order = new int[]{0, 2}; break;
			case  NE: order = new int[]{1}; break;
			case  NW: order = new int[]{0}; break;
			case  SE: order = new int[]{4, 3, 1}; break;
			case  SW: order = new int[]{2, 3, 0}; break;
			case  NNE: order = new int[]{}; break;
			case  NEE: order = new int[]{1}; break;
			case  NNW: order = new int[]{}; break;
			case  NWW: order = new int[]{0}; break;
			case  SSE: order = new int[]{4, 3, 1}; break;
			case  SEE: order = new int[]{4, 1, 3}; break;
			case  SSW: order = new int[]{2, 3, 0}; break;
			case  SWW: order = new int[]{2, 0, 3}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getLowerTrial(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{1, 2, 0}; break;
			case  S: order = new int[]{}; break;
			case  E: order = new int[]{4, 2}; break;
			case  W: order = new int[]{3, 0}; break;
			case  NE: order = new int[]{2, 1, 4}; break;
			case  NW: order = new int[]{0, 3, 1}; break;
			case  SE: order = new int[]{4}; break;
			case  SW: order = new int[]{3}; break;
			case  NNE: order = new int[]{2, 1, 4}; break;
			case  NEE: order = new int[]{2, 4, 1}; break;
			case  NNW: order = new int[]{0, 1, 3}; break;
			case  NWW: order = new int[]{0, 3, 1}; break;
			case  SSE: order = new int[]{}; break;
			case  SEE: order = new int[]{4}; break;
			case  SSW: order = new int[]{}; break;
			case  SWW: order = new int[]{3}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getTrial_00(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{0}; break;
			case  S: order = new int[]{1, 2}; break;
			case  E: order = new int[]{0, 2}; break;
			case  W: order = new int[]{1}; break;
			case  NE: order = new int[]{0}; break;
			case  NW: order = new int[]{}; break;
			case  SE: order = new int[]{2, 1}; break;
			case  SW: order = new int[]{1}; break;
			case  NNE: order = new int[]{}; break;
			case  NEE: order = new int[]{0}; break;
			case  NNW: order = new int[]{}; break;
			case  NWW: order = new int[]{}; break;
			case  SSE: order = new int[]{2, 1}; break;
			case  SEE: order = new int[]{2, 0}; break;
			case  SSW: order = new int[]{1}; break;
			case  SWW: order = new int[]{}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getTrial_0N(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{}; break;
			case  S: order = new int[]{2, 1}; break;
			case  E: order = new int[]{2}; break;
			case  W: order = new int[]{0, 1}; break;
			case  NE: order = new int[]{}; break;
			case  NW: order = new int[]{0}; break;
			case  SE: order = new int[]{2}; break;
			case  SW: order = new int[]{1, 0}; break;
			case  NNE: order = new int[]{}; break;
			case  NEE: order = new int[]{2}; break;
			case  NNW: order = new int[]{}; break;
			case  NWW: order = new int[]{0}; break;
			case  SSE: order = new int[]{2}; break;
			case  SEE: order = new int[]{}; break;
			case  SSW: order = new int[]{1, 2}; break;
			case  SWW: order = new int[]{1, 0}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getTrial_N0(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{0, 1}; break;
			case  S: order = new int[]{}; break;
			case  E: order = new int[]{2, 1}; break;
			case  W: order = new int[]{}; break;
			case  NE: order = new int[]{1, 0, 2}; break;
			case  NW: order = new int[]{0}; break;
			case  SE: order = new int[]{2}; break;
			case  SW: order = new int[]{}; break;
			case  NNE: order = new int[]{1, 0}; break;
			case  NEE: order = new int[]{1, 2}; break;
			case  NNW: order = new int[]{0}; break;
			case  NWW: order = new int[]{}; break;
			case  SSE: order = new int[]{}; break;
			case  SEE: order = new int[]{2}; break;
			case  SSW: order = new int[]{}; break;
			case  SWW: order = new int[]{}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getTrial_NN(int[] from, int[] to) {
		int[] order = new int[]{};
		Orientation orientation = getOrientation(from, to);
		if (orientation == null) return order;
		switch (orientation) {
			case  N: order = new int[]{1, 0}; break;
			case  S: order = new int[]{}; break;
			case  E: order = new int[]{}; break;
			case  W: order = new int[]{2, 0}; break;
			case  NE: order = new int[]{1}; break;
			case  NW: order = new int[]{0, 1, 2}; break;
			case  SE: order = new int[]{}; break;
			case  SW: order = new int[]{2}; break;
			case  NNE: order = new int[]{1}; break;
			case  NEE: order = new int[]{}; break;
			case  NNW: order = new int[]{0, 1, 2}; break;
			case  NWW: order = new int[]{0, 2, 1}; break;
			case  SSE: order = new int[]{}; break;
			case  SEE: order = new int[]{}; break;
			case  SSW: order = new int[]{}; break;
			case  SWW: order = new int[]{2}; break;
			default: throw new JLayerException();
		}
		return order;
	}
	
	private int[] getTrial(int[] from, int[] to) {
		int[] order = new int[]{};
		
		int i = from[0];
		int j = from[1];
		
		if ( (i == 0) && (j == 0) ) {
			order = getTrial_00(from, to);
		} else if ( (i == 0) && (j == columns-1) ) {
			order = getTrial_0N(from, to);
		} else if ( (i == lines-1) && (j == 0) ) {
			order = getTrial_N0(from, to);
		} else if ( (i == lines-1) && (j == columns-1) ) {
			order = getTrial_NN(from, to);
		} else if (i == 0) {
			order = getUpperTrial(from, to);
		} else if (i == lines-1) {
			order = getLowerTrial(from, to);
		} else if (j == 0) {
			order = getLeftTrial(from, to);
		} else if (j == columns-1) {
			order = getRightTrial(from, to);
		} else {
			order = getInnerTrial(from, to);
		}
		
		return order;
	}
	
	private int[] getGoal_AMe_B(Person personA, Person personB) {
		int[] goal;
		int[] indexA = new int[]{personA.pos.index[0], personA.pos.index[1]};
		Orientation orientation = getOrientation(personA.pos.index, personB.pos.index);
		if (orientation == null) throw new JLayerException();
		switch (orientation) {
			case  N: goal = new int[]{indexA[0] - 1, indexA[1]}; break;
			case  S: goal = new int[]{indexA[0] + 1, indexA[1]}; break;
			case  E: goal = new int[]{indexA[0], indexA[1] + 1}; break;
			case  W: goal = new int[]{indexA[0], indexA[1] - 1}; break;
			case  NE: goal = new int[]{indexA[0] - 1, indexA[1] + 1}; break;
			case  NW: goal = new int[]{indexA[0] - 1, indexA[1] - 1}; break;
			case  SE: goal = new int[]{indexA[0] + 1, indexA[1] + 1}; break;
			case  SW: goal = new int[]{indexA[0] + 1, indexA[1] - 1}; break;
			case  NNE: goal = new int[]{indexA[0] - 1, indexA[1] + 1}; break;
			case  NEE: goal = new int[]{indexA[0] - 1, indexA[1] + 1}; break;
			case  NNW: goal = new int[]{indexA[0] - 1, indexA[1] - 1}; break;
			case  NWW: goal = new int[]{indexA[0] - 1, indexA[1] - 1}; break;
			case  SSE: goal = new int[]{indexA[0] + 1, indexA[1] + 1}; break;
			case  SEE: goal = new int[]{indexA[0] + 1, indexA[1] + 1}; break;
			case  SSW: goal = new int[]{indexA[0] + 1, indexA[1] - 1}; break;
			case  SWW:goal = new int[]{indexA[0] + 1, indexA[1] - 1}; break;
			default: throw new JLayerException();
		}
		return goal;
	}
	
	private int[] getGoal_A_MeB(Person personA, Person personB) {
		int[] goal;
		int[] indexB = new int[]{personB.pos.index[0], personB.pos.index[1]};
		Orientation orientation = getOrientation(personB.pos.index, personA.pos.index);
		if (orientation == null) throw new JLayerException();
		switch (orientation) {
			case  N: goal = new int[]{indexB[0] - 1, indexB[1]}; break;
			case  S: goal = new int[]{indexB[0] + 1, indexB[1]}; break;
			case  E: goal = new int[]{indexB[0], indexB[1] + 1}; break;
			case  W: goal = new int[]{indexB[0], indexB[1] - 1}; break;
			case  NE: goal = new int[]{indexB[0] - 1, indexB[1] + 1}; break;
			case  NW: goal = new int[]{indexB[0] - 1, indexB[1] - 1}; break;
			case  SE: goal = new int[]{indexB[0] + 1, indexB[1] + 1}; break;
			case  SW: goal = new int[]{indexB[0] + 1, indexB[1] - 1}; break;
			case  NNE: goal = new int[]{indexB[0] - 1, indexB[1] + 1}; break;
			case  NEE: goal = new int[]{indexB[0] - 1, indexB[1] + 1}; break;
			case  NNW: goal = new int[]{indexB[0] - 1, indexB[1] - 1}; break;
			case  NWW: goal = new int[]{indexB[0] - 1, indexB[1] - 1}; break;
			case  SSE: goal = new int[]{indexB[0] + 1, indexB[1] + 1}; break;
			case  SEE: goal = new int[]{indexB[0] + 1, indexB[1] + 1}; break;
			case  SSW: goal = new int[]{indexB[0] + 1, indexB[1] - 1}; break;
			case  SWW:goal = new int[]{indexB[0] + 1, indexB[1] - 1}; break;
			default: throw new JLayerException();
		}
		return goal;
	}
	
	private int[] getGoal_MeA_B(Person personA, Person personB) {
		int[] goal;
		int[] indexA = new int[]{personA.pos.index[0], personA.pos.index[1]};
		Orientation orientation = getOrientation(personB.pos.index, personA.pos.index);
		if (orientation == null) throw new JLayerException();
		switch (orientation) {
			case  N: goal = new int[]{indexA[0] - 1, indexA[1]}; break;
			case  S: goal = new int[]{indexA[0] + 1, indexA[1]}; break;
			case  E: goal = new int[]{indexA[0], indexA[1] + 1}; break;
			case  W: goal = new int[]{indexA[0], indexA[1] - 1}; break;
			case  NE: goal = new int[]{indexA[0] - 1, indexA[1] + 1}; break;
			case  NW: goal = new int[]{indexA[0] - 1, indexA[1] - 1}; break;
			case  SE: goal = new int[]{indexA[0] + 1, indexA[1] + 1}; break;
			case  SW: goal = new int[]{indexA[0] + 1, indexA[1] - 1}; break;
			case  NNE: goal = new int[]{indexA[0] - 1, indexA[1] + 1}; break;
			case  NEE: goal = new int[]{indexA[0] - 1, indexA[1] + 1}; break;
			case  NNW: goal = new int[]{indexA[0] - 1, indexA[1] - 1}; break;
			case  NWW: goal = new int[]{indexA[0] - 1, indexA[1] - 1}; break;
			case  SSE: goal = new int[]{indexA[0] + 1, indexA[1] + 1}; break;
			case  SEE: goal = new int[]{indexA[0] + 1, indexA[1] + 1}; break;
			case  SSW: goal = new int[]{indexA[0] + 1, indexA[1] - 1}; break;
			case  SWW:goal = new int[]{indexA[0] + 1, indexA[1] - 1}; break;
			default: throw new JLayerException();
		}
		return goal;
	}

	int[] getTrial_A_Me_B(Person me, Person personA, Person personB) {
		int[] goal = new int[2];
		goal[0] = (personA.pos.index[0] + personB.pos.index[0] + 1) / 2;
		goal[1] = (personA.pos.index[1] + personB.pos.index[1] + 1) / 2;
		return getTrial(me.pos.index, goal);
	}
	
	int[] getTrial_AMe_B(Person me, Person personA, Person personB) {
		int[] goal = getGoal_AMe_B(personA, personB);
		return getTrial(me.pos.index, goal);
	}
	
	int[] getTrial_A_MeB(Person me, Person personA, Person personB) {
		int[] goal = getGoal_A_MeB(personA, personB);
		return getTrial(me.pos.index, goal);
	}
	
	int[] getTrial_Me_A_B(Person me, Person personA, Person personB) {
		int[] goal = new int[2];
		goal[0] = 2 * personA.pos.index[0] - personB.pos.index[0];
		goal[1] = 2 * personA.pos.index[1] - personB.pos.index[1];
		return getTrial(me.pos.index, goal);
	}
	
	int[] getTrial_MeA_B(Person me, Person personA, Person personB) {
		int[] goal = getGoal_MeA_B(personA, personB);
		return getTrial(me.pos.index, goal);
	}
}
