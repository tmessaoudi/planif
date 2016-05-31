package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe de salle de cours.
 */
public class Room {

	protected String name;
	protected LinkedList<TimeSlot> indispo;

	/**
	 * @param name
	 * 		Nom de la salle.
	 * @param indispo
	 * 		Liste de cr�neau pendant lesquels la salle n'est pas disponible.
	 */
	public Room(String name, LinkedList<TimeSlot> indispo) {
		this.name = name;
		this.indispo = indispo;
	}

	/**
	 * @return Nom de la salle.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Liste des indisponibilit�s de la salle.
	 */
	public LinkedList<TimeSlot> getIndispo() {
		return indispo;
	}

	/**
	 * Modifie les indisponibilit�s de la salle.
	 * 
	 * @param indispo
	 * 		Nouvelle liste des indiponibilit�s.
	 */
	public void setIndispo(LinkedList<TimeSlot> indispo) {
		this.indispo = indispo;
	}

	/**
	 * Modifie le nom de la salle.
	 * 
	 * @param name
	 * 		Nouveau nom de la salle.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * V�rifie si une salle est disponible pour un cr�neau donn�.
	 * 
	 * @param timeActivity
	 * 		Cr�neau pendant lequel on veut v�rifie si la salle est disponible.
	 * @return true
	 * 		Si la salle est diponible.
	 */
	public boolean canTeach(TimeSlot timeActivity){
		if (this.indispo == null){
			return true;
		}
		else{
			if(this.indispo.contains(timeActivity))
			{
				return false;
			}
			return true;
		}
	}
}
