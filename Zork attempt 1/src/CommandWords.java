class CommandWords
{
    private static final String validCommands[] = {
        "go", "quit", "help", "health", "damage", "suicide"
    };

    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * Return true if it is, false if it isn't.
     **/
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++)
        {
            if(validCommands[i].equals(aString))
                return true;
        }
        return false;
    }

    public void showAll() 
    {
        for(int i = 0; i < validCommands.length; i++)
        {
            System.out.print(validCommands[i] + "  ");
        }
        System.out.println();
    }
}
