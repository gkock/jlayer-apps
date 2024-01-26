package org.jlayer.app;

class Patterns {
	
	static class Entry {
		Entry(int line, int col, int len){
			this.line = line;
			this.col = col;
			this.len = len;
		}
		int line;
		int col;
		int len;
	}
	
	static int trainPatsNo = 2;
	static int linesNo = 50;
	static int colsNo  = 100;
	
	static int[][][] trainPatterns;
	static int[][]   recallPat;
	
	static int curRecNo = 0;
	
	static void createTrainPatterns(int linesNo, int colsNo) {
		
		trainPatterns = new int[trainPatsNo][linesNo][colsNo];
		
		for (int p = 0; p < trainPatsNo; p++) {
			for (int n = 0; n < linesNo; n++) {
				for (int m = 0; m < colsNo; m++) {
					trainPatterns[p][n][m] = 1;
				}
			}
		}
		
		setEntry(patJ, trainPatterns[0]);
		setEntry(patL, trainPatterns[0]);
		setEntry(patA, trainPatterns[0]);
		setEntry(patY, trainPatterns[0]);
		setEntry(patE, trainPatterns[0]);
		setEntry(patR, trainPatterns[0]);
		
		mirrorPat(trainPatterns[1], trainPatterns[0]);

	}
	
	static void setEntry(Entry[] pat, int[][] base) {
		for (Entry e: pat) {
			for (int col = e.col; col < e.col + e.len; col++) {
				base[e.line][col] = -1;
			}
		}
	}
	
	static void mirrorPat(int[][] mirror, int[][] base) {
		for (int n = 0; n < linesNo; n++) {
			for (int m = 0; m < colsNo; m++) {
				mirror[n][m] = base[n][colsNo -1 - m];
			}
		}
	}
	
	static void setRecallPattern(int linesNo, int colsNo) {
		
		recallPat = new int[linesNo][colsNo];
		
		// elements are set randomly
		for (int n = 0; n < linesNo; n++) {
			for (int m = 0; m < colsNo; m++) {
				recallPat[n][m] = switchRandomly(trainPatterns[curRecNo][n][m]);
			}
		}
		
		curRecNo++;
		if (curRecNo == trainPatsNo) curRecNo = 0;

	}	
	
	static int switchRandomly(int inVal) {
		if (Math.random() > 0.75) {
			return -inVal;
		} else {
			return inVal;
		}
	}

	// description of the pattern entries J L A Y E R
	
	static Entry[] patJ = new Entry[] {
			new Entry(5,5,20), new Entry(6,5,20), new Entry(7,5,20), new Entry(8,5,20),
			new Entry(9,21,4), new Entry(10,21,4), new Entry(11,21,4), new Entry(12,21,4), 
			new Entry(13,21,4), new Entry(14,21,4), new Entry(15,21,4), new Entry(16,21,4),
			new Entry(17,21,4), new Entry(18,21,4), new Entry(19,21,4), new Entry(20,21,4),
			new Entry(21,21,4), new Entry(22,21,4), new Entry(23,21,4), new Entry(24,21,4),
			new Entry(25,21,4), new Entry(26,21,4), new Entry(27,21,4), new Entry(28,21,4),
			new Entry(26,21,4), new Entry(27,21,4), new Entry(28,21,4), new Entry(29,21,4),
			new Entry(30,21,4), new Entry(31,21,4), new Entry(32,21,4), new Entry(33,21,4),
			new Entry(34,21,4), new Entry(35,21,4), new Entry(36,21,4), new Entry(37,21,4),
			new Entry(38,21,4), new Entry(39,21,4), new Entry(40,21,4), new Entry(41,21,4),
			new Entry(42,10,15), new Entry(43,10,15), new Entry(44,10,15), new Entry(45,10,15)
		};
	
	static Entry[] patL = new Entry[] {
			new Entry(30,28,3), new Entry(31,28,3), new Entry(32,28,3), new Entry(33,28,3),
			new Entry(34,28,3), new Entry(35,28,3), new Entry(36,28,3), new Entry(37,28,3),
			new Entry(38,28,3), new Entry(39,28,3), new Entry(40,28,3), new Entry(41,28,3),
			new Entry(42,28,3),
			new Entry(43,28,10), new Entry(44,28,10), new Entry(45,28,10)
		};
	
	static Entry[] patA = new Entry[] {
			new Entry(18,43,7), 
			new Entry(19,43,2), new Entry(19,48,2), 
			new Entry(20,42,3), new Entry(20,48,3), 
			new Entry(21,42,3), new Entry(21,48,3), 
			new Entry(22,42,3), new Entry(22,48,3), 
			new Entry(23,42,3), new Entry(23,48,3),
			new Entry(24,42,3), new Entry(24,48,3),
			new Entry(25,42,3), new Entry(25,48,3),
			new Entry(26,42,9),
			new Entry(27,42,9),
			new Entry(28,41,3), new Entry(28,49,3),
			new Entry(29,41,3), new Entry(29,49,3),
			new Entry(30,40,3), new Entry(30,50,3),
			new Entry(31,40,3), new Entry(31,50,3),
			new Entry(32,39,3), new Entry(32,51,3),
			new Entry(33,39,3), new Entry(33,51,3)
		};
	
	static Entry[] patY = new Entry[] {
			new Entry(5,55,3), new Entry(6,55,3),  
			new Entry(7,56,3), new Entry(8,56,3),  
			new Entry(9,57,3), new Entry(10,57,3),  
			new Entry(11,58,3),	new Entry(12,58,3),
			new Entry(5,63,3),	new Entry(6,63,3),
			new Entry(7,62,3),	new Entry(8,62,3),
			new Entry(9,61,3),	new Entry(10,61,3),
			new Entry(11,60,3),	new Entry(12,60,3),
			new Entry(13,59,3),	new Entry(14,59,3),
			new Entry(15,58,3),	new Entry(16,58,3),
			new Entry(17,57,3),	new Entry(18,57,3),
			new Entry(19,56,3),	new Entry(20,56,3)
		};
	
	static Entry[] patE = new Entry[] {
			new Entry(18,69,10), new Entry(19,69,10), 
			new Entry(20,69,3), new Entry(21,69,3), new Entry(22,69,3),
			new Entry(23,69,3), new Entry(24,69,3),
			new Entry(25,69,10), new Entry(26,69,10), 
			new Entry(27,69,3),	new Entry(28,69,3), new Entry(29,69,3),
			new Entry(30,69,3),	new Entry(31,69,3), 
			new Entry(32,69,10), new Entry(33,69,10)
		};
	
	static Entry[] patR = new Entry[] {
			new Entry(30,84,9), new Entry(31,84,10), 
			
			new Entry(32,84,3), new Entry(33,84,3),	new Entry(34,84,3), 
			new Entry(35,84,3), new Entry(36,84,3),
			
			new Entry(32,91,3), new Entry(33,91,3),	new Entry(34,91,3), 
			new Entry(35,91,3), new Entry(36,91,3),
			
			new Entry(37,84,9), new Entry(38,84,9), 
			
			new Entry(39,91,3), new Entry(40,91,3),
			new Entry(41,92,3), new Entry(42,92,3),
			new Entry(43,93,3), new Entry(44,93,3),
			
			new Entry(39,84,3),	new Entry(40,84,3), new Entry(41,84,3),
			new Entry(42,84,3),	new Entry(43,84,3), new Entry(44,84,3)
		};

}
