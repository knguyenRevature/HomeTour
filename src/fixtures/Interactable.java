package fixtures;

public class Interactable extends Fixture {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";

	public String interactDescription;
	public String useDescription;
	public boolean isBaggable;
	
	public Interactable(String name, String shortDescription, String longDescription, boolean isBaggable) {
		super(name, shortDescription, longDescription);
		this.isBaggable = isBaggable;
	}
	
	public void setInteractDescription(String interactDescription) {
		this.interactDescription = interactDescription;
	}
	
	public void setUseDescription(String useDescription) {
		this.useDescription = useDescription;
	}
	
	public String getColorName() {
		return ANSI_GREEN + this.getName() + ANSI_RESET;
	}
}
