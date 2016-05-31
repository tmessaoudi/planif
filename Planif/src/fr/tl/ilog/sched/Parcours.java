package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe pour la génération des différents emplois du temps possibles.
 */
public class Parcours {

	protected LinkedList<Activity> traversal;
	protected LinkedList<Activity> possibilities;
	protected LinkedList<Schedule> edts;
	protected LinkedList<Activity> activities;
	protected int num_name;

	/**
	 * @param activities
	 * 		Liste des activités à trier.
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
	 * @return Liste des activités qui peuvent être ajoutées dans l'emploi du temps.
	 * 
	 */
	public LinkedList<Activity> getPossibilities() {
		return possibilities;
	}

	/**
	 * @param possibilities
	 * 		Liste des activités qui peuvent être ajoutées dans l'emploi du temps.
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
	 * @return Liste des activités à trier.
	 */
	public LinkedList<Activity> getActivities() {
		return activities;
	}

	/**
	 * Modifie la liste des activités à trier.
	 * 
	 * @param activities
	 * 		Nouvelle liste d'activités à trier.
	 */
	public void setActivities(LinkedList<Activity> activities) {
		this.activities = activities;
	}

	/**
	 * Renvoie la première activité à ajouter dans l'emploi du temps.
	 * 
	 * @param activities
	 * 		Liste des activités à trier.
	 * @return L'activité qui n'a aucun pré-requis.
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
	 * Effectue tous les chemins possibles dans la liste des activités à trier en respectant leurs pré-requis.
	 * 
	 * @param i
	 * 		Numéro de la possibilité à explorer.
	 * @param possibilities
	 * 		Liste des activités possibles à ajouter dans l'emploi du temps.
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
		// Quand il n'y a plus de possibilités, c'est-à-dire que toutes les activités ont été ajoutées.
		if (possibilities.size()==0){
			for (int j = 0; j < traversal.size(); j++) {
				tmp.add(traversal.get(j));
			}
			//Suppression du premier noeud, qui est juste un noeud racine vide.
			tmp.remove(0);
			//Création d'un nouvel emploi du temps à partir de la liste d'activités générée.
			Schedule schedtmp = intoSched(tmp, "Schedule n°" + num_name);
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
	 * Génère un emploi du temps à partir d'une liste d'activités.
	 * 
	 * @param edts_list
	 * 		Liste d'activités triées.
	 * @param name
	 * 		Nom de l'emploi du temps
	 * @return Un emploi du temps avec des activités attribuées à chacun de ses créneaux.
	 */
	public Schedule intoSched(LinkedList<Activity> edts_list, String name){;
	Schedule sched = new Schedule(5, name);
	for (int i = 0; i < edts_list.size() ; i++) {
		Activity act = edts_list.get(i);
		TimeSlot ts = sched.getTimeslots().get(i);
		// Si le professeur de l'activité est disponible à ce créneau
		if(act.getTeacher() == null || act.getTeacher().canTeach(ts)){
			LinkedList<Room> rooms = act.getRooms();
			if ( rooms == null){
				ts.setActivity(act);
			}
			else{
				// Si une des salles de l'activité est disponible
				for (int j = 0; j < rooms.size(); j++) {
					if(rooms.get(j).canTeach(ts)){
						ts.setActivity(act);
					}
				}
			}

		}
		else
		{
			// Si aucune des contraintes n'est respectée
			sched = null;
		}
	}
	return sched;
	}
}




