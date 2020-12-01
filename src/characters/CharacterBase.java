package characters;

import java.lang.String;
import java.util.ArrayList;
import items.ITEM;

public abstract class CharacterBase {
	protected String name;
	protected ArrayList<String> Pronouns;
	protected int intelligence;
	protected int strength;
	protected int agility;
	protected int charisma;
	protected int luck;
	protected ArrayList<ITEM> Inventory;
	
	public CharacterBase (String name, ArrayList<String> pronouns, int intelligence, int strength, 
						  int agility, int charisma, int luck, ArrayList<ITEM> Inventory) {
		this.name = name;
		this.Pronouns = pronouns;
		this.intelligence = intelligence;
		this.strength = strength;
		this.agility = agility;
		this.charisma = charisma;
		this.luck = luck;
		this.Inventory = Inventory;
	}
	
	public CharacterBase () {
		this.name = "Null";
		this.Pronouns = null;
		this.intelligence = 0;
		this.strength = 0;
		this.agility = 0;
		this.charisma = 0;
		this.luck = 0;
		this.Inventory = null;
	}
	
	public abstract String getName();

	public abstract String getPronouns(int placement);

	public abstract int getIntelligence();

	public abstract int getStrength();

	public abstract int getAgility();

	public abstract int getCharisma();

	public abstract int getLuck();

	public abstract ITEM getItem(ITEM item);

	public abstract void modifyIntelligence(int value);

	public abstract void modifyStrength(int value);

	public abstract void modifyAgility(int value);

	public abstract void modifyCharisma(int value);

	public abstract void modifyLuck(int value);

}
