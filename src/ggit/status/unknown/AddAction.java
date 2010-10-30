package ggit.status.unknown;

import ggit.Config;

import org.eclipse.jface.action.Action;

public class AddAction extends Action {

	private final String filename;

	public AddAction(String filename) {
		this.filename = filename;
		setText("Add");
	}

	@Override
	public void run() {
		Config.execGit("add",filename);
	}

}
