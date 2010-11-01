package ggit.status.unknown;

import ggit.Config;
import ggit.status.FileAction;

public class AddAction extends FileAction {

	public AddAction(String filename) {
		super(filename, "Add");
	}

	@Override
	public void run() {
		Config.execGit("add", getFileName());
	}

}
