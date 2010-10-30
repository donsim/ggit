package ggit.status.modified;

import ggit.Config;

import org.eclipse.jface.action.Action;

public class StageAction extends Action {

	private final String filename;


	public StageAction(String filename) {
		this.filename = filename;
		setText("Stage");
		setToolTipText(getText()+" "+filename);
	}


	@Override
	public void run() {
		Config.execGit("add",filename);
	}


}
