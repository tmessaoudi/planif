package fr.tl.ilog.sched;
import java.util.LinkedList;

public class Activity {

	protected LinkedList<Activity> fils;
	protected String name;
	protected boolean isVisited;

	public Activity(String name, LinkedList<Activity> fils){
		this.name = name;
		this.fils = fils;
		isVisited = false;
	}

	public LinkedList<Activity> getChildren() {
		return fils;
	}
	
	public Activity getSon(int index){
		return fils.get(index);
	}

	public void setSons(LinkedList<Activity> fils) {
		this.fils = fils;
	}
	
	public void setSon(int index, Activity newFils){
		this.fils.set(index, newFils);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addSon(Activity newFils){
		this.fils.add(newFils);
	}
	
	public boolean isChild(Activity parent){
		if(parent.getChildren().contains(this)){
			return true;
		}
		return false;
	}
	
	public boolean hasParent(LinkedList<Activity> activities){
		for (int i = 0; i < activities.size(); i++) {
			if(this.isChild(activities.get(i)))
			{
				return true;
			}
		}
		return false;
	}
	
	public void visit(){
		this.isVisited = true;
	}
	
	public boolean isVisited(){
		return this.isVisited;
	}

}
