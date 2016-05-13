package fr.tl.ilog.sched;

import java.util.LinkedList;
import java.util.List;

public class TP extends Activity {
	
	protected List<Room> rooms;

	public TP(String name, LinkedList<Activity> fils) {
		super(name, fils);
		rooms = null;
	}

	public List<Room> getGoodroom() {
		return rooms;
	}

	public void setGoodroom(List<Room> goodroom) {
		rooms = goodroom;
	}

}
