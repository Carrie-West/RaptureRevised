package characters;

import java.util.ArrayList;

import items.ITEM;

public class NPC extends CharacterBase {
	private int opinion;
	
	public NPC(String name, ArrayList<String> pronouns, int intelligence, int strength, int agility, int charisma,
			int luck, ArrayList<ITEM> Inventory, int opinion) {
		this.name = name;
		this.Pronouns = pronouns;
		this.intelligence = intelligence;
		this.strength = strength;
		this.agility = agility;
		this.charisma = charisma;
		this.luck = luck;
		this.Inventory = Inventory;
		this.opinion = opinion;
	}
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

	public void modifyLuck(int value) { luck += value; }

	public int getOpinion() {
		return opinion;
	}
	
	public void setOpinion(int value) {
		opinion = value;
	}
}
