package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe d'activit� de type "Travaux Dirig�s".
 */
public class TD extends Activity {

	/**
	 * @param name
	 * 		Nom du TD.
	 * @param children
	 * 		Liste des activit�s qui ont ce TD pour pr�-requis.
	 */
	public TD(String name, LinkedList<Activity> children) {
		super(name, children);
		rooms = null;
	}
}
