package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe d'activité de type "Cours".
 */
public class Cours extends Activity {

	/**
	 * @param name
	 * 		Nom du cours.
	 * @param children
	 * 		Liste des activités qui ont ce cours pour pré-requis.
	 */
	public Cours(String name, LinkedList<Activity> children){
		super(name, children);
		rooms = null;
	}
}
