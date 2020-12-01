package characters;

import java.util.ArrayList;
import java.util.Random;



import items.ITEM;
import rooms.ROOM;

public class Player extends CharacterBase {

	public Player(String name, ArrayList<String> pronouns, int intelligence, int strength, int agility, int charisma, int luck, ArrayList<ITEM> Inventory, ArrayList<NPC> Party, ROOM room) {
		super(name, pronouns, intelligence, strength, agility, charisma, luck, Inventory);
		this.Party = Party;
		this.location = room;
	}
	public Player() {
		this.Party = null;
		this.location = null;
	}

	private ArrayList <NPC> Party;
	private ROOM location;

	public String getName() {
		return name;
	}
	public String getPronouns(int placement) {
		return Pronouns.get(placement);
	}
	public int getIntelligence() {
		return intelligence;
	}
	public int getStrength() {
		return strength;
	}
	public int getAgility() {
		return agility;
	}
	public int getCharisma() {
		return charisma;
	}
	public int getLuck() {
		return luck;
	}

	public ITEM getItem(ITEM item) {
		return Inventory.get(Inventory.indexOf(item));
	}
	public void modifyInventory(ITEM item, Boolean addOrRemove){
		if (addOrRemove = true){
			Inventory.add(item);
		}else{
			if (Inventory.contains(item)){
				Inventory.remove(item);
			}
		}
	}

	public void modifyIntelligence(int value) {
		intelligence += value;
	}

	public void modifyStrength(int value) {
		strength += value;
	}
	public void modifyAgility(int value) {
		agility += value;
	}
	public void modifyCharisma(int value) { charisma += value; }
	public void modifyLuck(int value) {
		luck += value;
	}

	public ROOM getLocation() {
		return location;
	}
	public NPC getPartyMember(int value) {
		return Party.get(value);
	}
	public void setRoom(ROOM value) {
		location = value;
	}

	public void addParty(NPC character) {
		Party.add(character);
	}
	
	public void removeParty(NPC character) {
		Party.remove(character);
	}

	public void randomEvent(){
		int eventThreshold = 80;
		Random roll = new Random();
		int chance = (roll.nextInt(100) + 10*(this.luck));	//each level of luck = 10% chance added
		if(chance >= eventThreshold) {
			switch (this.location.getName()) {

				case ("Seat"):
					break;
				case ("Bathroom"):
					if (!checkParty("Holly")) {

					}
					break;

			}
		}

	}

	public boolean checkParty(String name) {
		for (int i = 0; i < Party.size(); i++){
			if (getPartyMember(i).getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	
	
}
