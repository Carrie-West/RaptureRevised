package events;

import characters.Player;
import items.ITEM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemEvent extends Event {
    ITEM item;
    //addOrRemove -- True if item added, False if item removed
    boolean addOrRemove;

    public ItemEvent(String eventText, ITEM item, boolean addOrRemove){
        this.eventText = eventText;
        this.item = item;
        this.addOrRemove = addOrRemove;

    }

    public ItemEvent(ArrayList<String> rs){
        this.chance = Integer.parseInt(rs.get(3));
        this.eventText = rs.get(4);
        this.item = ITEM.valueOf(rs.get(5));
        this.addOrRemove = Boolean.parseBoolean(rs.get(6));

    }

    public String toString(){
        return (this.item.toString() + ", " + this.eventText + ", " + this.chance);
    }

    public void triggerEvent(Player player) {
        System.out.println(this.eventText);
        player.modifyInventory(this.item, this.addOrRemove);
    }
}
