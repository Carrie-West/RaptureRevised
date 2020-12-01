package rooms;
import items.ITEM;

import java.util.ArrayList;
import java.util.Arrays;

public enum ROOM{
		Seat("Seat", new ArrayList<ITEM>(Arrays.asList(ITEM.valueOf("Magazine"), ITEM.valueOf("Manual")))),
		Coach("Coach", new ArrayList<ITEM>()),
		Business("Business", new ArrayList<ITEM>()),
		FirstClass("First Class", new ArrayList<ITEM>()),
		Bathroom("Bathroom", new ArrayList<ITEM>());


		ROOM(String name, ArrayList<ITEM> Inventory) {
			this.name = name;
			this.Inventory = Inventory;
			this.beenHere = false;
		};
		private String name;
		private ArrayList <ITEM> Inventory;
		private boolean beenHere;
		
		public String getName() {
			return this.name;
		}
		public boolean hasBeenHere() {return this.beenHere;}
		public void toggleBeenHere() { this.beenHere = !(this.beenHere); }

	}
	


