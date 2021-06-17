package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import fixtures.Interactable;
import fixtures.Room;

public class RoomManager {
	
	public List<Room> rooms = new ArrayList<Room>();
	
	public void init() {
		/*
		 * Creating all rooms (9 total)
		 */
		Room courtyard = new Room("Courtyard", "An abandoned courtyard", "Dead leaves and broken tree branches litter every inch of this courtyard. "
				+ "\nThe stairs leading up to the large oak doors are all crumbling, perhaps due to years of neglect. ", false);
		Room lobby = new Room("Lobby", "A dusty lobby", "The lobby is covered in dust and cobwebs. There hasn't been any traffic in here for years. "
				+ "\nMost of the floorboards are snapped, and the lights have long since been burned out. ", false);
		Room closet = new Room("Closet", "A small closet", "Nothing special here, just your average closet. Just spacious enough to fit a person... ", false);
		Room kitchen = new Room("Kitchen", "A large kitchen", "Pots and pans are scattered all over the tiled floors. A strange odor emits from the stoves... "
				+ "\nI wouldn't linger in here for too long.", false);
		Room garage = new Room("Garage", "A smelly old garage", "The garage might just smell worse than those stoves. Small beams of light are seeping in "
				+ "\nthrough holes in the ceiling. Those rafters look like they could break at any moment. ", false);
		Room backyard = new Room("Backyard", "An overgrown backyard", "Wild grass and overgrown hedges dominate the backyard. The fences along the "
				+ "\nperimeter are all rusted and warped, as if blown back by some force. ", false);
		Room study = new Room("Study", "A messy study", "Looks like a storm went straight through this room. Books and notes lie scattered over the floor, "
				+ "\nand most of the furniture is either flipped over or completely destroyed. ", true);
		Room bedroom = new Room("Bedroom", "A decrepit bedroom", "A large, broken-down bed stands in the center of this bedroom. The windows are shattered, "
				+ "\nand the walls bare strange markings. What could have possibly done this? ", false);
		Room secret = new Room("Secret Exit", "An odd stone door", "There's a hole at the bottom of the floor. It's too dark to see down there completely, "
				+ "\neven with a flashlight. Continuing forward may be dangerous... ", true);
		
		/*
		 * Connecting rooms together
		 */
		courtyard.addAdjacentRoom("North", lobby);
		
		lobby.addAdjacentRoom("South", courtyard);
		lobby.addAdjacentRoom("North", study);
		lobby.addAdjacentRoom("East", closet);
		lobby.addAdjacentRoom("West", kitchen);
		
		closet.addAdjacentRoom("West", lobby);
		
		kitchen.addAdjacentRoom("South", garage);
		kitchen.addAdjacentRoom("North", backyard);
		kitchen.addAdjacentRoom("East", lobby);
		
		garage.addAdjacentRoom("North", kitchen);
		
		backyard.addAdjacentRoom("South", kitchen);
		
		study.addAdjacentRoom("South", lobby);
		study.addAdjacentRoom("North", secret);
		study.addAdjacentRoom("East", bedroom);
		
		bedroom.addAdjacentRoom("West", study);
		
		secret.addAdjacentRoom("South", study);
		
		/*
		 * Creating interactables
		 */
		Interactable key = new Interactable("Key", "A key to unlock the study", "Its covered in dust and is slightly rusted. ", true);
		key.setInteractDescription("The " + key.getName() + " is just big enough to fit in the palm of my hand, yet it feels so heavy...");
		key.setUseDescription("You insert the " + key.getName() + " into the door. The door clicks open, releasing a rush of air.");
		Interactable secretKey = new Interactable("Finger", "I've never seen anything like it", "The light from the ceiling makes it pretty noticable.", true);
		secretKey.setInteractDescription("The " + secretKey.getName() + " comes out of the rubble in one piece. Pretty sturdy.");
		secretKey.setUseDescription("You insert the " + secretKey.getName() + " into the stone door... There's a brief silence, followed by a loud snap.");
		
		/*
		 * Placing interactables
		 */
		closet.addInteractable(key);
		garage.addInteractable(secretKey);
		
		/*
		 * Adding unlock conditions for locked rooms
		 */
		study.addUnlockCondition(key);
		secret.addUnlockCondition(secretKey);
		
		/*
		 * Adding rooms to List
		 */
		rooms.add(courtyard);
		rooms.add(lobby);
		rooms.add(kitchen);
		rooms.add(garage);
		rooms.add(backyard);
		rooms.add(closet);
		rooms.add(study);
		rooms.add(bedroom);
		rooms.add(secret);
	}
}
