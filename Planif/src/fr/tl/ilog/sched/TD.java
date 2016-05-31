package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe d'activité de type "Travaux Dirigés".
 */
public class TD extends Activity {

	/**
	 * @param name
	 * 		Nom du TD.
	 * @param children
	 * 		Liste des activités qui ont ce TD pour pré-requis.
	 */
	public TD(String name, LinkedList<Activity> children) {
		super(name, children);
		rooms = null;
	}
}
