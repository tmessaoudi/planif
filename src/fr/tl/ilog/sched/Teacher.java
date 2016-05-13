package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe de professeur.
 */
public class Teacher {

	protected String name;
	protected LinkedList<TimeSlot> indispo;

	/**
	 * @param name
	 * 		Nom du professeur.
	 * @param indispo
	 * 		Liste de créneau pendant lesquels le professeur n'est pas disponible.
	 */
	public Teacher(String name, LinkedList<TimeSlot> indispo){
		this.name = name;
		this.indispo = indispo;
	}

	/**
	 * @return Nom du professeur.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom du professeur.
	 * 
	 * @param name
	 * 		Nouveau nom du professeur.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Listes des indisponibilités du professeur.
	 */
	public LinkedList<TimeSlot> getIndispo() {
		return indispo;
	}

	/**
	 * Modifie la liste des indisponibilités du professeur.
	 * 
	 * @param indispo
	 * 		Nouvelle liste des indisponibilités.
	 */
	public void setIndispo(LinkedList<TimeSlot> indispo) {
		this.indispo = indispo;
	}

	/**
	 * Vérifie si un professeur est disponible pour un créneau donné.
	 * 
	 * @param timeActivity
	 * 		Créneau pendant lequel on veut vérifier si le professeur est disponible.
	 * @return true
	 * 		Si le professeur est disponible
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
