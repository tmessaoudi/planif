package fr.tl.ilog.sched;
import java.util.LinkedList;

/**
 * Classe d'activité pédagogique.
 */
public class Activity {

	protected LinkedList<Activity> children;
	protected String name;
	protected Teacher teacher;
	protected LinkedList<Room> rooms;


	/**
	 * @param name
	 * 		Nom de l'activité
	 * @param children
	 * 		Liste des enfants de l'activité. Ce sont les activités qui l'ont pour pré-requis.
	 */
	public Activity(String name, LinkedList<Activity> children){
		this.name = name;
		this.children = children;
	}

	/**
	 * Renvoie la liste des activités enfants.
	 * 
	 * @return Liste des activités enfants.
	 */
	public LinkedList<Activity> getChildren() {
		return children;
	}

	/**
	 * Renvoie l'activité enfant présente à un certain rang.
	 * 
	 * @param index
	 * 		Rang.
	 * @return Activité au rang index
	 */
	public Activity getChild(int index){
		return children.get(index);
	}

	/**
	 * Assigne à une activité, une liste d'activités enfants.
	 * @param children
	 * 		Nouvelle liste d'activités enfants.
	 * 
	 */
	public void setChildren(LinkedList<Activity> children) {
		this.children = children;
	}

	/**
	 * Remplace l'activité à un certain rang par une nouvelle activité dans la liste des enfants.
	 * 
	 * @param index
	 * 		Rang.
	 * @param newChild
	 * 		Nouvelle activité de remplacement.
	 */
	public void setChild(int index, Activity newChild){
		this.children.set(index, newChild);
	}

	/**
	 * Renvoie le nom de l'activité.
	 * 
	 * @return Nom de l'activité.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de l'activité.
	 * 
	 * @param name
	 * 		Nouveau nom de l'activité.
	 * 		
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ajoute une nouvelle activité dans la liste des activités enfants.
	 * 
	 * @param newChild
	 * 		Nouvelle activité à ajouter.
	 */
	public void addChild(Activity newChild){
		this.children.add(newChild);
	}

	/**
	 * Vérifie si une activité a pour pré-requis l'activité parent.
	 * 
	 * @param parent
	 * 		Activité pré-requis.
	 * @return true
	 * 		Si l'activité a bien pour pré-requis l'activité parent.
	 */
	public boolean isChild(Activity parent){
		if(parent.getChildren().contains(this)){
			return true;
		}
		return false;
	}

	/**
	 * Vérifie si une activité a un pré-requis dans une liste d'activités.
	 * 
	 * @param activities
	 * 		Liste des activités.
	 * @return false 
	 * 		Si l'activité n'a aucun pré-requis dans la liste.
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
	 * @return Professeur chargé de l'activité
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * Modifie le professeur chargé de l'activité
	 * 
	 * @param teacher
	 * 		Nouveau professeur.
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	/**
	 * @return Liste des salles dans lesquelles l'activité peut avoir lieu.
	 */
	public LinkedList<Room> getRooms() {
		return rooms;
	}

	/**
	 * Modifie la liste des salles dans lequel l'activité peut avoir lieu.
	 * 
	 * @param rooms
	 * 		Nouvel liste de salles
	 */
	public void setRooms(LinkedList<Room> rooms) {
		this.rooms = rooms;
	}

}
