package fr.tl.ilog.sched;

import java.util.LinkedList;

/**
 * Classe d'activité de type "Travaux Personnels".
 */
public class TP extends Activity {

	/**
	 * @param name
	 * 		Nom du TP.
	 * @param children
	 * 		Liste des activités qui ont ce TP pour pré-requis.
	 */
	public TP(String name, LinkedList<Activity> children) {
		super(name, children);
		rooms = null;
	}
}
