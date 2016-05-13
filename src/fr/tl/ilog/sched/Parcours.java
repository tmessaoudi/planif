package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe pour la g�n�ration des diff�rents emplois du temps possibles.
 */
public class Parcours {

	protected LinkedList<Activity> traversal;
	protected LinkedList<Activity> possibilities;
	protected LinkedList<Schedule> edts;
	protected LinkedList<Activity> activities;
	protected int num_name;

	/**
	 * @param activities
	 * 		Liste des activit�s � trier.
	 */
	public Parcours(LinkedList<Activity> activities){
		this.activities = activities;
		edts = new LinkedList<Schedule>();
		traversal = new LinkedList<Activity>();
		possibilities = new LinkedList<Activity>();
		possibilities.add(this.firstElt(activities));
		num_name = 1;
	}

	/**
	 * @return Liste des activit�s qui peuvent �tre ajout�es dans l'emploi du temps.
	 * 
	 */
	public LinkedList<Activity> getPossibilities() {
		return possibilities;
	}

	/**
	 * @param possibilities
	 * 		Liste des activit�s qui peuvent �tre ajout�es dans l'emploi du temps.
	 */
	public void setPossibilities(LinkedList<Activity> possibilities) {
		this.possibilities = possibilities;
	}

	/**
	 * @return Liste des emplois du temps possibles.
	 */
	public LinkedList<Schedule> getEdts() {
		return edts;
	}

	/**
	 * Modifie la liste des emplois du temps possibles.
	 * 
	 * @param edts
	 * 		Nouvelle liste des emplois du temps possibles.
	 */
	public void setEdts(LinkedList<Schedule> edts) {
		this.edts = edts;
	}

	/**
	 * @return Liste des activit�s � trier.
	 */
	public LinkedList<Activity> getActivities() {
		return activities;
	}

	/**
	 * Modifie la liste des activit�s � trier.
	 * 
	 * @param activities
	 * 		Nouvelle liste d'activit�s � trier.
	 */
	public void setActivities(LinkedList<Activity> activities) {
		this.activities = activities;
	}

	/**
	 * Renvoie la premi�re activit� � ajouter dans l'emploi du temps.
	 * 
	 * @param activities
	 * 		Liste des activit�s � trier.
	 * @return L'activit� qui n'a aucun pr�-requis.
	 */
	public Activity firstElt(LinkedList<Activity> activities){
		for (int i = 0; i < activities.size(); i++) {
			if (!activities.get(i).hasParent(activities)){
				return activities.get(i);
			}
		}
		return null;
	}

	/**
	 * Effectue tous les chemins possibles dans la liste des activit�s � trier en respectant leurs pr�-requis.
	 * 
	 * @param i
	 * 		Num�ro de la possibilit� � explorer.
	 * @param possibilities
	 * 		Liste des activit�s possibles � ajouter dans l'emploi du temps.
	 */
	public void permutations(int i, LinkedList<Activity> possibilities){
		LinkedList<Activity> tmp = new LinkedList<Activity>();
		Activity act = possibilities.get(i);
		LinkedList<Activity> children = act.getChildren();
		traversal.add(act);
		possibilities.remove(act);
		for (int j = 0; j < children.size(); j++) {
			possibilities.add(i+j,children.get(j));
		}
		// Quand il n'y a plus de possibilit�s, c'est-�-dire que toutes les activit�s ont �t� ajout�es.
		if (possibilities.size()==0){
			for (int j = 0; j < traversal.size(); j++) {
				tmp.add(traversal.get(j));
			}
			//Suppression du premier noeud, qui est juste un noeud racine vide.
			tmp.remove(0);
			//Cr�ation d'un nouvel emploi du temps � partir de la liste d'activit�s g�n�r�e.
			Schedule schedtmp = intoSched(tmp, "Schedule n�" + num_name);
			schedtmp.evaluate();
			// Ajout du nouvel emploi du temps dans la liste des emplois du temps possibles s'il respecte les contraintes.
			if (schedtmp!=null){
				edts.add(schedtmp);
				num_name++;
			}
		}
		for (int j = 0; j < possibilities.size(); j++) {
			permutations(j, possibilities);
		}
		for (int j = 0; j < children.size(); j++) {
			possibilities.remove(i);
		}
		possibilities.add(i, act);
		traversal.remove(act);
	}


	/**
	 * 
	 * G�n�re un emploi du temps � partir d'une liste d'activit�s.
	 * 
	 * @param edts_list
	 * 		Liste d'activit�s tri�es.
	 * @param name
	 * 		Nom de l'emploi du temps
	 * @return Un emploi du temps avec des activit�s attribu�es � chacun de ses cr�neaux.
	 */
	public Schedule intoSched(LinkedList<Activity> edts_list, String name){;
	Schedule sched = new Schedule(5, name);
	for (int i = 0; i < edts_list.size() ; i++) {
		Activity act = edts_list.get(i);
		TimeSlot ts = sched.getTimeslots().get(i);
		// Si le professeur de l'activit� est disponible � ce cr�neau
		if(act.getTeacher() == null || act.getTeacher().canTeach(ts)){
			LinkedList<Room> rooms = act.getRooms();
			if ( rooms == null){
				ts.setActivity(act);
			}
			else{
				// Si une des salles de l'activit� est disponible
				for (int j = 0; j < rooms.size(); j++) {
					if(rooms.get(j).canTeach(ts)){
						ts.setActivity(act);
					}
				}
			}

		}
		else
		{
			// Si aucune des contraintes n'est respect�e
			sched = null;
		}
	}
	return sched;
	}
}




