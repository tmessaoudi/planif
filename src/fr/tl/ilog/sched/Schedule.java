package fr.tl.ilog.sched;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Classe d'emploi du temps
 */
public class Schedule {

	protected LinkedList<TimeSlot> timeslots;
	protected int length;
	protected int malus;
	protected String name;


	/**
	 * @param length
	 * 		Nombre de jours de l'emploi du temps.
	 * @param name
	 * 		Nom de l'emploi du temps.
	 */
	public Schedule(int length, String name){
		this.length = length;
		this.malus = 0;
		timeslots = new LinkedList<TimeSlot>();
		this.name = name;
		initialize();
	}

	/**
	 * @return Nom de l'emploi du temps.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de l'emploi du temps.
	 * 
	 * @param name
	 * 		Nouveau nom de l'emploi du temps.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Résultat de l'évalution.
	 */
	public int getPoints() {
		return malus;
	}

	/**
	 * Augmente le résultat de l'évaluation.
	 * 
	 * @param malus
	 * 		Nombre de points à ajouter au résulat de l'évaluation.
	 */
	public void increasePoints(int malus){
		this.malus+=malus;
	}

	/**
	 * Modifie le résultat de l'évaluation.
	 * 
	 * @param points
	 * 		Nombre de points à assigner au résultat de l'évaluation.
	 */
	public void setPoints(int points) {
		this.malus = points;
	}

	/**
	 * Evalue la qualité de l'emploi du temps.
	 * Plus le chiffre est faible, meilleur est l'emploi du temps.
	 * 
	 */
	public void evaluate(){
		LinkedList<Activity> activities = new LinkedList<Activity>();
		for (int i = 0; i < timeslots.size(); i++) {
			if (timeslots.get(i).getActivity()!=null){
				activities.add(timeslots.get(i).getActivity());
			}
		}
		for (int i = 0; i < activities.size(); i++) {
			Activity activity = activities.get(i);
			Date start = timeslots.get(i).getDebut();
			String hour_start = String.valueOf(start).split(" ")[3];
			// Si l'activité est trop éloignée de son parent, le résultat de l'évaluation est augmentée de 1.
			if(activity.hasParent(activities)){
				if (i > 0 && !(activity.isChild(activities.get(i-1)))){
					this.increasePoints(1);
				}
			}
			// Si un cours a lieu l'après-midi, le résultat est augmenté de 1.
			if (activity.getClass() == Cours.class){
				if(hour_start.startsWith("13") || hour_start.startsWith("14") || hour_start.startsWith("16")){
					this.increasePoints(1);
				}
			}

		}
	}

	/**
	 * @return Liste des créneaux de l'emploi du temps.
	 */
	public LinkedList<TimeSlot> getTimeslots() {
		return timeslots;
	}

	/**
	 * Modifie la liste des créneaux de l'emploi du temps.
	 * 
	 * @param timeslots
	 * 		Nouvelle liste de créneaux de l'emploi du temps
	 */
	public void setTimeslots(LinkedList<TimeSlot> timeslots) {
		this.timeslots = timeslots;
	}

	/**
	 * Ajoute un nouveau créneau dans ceux de l'emploi du temps.
	 * 
	 * @param timeslot
	 * 		Créneau à ajouter
	 */
	public void addTimeslot(TimeSlot timeslot) {
		this.timeslots.push(timeslot);
	}

	/**
	 * @return Nombre de jours de l'emploi du temps.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Modifie le nombre de jours de l'emploi du temps
	 * 
	 * @param length
	 * 		Nouveau nombre de jours de l'emploi du temps
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Initialise les différents créneaux de l'emploi du temps.
	 */
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

	/**
	 * Affiche l'emploi du temps
	 */
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

	/**
	 * Affiche les créneaux dans l'emploi du temps.
	 * 
	 * @param index
	 * 		Rang.
	 * @return Texte à mettre dans les cases de créneaux.
	 */
	public String displayTimeslot(int index){
		String start;
		if(index <= timeslots.size()-1){
			TimeSlot ts = timeslots.get(index);
			start = ts.displayCreneau();
		}else {
			start= "Pas de créneau";
		}
		return start;

	}

	/**
	 * Affiche les jours de l'emploi du temps.
	 * 
	 * @param index
	 * 		Rang.
	 * @return Texte à mettre dans les cases des jours.
	 */
	public String displayTSDay(int index){
		String start ="Pas de cours";
		if(index <= (timeslots.size()-1)/5){
			TimeSlot ts = timeslots.get(index*5);
			System.out.println();
			start = ts.displayDay();
		}else {

		}
		return start;
	}

}
