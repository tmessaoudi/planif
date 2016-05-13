package fr.tl.ilog.sched;
import java.util.Date;

public class TimeSlot {

	protected Date start;
	protected Date end;
	protected Activity activity;
		
	public TimeSlot(Date debut, Date fin){
		this.start = debut;
		this.end = fin;
		activity = null;
	}
	
	public Date getDebut() {
		return start;
	}

	public void setDebut(Date debut) {
		this.start = debut;
	}

	public Date getFin() {
		return end;
	}

	public void setFin(Date fin) {
		this.end = fin;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public String displayCreneau(){
		String debut_hour = String.valueOf(start).split(" ")[3];
		String fin_hour = String.valueOf(end).split(" ")[3];
		if(this.activity != null){
		return  debut_hour  + " ->" + fin_hour + "\n" + this.activity.getName();
		}else{
			return  debut_hour  + " ->" + fin_hour + "\n Pas de cours";
		}
	}
	
	public String displayDay(){
		String day = String.valueOf(start).split(" ")[2] + " " + String.valueOf(start).split(" ")[1];
	
		return  day;
		
	}
}
