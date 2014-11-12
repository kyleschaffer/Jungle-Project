/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Game 
{
    private Parser parser;
    private ParserWithFileInput parserWithFileInput;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        parserWithFileInput = new ParserWithFileInput();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room start, finish, room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15, room16;
      
        // create the rooms
        //outside = new Room("outside the main entrance of the university");
        //theater = new Room("in a lecture theater");
        //pub = new Room("in the campus pub");
        //lab = new Room("in a computing lab");
        //office = new Room("in the computing admin office");
        
        start = new Room("Blank");
        finish= new Room("Blank");
        room1 = new Room("Blank");
        room2 = new Room("Blank");
        room3 = new Room("Blank");
        room4 = new Room("Blank");
        room5 = new Room("Blank");
        room6 = new Room("Blank");
        room7 = new Room("Blank");
        room8 = new Room("Blank");
        room9 = new Room("Blank");
        room10 = new Room("Blank");
        room11 = new Room("Blank");
        room12 = new Room("Blank");
        room13 = new Room("Blank");
        room14 = new Room("Blank");
        room15 = new Room("Blank");
        room16 = new Room("Blank");
        
        start.setExit("north", room6);
        start.setExit("west", room9);
        start.setExit("east", room10);
        start.setExit("south", room12);
        
        room1.setExit("east", room2);
        room1.setExit("south", room5);
        
        room2.setExit("north", finish);
        room2.setExit("west", room1);
        room2.setExit("east", room3);
        room2.setExit("south", room6);
        
        room3.setExit("west", room2);
        room3.setExit("south", room7);
        
        room4.setExit("east", room5);
        
        room5.setExit("north", room1);
        room5.setExit("west", room4);
        room5.setExit("east", room6);
        room5.setExit("south", room9);
        
        room6.setExit("north", room2);
        room6.setExit("west", room5);
        room6.setExit("east", room7);
        room6.setExit("south", start);
        
        room7.setExit("north", room3);
        room7.setExit("west", room6);
        room7.setExit("east", room8);
        room7.setExit("south", room10);
        
        room8.setExit("west", room7);
        
        room9.setExit("north", room5);
        room9.setExit("east", start);
        room9.setExit("south", room11);
        
        room10.setExit("north", room7);
        room10.setExit("west", start);
        room10.setExit("south", room13);
        
        room11.setExit("north", room9);
        room11.setExit("east", room12);
        room11.setExit("south", room14);
        
        room12.setExit("north", start);
        room12.setExit("west", room11);
        room12.setExit("east", room13);
        room12.setExit("south", room15);
        
        room13.setExit("north", room10);
        room13.setExit("west", room12);
        room13.setExit("south", room16);
        
        room14.setExit("north", room11);
        room14.setExit("east", room15);
        
        room15.setExit("north", room12);
        room15.setExit("west", room14);
        room15.setExit("east", room16);
        
        room16.setExit("north", room13);
        room16.setExit("west", room15);

        currentRoom = start;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    public void playWithFileInput() 
    {            
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parserWithFileInput.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
