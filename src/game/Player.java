package game;

import java.util.ArrayList;
import java.util.List;
import fixtures.Interactable;
import fixtures.Room;

public class Player {
	
	public List<Interactable> bag = new ArrayList<Interactable>();
	
	private Room currentRoom;
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	//Removes interactable from room, adds to player bag
	public void addToBag(Interactable interactable) {
		if (currentRoom.interactables.contains(interactable)) {
			currentRoom.removeInteractable(interactable);
			bag.add(interactable);
		}
	}
	
	//Removes interactable from player bag
	public void removeFromBag(Interactable interactable) {
		if (bag.contains(interactable)) {
			bag.remove(interactable);
		}
	}
	
	//Displays the content of player's bag
	public void bagStatus() {
		if (bag.isEmpty()) {
			System.out.println("\nYour bag is empty.");
		} else {
			System.out.println("\nYour bag contains: ");
			int index = 1;
			for (Interactable interactable : bag) {
				System.out.println(index + ". " + interactable.getColorName() + "; " + interactable.getShortDescription());
				index++;
			}
		}
	}
}
