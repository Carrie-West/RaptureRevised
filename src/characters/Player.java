package characters;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


import events.Event;
import events.ItemEvent;
import events.StatEvent;
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
		int eventThreshold = 40;
		Random roll = new Random();
		int chance = (roll.nextInt(100) + 10*(this.luck));	//each level of luck = 10% chance added
		if(chance >= eventThreshold) {
			switch (this.location.getName()) {

				case ("Seat"):
					eventRandomizer("seat", chance);

					break;
				case ("Bathroom"):
					eventRandomizer("bathroom", chance);


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

	public void eventRandomizer(String eventType, int eventRoll){
		try {
			Connection conn;
			String url = "jdbc:h2:mem:EventDatabase";
			conn = DriverManager.getConnection(url);
			PreparedStatement query = conn.prepareStatement("SELECT * FROM Events WHERE category = ? AND chance < ? ORDER BY RANDOM() LIMIT 1");
			query.setString(1, eventType);
			query.setInt(2, eventRoll);
			ResultSet rs = query.executeQuery();
			ArrayList<String> results = new ArrayList<>();


			while (rs.next()){
				for(int i = 1; i < 9; i++){
					results.add(rs.getString(i));
				}

			}
			results.forEach(result -> System.out.println(result));
			if (results.get(1).equals("stat")){

				StatEvent event = new StatEvent(results);
				event.triggerEvent(this);
			}else if(results.get(1).equals("item")){
				ItemEvent event =  new ItemEvent(results);
				System.out.println(event.toString());
				event.triggerEvent(this);
			}

		}catch (SQLException sq){
			sq.printStackTrace();
		}

	}
	
	
	
}
