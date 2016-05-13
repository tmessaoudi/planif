package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe d'activit� de type "Travaux Personnels".
 */
public class TP extends Activity {

	/**
	 * @param name
	 * 		Nom du TP.
	 * @param children
	 * 		Liste des activit�s qui ont ce TP pour pr�-requis.
	 */
	public TP(String name, LinkedList<Activity> children) {
		super(name, children);
		rooms = null;
	}
}
