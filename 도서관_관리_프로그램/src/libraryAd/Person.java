package libraryAd;

import java.util.*;

public class Person {
	private String ID;
	private String name;
	private String passward;
	
	public Person(String ID, String name, String passward) {
		this.ID = ID;
		this.name = name;
		this.passward = passward;
	}
	
	public String getID() { return ID; }
	public String getName() { return name; }
	public String getPassward() { return passward; }
	
	public void setID(String id) { this.ID = ID; }
	public void setName(String name) { this.name = name; }
	public void setPassward(String passward) { this.passward = passward; }
}
