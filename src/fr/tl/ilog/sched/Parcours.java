package fr.tl.ilog.sched;

import java.util.LinkedList;



public class Parcours {

	protected LinkedList<Activity> traversal;
	


	protected LinkedList<Activity> possibilities;
	protected LinkedList<Schedule> edts;
	protected LinkedList<LinkedList<Activity>> list_edt;
	protected LinkedList<Activity> activities;



	protected int num_name;
	
	public Parcours(LinkedList<Activity> activities){
		this.activities = activities;
		edts = new LinkedList<Schedule>();
		list_edt = new LinkedList<LinkedList<Activity>>();
		traversal = new LinkedList<Activity>();
		possibilities = new LinkedList<Activity>();
		possibilities.add(this.firstElt(activities));
		num_name = 1;
	}
	
	public LinkedList<Activity> getTraversal() {
		return traversal;
	}

	public void setTraversal(LinkedList<Activity> traversal) {
		this.traversal = traversal;
	}

	public LinkedList<Activity> getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(LinkedList<Activity> possibilities) {
		this.possibilities = possibilities;
	}

	public LinkedList<Schedule> getEdts() {
		return edts;
	}

	public void setEdts(LinkedList<Schedule> edts) {
		this.edts = edts;
	}

	public LinkedList<LinkedList<Activity>> getList_edt() {
		return list_edt;
	}

	public void setList_edt(LinkedList<LinkedList<Activity>> list_edt) {
		this.list_edt = list_edt;
	}

	public LinkedList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(LinkedList<Activity> activities) {
		this.activities = activities;
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
			Schedule schedtmp = intoSched(tmp, "Schedule n°" + num_name);
			schedtmp.evaluate();
			edts.add(schedtmp);
			list_edt.add(tmp);
			num_name++;
			
			
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
	
	
	public Schedule  intoSched(LinkedList<Activity> edts_list, String name){;
		Schedule sched = new Schedule(5, name);
		for (int i = 0; i < edts_list.size() ; i++) {
			Activity act = edts_list.get(i);
			sched.getTimeslots().get(i).setActivity(act);
		}
		return sched;
	}
}




