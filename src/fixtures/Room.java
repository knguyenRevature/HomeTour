package fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Room extends Fixture{
	
	public Map<String, Room> adjacentRooms = new TreeMap<String, Room>();
	public List<Interactable> interactables = new ArrayList<Interactable>();
	public boolean isLocked;
	public Interactable unlockCondition;

	public Room(String name, String shortDescription, String longDescription, boolean isLocked) {
		super(name, shortDescription, longDescription);
		this.isLocked = isLocked;
	}
	
	public void addAdjacentRoom(String direction, Room room) {
		adjacentRooms.put(direction, room);
	}
	
	public void addInteractable(Interactable interactable) {
		interactables.add(interactable);
	}
	
	public void removeInteractable(Interactable interactable) {
		interactables.remove(interactable);
	}
	
	public void addUnlockCondition(Interactable unlockCondition) {
		this.unlockCondition = unlockCondition;
	}
	
	public boolean checkUnlockCondition(Interactable interactable) {
		return (unlockCondition.equals(interactable));
	}

	public void displayLongDescription() {
		System.out.println(getLongDescription());
		for (Interactable interactable : interactables) {
			System.out.println("A " + interactable.getName() + " is visible. " + interactable.getLongDescription());
		}
		System.out.println();
	}
}
