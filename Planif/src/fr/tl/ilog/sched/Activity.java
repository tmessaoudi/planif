package fr.tl.ilog.sched;
import java.util.LinkedList;

/**
 * Classe d'activit� p�dagogique.
 */
public class Activity {

	protected LinkedList<Activity> children;
	protected String name;
	protected Teacher teacher;
	protected LinkedList<Room> rooms;


	/**
	 * @param name
	 * 		Nom de l'activit�
	 * @param children
	 * 		Liste des enfants de l'activit�. Ce sont les activit�s qui l'ont pour pr�-requis.
	 */
	public Activity(String name, LinkedList<Activity> children){
		this.name = name;
		this.children = children;
	}

	/**
	 * Renvoie la liste des activit�s enfants.
	 * 
	 * @return Liste des activit�s enfants.
	 */
	public LinkedList<Activity> getChildren() {
		return children;
	}

	/**
	 * Renvoie l'activit� enfant pr�sente � un certain rang.
	 * 
	 * @param index
	 * 		Rang.
	 * @return Activit� au rang index
	 */
	public Activity getChild(int index){
		return children.get(index);
	}

	/**
	 * Assigne � une activit�, une liste d'activit�s enfants.
	 * @param children
	 * 		Nouvelle liste d'activit�s enfants.
	 * 
	 */
	public void setChildren(LinkedList<Activity> children) {
		this.children = children;
	}

	/**
	 * Remplace l'activit� � un certain rang par une nouvelle activit� dans la liste des enfants.
	 * 
	 * @param index
	 * 		Rang.
	 * @param newChild
	 * 		Nouvelle activit� de remplacement.
	 */
	public void setChild(int index, Activity newChild){
		this.children.set(index, newChild);
	}

	/**
	 * Renvoie le nom de l'activit�.
	 * 
	 * @return Nom de l'activit�.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de l'activit�.
	 * 
	 * @param name
	 * 		Nouveau nom de l'activit�.
	 * 		
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ajoute une nouvelle activit� dans la liste des activit�s enfants.
	 * 
	 * @param newChild
	 * 		Nouvelle activit� � ajouter.
	 */
	public void addChild(Activity newChild){
		this.children.add(newChild);
	}

	/**
	 * V�rifie si une activit� a pour pr�-requis l'activit� parent.
	 * 
	 * @param parent
	 * 		Activit� pr�-requis.
	 * @return true
	 * 		Si l'activit� a bien pour pr�-requis l'activit� parent.
	 */
	public boolean isChild(Activity parent){
		if(parent.getChildren().contains(this)){
			return true;
		}
		return false;
	}

	/**
	 * V�rifie si une activit� a un pr�-requis dans une liste d'activit�s.
	 * 
	 * @param activities
	 * 		Liste des activit�s.
	 * @return false 
	 * 		Si l'activit� n'a aucun pr�-requis dans la liste.
	 */
	public boolean hasParent(LinkedList<Activity> activities){
		for (int i = 0; i < activities.size(); i++) {
			if(this.isChild(activities.get(i)))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return Professeur charg� de l'activit�
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * Modifie le professeur charg� de l'activit�
	 * 
	 * @param teacher
	 * 		Nouveau professeur.
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	/**
	 * @return Liste des salles dans lesquelles l'activit� peut avoir lieu.
	 */
	public LinkedList<Room> getRooms() {
		return rooms;
	}

	/**
	 * Modifie la liste des salles dans lequel l'activit� peut avoir lieu.
	 * 
	 * @param rooms
	 * 		Nouvel liste de salles
	 */
	public void setRooms(LinkedList<Room> rooms) {
		this.rooms = rooms;
	}

}
