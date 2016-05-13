package fr.tl.ilog.sched;
import java.util.Date;

/**
 * Classe de créneau de l'emploi du temps.
 */
public class TimeSlot {

	protected Date start;
	protected Date end;
	protected Activity activity;
		
	/**
	 * @param debut
	 * 		Date de début du créneau.
	 * @param fin
	 * 		Date de fin du créneau.
	 */
	public TimeSlot(Date debut, Date fin){
		this.start = debut;
		this.end = fin;
		activity = null;
	}
	
	/**
	 * @return Date de début du créneau
	 */
	public Date getDebut() {
		return start;
	}

	/**
	 * Modifie la date de début d'un créneau.
	 * 
	 * @param debut
	 * 		Nouvelle date de début.
	 */
	public void setDebut(Date debut) {
		this.start = debut;
	}

	/**
	 * @return Date de fin d'un créneau.
	 */
	public Date getFin() {
		return end;
	}

	/**
	 * Modifie la date de fin d'un créneau.
	 * 
	 * @param fin
	 * 		Nouvelle date de fin.
	 */
	public void setFin(Date fin) {
		this.end = fin;
	}

	/**
	 * @return Activité associée au créneau.
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Modifie l'activité associée au créneau.
	 * 
	 * @param activity
	 * 		Nouvelle activité.
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	/**
	 * Renvoie le texte correspondant aux dates de début et de fin du créneau.
	 * 
	 * @return Texte des dates du créneau.
	 */
	public String displayCreneau(){
		String debut_hour = String.valueOf(start).split(" ")[3];
		String fin_hour = String.valueOf(end).split(" ")[3];
		if(this.activity != null){
		return  debut_hour  + " ->" + fin_hour + "\n" + this.activity.getName();
		}else{
			return  debut_hour  + " ->" + fin_hour + "\n Pas de cours";
		}
	}
	
	/**
	 * Renvoie le jour du créneau.
	 * 
	 * @return Texte du jour
	 */
	public String displayDay(){
		String day = String.valueOf(start).split(" ")[2] + " " + String.valueOf(start).split(" ")[1];
		return  day;	
	}
}
