package events;

import characters.Player;
import items.ITEM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatEvent extends Event {
    String stat;
    int modifier;
    public StatEvent(String eventText, String stat, int modifier){
        this.eventText = eventText;
        this.stat = stat;
        this.modifier = modifier;
    }
    public StatEvent(ResultSet rs) throws SQLException {
        while(rs.next()) {
            this.chance = rs.getInt("chance");
            this.eventText = rs.getString("event_text");
            this.stat = rs.getString("value_modified");
            this.modifier = rs.getInt("stat_modifier");
        }

    }

    public void triggerEvent(Player player) {
        System.out.println(eventText);
        switch (stat) {
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
