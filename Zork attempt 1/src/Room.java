import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

class Room
{
    private String description;
    public int damage;
    private Health health;
    private HashMap exits;        // stores exits of this room.

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap();
    }

    public void setExits(Room north, Room east, Room south, Room west, Room up, Room down) 
    {
        if(north != null)
            exits.put("north", north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
            exits.put("south", south);
        if(west != null)
            exits.put("west", west);
        if(up != null)
        	exits.put("up", up);
        if(down != null)
        	exits.put("down", down);      	
    }

    public String shortDescription()
    {
        return description;
    }

    public String longDescription()
    {
        return "You are in " + description + ".\n" + exitString();
    }

    public String exitString()
    {
        String returnString = "Exits:";
		Set keys = exits.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    public Room nextRoom(String direction) 
    {
        return (Room)exits.get(direction);
    }
}
