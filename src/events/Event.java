package events;

import characters.Player;

 public abstract class Event{
        String eventText;
        int chance;


        public void triggerEvent(Player player){

        }

        public String getEventText(){
            return this.eventText;
        }
    }

