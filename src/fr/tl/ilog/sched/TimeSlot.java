package fr.tl.ilog.sched;
import java.util.Date;

/**
 * Classe de cr�neau de l'emploi du temps.
 */
public class TimeSlot {

	protected Date start;
	protected Date end;
	protected Activity activity;
		
	/**
	 * @param debut
	 * 		Date de d�but du cr�neau.
	 * @param fin
	 * 		Date de fin du cr�neau.
	 */
	public TimeSlot(Date debut, Date fin){
		this.start = debut;
		this.end = fin;
		activity = null;
	}
	
	/**
	 * @return Date de d�but du cr�neau
	 */
	public Date getDebut() {
		return start;
	}

	/**
	 * Modifie la date de d�but d'un cr�neau.
	 * 
	 * @param debut
	 * 		Nouvelle date de d�but.
	 */
	public void setDebut(Date debut) {
		this.start = debut;
	}

	/**
	 * @return Date de fin d'un cr�neau.
	 */
	public Date getFin() {
		return end;
	}

	/**
	 * Modifie la date de fin d'un cr�neau.
	 * 
	 * @param fin
	 * 		Nouvelle date de fin.
	 */
	public void setFin(Date fin) {
		this.end = fin;
	}

	/**
	 * @return Activit� associ�e au cr�neau.
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Modifie l'activit� associ�e au cr�neau.
	 * 
	 * @param activity
	 * 		Nouvelle activit�.
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	/**
	 * Renvoie le texte correspondant aux dates de d�but et de fin du cr�neau.
	 * 
	 * @return Texte des dates du cr�neau.
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
	 * Renvoie le jour du cr�neau.
	 * 
	 * @return Texte du jour
	 */
	public String displayDay(){
		String day = String.valueOf(start).split(" ")[2] + " " + String.valueOf(start).split(" ")[1];
		return  day;	
	}
}
