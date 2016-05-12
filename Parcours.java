package fr.tl.ilog.sched;

import java.util.LinkedList;

public class Parcours {

	protected LinkedList<Activity> traversal;
	protected LinkedList<Activity> possibilities;
	//protected LinkedList<Schedule> edts;
	protected LinkedList<LinkedList<Activity>> list_edt;
	protected LinkedList<Activity> activities;
	
	public Parcours(LinkedList<Activity> activities){
		this.activities = activities;
		list_edt = new LinkedList<LinkedList<Activity>>();
		traversal = new LinkedList<Activity>();
		possibilities = new LinkedList<Activity>();
		possibilities.add(this.firstElt(activities));
	}
	
	public Activity firstElt(LinkedList<Activity> activities){
		for (int i = 0; i < activities.size(); i++) {
			if (!activities.get(i).hasParent(activities)){
				return activities.get(i);
			}
		}
		return null;
	}

	public void permutations(int i, LinkedList<Activity> possibilities){
		LinkedList<Activity> tmp = new LinkedList<Activity>();
		Activity act = possibilities.get(i);
		LinkedList<Activity> children = act.getChildren();
		traversal.add(act);
		possibilities.remove(act);
		for (int j = 0; j < children.size(); j++) {
			possibilities.add(i+j,children.get(j));
		}
		if (possibilities.size()==0){
			for (int j = 0; j < traversal.size(); j++) {
				tmp.add(traversal.get(j));
				System.out.println(traversal.get(j).getName());
			}
			System.out.println("------------");
			list_edt.add(tmp);
		}
		for (int j = 0; j < possibilities.size(); j++) {
			permutations(j, possibilities);
		}
		for (int j = 0; j < children.size(); j++) {
			possibilities.remove(i);
		}
		possibilities.add(i, act);
		traversal.remove(act);
	}
}


