
/**
 * This is Lost. Lost is a 8 room text based adventure game.  
 * @author Jai Janse
 * @version 18-May-2025
 */

import java.util.Scanner;  // Keyboard input package
public class Main 
{
    Scanner input = new Scanner(System.in); // keyboard scanner
    // variable setup
    // These are variables that get called once or multiple times 
    final String lookAround = "Type 'look' to look around"; 
    final String direction = "What do you want to do?";
    final String help = "Movement Phrases:\n" +"Forward,\n" + "Back,\n" + "Explore,\n" + "Stay,\n" + "Pickup,\n" + "Yes,\n" + "No,\n" + "Quit,\n" + "(Read context of sentence)";
    final String invalid = "Invalid Input! Please try again!";
    final String youDied = "You died...";
    final String gameOver = "GAME OVER";
    final String theEnd = "The End...";
    int tool; // flag to control the room loop. Is set to 1 when the player completes the required action
    int noCampCount = 0; // Variable for how many times player has said no to making campfire. If they say no three times they die.
    int stayCampCount = 0; // Variable for how many times the player wanted to stay at the campfire. If they say no three times they die.
    String choice; // Keyboard input for all the switch statements

    String[] inventory = new String[1]; // Creates the array for the inventory
    
    // If the element isn't null, the method returns true meaning its in the inventory. 
    // Therefore if null the method returns false meaning it's not in the inventory.
    public boolean hasKey() { 
        return inventory[0] != null;
    }
    
    // Adds the key to the first slot in the inventory
    public void addKey(String key) { 
        inventory[0] = key;
        System.out.println(key + " has been added to your inventory");
    }
    
    public Main() // Starts game here 
    {   
        intro(); // Calls intro method
    }
    
    public void intro() 
    {
        // Room1 (intro): This room the player learns helpful commands.
                        
        System.out.println(" ^  ^  ^   ^      ___I_      ^  ^   ^  ^  ^   ^  ^  \n" +  //ASCII art
            "/|\\/|\\/|\\ /|\\    /\\-_--\\    /|\\/|\\ /|\\/|\\/|\\ /|\\/|\\ \n" +
            "/|\\/|\\/|\\ /|\\   /  \\_-__\\   /|\\/|\\ /|\\/|\\/|\\ /|\\/|\\ \n" +
            "/|\\/|\\/|\\ /|\\   |[]| [] |   /|\\/|\\ /|\\/|\\/|\\ /|\\/|\\ ");  

        System.out.println(); 
        System.out.println("Darkness yielded, as my eyes began to open.");
        System.out.println("I don't remember what happened, I slowly came to a upright stance.");
        System.out.println(); 
        System.out.println(lookAround); // Calls look around

        String looking = input.nextLine(); // Keyboard input for looking
        while (!looking.equalsIgnoreCase("look")) {
            System.out.println(); 
            System.out.println(invalid); // Calls the Invalid variable if Invalid input
            System.out.println(lookAround); // Calls the Look Around variable
            looking = input.nextLine();
        }

        System.out.println(); 
        System.out.println("If stuck type 'help' for movement phrases");
        System.out.println(); 
        System.out.println("Type 'quit' to leave game early");
        System.out.println(); 

        forest(); // Move to the Forest
    }
    
    public void forest() 
    {
        // Room2 (forest): This room features a cold dark forest where the player must decide to make a campfire.

        tool = 0;
        System.out.println("You're in a forest you see fog looming above the treeline, cloaking the eerie surroundings. \n" +
            "It's dark like really dark and it's getting cold real fast. It must be like 5°C");

        while (tool != 1) {
            System.out.println("Do you make a Campfire?");
            choice = input.nextLine();

            switch (choice.toLowerCase()) {
                case "yes": // Player decides to make a campfire
                    System.out.println();
                    tool = 1;
                    campfire(); // Calls the campfire room
                    break;
                case "no": // If player dosen't make campfire, consequences increase
                    System.out.println(); 
                    noCampCount++; // counts the number of times the player didn't make a campfire
                    if (noCampCount == 1) {
                        System.out.println("You decided not to make a campfire. Your body temperature is decreasing at an alarming rate.");
                    } else if (noCampCount == 2) {
                        System.out.println("It's now 3°C. If you don't act soon you might die.");
                    } else { 
                        // Player dies if refusing too many times
                        System.out.println("You fell on your knees in the fluffy snow... \n" +
                            "Your heart begins slow, your vision blurs, and your eyes become a victim to the cold... \n" +
                            "There's no one near to come save you... \n" +
                            "Your last thoughts are of your family as they fade into the cold white fluffy snow brushing your skin... ");
                        System.out.println(); 
                        System.out.println(youDied); // Calls you died
                        System.out.println(); 
                        System.out.println(gameOver); // Calls game over
                        System.out.println(); 
                        System.exit(0); // Exit's game
                    }
                    break;
                case "help": // Displays help commands
                    System.out.println();
                    System.out.println(help); // Calls string help 
                    break;
                case "quit": // Ends game immediately when typed
                    System.out.println(); 
                    System.out.println(gameOver); // Calls game over
                    System.exit(0); // Exit's game
                    break;
                default: // Invalid input provided
                    System.out.println();
                    System.out.println(invalid); // Calls invalid
                    break;
            }
        }
        System.out.println("You slowly gathered all the materials to build a campfire.");
    }

    public void campfire()
    {
        // Room3 (campfire): In this room the player decides whether they should venture deeper into the forest or stay by the fire.

        tool = 0; 
        System.out.println("You successfully collected the materials to make the campfire. \n" +
            "You slowly start to build the campfire from the ground up, \n" + 
            "Starting with stones, then larger logs, and finally the small, easy to light sticks. \n" + 
            "You decide to light the campfire and get warm for a couple hours, \n" +
            "you're unsure whether to move on or stay by the fire.");
        while (tool != 1) {
            System.out.println(); 
            System.out.println(direction); // Calls the variable that asks the player where they want to go 
            System.out.println(); 
            System.out.println("Do you type 'forward' to venture deeper into the eerie forest,");
            System.out.println("or type 'stay' to stay by the campfire.");
            choice = input.nextLine();               
            switch (choice.toLowerCase()) {
                case "forward": // Player decided to proceed to eerieForest
                    tool = 1;
                    System.out.println(); 
                    System.out.println("You continue exploring the eerie forest and stumble across an abandoned house.");
                    eerieForest(); // Calls room eerieForest
                    break;
                case "stay":  // If player decides to stay to long by campfire, the consequences will increase
                    System.out.println();
                    stayCampCount++; // Counts the amount of times the player has stayed by the campfire
                    if (stayCampCount == 1) {
                        System.out.println("Your body is now at a nice warm temperature, like after hopping out of the shower.");
                    } else if (stayCampCount == 2) {
                        System.out.println("Your getting too hot now, and you dont have much drinking water left.");
                    } else { 
                        // Player dies from cold exposure
                        System.out.println("You ran out of dry wood, food and drinking water. \n" + 
                            "Maybe you should've ventured deeper into the eerie forest...");
                        System.out.println(); 
                        System.out.println(youDied); // Calls you died
                        System.out.println(); 
                        System.out.println(gameOver); // Calls game over
                        System.out.println(); 
                        System.exit(0); // Exit's game
                    }
                    break;
                case "help": // Displays help commands
                    System.out.println(); 
                    System.out.println(help); // Calls string help 
                    break;
                case "quit": // Ends game immediately when typed
                    System.out.println();
                    System.out.println(gameOver); // Calls game over
                    System.exit(0); // Exit's game
                    break;
                default: // Invalid input provided
                    System.out.println();
                    System.out.println(invalid); // Calls invalid
                    break;
            }
        } 
    }

    public void eerieForest () 
    {
        // Room4 (eerieForest): In this room the player decides if they would like to explore the abandonedHouse.
        
        tool = 0;
        System.out.println("You notice the house you thought was abandoned has smoke coming out the chimney. \n" +
            "This could be risky, but you've only got one day of food left.");
        System.out.println("The choice is yours...");
        while (tool != 1) {
            System.out.println(); 
            System.out.println(direction); // Calls the variable that asks the player where they want to go 
            System.out.println(); 
            System.out.println("Do you type 'forward' to explore the not so abandoned house and look for supplies,");
            System.out.println("or type 'back' to return and build another campfire.");
            choice = input.nextLine();               
            switch (choice.toLowerCase()) {
                case "forward": // Player decided to proceed up to the so called abandoned house
                    tool = 1;
                    System.out.println(); 
                    System.out.println("You slowly walk upto the house on high alert. \n" +
                        "Your very suddenly turning your attention, to different parts of the house.\n" +
                        "You finally make it to the front door.");
                    abandonedHouse (); // Calls room abandonedHouse 
                    break;
                case "back": // Player decides to go build another campfire
                    System.out.println(); 
                    tool = 1;
                    campfire(); // Calls room campfire
                    break;
                case "help": // Displays help commands
                    System.out.println(); 
                    System.out.println(help); // Calls string help 
                    break;
                case "quit": // Ends game immediately when typed
                    System.out.println(); 
                    System.out.println(gameOver); // Calls string game over
                    System.exit(0); // Exit's game
                    break;
                default: // Invalid input provided
                    System.out.println(); 
                    System.out.println(invalid); // Calls invalid
                    break;
            }
        }
    }

    public void abandonedHouse () 
    {
        // Room5 (abandonedHouse): In this room the player decides whether they want to explore upstairs, go down the hall to the kitchen or go back to the eerieForest.
        
        tool = 0;
        System.out.println("As you slowly twist the knob. The door screeches like finger nails on a chalkboard. \n" +
            "As the door finally opens. Your met with a long hallway. \n" +
            "Adjacent to the hallway is a large menacing staircase to upstairs...");
        while (tool != 1) {
            System.out.println(); 
            System.out.println(direction); // Asks where the players wants to go 
            System.out.println(); 
            System.out.println("Do you type 'explore' to explore the first floor up the large menacing staircase, \n" +
                "or do you type 'forward' to go down the hallway and search the kitchen to see if you can scrummage some supplies, \n" +
                "or did you not like your decision to enter the house and decide to type 'back' to run back to the forest.");
            choice = input.nextLine(); // Choice keyboard input
            switch (choice.toLowerCase()) { 
                case "explore": // Player decides to go upstairs
                    tool = 1; 
                    System.out.println();
                    System.out.println("You decided to go up the large menacing staircase to upstairs, \n" +
                        "once your upstairs you notice a console table with stuff on it."); 
                    upstairs();  // Calls room upstairs
                    break;
                case "forward": // Player decides to explore kitchen
                    tool = 1;
                    System.out.println(); 
                    System.out.println("You decide to explore the kitchen and scrummage what supplies you can, \n" +
                        "you make it quick because you don't know who is in the house.");
                    kitchen(); // Calls room kitchen
                    break;
                case "back": // Player decides to go back to the eerieForest
                    tool = 1; 
                    System.out.println(); 
                    eerieForest(); // Calls eerieForest
                    break;
                case "help": // Displays help commands
                    System.out.println(); 
                    System.out.println(help); // Calls string help
                    break;
                case "quit": // Ends game immediately when typed
                    System.out.println(); 
                    System.out.println(gameOver); // Calls string game over
                    System.exit(0); // Exit's game
                    break;
                default: // Invalid input provided
                    System.out.println();
                    System.out.println(invalid); // Calls string invalid
                    break;
            }
        }
    }

    public void upstairs () 
    {
        // Room6 (upstairs): In this room the player gets asked if they want to pick up the key if yes the key gets added to the player inventory.  
        
        tool = 0;
        System.out.println("You decide to take a closer look at what is on the console table and you stumble across a key with a tag on it saying bedroom");
        while (tool != 1) {
            System.out.println(); 
            System.out.println(direction); // Calls the variable that asks the player where they want to go 
            System.out.println();  
            System.out.println("Do you type 'pickup' to pickup the key and go down the upstairs hallway and unlock the bedroom door, \n" +
                "or do you type 'back' to back to the front door and decide what you want to do again.");
            choice = input.nextLine(); // Choice keyboard input
            switch (choice.toLowerCase()) { 
                case "pickup": // Player picks up key and adds it to the inventory
                    tool = 1;
                    System.out.println();   
                    addKey("Key"); // Adds key to inventory
                    System.out.println();
                    System.out.println("You picked up the key and slowly walked towards the bedroom door trying not to make any sound. \n" +
                        "You take every step really slowly as if you get caught now. Your're toast!");
                    bedroom(); // Calls room bedroom
                    break;
                case "back": // Player decides to return to the front door
                    tool = 1;
                    System.out.println();
                    abandonedHouse(); // Calls room abandonedHouse
                    break;
                case "help": // Displays help commands
                    System.out.println(); 
                    System.out.println(help); // Calls string help
                    break;
                case "quit": // Ends game immediately when typed
                    System.out.println(); 
                    System.out.println(gameOver); // Calls string gameOver
                    System.exit(0); // Exit's game
                    break;
                default: // Invalid input provided
                    System.out.println();
                    System.out.println(invalid); // Calls string invalid
                    break;
            }
        }
    }

    public void kitchen () 
    {
        // Room7 (kitchen): In this room the player can go explore the weird sound or go back to the front door.
        
        tool = 0;
        System.out.println();
        System.out.println("You're now in the kitchen, first you check the pantry then the fridge. \n" +
            "You notice your favourite thing. Chocolate. You feel happy again like when your child was born. \n" +
            "There's hope... \n" +
            "But you hear a weird noise coming from down the hallway almost a radio sound...");
        while (tool != 1) {    
            System.out.println(); 
            System.out.println(direction); // Calls the variable that asks the player where they want to go 
            System.out.println(); 
            System.out.println("Do you type 'explore' to investigate the radio sound from down the hallway  \n" +
                "or do you type 'back' and go back to the front door");
            choice = input.nextLine(); // Choice keyboard input
            switch (choice.toLowerCase()) { 
                case "explore": // Player decides to explore the sounds down the hallway
                    tool = 1;
                    System.out.println();
                    System.out.println("You decided to investigate the radio sound you slowly push open the door. \n" +
                        "You can't believe it. It's a radio station. You take your time to figure out how to use the radio by yourself. \n" + 
                        "It took you 2 hours to figure it out all on your own and 30 minutes to contact the authorities. \n" +
                        "You fell hope...");
                    finalChapter(); // Calls room finalChapter
                    break;
                case "back": // Player goes back to the front door
                    tool = 1;
                    System.out.println();
                    abandonedHouse(); // Calls room abandonedHouse
                    break;
                case "help": // displays help commands
                    System.out.println(); 
                    System.out.println(help); // Calls string help
                    break;
                case "quit": // ends game immediately when typed
                    System.out.println(); 
                    System.out.println(gameOver); // Calls string gameOver
                    System.exit(0); // Exit's game
                    break;
                default: // invalid input provided
                    System.out.println();
                    System.out.println(invalid); // Calls string invalid
                    break;
            }
        }      
    }

    public void bedroom () 
    {
        // Room8 (bedroom): In this room the player uses the key and opens the bedroom door, inside the player finds a man who helps you gain contact with your family. 
        
        tool = 0;
        System.out.println();
        if (hasKey()) {
            System.out.println("You slowly insert your key into the lock... it clicks. \n" +
            "You gently push open the door. The hinges creak loudly as you open the door to a moderately basic bedroom consisting of a dresser, \n" +
            " a side table and a double bed. You notice movement under the sheets on the bed. You decided to investigate. \n" +
            "You pulled the sheet off abruptly. It's as you suspected it's a person. \n" +
            "A middle aged man to be precise. You scream at each other in shock. He asks what you doing out in the middle of no where, \n" + 
            "you reply with the same question he asks. He replies with so I don't see people like you. You agree with him. \n" + 
            "But then you actually say why your here. Your lost and you need help making contact, so you can get back home to your family. \n" + 
            "He says your in luck I have a radio tower for situations like this. He says I can show you how to use it if you like?");
        } else {
            System.out.println("You need a key to unlock the bedroom door.");
        }
        while (tool != 1) {
            System.out.println(); 
            System.out.println(direction); // Calls the variable that asks the player where they want to go 
            System.out.println();    
            System.out.println("Do you type 'yes' for him to show you how to use it and make contact with your family, \n" + 
                "or do you type 'no' and figure out how to use it yourself.");
            choice = input.nextLine(); // Choice keyboard input    
            switch (choice.toLowerCase()) { 
                case "yes": // The man shows you how to use the radio 
                    tool = 1;
                    System.out.println(); 
                    System.out.println("You decided for him to show you how to use the radio and make contact with the authorities. \n" + 
                        "It only took you 15 minutes to learn how to use it and make contact with the authorities.");
                    finalChapter(); // Calls room finalChapter
                    break;
                case "no": // You decide to figure out how to use the radio yourself
                    tool = 1;
                    System.out.println(); 
                    System.out.println("You decided to figure out how to use the radio by yourself and make contact with the authorities. \n" + 
                        "It took you 2 hours to figure it out on your own and 30 minutes to contact the authorities. ");
                    finalChapter(); // Calls room finalChapter
                    break;
                case "help": // Displays help commands
                    System.out.println(); 
                    System.out.println(help); // Calls string help
                    break;
                case "quit": // Ends game immediately when typed
                    System.out.println(); 
                    System.out.println(gameOver); // Calls string gameOver
                    System.exit(0); // Exit's game
                    break;
                default: // Invalid input provided
                    System.out.println();
                    System.out.println(invalid); // Calls string invalid
                    break;
            }
        }
    }

    public void finalChapter() 
    {
        // Room9 (finalChapter): This is the ending of the game it gets called by the kitchen and bedroom method 
        
        System.out.println(); 
        System.out.println("You tell the authorities your coordinates and they said will be there within two hours. \n" + 
            "In no time the authorities arrive in a helicopter for your rescue. Help is here! you shout. \n" + 
            "Without the radio station you would've be dead. You can't wait to tell your family your alive and what happened...  \n" + 
            "You fly off into the sunset never to be seen in these parts again..."); 
        System.out.println();
        System.out.println(theEnd); // Calls string theEnd
        System.out.println();
        System.out.println(gameOver); // Calls string gameOver
        System.exit(0); // Exit's game 
    }
}