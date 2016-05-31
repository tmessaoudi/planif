package fr.tl.ilog.jface;

import org.eclipse.jface.action.Action;

public class ExitAction extends Action {
	protected ScheduleUI emploi;
    public ExitAction(ScheduleUI emploi) {
            this.emploi = emploi;
            setText("E&xit");
            setToolTipText("Exit the application");
    }
    @Override
    public void run() {
            emploi.close();
    }
}
