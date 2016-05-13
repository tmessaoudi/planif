package fr.tl.ilog.sched;

import java.util.Date;

public class Professeur {
	
	protected String name;
	protected Date indisp_start;
	protected Date indisp_end;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIndisp_start() {
		return indisp_start;
	}
	public void setIndisp_start(Date indisp_start) {
		this.indisp_start = indisp_start;
	}
	public Date getIndisp_end() {
		return indisp_end;
	}
	public void setIndisp_end(Date indisp_end) {
		this.indisp_end = indisp_end;
	}
	
	public boolean canTeach(TimeSlot t){
		if(t.getDebut().after(indisp_start) && t.getFin().before(indisp_end)){
			return false;
		}
		else{
			return true;
		}
	}
}
