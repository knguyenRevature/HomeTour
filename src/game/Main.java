package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import fixtures.Interactable;
import fixtures.Room;

public class Main {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	
	public static List<String> acceptableCommands = new ArrayList<String>();
	
	public static void main(String[] args) {
		RoomManager rm = new RoomManager();
		Player player = new Player();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("--- Welcome to HomeTour ---");
		hint();
		initAcceptableCommands();
		rm.init();
		player.setCurrentRoom(rm.rooms.get(0));
		boolean playing = true;
		
		while (playing) {
			printRoom(player);
			String input = scanner.nextLine();
			String[] commands = collectInput(input);
			
			if (acceptableCommands.contains(commands[0])) {
				int index = acceptableCommands.indexOf(commands[0]);
				
				switch(acceptableCommands.get(index)) {
					case "help":
						if (commands.length == 1) {
							System.out.println("\n--- Help ---");
							hint();
						} else {
							invalidInput();
						}
						break;
					case "quit":
						if (commands.length == 1) {
							System.out.println("\nClosing HomeTour.");
							playing = false;
						} else {
							invalidInput();
						}
						break;
					case "move":
						if (commands.length == 2) {
							movePlayer(commands[1], player);
						} else {
							invalidInput();
						}
						break;
					case "interact":
						if (commands.length == 2) {
							interactObject(commands[1], player);
						} else {
							invalidInput();
						}
						break;
					case "use":
						if (commands.length == 2) {
							useObject(commands[1], player);
						} else {
							invalidInput();
						}
						break;
					case "bag":
						player.bagStatus();
						break;
				}
			} else {
				System.out.println("\nNo such commands found. Type 'help' for the rules again.");
			}
		}
		
		scanner.close();
	}
	
	public static String[] collectInput(String input) {
		String[] commands = input.split(" ");
		return commands;
	}
	
	public static void initAcceptableCommands() {
		/*
		 * Initializes acceptable commands
		 */
		acceptableCommands.add("help");
		acceptableCommands.add("quit");
		
		acceptableCommands.add("move");
		acceptableCommands.add("interact");
		acceptableCommands.add("use");
		acceptableCommands.add("bag");
	}
	
	public static void hint() {
		/*
		 * Provides a reminder of acceptable commands to the player
		 */
		System.out.println("To navigate through the house use " + ANSI_YELLOW + "move <direction>." + ANSI_RESET);
		System.out.println("To interact with objects use " + ANSI_YELLOW + "interact <object>." + ANSI_RESET);
		System.out.println("To use something in your bag use " + ANSI_YELLOW + "use <object>." + ANSI_RESET);
		System.out.println("To check the status of your bag use " + ANSI_YELLOW + "bag." + ANSI_RESET);
		System.out.println("To exit HomeTour use " + ANSI_YELLOW + "quit." + ANSI_RESET);
	}
	
	public static void invalidInput() {
		/*
		 * Displays invalid input warning
		 */
		System.out.println("\nInvalid Input. Too many/few arguments entered.");
	}
	
	public static void printRoom(Player player) {
		/*
		 * Displays room information and the exit options
		 */
		System.out.println("\n--- " + player.getCurrentRoom().getName() + " ---");
		player.getCurrentRoom().displayLongDescription();
		
		System.out.println("\nExits: ");
		for (Entry<String, Room> entry : player.getCurrentRoom().adjacentRooms.entrySet()) {
			String roomDirection = entry.getKey();
			Room room = entry.getValue();
			System.out.println(roomDirection + ": " + room.getShortDescription());
		}
	}
	
	public static void movePlayer(String direction, Player player) {
		/*
		 * Moves player to next room based on direction input
		 */
		boolean roomFound = false;
		
		for (Entry<String, Room> entry : player.getCurrentRoom().adjacentRooms.entrySet()) {
			String roomDirection = entry.getKey();
			Room room = entry.getValue();
			
			if (roomDirection.equalsIgnoreCase(direction)) {
				if (room.isLocked) {
					System.out.println("\nThe door to " + room.getName() + " is locked.");
				} else {
					System.out.println("\nPlayer moves into " + room.getName());
					player.setCurrentRoom(room);
				}
				
				roomFound = true;
				break;
			}
		}
		
		if (!roomFound) {
			System.out.println("\nInvalid direction.");
		}
	}
	
	public static void interactObject(String object, Player player) {
		/*
		 * Player interacts with object based on object name
		 * If object isBaggable, will be place in bag
		 */
		boolean objectFound = false;
		
		for (Interactable interaction : player.getCurrentRoom().interactables) {
			if (interaction.getName().equalsIgnoreCase(object)) {
				objectFound = true;
				System.out.println("\n" + interaction.interactDescription);
				
				if (interaction.isBaggable) {
					player.addToBag(interaction);
					System.out.println("\n" + interaction.getColorName() + " was added to your bag.");
				}
				
				break;
			}
		}
		
		if (!objectFound) {
			System.out.println("\nInvalid interaction. No such object found.");
		}
	}
	
	public static void useObject(String object, Player player) {
		/*
		 * Player uses object in bag to progress
		 */
		boolean objectFound = false;
		boolean roomUnlocked = false;
		
		for (Interactable interaction : player.bag) {
			if (interaction.getName().equalsIgnoreCase(object)) {
				objectFound = true;
				
				for (Room room : player.getCurrentRoom().adjacentRooms.values()) {
					if (room.isLocked && room.checkUnlockCondition(interaction)) {
						roomUnlocked = true;
						room.setIsLocked(!roomUnlocked);
						System.out.println("\n" + interaction.useDescription);
						break;
					}
				}
				
				if (!roomUnlocked) {
					System.out.println("\n" + interaction.getColorName() + " cannot be used here.");
				}
				
				break;
			}
		}
		
		if (!objectFound) {
			System.out.println("\nInvalid interaction. No such object found.");
		}
	}
}
