package events;

import characters.Player;
import items.ITEM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatEvent extends Event {
    String stat;
    int modifier;
    public StatEvent(String eventText, String stat, int modifier){
        this.eventText = eventText;
        this.stat = stat;
        this.modifier = modifier;
    }
    public StatEvent(ArrayList<String> rs){
        this.chance = Integer.parseInt(rs.get(3));
        this.eventText = rs.get(4);
        this.stat = rs.get(5);
        this.modifier = Integer.parseInt(rs.get(7));
    }

    public String toString(){
        return (this.stat.toString() + ", " + this.eventText + ", " + this.chance);
    }

    public void triggerEvent(Player player) {
        System.out.println(eventText);
        switch (this.stat) {
            case ("intelligence"):
                player.modifyIntelligence(modifier);
                break;
            case ("strength"):
                player.modifyStrength(modifier);
                break;
            case ("agility"):
                player.modifyAgility(modifier);
                break;
            case ("charisma"):
                player.modifyCharisma(modifier);
                break;
            case ("luck"):
                player.modifyLuck(modifier);
        }
    }
}
