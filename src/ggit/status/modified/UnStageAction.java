package ggit.status.modified;

import ggit.Config;

import org.eclipse.jface.action.Action;

public class UnStageAction extends Action {

	private final String filename;

	public UnStageAction(String filename) {
		this.filename = filename;
		setText("Unstage");
	}

	@Override
	public void run() {
		Config.execGit("reset","HEAD",filename);
	}
}
