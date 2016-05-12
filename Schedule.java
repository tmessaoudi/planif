package fr.tl.ilog.sched;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class Schedule {
	
	protected LinkedList<TimeSlot> timeslots;
	protected int length;
	protected int malus;
	protected String name;
	

	public Schedule(int length, String name){
		this.length = length;
		timeslots = new LinkedList<TimeSlot>();
		this.name =name;
		initialize();
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPoints() {
		return malus;
	}

	public void increasePoints(int mal){
			this.malus+=mal;
	}

	public void setPoints(int points) {
		this.malus = points;
	}



	public LinkedList<TimeSlot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(LinkedList<TimeSlot> timeslots) {
		this.timeslots = timeslots;
	}
	
	public void addTimeslot(TimeSlot timeslot) {
		this.timeslots.push(timeslot);
	}
	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	
	public void initialize(){
		for(int i = 0; i < length; i++){
			Calendar c1 = Calendar.getInstance();
			c1.set(2016, 4, 11+i, 8, 30);
			Date debut = c1.getTime();
			c1.set(2016, 4, 11+i, 10, 00);
			Date fin = c1.getTime();
			timeslots.add(new TimeSlot(debut, fin));
			c1.set(2016, 4, 11+i, 10, 15);
			debut = c1.getTime();
			c1.set(2016, 4, 11+i, 11, 45);
			fin = c1.getTime();
			timeslots.add(new TimeSlot(debut, fin));
			c1.set(2016, 4, 11+i, 13, 00);
			debut = c1.getTime();
			c1.set(2016, 4, 11+i, 14, 30);
			fin = c1.getTime();
			timeslots.add(new TimeSlot(debut, fin));
			c1.set(2016, 4, 11+i, 14, 45);
			debut = c1.getTime();
			c1.set(2016, 4, 11+i, 16, 15);
			fin = c1.getTime();
			timeslots.add(new TimeSlot(debut, fin));
			c1.set(2016, 4, 11+i, 16, 30);
			debut = c1.getTime();
			c1.set(2016, 4, 11+i, 18, 00);
			fin = c1.getTime();
			timeslots.add(new TimeSlot(debut, fin));
		}
	}
	
	public void displaySchedule(){
		ListIterator<TimeSlot> iterator = timeslots.listIterator(0);
		while(iterator.hasNext()){
			TimeSlot currentSlot = iterator.next();
			if(currentSlot.getActivity()!=null)
			System.out.println(currentSlot.displayDay()+"  " + currentSlot.displayCreneau() + " : " + currentSlot.getActivity().getName());
			else
			System.out.println(currentSlot.displayCreneau() + " : " + "aucune matière");
		}
	}
	
	public String displayTimeslot(int index){
		String start ="Pas de cours";
		if(index <= timeslots.size()-1){
		TimeSlot ts = timeslots.get(index);
		start = ts.displayCreneau();
		}else {
			start= "Pas de créneau";
		}
		return start;
		
	}
	
	public String displayTSDay(int index){
		String start ="Pas de cours";
		if(index <= timeslots.size()-1){
		TimeSlot ts = timeslots.get(index*5);
		System.out.println();
		start = ts.displayDay();
		}else {
			
		}
		return start;
		
	}
	
}
