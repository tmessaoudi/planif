package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe d'activit� de type "Cours".
 */
public class Cours extends Activity {

	/**
	 * @param name
	 * 		Nom du cours.
	 * @param children
	 * 		Liste des activit�s qui ont ce cours pour pr�-requis.
	 */
	public Cours(String name, LinkedList<Activity> children){
		super(name, children);
		rooms = null;
	}
}
