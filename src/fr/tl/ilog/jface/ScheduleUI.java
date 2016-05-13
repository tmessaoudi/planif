package fr.tl.ilog.jface;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import fr.tl.ilog.sched.Activity;
import fr.tl.ilog.sched.Cours;
import fr.tl.ilog.sched.Parcours;
import fr.tl.ilog.sched.Schedule;
import fr.tl.ilog.sched.TD;
import fr.tl.ilog.sched.TP;

import org.eclipse.jface.viewers.TableViewer;

public class ScheduleUI extends ApplicationWindow {

	protected Action actExit;
	protected Table table;
	protected SashForm sashForm;
	protected LinkedList<Schedule> list_sched;
	protected LinkedList<Activity> list_activity;
	protected Schedule schsel;
	protected Parcours parcours;
	protected ListViewer livwGen;
	protected ListViewer livw;

	/**
	 * Crée la fenêtre d'application.
	 */
		
	public ScheduleUI() {
		super(null);
		list_activity = new LinkedList<Activity>();
		parcours = new Parcours(list_activity);
		list_sched = parcours.getEdts();
		if(list_sched.isEmpty()){
			list_sched = new LinkedList<Schedule>();
			schsel = createScheTest() ;
			list_sched.addFirst(schsel);
			list_sched.get(0).getTimeslots().get(0).setActivity(new Activity("Cours Test", null));
		}else{
			schsel = list_sched.getFirst();
		}
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * @return Emploi du temps initial
	 */
	public Schedule createScheTest(){
		Schedule test = new Schedule(5,"Schedule 1");
		test.initialize();
		return  test;
	}
	/**
	 * Crée les composants de la fenêtre d'application.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);

		TabItem tbtmGener = new TabItem(tabFolder, SWT.NONE);
		tbtmGener.setText("Géneration des emploi du temps possible");

		TabItem tbtmDisplay = new TabItem(tabFolder, SWT.NONE);
		tbtmDisplay.setText("Les Résultats");

		Control sashForm = createSashForm(tabFolder);
		tbtmDisplay.setControl(sashForm);

		Control sashFormGen = createSashFormGen(tabFolder);
		tbtmGener.setControl(sashFormGen);


		setStatus("Prêt");
		return tabFolder;
	}

	/**
	 * Crée l'interface pour la création des activités
	 * 
	 * @param parent
	 */
	public void createActivityGen(Composite parent) {
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		parent.setLayout(fillLayout);
		Label lbl_actvity = new Label(parent, SWT.NONE | SWT.PUSH);
		lbl_actvity.setText("Nom de l'Activité");
		Text text = new Text(parent, SWT.BORDER);
		Label lbl_son = new Label(parent, SWT.NONE | SWT.PUSH);
		lbl_son.setText("Vous pouvez selectionner les fils \nde l'activité à créer (utilisez Ctrl \npour en selectionner plusieurs)");
		List list = new List(parent, SWT.BORDER | SWT.PUSH |SWT.MULTI);
		Button genButton = new Button(parent, OK);
		genButton.setText("Créer Activité");
		Listener generatelistener = new Listener(){
			@Override
			public void handleEvent(Event event) {
				final String saisie = text.getText();
				if(saisie == ""){
					System.out.println("Vous n'avez rien saisi");
				}else{
					LinkedList<Activity> fils = new LinkedList<Activity>();
					for (int i = 0; i < list.getItems().length ; i++) {
						for (int j = 0; j <list_activity.size(); j++) {
							Activity activity = list_activity.get(j);
							if(list.getItems()[i].startsWith(activity.getName().toString())){
								fils.add(activity);
							}
						}
					}
					Activity act = new Activity(saisie, fils);
					list_activity.add(act);
					list.add(act.getName());
					livwGen.setInput(list_activity.toArray());
					livwGen.getList();
				}
			}
		};
		genButton.addListener(SWT.Selection, generatelistener);
		parent.pack();
	}

	/**
	 * Crée l'interface pour la génération d'emplois du temps
	 * 
	 * @param parent
	 */
	public void createScheduleGen(Composite parent){
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		parent.setLayout(fillLayout);
		Label lbl_actvity = new Label(parent, SWT.NONE | SWT.PUSH);
		lbl_actvity.setText("Taille de l'emploi du temps \n \n \n Saisissez un nombre \nde jours");
		Text text = new Text(parent, SWT.BORDER);
		Button genButton = new Button(parent, OK);
		genButton.setText("Générer les emplois du temps");
		Listener generatelis = new Listener(){
			@Override
			public void handleEvent(Event event) {
				final int saisie = Integer.parseInt(text.getText());
				if(saisie ==0){
					System.out.println("Vous n'avez rien saisi");
				}else{
					parcours = new Parcours(list_activity);
					parcours.permutations(0, parcours.getPossibilities());
					list_sched = parcours.getEdts();
					if(list_sched.isEmpty()){
						list_sched = new LinkedList<Schedule>();
						schsel = createScheTest() ;
						list_sched.addFirst(schsel);
						list_sched.get(0).getTimeslots().get(0).setActivity(new Activity("Cours Test", null));
					}else{
						schsel = list_sched.getFirst();
						livw.setInput(list_sched.toArray());
						livw.getList();
					}
				}	
			}
		};
		genButton.addListener(SWT.Selection, generatelis);
		parent.pack();
		
		
	}

	public Control createSashFormGen(Composite parent) {
		sashForm = new SashForm(parent, SWT.NONE);

		createListViewerGen(sashForm);
		Composite composite = new Composite(sashForm, SWT.NONE);
		createActivityGen(composite);
		Composite composite2 = new Composite(sashForm, SWT.NONE);
		createScheduleGen(composite2);
		sashForm.setWeights(new int[] {1, 1, 1});

		return sashForm;
	}


	/**
	 * Crée la vue de liste des activités crées 
	 * 
	 * @param sashForm
	 */
	protected void createListViewerGen(SashForm sashForm) {
		livwGen = new ListViewer(sashForm, SWT.BORDER);
		ActivityContentProvider acp = new ActivityContentProvider();
		livwGen.setContentProvider(acp);
		livwGen.setLabelProvider(acp);
		livwGen.setInput(list_activity.toArray());
		livwGen.getList();
	}

	protected Control createSashForm(Composite parent) {
		sashForm = new SashForm(parent, SWT.NONE);

		createListViewer(sashForm);
		createTableViewer(sashForm);

		sashForm.setWeights(new int[] {1, 3});
		return sashForm;
	}

	/**
	 * Crée la vue de la liste des emplois du temps 
	 * 
	 * @param sashForm
	 */
	protected void createListViewer(SashForm sashForm) {
		livw = new ListViewer(sashForm, SWT.BORDER);
		ScheduleContentProvider scp = new ScheduleContentProvider();
		livw.setContentProvider(scp);
		livw.setLabelProvider(scp);
		livw.setInput(list_sched.toArray());
		livw.addSelectionChangedListener(
				new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection =
								(IStructuredSelection) event.getSelection();
						schsel = (Schedule) selection.getFirstElement();
						createCalendar(schsel.getLength());

					}
				});
		livw.getList();
	}

	/**
	 * Crée la vue de l'emploi du temps séléctionné dans la liste des emplois du temps possibles
	 * 
	 * @param sashForm
	 */
	protected void createTableViewer(SashForm sashForm) {
		TableViewer tbvw = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table = tbvw.getTable();
		table.setHeaderVisible(true);
		table.pack();
		Method setItemHeightMethod;
		try {
			setItemHeightMethod = table.getClass().getDeclaredMethod("setItemHeight", int.class);
			setItemHeightMethod.setAccessible(true);
			try {
				setItemHeightMethod.invoke(table, 50);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		createCalendar(10);
	}

	/**
	 * Affiche les données de l'emploi du temps séléctionné dans la liste des emplois du temps possibles 
	 * 
	 * @param length
	 */
	public void createCalendar(int length) {
		for (int i = 0; i < length; i++) {
			TableColumn tcDay = new TableColumn(table, SWT.LEFT);
			tcDay.setText(schsel.displayTSDay(i));
			tcDay.setWidth(130);

			for (int j = 0; j < 5; j++) {
				new TableItem(table, SWT.RIGHT);
				TableItem items[] = table.getItems();
				items[j].setText(i,schsel.displayTimeslot(j+i*5));

			}

		}

	}



	/**
	 * Crée les actions .
	 */
	private void createActions() {
		// Create the actions
		actExit = new ExitAction(this);
	}

	/**
	 * Crée le menu.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Crée la barre à outil.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		toolBarManager.add(actExit);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			ScheduleUI window = new ScheduleUI();
			window.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public void run() {
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Planificateur d'emploi du temps");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(600, 450);
	}
}
