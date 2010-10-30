package ggit.status.modified;

import ggit.Config;

import org.eclipse.jface.action.Action;

public class DiffAction extends Action {

	private final String filename;

	public DiffAction(String filename) {
		this.filename = filename;
		setText("Diff");
	}

	@Override
	public void run() {
		String execGit = Config.execGit("diff",filename);

	}
}
