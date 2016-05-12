package fr.tl.ilog.sched;

import java.util.LinkedList;
import java.util.List;

public class TD extends Activity {

	public TD(String name, LinkedList<Activity> fils) {
		super(name, fils);
		// TODO Auto-generated constructor stub
	}

	
	protected List<Room> Goodroom;

	public List<Room> getGoodroom() {
		return Goodroom;
	}

	public void setGoodroom(List<Room> goodroom) {
		Goodroom = goodroom;
	}
}
