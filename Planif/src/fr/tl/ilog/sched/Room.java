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
	 * 		Liste de créneau pendant lesquels la salle n'est pas disponible.
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
	 * @return Liste des indisponibilités de la salle.
	 */
	public LinkedList<TimeSlot> getIndispo() {
		return indispo;
	}

	/**
	 * Modifie les indisponibilités de la salle.
	 * 
	 * @param indispo
	 * 		Nouvelle liste des indiponibilités.
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
	 * Vérifie si une salle est disponible pour un créneau donné.
	 * 
	 * @param timeActivity
	 * 		Créneau pendant lequel on veut vérifie si la salle est disponible.
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
