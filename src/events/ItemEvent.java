package events;

import characters.Player;
import items.ITEM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemEvent extends Event {
    ITEM item;
    //addOrRemove -- True if item added, False if item removed
    boolean addOrRemove;

    public ItemEvent(String eventText, ITEM item, boolean addOrRemove){
        this.eventText = eventText;
        this.item = item;
        this.addOrRemove = addOrRemove;

    }

    public ItemEvent(ResultSet rs) throws SQLException {
        while(rs.next()) {
            this.chance = rs.getInt("chance");
            this.eventText = rs.getString("event_text");
            this.item = ITEM.valueOf(rs.getString("value_modified"));
            this.addOrRemove = rs.getBoolean("addOrRemove");
        }


    }


    public void triggerEvent(Player player) {
        System.out.println(eventText);
        player.modifyInventory(item, addOrRemove);
    }
}
