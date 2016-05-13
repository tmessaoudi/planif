package fr.tl.ilog.jface;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

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
	protected Schedule schsel;


	/**
	 * Create the application window.
	 */
	public ScheduleUI() {
		super(null);
		list_sched = new LinkedList<Schedule>();
		schsel = createScheTest() ;
		list_sched.addFirst(schsel);
		list_sched.get(0).getTimeslots().get(0).setActivity(new Activity("Cours Test", null));
		list_sched.add(new Schedule(5, "Schedule 2"));
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}
	public ScheduleUI(LinkedList<Schedule> parcshed) {
		super(null);
		list_sched = parcshed;
		schsel = list_sched.getFirst();
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	public Schedule createScheTest(){
		Schedule test = new Schedule(5,"Schedule 1");
		test.initialize();
		return  test;
	}
	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control sashForm = createSashForm(parent);
		setStatus("Prêt");
		return sashForm;
	}

	protected Control createSashForm(Composite parent) {
		sashForm = new SashForm(parent, SWT.NONE);

		createListViewer(sashForm);
		createTableViewer(sashForm);

		sashForm.setWeights(new int[] {1, 3});
		return sashForm;
	}

	protected void createListViewer(SashForm sashForm) {
		ListViewer livw = new ListViewer(sashForm, SWT.BORDER);
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
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		actExit = new ExitAction(this);
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
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
			Activity cours_maths = new Cours("B", new LinkedList<Activity>());
			Activity TD_maths = new TD("D", new LinkedList<Activity>());
			Activity cours_SVT = new Cours("C", new LinkedList<Activity>());
			Activity activityNull = new Activity("Pas de Cours", new LinkedList<Activity>());
			Activity TP_maths = new TP("E", new LinkedList<Activity>());
			Activity TP_svt = new TP("F", new LinkedList<Activity>());
			cours_maths.addSon(TD_maths);
			cours_maths.addSon(TP_maths);
			cours_SVT.addSon(TP_svt);
			activityNull.addSon(cours_SVT);
			activityNull.addSon(cours_maths);
			LinkedList<Activity> list_activity = new LinkedList<Activity>();
			list_activity.add(TD_maths);
			list_activity.add(cours_maths);
			list_activity.add(cours_SVT);
			list_activity.add(activityNull);
			Parcours parcours = new Parcours(list_activity);
			parcours.permutations(0, parcours.getPossibilities());
			ScheduleUI window = new ScheduleUI(parcours.getEdts());
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
