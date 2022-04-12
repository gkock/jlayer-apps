package org.jlayer.app;

import java.util.Set;
import java.util.HashSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * tests PartyNetwork.
 * @author Gerd Kock
 */
@Test(groups = {"suiteC"})
public class PartyTest {
	
	PartyNetwork partyNetwork;
	
	private void showNetwork() {
		for (int i = 0; i < partyNetwork.lines; i++) {
			for (int j = 0; j < partyNetwork.columns; j++) {
				String name = "";
				if (partyNetwork.floorArray[i][j].person != null) {
					name = partyNetwork.floorArray[i][j].person.name;
				}
				System.out.printf("|%-3s", name);
			}
			System.out.println("|");
		}
		System.out.println("");
	}
	
	
	@BeforeClass
    public void beforeClass() {
		partyNetwork = new PartyNetwork();
		// fixed settings w.r.t. the tests !
		partyNetwork.lines = 6;     //******
		partyNetwork.columns = 6;   //******
		partyNetwork.personCnt = 6; //******
		partyNetwork.initSeed = 27; //******
	}

	
	@Test(groups = "PartyTest-A")
	public void testCreateNetwork() {
		partyNetwork.createNetwork();
		
		assertEquals(partyNetwork.floorArray.length, partyNetwork.lines);
		assertEquals(partyNetwork.floorArray[0].length, partyNetwork.columns);
		for (int i = 0; i < partyNetwork.lines; i++) {
			for (int j = 0; j < partyNetwork.columns; j++) {
				assertNotNull(partyNetwork.floorArray[i][j]);
				assertEquals(partyNetwork.floorArray[i][j].index[0], i);
				assertEquals(partyNetwork.floorArray[i][j].index[1], j);
				assertNull(partyNetwork.floorArray[i][j].person);
			}
		}
		
		assertEquals(partyNetwork.personArray.length, partyNetwork.personCnt);
		for (int i = 0; i < partyNetwork.personCnt; i++) {
			assertEquals(partyNetwork.personArray[i].name, String.valueOf(i));
			assertNull(partyNetwork.personArray[i].pos);
			assertNull(partyNetwork.personArray[i].personA);
			assertNull(partyNetwork.personArray[i].personB);
		}
		
//		System.out.println("TEST testCreateNetwork");
	}
	
	@Test(groups = "PartyTest-B", dependsOnGroups  = "PartyTest-A")
	public void testInitNetwork() {
		partyNetwork.initNetwork();
		
		int counter = 0;
		Set<String> nameSet = new HashSet<String>();
		for (int i = 0; i < partyNetwork.lines; i++) {
			for (int j = 0; j < partyNetwork.columns; j++) {
				Position position = partyNetwork.floorArray[i][j];
				if (position.person != null) {
					counter++;
					nameSet.add(position.person.name);
					assertEquals(position.person.pos, position);
				}
			}
		}
		assertEquals(counter, partyNetwork.personArray.length);
		assertEquals(nameSet.size(), partyNetwork.personArray.length);
		
		for (int i = 0; i < partyNetwork.personCnt; i++) {
			Person person = partyNetwork.personArray[i];
			assertNotEquals(person, person.personA);
			assertNotEquals(person, person.personB);
			assertNotEquals(person.personA, person.personB);
		}
		
		// checks for the fixed settings from "beforeClass()" 
		Position[][] floorArray = partyNetwork.floorArray;
		Person[]     personArray = partyNetwork.personArray;
		
		assertEquals(personArray[0].pos, floorArray[4][2]);
		assertEquals(personArray[1].pos, floorArray[5][2]);
		assertEquals(personArray[2].pos, floorArray[0][2]);
		assertEquals(personArray[3].pos, floorArray[4][1]);
		assertEquals(personArray[4].pos, floorArray[3][1]);
		assertEquals(personArray[5].pos, floorArray[0][5]);
		
		assertEquals(personArray[0].personA, personArray[1]);
		assertEquals(personArray[1].personA, personArray[2]);
		assertEquals(personArray[2].personA, personArray[1]);
		assertEquals(personArray[3].personA, personArray[1]);
		assertEquals(personArray[4].personA, personArray[3]);
		assertEquals(personArray[5].personA, personArray[3]);
		
		assertEquals(personArray[0].personB, personArray[5]);
		assertEquals(personArray[1].personB, personArray[4]);
		assertEquals(personArray[2].personB, personArray[5]);
		assertEquals(personArray[3].personB, personArray[2]);
		assertEquals(personArray[4].personB, personArray[1]);
		assertEquals(personArray[5].personB, personArray[4]);
		
//		for (int i = 0; i < partyNetwork.personNo; i++) {
//			Person person = partyNetwork.personArray[i];
//			System.out.printf("%d: A=%s B=%s %n", i, person.personA.name, person.personB.name);
//		}
		
//		System.out.println("TEST testInitNetwork");
	}
	
	@Test(groups = "PartyTest-C", dependsOnGroups  = "PartyTest-B")
	public void test1_A_Me_B() {
		partyNetwork.strategy = Decider.Strategy.A_Me_B;
		partyNetwork.moveMe();
		
		// checks for the fixed settings from "beforeClass()" 
		Position[][] floorArray = partyNetwork.floorArray;
		Person[]     personArray = partyNetwork.personArray;
		
		assertEquals(personArray[0].pos, floorArray[4][3]);
		assertEquals(personArray[1].pos, floorArray[4][2]);
		assertEquals(personArray[2].pos, floorArray[1][3]);
		assertEquals(personArray[3].pos, floorArray[3][2]);
		assertEquals(personArray[4].pos, floorArray[4][1]);
		assertEquals(personArray[5].pos, floorArray[1][4]);
		
//		System.out.println("TEST test1_A_Me_B");
	}
	
	@Test(groups = "PartyTest-D", dependsOnGroups  = "PartyTest-C")
	public void test2_A_Me_B() {
		partyNetwork.strategy = Decider.Strategy.A_Me_B;
		partyNetwork.moveMe();
		
		// checks for the fixed settings from "beforeClass()" 
		Position[][] floorArray = partyNetwork.floorArray;
		Person[]     personArray = partyNetwork.personArray;
		
		assertEquals(personArray[0].pos, floorArray[3][3]);
		assertEquals(personArray[1].pos, floorArray[3][1]);
		assertEquals(personArray[2].pos, floorArray[2][3]);
		assertEquals(personArray[3].pos, floorArray[3][2]);
		assertEquals(personArray[4].pos, floorArray[4][2]);
		assertEquals(personArray[5].pos, floorArray[2][4]);
		
//		System.out.println("TEST test2_A_Me_B");
	}
	
	@Test(groups = "PartyTest-E", dependsOnGroups  = "PartyTest-D")
	public void test1_ME_A_B() {
		partyNetwork.strategy = Decider.Strategy.Me_A_B;
		partyNetwork.moveMe();
		
		// checks for the fixed settings from "beforeClass()" 
		Position[][] floorArray = partyNetwork.floorArray;
		Person[]     personArray = partyNetwork.personArray;
		
		assertEquals(personArray[0].pos, floorArray[4][3]);
		assertEquals(personArray[1].pos, floorArray[2][2]);
		assertEquals(personArray[2].pos, floorArray[1][2]);
		assertEquals(personArray[3].pos, floorArray[3][2]);
		assertEquals(personArray[4].pos, floorArray[4][2]);
		assertEquals(personArray[5].pos, floorArray[2][3]);
		
//		System.out.println("TEST test1_ME_A_B");
	}
	
	@Test(groups = "PartyTest-F", dependsOnGroups  = "PartyTest-E")
	public void test2_ME_A_B() {
		partyNetwork.strategy = Decider.Strategy.Me_A_B;
		partyNetwork.moveMe();
		
		// checks for the fixed settings from "beforeClass()" 
		Position[][] floorArray = partyNetwork.floorArray;
		Person[]     personArray = partyNetwork.personArray;
		
		assertEquals(personArray[0].pos, floorArray[3][3]);
		assertEquals(personArray[1].pos, floorArray[1][1]);
		assertEquals(personArray[2].pos, floorArray[0][1]);
		assertEquals(personArray[3].pos, floorArray[2][1]);
		assertEquals(personArray[4].pos, floorArray[3][1]);
		assertEquals(personArray[5].pos, floorArray[2][2]);
		
//		System.out.println("TEST test2_ME_A_B");
	}
		
}
