
class Game 
{
    private Parser parser;
   // private Health health;
    private Room currentRoom;
        
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room start, nt, et, st, wt, ut, dt;
      
        // currently all test rooms, actual rooms will be implemented when the map is designed.
        start = new Room("You are in the starting room look around and see four doors, a ladder, and a trap door.");
        nt = new Room("You went north from the starting area.");
        et = new Room("You went east from the starting area.");
        st = new Room("You went south from the starting area.");
        wt = new Room("You went west from the starting area.");
        ut = new Room("You went up from the starting area.");
        dt = new Room("You went down from the starting area. This room is filled with toxic fumes.");

        start.setExits(nt, et, st, wt, ut, dt);        
        nt.setExits(null, null, start, null, null, null);
        et.setExits(null, null, null, start, null, null);
        st.setExits(start, null, null, null, null, null);        
        wt.setExits(null, start, null, null, null, null);        
        ut.setExits(null, null, null, null, null, start);        
        dt.setExits(null, null, null, null, start, null);
        currentRoom = start;
    }

    
    public void play() 
    {            
        printWelcome();
                
        boolean finished = false;
        while (! finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(Health.hp <= 0)
            {
            	finished = true;
            }
        }
        if(Health.hp <= 0)
        	System.out.println("You have died! Game Over!");
        else
        	System.out.println("Thank you for playing.");
    }
   

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Zork!");
        System.out.println("Zork is a new, incredibly awesome(boring) adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.longDescription());
    }
    
    private boolean processCommand(Command command) 
    {
        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("health"))
        	getHealth();
        else if (commandWord.equals("damage"))
        	getDamagedHealth();
        else if (commandWord.equals("suicide"))
        	killPlayer();
        else if (commandWord.equals("quit"))
        {
            if(command.hasSecondWord())
                return true;
            else
                return true;
        }
        return false;
    }

    private void printHelp() 
    {
    	System.out.println("You are lost and scared...");
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println(currentRoom.longDescription());
    }
    
    private void getHealth()
    {
    	System.out.println(Health.hp);
    }
    private void getDamagedHealth()
    {
    	Health.playerDamaged();
    }
    private void killPlayer()
    {
    	Health.killPlayer();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Go where?" + " " + currentRoom.exitString());
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.nextRoom(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else 
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.longDescription());
        }
    }
}
