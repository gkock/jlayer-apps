package org.jlayer.app;

import java.util.Random;

import org.jlayer.annotations.*;
import org.jlayer.util.JLayerException;

@LayerUnit 
public class Person {

	String		name;
	Position	pos;
	Person 		personA, personB;
	Decider     decider;
	
	Person(String name, Decider decision) {
		this.name = name;
		this.decider = decision;
	}
	
	@LayerMethod
	public void initPos(Position[][] floor, Random r) {
		do {
			int line = r.nextInt(floor.length);
			int col  = r.nextInt(floor[0].length);
			if (floor[line][col].person == null) {
				floor[line][col].person = this;
				this.pos = floor[line][col];
			}
		} while (this.pos == null);
	}
	
	@LayerMethod
	public void initPersonAandB(Person[] persons, Random r) {
		Person chosenA, chosenB;
		do {
			chosenA = persons[r.nextInt(persons.length)];
			chosenB = persons[r.nextInt(persons.length)];
		} while ( (chosenA == chosenB) || (chosenA == this) || (chosenB == this) );
		this.personA = chosenA;
		this.personB = chosenB;
	}
	
	@LayerMethod
	public void moveMe(Decider.Strategy strategy) {
		int [] stepTrial;
		switch (strategy) {
			case A_Me_B: 
				stepTrial = decider.getTrial_A_Me_B(this, personA, personB);
				break;
			case AMe_B: 
				stepTrial = decider.getTrial_AMe_B(this, personA, personB);
				break;
			case A_MeB: 
				stepTrial = decider.getTrial_A_MeB(this, personA, personB);
				break;
			case Me_A_B: 
				stepTrial = decider.getTrial_Me_A_B(this, personA, personB);
				break;
			case MeA_B: 
				stepTrial = decider.getTrial_MeA_B(this, personA, personB);
				break;
			default: throw new JLayerException();
		}
		for (int i = 0; i < stepTrial.length; i++) {
			if (pos.neighbours[stepTrial[i]].person == null) {
				pos.neighbours[stepTrial[i]].person = this;
				pos.person = null;
				pos = pos.neighbours[stepTrial[i]];
				break;
			}
		}
	}
	
}
