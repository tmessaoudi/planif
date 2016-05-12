/**
 * 
 */
package fr.tl.ilog.sched;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tmessaoudi
 *
 */
public class Cours extends Activity {

	public Cours(String name, LinkedList<Activity> fils){
		super(name, fils);
	}
	
	protected List<Room> Goodroom;

	public List<Room> getGoodroom() {
		return Goodroom;
	}

	public void setGoodroom(List<Room> goodroom) {
		Goodroom = goodroom;
	}
	
	
}
