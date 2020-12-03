import characters.*;
import items.ITEM;
import rooms.ROOM;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
	
	static Scanner keyboard = new Scanner(System.in);
	public static void main(String[] args) {

		initialize();
		
		Player player = startUp();

		long current = (System.currentTimeMillis());
		System.out.println(current);
		long finish = current + 3600000;
		System.out.println(finish);
		System.out.println("Your eyes flutter open, the first person to ever get a good nap on an airplane\n" +
				"Sprawled out along the row, you think about how pissed your seat neighbor would be if they were there\n" +
				"Wait... you DID have a seat neighbor.");
		while(current<finish){
			System.out.println("What do you want to do?");
			switch(keyboard.next()){
				case("rest"):
					System.out.println("rest");
			}
			switch(player.getLocation().getName()){

				case("Seat"):

					if(!(ROOM.Seat.hasBeenHere())){
						System.out.println("Where is everyone?");
						ROOM.Seat.toggleBeenHere();
					}else{
						player.randomEvent();
					}
					break;
				case("Bathroom"):
					if(!(ROOM.Bathroom.hasBeenHere())){

					}else{
						player.randomEvent();
					}
					break;
				default:
					System.out.println("You feel lost to the void...");

			}
			

			current = System.currentTimeMillis();
		}

		
	}

	public static void initialize(){

		//events are stored in an in-memory database initialized on startup
		Connection conn;
		String url = "jdbc:h2:mem:EventDatabase";
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException cl){
			cl.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(url);
			PreparedStatement init_table = conn.prepareStatement("CREATE TABLE Events (" +
					"    id int primary key,\n" +
					"  	 category VARCHAR,\n" +
					"	 event_type VARCHAR, \n"+
					"    chance int,\n" +
					"    event_text VARCHAR,\n" +
					"    value_modified VARCHAR,\n" +
					"    add_or_remove boolean,\n" +
					"    stat_modifier int\n" +
					")");
			init_table.executeUpdate();

			PreparedStatement loadData = conn.prepareStatement("INSERT INTO Events SELECT * FROM CSVREAD('EventList')");

			loadData.executeUpdate();

			System.out.println("Initialized");



		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	public static Player startUp() {

		String name;
		int pronounChoice;
		ArrayList<String> Pronouns =  new ArrayList<>();
		
		ArrayList<ITEM> Inventory = new ArrayList<>();
		ArrayList<NPC> Party = new ArrayList<>();
		
		int intelligence = 5;
		int strength = 5;
		int agility = 5; 
		int charisma = 5;
		int luck = 0;

		System.out.println("Welcome to Rapture, a text based adventure game!");
		System.out.println("Before we start what is your name?");
		name = keyboard.next();
		System.out.println("What pronouns do you use? \n" +
			"1. He \n2. She \n3. They \n" +
			"4. Other (We'll go through and set these up) \n5. Just Use My Name");
		pronounChoice = keyboard.nextInt();

		if (pronounChoice == 1) {
			Pronouns.add(0, "he");
			Pronouns.add(1, "him");
			Pronouns.add(2, "his");
		}
		else if (pronounChoice == 2) {
			Pronouns.add(0, "she");
			Pronouns.add(1, "her");
			Pronouns.add(2, "her's");
		}
		else if (pronounChoice == 3) {
			Pronouns.add(0, "they");
			Pronouns.add(1, "them");
			Pronouns.add(2, "their");
			Pronouns.add(3, "their's");
		}
		else if (pronounChoice == 4) {
			String firstPronoun, secondPronoun, thirdPronoun;
			System.out.println("What word should be used in your third-person (ex. he/she/they)");
			firstPronoun = keyboard.next();
			System.out.println("What word should people use to refer to you? (ex. him/her/them)");
			secondPronoun = keyboard.next();
			System.out.println("What word should people use for something you possess? (ex. his/her's/their's)");
			thirdPronoun = keyboard.next();
			Pronouns.add(0, firstPronoun);
			Pronouns.add(1, secondPronoun);
			Pronouns.add(2, thirdPronoun);
			
		}
		else if (pronounChoice == 5) {
			Pronouns.add(0, name);
			Pronouns.add(1, name);
			Pronouns.add(2, name + "'s");
		}

		int response;
		System.out.println("Now, I have some questions for you.");
		System.out.println("Do you think before you speak? (1 or 2) \n" +
				"1. I'm always thinking of a plan \n" +
				"2. I prefer to fly off the cuff \n");
		response = keyboard.nextInt();
		switch (response) {
		case(1):
			intelligence++;
			break;
		case (2):
			luck++;
		}

		System.out.println("You are approached by a drunk guy at a bar, who claims you were" +
				"looking at him the wrong way. What do you do? (1, 2 or 3) \n" +
				"1. Punch him square in the nose for even approaching me. \n" +
				"2. Make a distraction and slip off while he's not looking \n" +
				"3. 'Hey! Don't you remember me? We were best friends in preschool!");
		response = keyboard.nextInt();
		switch (response) {
			case(1):
				strength++;
				break;
			case (2):
				agility++;
				break;
			case(3):
				charisma++;

		}


		return new Player(name, Pronouns, intelligence, strength, agility, charisma, luck, Inventory, Party, ROOM.Seat);
	}
}
