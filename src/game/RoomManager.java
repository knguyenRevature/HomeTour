package game;

import java.util.ArrayList;
import java.util.List;
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
		backyard.addAdjacentRoom("South", kitchen);
		bedroom.addAdjacentRoom("West", study);
		courtyard.addAdjacentRoom("North", lobby);
		closet.addAdjacentRoom("West", lobby);
		garage.addAdjacentRoom("North", kitchen);
		kitchen.addAdjacentRoom("South", garage);
		kitchen.addAdjacentRoom("North", backyard);
		kitchen.addAdjacentRoom("East", lobby);
		lobby.addAdjacentRoom("South", courtyard);
		lobby.addAdjacentRoom("North", study);
		lobby.addAdjacentRoom("East", closet);
		lobby.addAdjacentRoom("West", kitchen);
		secret.addAdjacentRoom("South", study);
		study.addAdjacentRoom("South", lobby);
		study.addAdjacentRoom("North", secret);
		study.addAdjacentRoom("East", bedroom);
		
		/*
		 * Creating interactables; Can be picked up
		 */
		Interactable key = new Interactable("Key", "A key to unlock the study", "Its covered in dust and is slightly rusted. ", true);
		key.setInteractDescription("The " + key.getColorName() + " is just big enough to fit in the palm of my hand, yet it feels so heavy...");
		key.setUseDescription("You insert the " + key.getColorName() + " into the door. The door clicks open, releasing a rush of air.");
		Interactable secretKey = new Interactable("Finger", "I've never seen anything like it", "The light from the ceiling makes it pretty noticable.", true);
		secretKey.setInteractDescription("The " + secretKey.getColorName() + " comes out of the rubble in one piece. Pretty sturdy.");
		secretKey.setUseDescription("You insert the " + secretKey.getColorName() + " into the stone door... There's a brief silence, followed by a loud snap.");
		Interactable knife = new Interactable("Knife", "An old rusty kitchen knife", "It's stuck into the counter. Probably took a lot of force to do that...", true);
		knife.setInteractDescription("The " + knife.getColorName() + " chips even more as I pull it out. Won't be much use in this state.");
		
		/*
		 * Creating interactables; Cannot be picked up
		 */
		Interactable fridge = new Interactable("Fridge", "", "The door seems to chained up pretty secure. There's no way I'm getting that open.", false);
		fridge.setInteractDescription("It's no good, the door isn't going to budge.");
		Interactable car = new Interactable("Car", "", "All the wheels are slashed, and the windshield is in pieces. Who would do such a thing?", false);
		car.setInteractDescription("What an antique.");
		Interactable toolbox = new Interactable("Toolbox", "", "It looks like it's open. Could there be anything useful inside?", false);
		toolbox.setInteractDescription("Not a single tool in sight. Perhaps someone beat me to it?");
		Interactable telephone = new Interactable("Telephone", "", "Haven't seen a model like this in years.", false);
		telephone.setInteractDescription("The line is missing. Was it cut deliberately, or perhaps something else..?");
		Interactable mirror = new Interactable("Mirror", "", "It's hanging crooked above a dresser.", false);
		mirror.setInteractDescription("I see only myself in the mirror, yet I can't help but feel like something else is here too.");
		Interactable hole = new Interactable("Hole", "", "It looks like it's wide enough to fit a person...", false);
		hole.setInteractDescription("Yeah right, like I'm going down there.");
		
		/*
		 * Placing baggable interactables
		 */
		closet.addInteractable(key);
		garage.addInteractable(secretKey);
		kitchen.addInteractable(knife);
		
		/*
		 * Placing stationary interactables
		 */
		bedroom.addInteractable(mirror);
		garage.addInteractable(car);
		garage.addInteractable(toolbox);
		kitchen.addInteractable(fridge);
		lobby.addInteractable(telephone);
		secret.addInteractable(hole);
		
		/*
		 * Adding unlock conditions for locked rooms
		 */
		secret.addUnlockCondition(secretKey);
		study.addUnlockCondition(key);
		
		/*
		 * Adding rooms to List
		 */
		rooms.add(backyard);
		rooms.add(bedroom);
		rooms.add(closet);
		rooms.add(courtyard);
		rooms.add(garage);
		rooms.add(kitchen);
		rooms.add(lobby);
		rooms.add(secret);
		rooms.add(study);
	}
}
